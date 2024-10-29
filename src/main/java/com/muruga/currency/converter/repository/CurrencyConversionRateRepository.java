package com.muruga.currency.converter.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.muruga.currency.converter.entity.CurrencyConversionRate;

@Repository
public interface CurrencyConversionRateRepository extends JpaRepository<CurrencyConversionRate, Long> {

	@Query("Select ccr FROM CurrencyConversionRate ccr where ccr.sourceCurrency=:sourceCurrency and ccr.targetCurrency=:targetCurrency and ccr.date BETWEEN :startTime and :endTime")
	Optional<CurrencyConversionRate> findBySourceAndTargetCurrencyWithinTimestampRange(
			@Param("sourceCurrency") String sourceCurrency, @Param("targetCurrency") String targetCurrency,
			@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
