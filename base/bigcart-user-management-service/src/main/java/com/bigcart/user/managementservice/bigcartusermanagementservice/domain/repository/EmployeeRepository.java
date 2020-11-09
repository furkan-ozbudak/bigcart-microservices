package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Employee;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Status;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeRepository extends PersonBaseRepository<Employee>, QueryByExampleExecutor<Employee> {

    @Query("SELECT emp from Employee emp where emp.userName = :userName")
    Employee findByUserName(@Param("userName") String userName);

    @Query("SELECT emp FROM Employee emp where emp.firstName LIKE %:name% OR emp.lastName Like %:name% OR emp.userName Like %:name%")
    Iterable<Employee> findByName(@Param("name") String name);

    @Query("select emp from Employee emp where emp.isAdmin = true")
    Iterable<Employee> findAllAdmins();
}
