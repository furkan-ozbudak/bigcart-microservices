package com.bigcart.paymentservice.bigcartpaymentservice.service.imp;

import com.bigcart.paymentservice.bigcartpaymentservice.model.CardDTO;
import com.bigcart.paymentservice.bigcartpaymentservice.model.VisaCard;
import com.bigcart.paymentservice.bigcartpaymentservice.repository.VisaCardRepository;
import com.bigcart.paymentservice.bigcartpaymentservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisaCardServiceImp implements CardService {
    @Autowired
    private VisaCardRepository visaCardRepository;
    @Override
    public CardDTO addCard(CardDTO cardDTO) {
        VisaCard visaCard = new VisaCard(cardDTO.getCardNumber(),cardDTO.getCvs(),
                cardDTO.getExpirationDate(),cardDTO.getNameOnCard(),cardDTO.getUserId());
         visaCard = visaCardRepository.save(visaCard);
         cardDTO.setId(visaCard.getId());
         return cardDTO;
    }

    @Override
    public void removeCard(CardDTO cardDTO) {
        visaCardRepository.deleteById(cardDTO.getId());
    }

    @Override
    public boolean pay(CardDTO cardDTO, double amount) {
        long id = cardDTO.getId();
        VisaCard v =  visaCardRepository.findById(id).get();
        if (v.getBalance() < amount)
            return false;
        double newBalance = v.getBalance() - amount;
        v.setBalance(newBalance);
        visaCardRepository.save(v);
        return true;
    }

    @Override
    public boolean oneTimePayment(CardDTO cardDTO) {
        return pay(cardDTO, 25000);
    }
}
