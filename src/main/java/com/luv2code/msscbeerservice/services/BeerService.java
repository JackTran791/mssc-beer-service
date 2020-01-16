package com.luv2code.msscbeerservice.services;

import com.luv2code.msscbeerservice.web.model.BeerDto;
import com.luv2code.msscbeerservice.web.model.BeerPagedList;
import com.luv2code.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * @author Jack Tran
 */
public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);
}
