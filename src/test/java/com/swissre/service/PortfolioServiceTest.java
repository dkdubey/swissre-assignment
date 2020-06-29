package com.swissre.service;

import com.swissre.exception.PortfolioException;
import com.swissre.rest.RestClient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PortfolioServiceTest {

    private PortfolioService portfolioService;

    @Before
    public void setUp() throws Exception {
        RestClient restClient = (crypto, currency) -> new BigDecimal(2);
        portfolioService = new PortfolioService(restClient);
    }

    @Test
    public void shouldCalculatePortfolio() {
        Map<String, Integer> cryptoFileContents = mockCryptoContents();

        Map<String, BigDecimal> actualValuations = portfolioService.calculatePortfolio("any-currency", cryptoFileContents);

        assertThat(actualValuations.size(), is(2));
        assertThat(actualValuations.get("BTC"), is(new BigDecimal(20)));
        assertThat(actualValuations.get("ETH"), is(new BigDecimal(10)));
    }

    @Test
    public void shouldRetrunEmptyMapIfFileContentIsNull() {
        Map<String, BigDecimal> actualValuations = portfolioService.calculatePortfolio("any-currency", null);
        assertThat(actualValuations.size(), is(0));
    }

    @Test
    public void shouldRetrunEmptyMapIfFileContentIsEmpty() {
        Map<String, BigDecimal> actualValuations = portfolioService.calculatePortfolio("any-currency", new HashMap<>());
        assertThat(actualValuations.size(), is(0));
    }

    @Test(expected = PortfolioException.class)
    public void shouldThrowExceptionIfPriceNotFound() {
        Map<String, Integer> cryptoFileContents = mockCryptoContents();

        RestClient restClient = (crypto, currency) -> null;

        portfolioService = new PortfolioService(restClient);
        Map<String, BigDecimal> actualValuations = portfolioService.calculatePortfolio("any-currency", cryptoFileContents);
        assertThat(actualValuations.size(), is(0));
    }

    private Map<String, Integer> mockCryptoContents() {
        Map<String, Integer> cryptoFileContents = new HashMap<>();
        cryptoFileContents.put("BTC", 10);
        cryptoFileContents.put("ETH", 5);
        return cryptoFileContents;
    }

}
