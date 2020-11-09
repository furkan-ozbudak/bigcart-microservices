package com.bigcart.paymentservice.bigcartpaymentservice.repository;


import com.bigcart.paymentservice.bigcartpaymentservice.model.VisaCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaCardRepository extends JpaRepository<VisaCard,Long> {
}
