package com.luv2code.msscbeerservice.events;

import com.luv2code.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author Jack Tran
 */
@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 8706404705952120836L;

    private final BeerDto beerDto;
}
