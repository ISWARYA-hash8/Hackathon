package com.hack.hackathon.service;

import com.hack.hackathon.Entity.SeasonalOffer;

import java.util.List;

public interface SeasonalOfferService {

    SeasonalOffer createOffer(SeasonalOffer offer);

    List<SeasonalOffer> getAllOffers();

    List<SeasonalOffer> getActiveOffers();

    SeasonalOffer getOfferById(Long id);

    SeasonalOffer updateOffer(Long id, SeasonalOffer offer);

    void deleteOffer(Long id);

}