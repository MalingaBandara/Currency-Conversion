package com.bitlord.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CurrencyConvesionController {
	
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	
	// REST Template (Old & Hard)
	@GetMapping ( "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}" )
	public CurrencyConversion calculateCurrencyConversion ( @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity ) {
	
		
		// URI Variables
		HashMap< String , String > uriVariables = new HashMap<>();
		
		uriVariables.put( "from", from );
		uriVariables.put( "to", to );
		
		
		// get data form another Microservice 
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity ( 
									"http://localhost:8000/currency-exchange/from/{from}/to/{to}" , 
									CurrencyConversion.class, uriVariables 	
				);
		
		
		CurrencyConversion currencyConversion = responseEntity.getBody(); // get data from Response Entity
		
		
		return new CurrencyConversion( 
				currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply( currencyConversion.getConversionMultiple() ), 
				currencyConversion.getEnvironment()  + " " + "REST Template"
			);
		
	}
	
	
	
	// Feign Framework (Using)
	@GetMapping ( "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}" )
	public CurrencyConversion calculateCurrencyConversionFeign ( @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity ) {
	
		
		CurrencyConversion currencyConversion = proxy.retriveExchangeValue( from, to );  // get data from Proxy
		
		
		return new CurrencyConversion( 
				currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply( currencyConversion.getConversionMultiple() ), 
				currencyConversion.getEnvironment() + " " + "Feign"
			);
		
	}
	
}
