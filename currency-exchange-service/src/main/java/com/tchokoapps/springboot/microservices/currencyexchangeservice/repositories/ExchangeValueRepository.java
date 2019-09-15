package com.tchokoapps.springboot.microservices.currencyexchangeservice.repositories;

import com.tchokoapps.springboot.microservices.currencyexchangeservice.entities.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExchangeValueRepository extends CrudRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}