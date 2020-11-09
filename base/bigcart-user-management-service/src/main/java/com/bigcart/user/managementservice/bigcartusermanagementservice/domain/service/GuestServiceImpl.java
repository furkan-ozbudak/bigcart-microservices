package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Guest;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Status;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository.GuestRepository;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.util.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService{

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    Notifier notifier;

    @Override
    public List<Guest> getAll(){

        List<Guest> list = new ArrayList<>();
        guestRepository.findAll().forEach(list::add);
        return  list;

    }

    @Override
    public Guest getById(long id)
    {
        Optional<Guest> temp = guestRepository.findById(id);
        return temp.orElse(null);
    }

    @Override
    public Guest add(Guest guest) throws URISyntaxException {
        guest.setStatus(Status.Approved);
        guest.setCreationDateTime(new Date());
        guest = guestRepository.save(guest);
        notifier.notifyNewAccount(guest);;
        return guest;
    }

    @Override
    public Guest update(long id, Guest newGuest) throws IllegalAccessException, URISyntaxException {

        Guest oldGuest = getById(id);

        for(Field field : Guest.class.getFields())
        {
            if(!field.get(oldGuest).equals(field.get(newGuest)))
                field.set(oldGuest, field.get(newGuest));
        }

        oldGuest = guestRepository.save(oldGuest);
        notifier.notifyDetailsEdited(oldGuest);
        return oldGuest;
    }

    @Override
    public boolean deleteById(long id)
    {
        if(getById(id) == null)
            return false;
        guestRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Guest> searchByName(String name) {
        List<Guest> list = new ArrayList<>();
        guestRepository.findByName(name).forEach(list::add);
        return list;
    }



}
