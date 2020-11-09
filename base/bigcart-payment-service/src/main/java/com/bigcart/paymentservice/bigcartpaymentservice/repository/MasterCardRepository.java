package com.bigcart.paymentservice.bigcartpaymentservice.repository;


import com.bigcart.paymentservice.bigcartpaymentservice.model.MasterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCardRepository extends JpaRepository<MasterCard,Long> {
}
