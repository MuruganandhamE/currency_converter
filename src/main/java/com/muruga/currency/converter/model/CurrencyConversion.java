package com.muruga.currency.converter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversion {

	private String sourceCurrency;

	private String targetCurrency;

	private Integer amount;

	private Integer result;

	private String message;

	
	

}
