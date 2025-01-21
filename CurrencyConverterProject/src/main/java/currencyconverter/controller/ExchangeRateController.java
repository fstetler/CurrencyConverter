package currencyconverter.controller;

import currencyconverter.service.ExchangeRateService;

public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;


    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }



}
