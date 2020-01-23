package com.luv2code.msscbeerservice.events;

import com.luv2code.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * @author Jack Tran
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
