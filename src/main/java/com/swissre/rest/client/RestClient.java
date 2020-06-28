package com.swissre.rest.client;

import com.swissre.exception.PortfolioException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    public static final String BASE_URL = "https://min-api.cryptocompare.com";

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
            JSONObject json = new JSONObject(output);
            return new BigDecimal(json.get(currency).toString());
        } catch (IOException | JSONException e) {
            throw new PortfolioException("Couldn't fetch price for crypto currency.", e);
        } finally {
            try {
                if (reader != null) reader.close();
                if (conn != null) conn.disconnect();
            } catch (IOException e) {
                //ignore exception while closing
            }
        }
    }
}
