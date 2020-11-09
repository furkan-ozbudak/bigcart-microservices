package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Guest;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;

import java.net.URISyntaxException;
import java.util.List;

public interface GuestService {

    List<Guest> getAll();

    Guest getById(long id);

    Guest add(Guest Guest) throws URISyntaxException;

    Guest update(long id, Guest newGuest) throws IllegalAccessException, URISyntaxException;

    boolean deleteById(long id);

    List<Guest> searchByName(String name);
}
