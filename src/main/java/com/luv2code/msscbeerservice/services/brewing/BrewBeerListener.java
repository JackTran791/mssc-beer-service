package com.luv2code.msscbeerservice.services.brewing;

import com.luv2code.msscbeerservice.config.JmsConfig;
import com.luv2code.msscbeerservice.domain.Beer;
import com.luv2code.msscbeerservice.events.BrewBeerEvent;
import com.luv2code.msscbeerservice.events.NewInventoryEvent;
import com.luv2code.msscbeerservice.repositories.BeerRepository;
import com.luv2code.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Jack Tran
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_EVENT, newInventoryEvent);

    }
}
