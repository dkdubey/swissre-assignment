package com.swissre.service;

import com.swissre.exception.PortfolioException;
import com.swissre.rest.RestClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PortfolioService {

    private RestClient restClient;

    public PortfolioService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Map<String, BigDecimal> calculatePortfolio(String currency, Map<String, Integer> cryptoFileContents) {

        Map<String, BigDecimal> portfolioValue = new HashMap<>();

        if (cryptoFileContents != null) {
            cryptoFileContents.forEach((key, value) -> {
                BigDecimal cryptoPrice = restClient.getCryptoCurrencyPrice(key, currency);
                if (cryptoPrice == null) {
                    throw new PortfolioException("Price not found for supplied crypto.");
                }
                BigDecimal cryptoValue = cryptoPrice.multiply(BigDecimal.valueOf(value));
                portfolioValue.put(key, cryptoValue);
            });
        }
        return portfolioValue;
    }
}
