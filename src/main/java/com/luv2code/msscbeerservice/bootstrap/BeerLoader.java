package com.luv2code.msscbeerservice.bootstrap;

import com.luv2code.msscbeerservice.domain.Beer;
import com.luv2code.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Jack Tran
 */
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObject();
    }

    private void loadBeerObject() {
        if(beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                            .beerName("Mango Bobs")
                            .beerStyle("IPA")
                            .minOnHand(12)
                            .quantityToBrew(200)
                            .upc(3254364365465L)
                            .price(new BigDecimal("12.95"))
                            .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(3444364365465L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }

        System.out.println("Loader Beers: " + beerRepository.count());
    }
}
