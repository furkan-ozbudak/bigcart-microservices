package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.util;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Address;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Person;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
    public void notifyNewAccount(Person person) throws URISyntaxException {
        Message message = new Message(person.getFirstName(), "Welcome to BigCart", "Your account created successfully", person.getEmail());
        sendNotification(message);
    }

    @Override
    public void notifyNewAddress(Person person, Address address) throws URISyntaxException {
        Message message = new Message(person.getFirstName(), "New address assigned", "The following address added to your account\n"+address.toString(), person.getEmail());
        sendNotification(message);
    }

    @Override
    public void notifyDetailsEdited(Person person) throws URISyntaxException {
        Message message = new Message(person.getFirstName(), "Few information updated", "Your account has few updates please check", person.getEmail());
        sendNotification(message);
    }

    @Override
    public void notifyStatusUpdate(Person person) throws URISyntaxException {
        Message message = new Message(person.getFirstName(), "Your account status changed", "Your account new status is " + person.getStatus().toString(), person.getEmail());
        sendNotification(message);
    }

    @Override
    public void notifyAdmins(List<Person> admins, String title, String message) {
        admins.forEach(x -> {
            try {
                sendNotification(new Message(x.getFirstName(), title, message, x.getEmail()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void notifyBalanceChanged(Vendor person) throws URISyntaxException {
        Message message = new Message(person.getFirstName(), "Your Balance has been changed", "New changes done in your balance, new balance is "+ person.getBalance().toString(), person.getEmail());
        sendNotification(message);
    }
}
