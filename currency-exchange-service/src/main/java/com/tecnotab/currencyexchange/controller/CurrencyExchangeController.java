package com.tecnotab.currencyexchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tecnotab.currencyexchange.model.ExchangeValue;
import com.tecnotab.currencyexchange.repository.ExchangeValueRepository;


@RestController
public class CurrencyExchangeController {

	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		logger.info("exchange value : {}", exchangeValue);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
}