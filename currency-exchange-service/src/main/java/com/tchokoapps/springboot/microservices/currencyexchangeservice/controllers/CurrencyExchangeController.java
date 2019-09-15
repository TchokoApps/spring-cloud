package com.tchokoapps.springboot.microservices.currencyexchangeservice.controllers;

import com.tchokoapps.springboot.microservices.currencyexchangeservice.entities.ExchangeValue;
import com.tchokoapps.springboot.microservices.currencyexchangeservice.repositories.ExchangeValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CurrencyExchangeController {
    private Environment env;
    private ExchangeValueRepository exchangeValueRepository;

    public CurrencyExchangeController(Environment env, ExchangeValueRepository exchangeValueRepository) {
        this.env = env;
        this.exchangeValueRepository = exchangeValueRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        log.info("env: " + env);
        return exchangeValue;
    }
}
