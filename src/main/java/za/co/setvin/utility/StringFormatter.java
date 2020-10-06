package za.co.setvin.utility;

import org.springframework.context.annotation.Bean;

public class StringFormatter {
	
	@Bean
	public String longFormat(Long number) {
		return String.valueOf(number);
	}
	
	@Bean
	public Long stringFormat(String text) {
		return Long.parseLong(text);
	}

}
