package com.luv2code.msscbeerservice.repositories;

import com.luv2code.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author Jack Tran
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
