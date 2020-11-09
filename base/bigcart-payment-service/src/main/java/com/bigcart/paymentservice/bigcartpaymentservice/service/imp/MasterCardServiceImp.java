package com.bigcart.paymentservice.bigcartpaymentservice.service.imp;

import com.bigcart.paymentservice.bigcartpaymentservice.model.CardDTO;
import com.bigcart.paymentservice.bigcartpaymentservice.model.MasterCard;
import com.bigcart.paymentservice.bigcartpaymentservice.model.VisaCard;
import com.bigcart.paymentservice.bigcartpaymentservice.repository.MasterCardRepository;
import com.bigcart.paymentservice.bigcartpaymentservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterCardServiceImp implements CardService {
    @Autowired
    private MasterCardRepository masterCardRepository;
    @Override
    public CardDTO addCard(CardDTO cardDTO) {
        MasterCard masterCard = new MasterCard(cardDTO.getCardNumber(),cardDTO.getCvs(),
                cardDTO.getExpirationDate(),cardDTO.getNameOnCard(),cardDTO.getUserId());
        masterCard = masterCardRepository.save(masterCard);
        cardDTO.setId(masterCard.getId());
        return cardDTO;
    }

    @Override
    public void removeCard(CardDTO cardDTO) {
        masterCardRepository.deleteById(cardDTO.getId());
    }

    @Override
    public boolean pay(CardDTO cardDTO, double amount) {
        long id = cardDTO.getId();
        MasterCard m =  masterCardRepository.findById(id).get();
        if (m.getBalance() < amount)
            return false;
        double newBalance = m.getBalance() - amount;
        m.setBalance(newBalance);
        masterCardRepository.save(m);
        return true;
    }

    @Override
    public boolean oneTimePayment(CardDTO cardDTO) {
        return pay(cardDTO, 25000);
    }
}
