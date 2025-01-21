package org.example.currencyconverter.service;

import org.example.currencyconverter.model.ExchangeRate;
import org.example.currencyconverter.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

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


    public void updateExchangeRates() {



    }


}
