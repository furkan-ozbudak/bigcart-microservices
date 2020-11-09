package com.bigcart.productservice.bigcartproductservice.util;

import com.bigcart.productservice.bigcartproductservice.DTO.PersonDTO;

import java.net.URISyntaxException;
import java.util.List;

public interface Notifier {

    public void notifyStatusUpdate(Long ID,String status) throws URISyntaxException;

    public void notifyAdmins(String title,String message) throws URISyntaxException;



}
