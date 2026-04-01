package com.hack.hackathon.controller;

import com.hack.hackathon.Entity.SeasonalOffer;
import com.hack.hackathon.service.SeasonalOfferService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class SeasonalOfferController {

    private final SeasonalOfferService seasonalOfferService;

    @PostMapping
    public SeasonalOffer createOffer(@RequestBody SeasonalOffer offer) {
        return seasonalOfferService.createOffer(offer);
    }

    @GetMapping
    public List<SeasonalOffer> getAllOffers() {
        return seasonalOfferService.getAllOffers();
    }

    @GetMapping("/active")
    public List<SeasonalOffer> getActiveOffers() {
        return seasonalOfferService.getActiveOffers();
    }

    @GetMapping("/{id}")
    public SeasonalOffer getOfferById(@PathVariable Long id) {
        return seasonalOfferService.getOfferById(id);
    }

    @PutMapping("/{id}")
    public SeasonalOffer updateOffer(@PathVariable Long id,
                                     @RequestBody SeasonalOffer offer) {

        return seasonalOfferService.updateOffer(id, offer);

    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id) {

        seasonalOfferService.deleteOffer(id);
        return "Seasonal offer deleted successfully";

    }
}