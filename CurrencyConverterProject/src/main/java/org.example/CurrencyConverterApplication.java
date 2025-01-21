package org.example;

import org.example.currencyconverter.util.RiksbankApiReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyConverterApplication {

	public static void main(String[] args) {
//		RiksbankApiReader riksbankApiReader = new RiksbankApiReader();
//		System.out.print(riksbankApiReader.exchangeRate("SEKEURPMI", "SEKETT"));
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

}
