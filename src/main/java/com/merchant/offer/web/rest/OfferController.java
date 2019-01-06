package com.merchant.offer.web.rest;

import com.merchant.offer.domain.Offer;
import com.merchant.offer.exception.ResourceNotFoundException;
import com.merchant.offer.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OfferController {

    @Autowired
    OfferService offerService;

    @RequestMapping(value = "/offer", method = RequestMethod.GET)
    public List<Offer> getOffer() {
        return offerService.listAll();
    }

    @RequestMapping(value = "/product/{productId}/offers", method = RequestMethod.GET)
    public List<Offer> getOfferByProduct(@PathVariable long productId) {
        return offerService.findByProductId(productId);
    }

    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    public Offer getOfferById(@PathVariable long id) {
        Offer o = offerService.getById(id);
        return o;
    }

    @RequestMapping(value = "/offer", method = RequestMethod.POST)
    public void addOffer(
            @RequestParam(value = "productId") int productId,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "currency") String currency,
            @RequestParam(value = "price") int price,
            @RequestParam(value = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startTime,
            @RequestParam(value = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endTime,
            @RequestParam(value = "cancelled", required = false) boolean cancelled) {

        offerService.createNew(productId, description, currency, price, startTime, endTime, cancelled);
    }

    @RequestMapping(value = "/offer/{id}", method = RequestMethod.DELETE)
    public void cancelOffer(@PathVariable long id) {
        offerService.cancelOffer(id);
    }
}
