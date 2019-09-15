package com.tchokoapps.springboot.microservices.currencyconversionservice.controllers;

import com.tchokoapps.springboot.microservices.currencyconversionservice.beans.CurrencyConversionBean;
import com.tchokoapps.springboot.microservices.currencyconversionservice.proxies.CurrencyExchangeServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    public CurrencyConversionController(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);

        CurrencyConversionBean responseBody = responseEntity.getBody();

        return new CurrencyConversionBean(responseBody.getId(), responseBody.getFrom(), responseBody.getTo(),
                responseBody.getConversionMultiple(), quantity, quantity.multiply(responseBody.getConversionMultiple()), responseBody.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurrencyConversionBean responseBody = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionBean(responseBody.getId(), responseBody.getFrom(), responseBody.getTo(),
                responseBody.getConversionMultiple(), quantity, quantity.multiply(responseBody.getConversionMultiple()), responseBody.getPort());
    }
}