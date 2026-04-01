package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.SeasonalOffer;
import com.hack.hackathon.exception.ResourceNotFoundException;
import com.hack.hackathon.repository.SeasonalOfferRepository;
import com.hack.hackathon.service.SeasonalOfferService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonalOfferServiceImpl implements SeasonalOfferService {

    private final SeasonalOfferRepository seasonalOfferRepository;

    @Override
    public SeasonalOffer createOffer(SeasonalOffer offer) {

        try {
            return seasonalOfferRepository.save(offer);
        } catch (Exception e) {
            throw new RuntimeException("Error creating seasonal offer");
        }

    }

    @Override
    public List<SeasonalOffer> getAllOffers() {

        try {
            return seasonalOfferRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching seasonal offers");
        }

    }

    @Override
    public List<SeasonalOffer> getActiveOffers() {

        try {
            return seasonalOfferRepository.findByActiveTrue();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching active offers");
        }

    }

    @Override
    public SeasonalOffer getOfferById(Long id) {

        try {

            return seasonalOfferRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Seasonal offer not found"));

        } catch (Exception e) {
            throw new RuntimeException("Error fetching seasonal offer");
        }

    }

    @Override
    public SeasonalOffer updateOffer(Long id, SeasonalOffer offer) {

        try {

            SeasonalOffer existing = seasonalOfferRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Seasonal offer not found"));

            existing.setTitle(offer.getTitle());
            existing.setDescription(offer.getDescription());
            existing.setDiscountPercentage(offer.getDiscountPercentage());
            existing.setActive(offer.isActive());

            return seasonalOfferRepository.save(existing);

        } catch (Exception e) {
            throw new RuntimeException("Error updating seasonal offer");
        }

    }

    @Override
    public void deleteOffer(Long id) {

        try {

            SeasonalOffer offer = seasonalOfferRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Seasonal offer not found"));

            seasonalOfferRepository.delete(offer);

        } catch (Exception e) {
            throw new RuntimeException("Error deleting seasonal offer");
        }

    }
}