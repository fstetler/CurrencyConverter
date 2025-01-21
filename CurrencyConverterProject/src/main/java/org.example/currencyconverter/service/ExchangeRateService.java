package org.example.currencyconverter.service;

import org.example.currencyconverter.model.ExchangeRate;
import org.example.currencyconverter.repository.ExchangeRateRepository;
import org.example.currencyconverter.util.ApiResponse;
import org.example.currencyconverter.util.RiksbankApiReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository repository;

    public ExchangeRateService(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    public List<ExchangeRate> getAllExchangeRates() {
        return repository.findAll();
    }

    public Optional<ExchangeRate> getExchangeRateByCode(String code) {
        return repository.findById(code);
    }

    public double exchangeCurrencyFromTo(double value, String fromCurrency, String toCurrency) {
        Optional<ExchangeRate> currency = Optional.of(repository.findById(fromCurrency.toUpperCase())).get();

        if (toCurrency.equalsIgnoreCase("SEK")) {
            return value * currency.get().getExchangeRateToSek();
        } else if (toCurrency.equalsIgnoreCase("USD")) {
            return value * currency.get().getExchangeRateToUsd();
        } else if (toCurrency.equalsIgnoreCase("EUR")) {
            return value * currency.get().getExchangeRateToEur();
        }

        return 1.0;
    }

    public void updateExchangeRates() {
        RiksbankApiReader riksbankApiReader = new RiksbankApiReader();
        double sekToEur = riksbankApiReader.exchangeRate("SEKETT", "SEKEURPMI");
        double sekToUsd = riksbankApiReader.exchangeRate("SEKETT", "SEKUSDPMI");

        double eurToSek = 1 / sekToEur;
        double usdToSek = 1 / sekToUsd;

        double eurToUsd = sekToUsd / sekToEur;
        double usdToEur = sekToEur / sekToUsd;

        repository.save(new ExchangeRate("SEK", 1, sekToEur, sekToUsd));
        repository.save(new ExchangeRate("EUR", eurToSek, 1, eurToUsd));
        repository.save(new ExchangeRate("USD", usdToSek, usdToEur, 1));

    }

    @EventListener(ApplicationReadyEvent.class)
    public void updateExchangeRatesOnStartup() {
        updateExchangeRates();
    }
}
