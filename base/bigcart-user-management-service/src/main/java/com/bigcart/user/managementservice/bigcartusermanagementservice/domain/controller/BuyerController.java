package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.controller;


import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.dto.BuyerDTO;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Buyer;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service.BuyerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    BuyerService buyerService;

    ModelMapper modelMapper = new ModelMapper();
    @GetMapping
    public ResponseEntity<List<BuyerDTO>> getBuyers() {
        HttpHeaders headers = new HttpHeaders();
        List<Buyer> buyers = buyerService.getAll();
        return getListResponseEntity(headers, buyers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BuyerDTO> getBuyer(@PathVariable long id) {

        Buyer buyer = buyerService.getById(id);
        if (buyer == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(buyer, BuyerDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BuyerDTO> addBuyer(@RequestBody Buyer buyer) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();

        if (buyer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        buyerService.add(buyer);

        return new ResponseEntity<>(modelMapper.map(buyer, BuyerDTO.class), headers, HttpStatus.CREATED);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BuyerDTO> editBuyer(@PathVariable long id, @RequestBody BuyerDTO buyerDTO) throws IllegalAccessException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        Buyer oldBuyer = buyerService.getById(id);

        if (oldBuyer == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Buyer updatedBuyer = buyerService.update(id, modelMapper.map(buyerDTO, Buyer.class));

        return new ResponseEntity<>(modelMapper.map(updatedBuyer, BuyerDTO.class), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBuyer(@PathVariable long id) {

       return new ResponseEntity( buyerService.deleteById(id)? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<BuyerDTO> login(@RequestParam() String userName, @RequestParam() String password)
    {
        Buyer emp = buyerService.login(userName.toLowerCase(), password);
        if(emp == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(modelMapper.map(emp, BuyerDTO.class), HttpStatus.OK);

    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<BuyerDTO>> searchByName(@RequestParam() String name)
    {
        HttpHeaders headers = new HttpHeaders();
        List<Buyer> buyers = buyerService.searchByName(name);
        return getListResponseEntity(headers, buyers);
    }

    private ResponseEntity<List<BuyerDTO>> getListResponseEntity(HttpHeaders headers, List<Buyer> buyers) {
        if (buyers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<BuyerDTO> res = new ArrayList<>();
        buyers.forEach(x-> res.add(modelMapper.map(x, BuyerDTO.class)));
        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }
}
