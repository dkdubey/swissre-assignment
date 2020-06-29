package com.swissre.rest;

import java.math.BigDecimal;

public interface RestClient {
    BigDecimal getCryptoCurrencyPrice(String crypto, String currency);
}
