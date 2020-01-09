package com.luv2code.msscbeerservice.web.controller;

import com.luv2code.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Jack Tran
 */
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {

        // TODO
        return new ResponseEntity<>(BeerDto.builder().build(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity savedNewBeer(@RequestBody @Validated BeerDto beerDto) {

        // TODO
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updatedBeerById(@PathVariable UUID beerId, @RequestBody @Validated BeerDto beerDto) {

        // TODO
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
