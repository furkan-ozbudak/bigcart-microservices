package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Employee;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Status;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository.EmployeeRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Notifier notifier;

    @Override
    public List<Employee> getAll(){
        List<Employee> list = new ArrayList<>();
        employeeRepository.findAllByStatus(Status.Approved).forEach(list::add);
        return  list;

    }

    @Override
    public Employee getById(long id)
    {
        Optional<Employee> temp = employeeRepository.findById(id);
        return temp.orElse(null);
    }

    @Override
    public Employee add(Employee employee) throws URISyntaxException {
        employee.setUserName(employee.getUserName().toLowerCase());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setCreationDateTime(new Date());
        employee =  employeeRepository.save(employee);
        notifier.notifyNewAccount(employee);
        return employee;
    }

    @Override
    public Employee update(long id, Employee newEmployee) throws IllegalAccessException, URISyntaxException {

        Employee oldEmployee = getById(id);

        for(Field field : Employee.class.getFields())
        {
            if(!field.get(oldEmployee).equals(field.get(newEmployee)))
                field.set(oldEmployee, field.get(newEmployee));
        }
        oldEmployee = employeeRepository.save(oldEmployee);
        notifier.notifyDetailsEdited(oldEmployee);
        return employeeRepository.save(oldEmployee);
    }

    @Override
    public boolean deleteById(long id)
    {
        if(getById(id) == null)
            return false;
        employeeRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateStatus(long id, boolean status) throws URISyntaxException {
        boolean res = employeeRepository.updateStatusById(id, status? Status.Approved : Status.Decline)>0;
        notifier.notifyStatusUpdate(employeeRepository.findById(id).get());
        return res;
    }

    @Override
    public Employee login(String userName, String password) {
        Employee emp = employeeRepository.findByUserName(userName.toLowerCase());
        if(emp == null)
            return null;
        if(passwordEncoder.matches(password, emp.getPassword()))
            return emp;
        return null;
    }

    @Override
    public List<Employee> searchByName(String name) {
        List<Employee> list = new ArrayList<>();
        employeeRepository.findByName(name).forEach(list::add);
        return list;
    }

    @Override
    public void notifyAdmins(String subject, String body) {
        List admins = StreamSupport.stream(employeeRepository.findAllAdmins().spliterator(), false).collect(Collectors.toList());
        notifier.notifyAdmins(admins, subject, body);
    }
}
