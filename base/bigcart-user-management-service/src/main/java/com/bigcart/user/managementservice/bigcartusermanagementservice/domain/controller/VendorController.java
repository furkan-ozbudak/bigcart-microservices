package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.controller;


import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.dto.VendorDTO;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Vendor;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service.VendorService;
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
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    VendorService vendorService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getVendors() {
        List<Vendor> vendors = vendorService.getAll();
        return getListResponseEntity(vendors);
    }

    @GetMapping(value = "/pending")
    public ResponseEntity<List<VendorDTO>> getPendingVendors() {
        List<Vendor> vendors = vendorService.getAllPending();
        return getListResponseEntity(vendors);
    }

    private ResponseEntity<List<VendorDTO>> getListResponseEntity(List<Vendor> vendors) {
        if (vendors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<VendorDTO> res = new ArrayList<>();
        vendors.forEach(x-> res.add(modelMapper.map(x, VendorDTO.class)));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable long id) {

        Vendor vendor = vendorService.getById(id);
        if (vendor == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(vendor, VendorDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VendorDTO> addVendor(@RequestBody Vendor vendor) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();

        if (vendor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        vendorService.add(vendor);

        return new ResponseEntity<>(modelMapper.map(vendor, VendorDTO.class), headers, HttpStatus.CREATED);

    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity updateVendorStatus(@PathVariable long id, @RequestBody boolean status) throws URISyntaxException {
        return new ResponseEntity(vendorService.updateStatus(id, status)?HttpStatus.OK:HttpStatus.NOT_FOUND);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VendorDTO> editVendor(@PathVariable long id, @RequestBody VendorDTO vendorDTO) throws IllegalAccessException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        Vendor oldVendor = vendorService.getById(id);

        if (oldVendor == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Vendor updatedVendor = vendorService.update(id, modelMapper.map(vendorDTO, Vendor.class));

        return new ResponseEntity<>(modelMapper.map(updatedVendor, VendorDTO.class), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteVendor(@PathVariable long id) {

       return new ResponseEntity( vendorService.deleteById(id)? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<VendorDTO> login(@RequestParam() String userName, @RequestParam() String password)
    {
        Vendor ven = vendorService.login(userName.toLowerCase(), password);
        if(ven == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(modelMapper.map(ven, VendorDTO.class), HttpStatus.OK);

    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<VendorDTO>> searchByName(@RequestParam() String name)
    {
        List<Vendor> list = vendorService.searchByName(name);
        return getListResponseEntity(list);
    }

    @PutMapping(value = "/onetimepayment/{id}")
    public ResponseEntity<VendorDTO> oneTimePayment(@PathVariable long id) throws URISyntaxException {
        return new ResponseEntity<VendorDTO>(modelMapper.map(vendorService.oneTimePayment(id), VendorDTO.class), HttpStatus.OK);
    }
}
