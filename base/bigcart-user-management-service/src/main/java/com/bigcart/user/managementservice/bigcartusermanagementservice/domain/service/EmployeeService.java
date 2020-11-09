package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Employee;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;

import java.net.URISyntaxException;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(long id);

    Employee add(Employee employee) throws URISyntaxException;

    Employee update(long id, Employee newEmployee) throws IllegalAccessException, URISyntaxException;

    boolean updateStatus(long id, boolean status) throws URISyntaxException;

    boolean deleteById(long id);

    Employee login(String userName, String password);

    List<Employee> searchByName(String name);

    void notifyAdmins(String subject, String body);
}
