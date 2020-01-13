package com.luv2code.msscbeerservice.services;

import com.luv2code.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Jack Tran
 */
public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
