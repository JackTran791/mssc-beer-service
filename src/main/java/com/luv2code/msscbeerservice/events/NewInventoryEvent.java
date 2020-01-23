package com.luv2code.msscbeerservice.events;

import com.luv2code.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * @author Jack Tran
 */
@NoArgsConstructor
public class NewInventoryEvent  extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
