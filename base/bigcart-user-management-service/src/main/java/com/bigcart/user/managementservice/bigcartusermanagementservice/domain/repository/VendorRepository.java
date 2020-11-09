package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Buyer;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VendorRepository extends PersonBaseRepository<Vendor>, QueryByExampleExecutor<Vendor> {

    @Query("SELECT ven from Vendor ven where ven.userName = :userName")
    Vendor findByUserName(@Param("userName")String userName);

    @Query("SELECT ven FROM Vendor ven where ven.firstName LIKE %:name% OR ven.lastName Like %:name%")
    Iterable<Vendor> findByName(@Param("name") String name);
}
