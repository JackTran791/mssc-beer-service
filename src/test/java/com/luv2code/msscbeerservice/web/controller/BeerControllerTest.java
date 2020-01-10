package com.luv2code.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.msscbeerservice.web.model.BeerDto;
import com.luv2code.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

/**
 * @author Jack Tran
 */
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {

        mockMvc.perform(get("/api/v1/beer/{beerId}" , UUID.randomUUID().toString())
                .param("isCold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer", RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("beerId").description("UUID of desired beer to get.")),
                        RequestDocumentation.requestParameters(RequestDocumentation.parameterWithName("isCold").description("Is Beer Cold Query param")),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("id").description("Id of Beer"),
                                PayloadDocumentation.fieldWithPath("version").description("Version number"),
                                PayloadDocumentation.fieldWithPath("createdDate").description("Date Created"),
                                PayloadDocumentation.fieldWithPath("lastModifiedDate").description("Date Updated"),
                                PayloadDocumentation.fieldWithPath("beerName").description("Beer Name"),
                                PayloadDocumentation.fieldWithPath("beerStyle").description("Beer Style"),
                                PayloadDocumentation.fieldWithPath("upc").description("UPC of Beer"),
                                PayloadDocumentation.fieldWithPath("price").description("Price"),
                                PayloadDocumentation.fieldWithPath("quantityOnHand").description("Quantity On hand")
                        )));
    }

    @Test
    void savedNewBeer() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updatedBeerById() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Jack Beer")
                .beerStyle(BeerStyleEnum.IPA)
                .upc(32454354353543534L)
                .price(new BigDecimal("12.95"))
                .build();
    }
}
