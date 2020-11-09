package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.controller;


import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model.Guest;
import com.bigcart.user.managementservice.bigcartusermanagementservice.domain.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    GuestService guestService;

    @GetMapping
    public ResponseEntity<List<Guest>> getGuests() {
        HttpHeaders headers = new HttpHeaders();
        List<Guest> guests = guestService.getAll();
        if (guests == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(guests, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Guest> getGuest(@PathVariable long id) {

        Guest guest = guestService.getById(id);
        if (guest == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();

        if (guest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        guestService.add(guest);

        return new ResponseEntity<>(guest, headers, HttpStatus.CREATED);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Guest> editGuest(@PathVariable long id, @RequestBody Guest guest) throws IllegalAccessException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        Guest oldGuest = guestService.getById(id);

        if (oldGuest == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Guest updatedGuest = guestService.update(id, guest);

        return new ResponseEntity<>(updatedGuest, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteGuest(@PathVariable long id) {

       return new ResponseEntity( guestService.deleteById(id)? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Guest>> searchByName(@RequestParam() String name)
    {
        HttpHeaders headers = new HttpHeaders();
        List<Guest> guests = guestService.searchByName(name);
        if (guests == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(guests, headers, HttpStatus.OK);
    }
}
