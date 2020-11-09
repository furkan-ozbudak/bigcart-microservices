package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Buyer;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Employee;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BuyerRepository extends PersonBaseRepository<Buyer>, QueryByExampleExecutor<Buyer> {

    @Query("SELECT buyer from Buyer buyer where buyer.userName = :userName")
    Buyer findByUserName(@Param("userName")String userName);

    @Query("SELECT buyer FROM Buyer buyer where buyer.firstName LIKE %:name% OR buyer.lastName Like %:name% OR buyer.userName Like %:name%")
    Iterable<Buyer> findByName(@Param("name") String name);
}
