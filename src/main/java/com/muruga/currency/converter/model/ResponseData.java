package com.muruga.currency.converter.model;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseData {

	private String date;
	
	private Map<String, Double> rates;

}
