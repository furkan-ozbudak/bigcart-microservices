package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;

import java.net.URISyntaxException;
import java.util.List;

public interface VendorService {

    List<Vendor> getAll();

    List<Vendor> getAllPending();

    Vendor getById(long id);

    Vendor add(Vendor Vendor) throws URISyntaxException;

    Vendor update(long id, Vendor newVendor) throws IllegalAccessException, URISyntaxException;

    boolean updateStatus(long id, boolean status) throws URISyntaxException;

    boolean deleteById(long id);

    Vendor login(String userName, String password);

    List<Vendor> searchByName(String name);

    Vendor oneTimePayment(long id) throws URISyntaxException;
}
