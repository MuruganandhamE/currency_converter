package com.muruga.currency.converter.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency_conversion_rate")
@Getter
@Setter
@NoArgsConstructor
public class CurrencyConversionRate {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_currency", nullable = false)
    private String sourceCurrency;
    
    @Column(name = "source_currency_amount", nullable = false)
    private Double sourceCurrencyAmount;

    @Column(name = "target_currency", nullable = false)
    private String targetCurrency;
    
    
    @Column(name = "target_currency_amount", nullable = false)
    private Double targetCurrencyAmount;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

}
