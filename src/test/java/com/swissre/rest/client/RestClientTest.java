package com.swissre.rest.client;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RestClientTest {

    @Test
    public void testGetCryptoCurrencyPrice(){
        RestClient restClient = new RestClient();
        BigDecimal result= restClient.getCryptoCurrencyPrice("BTC", "EUR");
        assertNotNull(result);
        assertTrue("Value should be greater than ZERO",result.intValue() > 0 );
    }

}
