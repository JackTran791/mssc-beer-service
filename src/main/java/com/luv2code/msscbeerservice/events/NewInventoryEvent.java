package com.luv2code.msscbeerservice.events;

import com.luv2code.msscbeerservice.web.model.BeerDto;

/**
 * @author Jack Tran
 */
public class NewInventoryEvent  extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
