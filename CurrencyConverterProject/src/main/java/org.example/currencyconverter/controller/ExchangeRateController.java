package org.example.currencyconverter.controller;

import org.example.currencyconverter.model.ExchangeRate;
import org.example.currencyconverter.service.ExchangeRateService;
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
    public Optional<ExchangeRate> getExchangeRate(@PathVariable String currencyCode) {
        return exchangeRateService.getExchangeRateByCode(currencyCode.toUpperCase());
    }

    @PostMapping("/updateExchangeRates")
    public void updateExchangeRates() {
        exchangeRateService.updateExchangeRates();
    }


}
