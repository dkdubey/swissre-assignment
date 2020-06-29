package com.swissre.rest;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class RestClientTest {

    @Test
    public void shouldGetCryptoCurrencyPrice() {
        RestClient restClient = new RestClientImpl();
        BigDecimal result = restClient.getCryptoCurrencyPrice("BTC", "EUR");
        assertThat(result, notNullValue());
        assertTrue("Value should be greater than ZERO", result.intValue() > 0);
    }

    @Test
    public void shouldGetCryptoCurrencyPrice1() {
        RestClient restClient = new RestClientImpl();
        BigDecimal result = restClient.getCryptoCurrencyPrice("BTC111", "EUR");
        assertThat(result, CoreMatchers.nullValue());
    }
}
