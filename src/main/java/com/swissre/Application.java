package com.swissre;

import com.swissre.readfile.FileReader;
import com.swissre.rest.client.RestClient;
import com.swissre.service.PortfolioService;

import java.math.BigDecimal;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        String currency = "EUR";

        RestClient restClient = new RestClient();
        FileReader fileReader = new FileReader();

        PortfolioService portfolioService = new PortfolioService(restClient, fileReader);

        Map<String, BigDecimal> portfolioValue = portfolioService.calculatePortfolio(currency, "bobs_crypto.txt");

        portfolioValue.keySet().forEach(key -> System.out.println("Crypto = " + key + " : value = " + portfolioValue.get(key) + " " + currency));
        BigDecimal sum = portfolioValue.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total Value of investments : " + sum + " " + currency);
    }
}
