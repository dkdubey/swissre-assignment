package com.swissre;

import com.swissre.file.FileReader;
import com.swissre.rest.RestClient;
import com.swissre.rest.RestClientImpl;
import com.swissre.service.PortfolioService;

import java.math.BigDecimal;
import java.util.Map;

public class Application {

    private static final String currency = "EUR";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please supply file path as argument to this program.");
            System.exit(1);
        }

        String file = args[0];

        RestClient restClient = new RestClientImpl();
        Map<String, Integer> fileContent = readFile(file);

        PortfolioService portfolioService = new PortfolioService(restClient);

        Map<String, BigDecimal> portfolioValue = portfolioService.calculatePortfolio(currency, fileContent);

        portfolioValue.keySet().forEach(key -> System.out.println("Crypto = " + key + " : value = " + portfolioValue.get(key) + " " + currency));
        BigDecimal sum = portfolioValue.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total Value of investments : " + sum + " " + currency);
    }

    private static Map<String, Integer> readFile(String fileName) {
        FileReader fileReader = new FileReader();
        return fileReader.readFile(fileName);
    }
}
