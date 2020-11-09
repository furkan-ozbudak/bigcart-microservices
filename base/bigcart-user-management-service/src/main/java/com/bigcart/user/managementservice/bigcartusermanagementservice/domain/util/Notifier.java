package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.util;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Address;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Person;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;

import java.net.URISyntaxException;
import java.util.List;

public interface Notifier {
    public void notifyNewAccount(Person person) throws URISyntaxException;

    public void notifyNewAddress(Person person, Address address) throws URISyntaxException;

    public void notifyDetailsEdited(Person person) throws URISyntaxException;

    public void notifyStatusUpdate(Person person) throws URISyntaxException;

    public void notifyAdmins(List<Person> admins, String title,String message);

    public void notifyBalanceChanged(Vendor person) throws URISyntaxException;

}
