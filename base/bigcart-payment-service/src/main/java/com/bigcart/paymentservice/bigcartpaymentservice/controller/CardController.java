package com.bigcart.paymentservice.bigcartpaymentservice.controller;

import com.bigcart.paymentservice.bigcartpaymentservice.model.CardDTO;
import com.bigcart.paymentservice.bigcartpaymentservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Qualifier("masterCardServiceImp")
    @Autowired
    private CardService masterCardService;
    @Qualifier("visaCardServiceImp")
    @Autowired
    private CardService visaCardService;
    @PostMapping
    public ResponseEntity<CardDTO> addCard(@RequestBody CardDTO card) {
        CardDTO cardDTO = null;
        if(!card.getCardNumber().startsWith("4")) {
            cardDTO = masterCardService.addCard(card);
        } else {
            cardDTO = visaCardService.addCard(card);
        }
        return new ResponseEntity<>(cardDTO, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity removeCard(@RequestBody CardDTO card) {
        CardDTO cardDTO = null;
        if(!card.getCardNumber().startsWith("4")) {
            masterCardService.removeCard(card);
        } else {
            visaCardService.removeCard(card);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/{amount}")
    public ResponseEntity<Boolean> pay(@RequestBody CardDTO card, @PathVariable double amount) {
        boolean x = false;
        if(!card.getCardNumber().startsWith("4")) {
           x = masterCardService.pay(card, amount);
        } else {
            x = visaCardService.pay(card, amount);
        }
        return x  ? new ResponseEntity<Boolean>(x, HttpStatus.ACCEPTED) : new ResponseEntity<Boolean>(x, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/oneTimePayment")
    public ResponseEntity<Long> oneTimePayment(@RequestBody CardDTO card) {
        boolean x = false;
        if(!card.getCardNumber().startsWith("4")) {
            x = masterCardService.oneTimePayment(card);
        } else {
            x = visaCardService.oneTimePayment(card);
        }
        return x  ? new ResponseEntity<Long>(card.getId(), HttpStatus.ACCEPTED) : new ResponseEntity<Long>( HttpStatus.BAD_REQUEST);
    }
}
