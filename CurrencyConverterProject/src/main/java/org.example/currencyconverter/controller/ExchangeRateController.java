package org.example.currencyconverter.controller;

import org.example.currencyconverter.model.ExchangeRate;
import org.example.currencyconverter.service.ExchangeRateService;
import org.example.currencyconverter.util.RiksbankApiReader;
import org.example.currencyconverter.util.TooManyRequestsException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/getAllExchangeRates")
    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateService.getAllExchangeRates();
    }

    @GetMapping("/getExchangeRate/{currencyCode}")
    public ResponseEntity<ExchangeRate> getExchangeRate(@PathVariable String currencyCode) {
        Optional<ExchangeRate> exchangeRate = exchangeRateService.getExchangeRateByCode(currencyCode.toUpperCase());
        return exchangeRate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/exchange/{value}/{fromCurrency}/{toCurrency}")
    public ResponseEntity<Double> exchangeCurrency(@PathVariable double value, @PathVariable String fromCurrency, @PathVariable String toCurrency) {
        Optional<ExchangeRate> exchangeRateFromCurrency = exchangeRateService.getExchangeRateByCode(fromCurrency.toUpperCase());
        Optional<ExchangeRate> exchangeRateToCurrency = exchangeRateService.getExchangeRateByCode(toCurrency.toUpperCase());

        if (exchangeRateFromCurrency.isEmpty() || exchangeRateToCurrency.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(exchangeRateService.exchangeCurrencyFromTo(value, fromCurrency, toCurrency));
    }

    @PostMapping("/updateExchangeRates")
    public ResponseEntity<String> updateExchangeRates() {
        try {
            RiksbankApiReader riksbankApiReader = new RiksbankApiReader();
            double sekToEur = riksbankApiReader.exchangeRate("SEKETT", "SEKEURPMI");
            double sekToUsd = riksbankApiReader.exchangeRate("SEKETT", "SEKUSDPMI");

            exchangeRateService.updateExchangeRates(sekToEur, sekToUsd);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (TooManyRequestsException e) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(e.getMessage());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void updateExchangeRatesOnStartup() {
        updateExchangeRates();
    }
}
