1: How to run the program
Clone the project to your machine, and open it in Intellij. Build Maven and make sure it runs.
Launch CurrencyConverterApplication. The program runs with a h2 database which automatically
gets the latest exchange rates from riksbanken on startup.

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


If I had more time: current problems are that if I update exchange rates more than 5 times, it will
give error code 429. Also if the api doesnt work at all, I would have liked to deal with that.
Idea for the first problem would have been to create another class in util similar to ApiResponse called
"TooManyResponses" or similar, and if I got the error code 429 I would instead return that in my
RiksbankApiReader on line 35. I tried to do it, but it got tangled and time limit made me go for a cleaner
approach. Similar would be made for if the api had crashed all together, maybe a 500 code would be returned
and processed in a similar way then.