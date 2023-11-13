package com.rangavdhoot.microservice.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;
    @GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange getCurrencyConversionInfo(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        String localPort = environment.getProperty("local.server.port");
//        CurrencyExchange currencyExchange = new CurrencyExchange(10001L, fromCurrency, toCurrency, new BigDecimal("65.00"));
//        currencyExchange.setEnvironment(localPort);

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(fromCurrency, toCurrency);
        if(currencyExchange == null) {
            throw new RuntimeException("Could not find appropriate currency exchange");
        }
        currencyExchange.setEnvironment(localPort);
        return currencyExchange;
    }
}

