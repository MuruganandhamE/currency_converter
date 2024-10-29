package com.muruga.currency.converter.service;

import com.muruga.currency.converter.model.CurrencyConversion;

public interface ConversionRateService {
	
	Integer calculateConversionAmount(CurrencyConversion currencyConversion);

}
