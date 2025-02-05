1: How to run the program
Clone the project to your machine, and open it in Intellij. Build Maven and make sure it runs.
Launch CurrencyConverterApplication. The program runs with a h2 database which automatically
gets the latest exchange rates from riksbanken api on startup.

2: Each endpoint explained

Launch a web browser or similar (for example Postman). Each end point will start with
http://localhost:8080/api/v1

After that there are 4 end points. Current currencies supported are SEK, USD, and EUR.

- http://localhost:8080/api/v1/getAllExchangeRates
This will get you all current exchange rates.
Currency Code - the current currency
Exchange rate to SEK
Exchange rate to EUR
Exchange rate to USD

- http://localhost:8080/api/v1/getExchangeRate/{currencyCode}
This will get you current exchange rates for a select currency
How to use: for example http://localhost:8080/api/v1/getExchangeRate/SEK
Currency Code - the current currency
Exchange rate to SEK
Exchange rate to EUR
Exchange rate to USD

- http://localhost:8080/api/v1/exchange/{value}/{fromCurrency}/{toCurrency}
Convert a value of a currency from one to another
How to use: for example http://localhost:8080/api/v1/exchange/100/usd/sek
Result will be a double

- http://localhost:8080/api/v1/updateExchangeRates
This will update the exchange rates. A new exchange rate is given by riksbanken at 16:15 every day.

Epilogue:
If I had more time I would have made tests, and mocked away the database and tested the service layer. I also
would have dealt with what happens when the api isnt reachable, so it would give a error code 500. I would have made it
so it returned a more reasonable code and a message telling us that the api was shut down or something similar. Also that
now its currently (probably) sensitive for changes around exaclty 16:15, new api might release 16:16 but data changes in
code at 16:15. Make it more robust would be preferable.