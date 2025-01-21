package org.example;

import org.example.currencyconverter.util.RiksbankApiReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CurrencyConverterApplication {

	public static void main(String[] args) {
//		RiksbankApiReader riksbankApiReader = new RiksbankApiReader();
//		System.out.print(riksbankApiReader.exchangeRate("SEKEURPMI", "SEKETT"));
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}


}
