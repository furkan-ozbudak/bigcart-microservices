package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Buyer;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Status;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository.BuyerRepository;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.util.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Notifier notifier;

    @Override
    public List<Buyer> getAll(){
        List<Buyer> list = new ArrayList<>();
        buyerRepository.findAllByStatus(Status.Approved).forEach(list::add);
        return  list;

    }

    @Override
    public Buyer getById(long id)
    {
        Optional<Buyer> temp = buyerRepository.findById(id);
        return temp.orElse(null);
    }

    @Override
    public Buyer add(Buyer buyer) throws URISyntaxException {
        buyer.setUserName(buyer.getUserName().toLowerCase());
        buyer.setPassword(passwordEncoder.encode(buyer.getPassword()));
        buyer.setStatus(Status.Approved);
        buyer.setCreationDateTime(new Date());
        buyer = buyerRepository.save(buyer);
        notifier.notifyNewAccount(buyer);
        return buyer;
    }

    @Override
    public Buyer update(long id, Buyer newBuyer) throws IllegalAccessException, URISyntaxException {

        Buyer oldBuyer = getById(id);

        for(Field field : Buyer.class.getFields())
        {
            if(!field.get(oldBuyer).equals(field.get(newBuyer)))
                field.set(oldBuyer, field.get(newBuyer));
        }

        oldBuyer = buyerRepository.save(oldBuyer);
        notifier.notifyDetailsEdited(oldBuyer);
        return oldBuyer;
    }

    @Override
    public boolean deleteById(long id)
    {
        if(getById(id) == null)
            return false;
        buyerRepository.deleteById(id);
        return true;
    }

    @Override
    public Buyer login(String userName, String password) {
        Buyer buyer = buyerRepository.findByUserName(userName.toLowerCase());
        if(buyer == null)
            return null;
        if(passwordEncoder.matches(password, buyer.getPassword()))
            return buyer;
        return null;
    }

    @Override
    public List<Buyer> searchByName(String name) {
        List<Buyer> list = new ArrayList<>();
        buyerRepository.findByName(name).forEach(list::add);
        return list;
    }



}
