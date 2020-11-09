package com.bigcart.paymentservice.bigcartpaymentservice.service;

import com.bigcart.paymentservice.bigcartpaymentservice.model.CardDTO;
import org.springframework.stereotype.Service;

public interface CardService {
    CardDTO addCard(CardDTO cardDTO);
    void removeCard(CardDTO cardDTO);
    boolean pay(CardDTO cardDTO, double amount);
    boolean oneTimePayment(CardDTO cardDTO);
}
