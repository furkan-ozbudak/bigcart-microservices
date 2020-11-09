package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.controller;


import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.dto.EmployeeDTO;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.dto.MsgDTO;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Employee;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    ModelMapper modelMapper = new ModelMapper();
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        List<Employee> employees = employeeService.getAll();

        return getListResponseEntity(headers, employees);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable long id) {

        Employee employee = employeeService.getById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(employee, EmployeeDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody Employee employee) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        employeeService.add(employee);

        return new ResponseEntity<>(modelMapper.map(employee, EmployeeDTO.class), headers, HttpStatus.CREATED);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> editEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) throws IllegalAccessException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        Employee oldEmployee = employeeService.getById(id);

        if (oldEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Employee updatedEmployee = employeeService.update(id, modelMapper.map(employeeDTO, Employee.class));

        return new ResponseEntity<>(modelMapper.map(updatedEmployee, EmployeeDTO.class), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable long id) {

       return new ResponseEntity( employeeService.deleteById(id)? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<EmployeeDTO> login(@RequestParam() String userName, @RequestParam() String password)
    {
        Employee emp = employeeService.login(userName.toLowerCase(), password);
        if(emp == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(modelMapper.map(emp, EmployeeDTO.class), HttpStatus.OK);

    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<EmployeeDTO>> searchByName(@RequestParam() String name)
    {
        HttpHeaders headers = new HttpHeaders();
        List<Employee> employees = employeeService.searchByName(name);
        return getListResponseEntity(headers, employees);
    }

    private ResponseEntity<List<EmployeeDTO>> getListResponseEntity(HttpHeaders headers, List<Employee> employees) {
        if (employees == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<EmployeeDTO> res = new ArrayList<>();
        employees.forEach(x-> res.add(modelMapper.map(x, EmployeeDTO.class)));
        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/notifyadmins")
    public ResponseEntity notifyAdmins(@RequestBody MsgDTO msg)
    {
        employeeService.notifyAdmins(msg.getSubject(), msg.getBody());
        return new ResponseEntity(HttpStatus.OK);
    }
}
