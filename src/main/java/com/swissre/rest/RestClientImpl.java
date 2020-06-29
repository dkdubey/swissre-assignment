package com.swissre.rest;

import com.swissre.exception.PortfolioException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestClientImpl implements RestClient {

    public static final String BASE_URL = "https://min-api.cryptocompare.com";
    public static final String REGEX_FOR_JSON_RESPONSE = ".\"EUR\":(.*?)}";

    @Override
    public BigDecimal getCryptoCurrencyPrice(String crypto, String currency) {

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(BASE_URL + "/data/price?fsym=" + crypto + "&tsyms=" + currency);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new PortfolioException("Couldn't fetch price for crypto currency.");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = br.readLine();
            //As no third party library is allowed for parsing json, using regex.
            Pattern pattern = Pattern.compile(REGEX_FOR_JSON_RESPONSE);
            Matcher matcher = pattern.matcher(output);
            if (matcher.find()) {
                return new BigDecimal(matcher.group(1));
            }
        } catch (IOException e) {
            throw new PortfolioException("Couldn't fetch price for crypto currency.", e);
        } finally {
            try {
                if (reader != null) reader.close();
                if (conn != null) conn.disconnect();
            } catch (IOException e) {
                //ignore exception while closing
            }
        }
        return null;
    }
}
