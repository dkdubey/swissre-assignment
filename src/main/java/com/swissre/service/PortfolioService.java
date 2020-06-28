package com.swissre.service;

import com.swissre.readfile.FileReader;
import com.swissre.rest.client.RestClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PortfolioService {

    private RestClient restClient;
    private FileReader fileReader;

    public PortfolioService(RestClient restClient, FileReader fileReader) {
        this.restClient = restClient;
        this.fileReader = fileReader;
    }

    public Map<String, BigDecimal> calculatePortfolio(String currency, String fileName) {

        Map<String, BigDecimal> portfolioValue = new HashMap<>();
        Map<String, Integer> mapCryptoCurrency = fileReader.readFile(fileName);

        if (mapCryptoCurrency != null) {
            mapCryptoCurrency.forEach((key, value) -> {
                BigDecimal cryptoPrice = restClient.getCryptoCurrencyPrice(key, currency);
                BigDecimal cryptoValue = cryptoPrice.multiply(BigDecimal.valueOf(value));
                portfolioValue.put(key, cryptoValue);
            });
        }
        return portfolioValue;
    }
}
