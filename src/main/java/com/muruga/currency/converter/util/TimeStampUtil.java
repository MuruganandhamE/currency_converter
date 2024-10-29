package com.muruga.currency.converter.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

@Component
public class TimeStampUtil {
	
	/**
	 * Convert the string to LocalDateTime (Format : 2024-10-29T07:46:23Z)
	 * @param timestamp the {@link String}
	 * @return 
	 */
	public LocalDateTime convertToLocalDateTime(String timestamp) {
        
        // Convert the timestamp to an Instant
        Instant instant = Instant.parse(timestamp);
        
        // Convert the Instant to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        
        // Print the result
        System.out.println("LocalDateTime: " + localDateTime);
        
        return localDateTime;
	}

}
