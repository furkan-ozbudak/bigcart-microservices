package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Buyer;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;

import java.net.URISyntaxException;
import java.util.List;

public interface BuyerService {

    List<Buyer> getAll();

    Buyer getById(long id);

    Buyer add(Buyer Buyer) throws URISyntaxException;

    Buyer update(long id, Buyer newBuyer) throws IllegalAccessException, URISyntaxException;

    boolean deleteById(long id);

    Buyer login(String userName, String password);

    List<Buyer> searchByName(String name);
}
