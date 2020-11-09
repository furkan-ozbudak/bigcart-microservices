package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Address;

import java.net.URISyntaxException;
import java.util.List;

public interface AddressService {

    List<Address> getAll();

    Address getById(long id);

    Address add(long personId, Address Address) throws URISyntaxException;

    Address update(long id, Address newAddress) throws IllegalAccessException;

    boolean deleteById(long id);
}
