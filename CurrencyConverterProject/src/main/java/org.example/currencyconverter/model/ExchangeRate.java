package org.example.currencyconverter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EXCHANGERATES")
public class ExchangeRate {

    @Id
    private String currencyCode;

    private double exchangeRateToSek;

    private double exchangeRateToEur;

    private double exchangeRateToUsd;

    public ExchangeRate() {

    }

    public ExchangeRate(String currency_code, double exchange_rate_to_sek, double exchange_rate_to_eur, double exchange_rate_to_usd) {
        this.currencyCode = currency_code;
        this.exchangeRateToSek = exchange_rate_to_sek;
        this.exchangeRateToEur = exchange_rate_to_eur;
        this.exchangeRateToUsd = exchange_rate_to_usd;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getExchangeRateToSek() {
        return exchangeRateToSek;
    }

    public void setExchangeRateToSek(double exchangeRateToSek) {
        this.exchangeRateToSek = exchangeRateToSek;
    }

    public double getExchangeRateToEur() {
        return exchangeRateToEur;
    }

    public void setExchangeRateToEur(double exchangeRateToEur) {
        this.exchangeRateToEur = exchangeRateToEur;
    }

    public double getExchangeRateToUsd() {
        return exchangeRateToUsd;
    }

    public void setExchangeRateToUsd(double exchangeRateToUsd) {
        this.exchangeRateToUsd = exchangeRateToUsd;
    }
}
