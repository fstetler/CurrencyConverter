package currencyconverter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExchangeRate {

    @Id
    private String currency;

    private double exchangeRateToSek;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchangeRateToSek() {
        return exchangeRateToSek;
    }

    public void setExchangeRateToSek(double rateToSek) {
        this.exchangeRateToSek = rateToSek;
    }

}
