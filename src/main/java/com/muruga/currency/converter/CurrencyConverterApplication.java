package com.muruga.currency.converter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CurrencyConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
		
		
//		List<String> countryCode=List.of(
//				"AFN", "ALL", "DZD", "USD", "EUR", "AOA", "XCD", "ARS", "AMD", "AWG", "AUD", "AZN", "BSD", "BHD", "BDT", "BBD", "BYN", "BZD", "XOF", "BMD", "BTN", "INR", "BOB", "BOV", "BAM", "BWP", "NOK", "BRL", "BND", "BGN", "BIF", "CVE", "KHR", "XAF", "CAD", "KYD", "CLF", "CLP", "CNY", "COP", "COU", "KMF", "CDF", "NZD", "CRC", "CUC", "CUP", "ANG", "CZK", "DKK", "DJF", "DOP", "EGP", "SVC", "GMD", "GEL", "GHS", "GIP", "GTQ", "GBP", "GNF", "GYD", "HTG", "HNL", "HKD", "HUF", "ISK", "IDR", "XDR", "IRR", "IQD", "ILS", "JMD", "JPY", "JOD", "KZT", "KES", "KPW", "KRW", "KWD", "KGS", "LAK", "LBP", "LSL", "ZAR", "LRD", "LYD", "CHF", "MOP", "MGA", "MWK", "MYR", "MVR", "MRU", "MUR", "XUA", "MXN", "MDL", "MNT", "MAD", "MZN", "MMK", "NAD", "NPR", "XPF", "NIO", "NGN", "OMR", "PKR", "PAB", "PGK", "PYG", "PEN", "PHP", "PLN", "QAR", "MKD", "RON", "RUB", "RWF", "SHP", "WST", "STN", "SAR", "RSD", "SCR", "SLE", "SGD", "SBD", "SOS", "SSP", "LKR", "SDG", "SRD", "SZL", "SEK", "SYP", "TWD", "TJS", "TZS", "THB", "TOP", "TTD", "TND", "TRY", "TMT", "UGX", "UAH", "AED", "USN", "UYI", "UYU", "UZS", "VUV", "VEF", "VED", "VND", "YER", "ZMW", "ZWL"
//				);
//		
//		List<String> distinctCode= countryCode.stream().distinct().sorted().toList();
//		System.out.println(distinctCode.toString());
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
