package com.example.agricolab.api;

import com.example.agricolab.model.Offers;
import com.example.agricolab.model.User;
import com.example.agricolab.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/api/v1//offer")
public class OfferAPI {

    private final OfferService offerService;

    @Autowired
    public OfferAPI(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public User getOffer() {
        return null;
    }

    @PostMapping
    public String postOffer(@RequestBody Offers o) {
        return offerService.addOffer(o);
    }

    @PutMapping
    public void putOffer() {
    }

    @DeleteMapping
    public void deleteOffer() {
    }

}
