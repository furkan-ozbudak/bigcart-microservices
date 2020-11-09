package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Address;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Person;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository.AddressRepository;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository.PersonRepository;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.util.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    Notifier notifier;

    @Override
    public List<Address> getAll(){

        List<Address> list = new ArrayList<>();
        addressRepository.findAll().forEach(list::add);
        return  list;

    }

    @Override
    public Address getById(long id)
    {
        Optional<Address> temp = addressRepository.findById(id);
        return temp.isPresent()? temp.get() : null;
    }

    @Override
    public Address add(long personId, Address address) throws URISyntaxException {

        Person person = personRepository.findById(personId).get();
        address.setPerson(person);
        address = addressRepository.save(address);
        notifier.notifyNewAddress(person, address);
        return address;
    }

    @Override
    public Address update(long id, Address newAddress) throws IllegalAccessException {

        Address oldAddress = getById(id);

        for(Field field : Address.class.getFields())
        {
            if(!field.get(oldAddress).equals(field.get(newAddress)))
                field.set(oldAddress, field.get(newAddress));
        }

        return addressRepository.save(oldAddress);
    }

    @Override
    public boolean deleteById(long id)
    {
        if(getById(id) == null)
            return false;
        addressRepository.deleteById(id);
        return true;
    }



}
