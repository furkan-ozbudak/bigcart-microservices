package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.repository;

import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Guest;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GuestRepository extends CrudRepository<Guest, Long> {

    @Query("SELECT guest FROM Guest guest where guest.firstName LIKE %:name% OR guest.lastName Like %:name%")
    Iterable<Guest> findByName(@Param("name") String name);
}
