package org.example.currencyconverter.util;

import java.time.LocalDate;

public class RiksbankApiReader {
    LocalDate todaysDate = LocalDate.now();

    String sekToUsdExchange = "https://api.riksbank.se/swea/v1/CrossRates/SEKETT/SEKUSDPMI/" + todaysDate;
    String sekToEurExchange = "https://api.riksbank.se/swea/v1/CrossRates/SEKETT/SEKEURPMI/" + todaysDate;
    String usdToEurExchange = "https://api.riksbank.se/swea/v1/CrossRates/SEKEURPMI/SEKUSDPMI/" + todaysDate;


}
