package com.bigcart.productservice.bigcartproductservice.util;

import com.bigcart.productservice.bigcartproductservice.DTO.MsgDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.PersonDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.VendorDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotifierImpl implements Notifier {
    private RestTemplate restTemplate;

    public NotifierImpl()
    {
        restTemplate = new RestTemplate();
    }

    private void sendNotification(Message message) throws URISyntaxException {
        URI uri = new URI("http://localhost:8006/notify/");
        restTemplate.postForObject(uri, message, Message.class);

    }


    @Override
    public void notifyStatusUpdate(Long id ,String status) throws URISyntaxException {
        URI uri = new URI("http://localhost:9988/vendor/"+id);
        VendorDTO v= restTemplate.getForObject(uri,VendorDTO.class);

        Message message = new Message(v.getFirstName(), "Your Product status changed", "Your Product status changed to  " +status,v.getEmail());
        sendNotification(message);
    }

    @Override
    public void notifyAdmins(String title, String message) throws URISyntaxException {
        URI uri = new URI("http://localhost:9988/employee/notifyadmins");
        MsgDTO msg = new MsgDTO(title,message);
        restTemplate.postForObject(uri,msg,MsgDTO.class);

    }

}
