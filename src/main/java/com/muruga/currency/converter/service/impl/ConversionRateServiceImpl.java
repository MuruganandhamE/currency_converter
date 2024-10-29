package com.muruga.currency.converter.service.impl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.muruga.currency.converter.entity.CurrencyConversionRate;
import com.muruga.currency.converter.model.CurrencyConversion;
import com.muruga.currency.converter.model.ResponseData;
import com.muruga.currency.converter.repository.CurrencyConversionRateRepository;
import com.muruga.currency.converter.service.ConversionRateService;
import com.muruga.currency.converter.util.ConversionRateUtils;
import com.muruga.currency.converter.util.TimeStampUtil;

@Service
public class ConversionRateServiceImpl implements ConversionRateService {

	private final CurrencyConversionRateRepository currencyConversionRateRepository;

	@Autowired
	public ConversionRateServiceImpl(CurrencyConversionRateRepository currencyConversionRateRepository) {
		this.currencyConversionRateRepository = currencyConversionRateRepository;
	}

	@Value("${currency.beacon.api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TimeStampUtil timeStampUtil;

	private final String BASE_URL = "https://api.currencybeacon.com/v1/latest?api_key=%s&symbols=%s";
	
	@Autowired
	private ConversionRateUtils conversionRateUtils;

	@Override
	public Integer calculateConversionAmount(CurrencyConversion currencyConversion) {
		Integer amount=0;
		LocalDateTime endTime = LocalDateTime.now();
		LocalDateTime startTime = endTime.minusHours(1);
		Optional<CurrencyConversionRate> currencyConversionRateopt = currencyConversionRateRepository
				.findBySourceAndTargetCurrencyWithinTimestampRange(currencyConversion.getSourceCurrency(),
						currencyConversion.getTargetCurrency(), startTime, endTime);
		if (currencyConversionRateopt.isEmpty()) {
			String apiURL = String.format(BASE_URL, apiKey,
					String.format("%s,%s", currencyConversion.getSourceCurrency(), currencyConversion.getTargetCurrency()));
			ResponseEntity<ResponseData> responseData = restTemplate.getForEntity(apiURL, ResponseData.class);
			System.out.println(responseData);
			if(responseData.getStatusCode().equals(HttpStatus.OK) && responseData.getBody()!=null) {
				CurrencyConversionRate conversionRate = new CurrencyConversionRate();
				LocalDateTime dateTime= timeStampUtil.convertToLocalDateTime(responseData.getBody().getDate());
				conversionRate.setDate(dateTime);
				
				Map<String, Double> countryCodeAndRates=responseData.getBody().getRates();
				if(!countryCodeAndRates.isEmpty()) {
					conversionRate.setSourceCurrency(currencyConversion.getSourceCurrency());
					conversionRate.setSourceCurrencyAmount(countryCodeAndRates.get(currencyConversion.getSourceCurrency()));
					
					conversionRate.setTargetCurrency(currencyConversion.getTargetCurrency());
					conversionRate.setTargetCurrencyAmount(countryCodeAndRates.get(currencyConversion.getTargetCurrency()));
					currencyConversionRateRepository.save(conversionRate);
					
					amount=conversionRateUtils.convertToTargetAmount(currencyConversion.getAmount(), conversionRate.getSourceCurrencyAmount(), conversionRate.getTargetCurrencyAmount());
				}
			}
		}else {
			CurrencyConversionRate conversionRate=currencyConversionRateopt.get();
			amount=conversionRateUtils.convertToTargetAmount(currencyConversion.getAmount(), conversionRate.getSourceCurrencyAmount(), conversionRate.getTargetCurrencyAmount());
		}
		return amount;

		

	}

}
