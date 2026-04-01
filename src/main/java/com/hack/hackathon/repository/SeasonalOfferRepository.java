package com.hack.hackathon.repository;

import com.hack.hackathon.Entity.SeasonalOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonalOfferRepository extends JpaRepository<SeasonalOffer, Long> {

    List<SeasonalOffer> findByActiveTrue();

}