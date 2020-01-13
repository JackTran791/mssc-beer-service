package com.luv2code.msscbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.msscbeerservice.bootstrap.BeerLoader;
import com.luv2code.msscbeerservice.services.BeerService;
import com.luv2code.msscbeerservice.web.model.BeerDto;
import com.luv2code.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.UUID;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Jack Tran
 */
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {

        BDDMockito.given(beerService.getById(ArgumentMatchers.any())).willReturn(getValidBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void savedNewBeer() throws Exception {

        BDDMockito.given(beerService.getById(ArgumentMatchers.any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updatedBeerById() throws Exception {

        BDDMockito.given(beerService.getById(ArgumentMatchers.any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Jack Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(BeerLoader.BEER_1_UPC)
                .price(new BigDecimal("12.95"))
                .build();
    }
}
