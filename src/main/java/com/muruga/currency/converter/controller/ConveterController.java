/**
 * 
 */
package com.muruga.currency.converter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.muruga.currency.converter.model.CurrencyConversion;
import com.muruga.currency.converter.service.ConversionRateService;
import com.muruga.currency.converter.util.ConversionRateUtils;

/**
 * @author admin
 */
@Controller
public class ConveterController {

	@Autowired
	private ConversionRateService conversionRateService;
	
	@Autowired
	private ConversionRateUtils conversionRateUtils;

	
	@GetMapping("/convert")
	public String getConverter(Model model) {
		model.addAttribute("countryCodes", conversionRateUtils.getCountryCode());
		model.addAttribute("currencyCoversionForm", new CurrencyConversion());
		return "convert-page";
	}

	@PostMapping("/convert")
	public String calculateConversion(
			@ModelAttribute("currencyCoversionForm") CurrencyConversion currencyConversionModel, Model model) {
		try {
		model.addAttribute("countryCodes", conversionRateUtils.getCountryCode());
		currencyConversionModel.setResult(conversionRateService.calculateConversionAmount(currencyConversionModel));
		currencyConversionModel.setMessage(null);
		model.addAttribute("currencyCoversionForm", currencyConversionModel);
		}catch (Exception e) {
			currencyConversionModel.setMessage("Check Your Internet Connect Or Try Again Later!");
			model.addAttribute("currencyCoversionForm", currencyConversionModel);
		}
		return "convert-page";
	}

}
