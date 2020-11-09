package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Status;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository.VendorRepository;
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
public class VendorServiceImpl implements VendorService{

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Notifier notifier;

    @Override
    public List<Vendor> getAll(){

        List<Vendor> list = new ArrayList<>();
        vendorRepository.findAllByStatus(Status.Approved).forEach(list::add);
        return  list;
    }

    @Override
    public List<Vendor> getAllPending() {
        List<Vendor> list = new ArrayList<>();
        vendorRepository.findAllByStatus(Status.Pending).forEach(list::add);
        return  list;
    }

    @Override
    public Vendor getById(long id)
    {
        Optional<Vendor> temp = vendorRepository.findById(id);
        return temp.orElse(null);
    }

    @Override
    public Vendor add(Vendor vendor) throws URISyntaxException {
        vendor.setUserName(vendor.getUserName().toLowerCase());
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
        vendor.setCreationDateTime(new Date());
        vendor = vendorRepository.save(vendor);
        notifier.notifyNewAccount(vendor);
        return vendor;
    }

    @Override
    public Vendor update(long id, Vendor newVendor) throws IllegalAccessException, URISyntaxException {

        Vendor oldVendor = getById(id);

        for(Field field : Vendor.class.getFields())
        {
            if(!field.get(oldVendor).equals(field.get(newVendor)))
                field.set(oldVendor, field.get(newVendor));
        }

        oldVendor = vendorRepository.save(oldVendor);
        notifier.notifyDetailsEdited(oldVendor);
        return oldVendor;
    }

    @Override
    public boolean deleteById(long id)
    {
        if(getById(id) == null)
            return false;
        vendorRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateStatus(long id, boolean status) throws URISyntaxException {
        boolean res = vendorRepository.updateStatusById(id, status? Status.Approved : Status.Decline)>0;
        notifier.notifyStatusUpdate(vendorRepository.findById(id).get());
        return res;
    }

    @Override
    public Vendor login(String userName, String password) {
        Vendor ven = vendorRepository.findByUserName(userName.toLowerCase());
        if(ven == null)
            return null;
        if(passwordEncoder.matches(password, ven.getPassword()))
            return ven;
        return null;
    }

    @Override
    public List<Vendor> searchByName(String name) {
        List<Vendor> list = new ArrayList<>();
        vendorRepository.findByName(name).forEach(list::add);
        return list;
    }

    @Override
    public Vendor oneTimePayment(long id) throws URISyntaxException {
        Vendor ven = getById(id);
        ven.setBalance(ven.getBalance() + 25000.0);
        vendorRepository.save(ven);
        notifier.notifyBalanceChanged(ven);
        return ven;
    }
}
