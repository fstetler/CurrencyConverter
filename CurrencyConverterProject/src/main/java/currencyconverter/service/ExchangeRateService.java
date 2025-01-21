package currencyconverter.service;

import currencyconverter.repository.ExchangeRateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository repository;

    public ExchangeRateService(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    public void updateExchangeRates() {

        LocalDate todaysDate = LocalDate.now();
    }


}
