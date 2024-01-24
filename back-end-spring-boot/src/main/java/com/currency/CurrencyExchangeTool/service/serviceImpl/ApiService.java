package com.currency.CurrencyExchangeTool.service.serviceImpl;

import com.currency.CurrencyExchangeTool.exception.ApiServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class ApiService {

    @Value("${external.api.access.key}")
    private String apiKey ;

    public String getCurrencyExchangeData(String baseCurrency, String targetCurrency) throws Exception {

        try {

            String url = "https://api.currencyapi.com/v3/latest?base_currency=" + baseCurrency + "&currencies=" + targetCurrency;
            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", apiKey);
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();

        } catch (RestClientException restClientException) {

            System.err.println("Error while fetching currency exchange data from the API" + restClientException.getMessage());
            throw new ApiServiceException("Error fetching currency exchange data");
        }
    }

    public String getCurrencies() throws Exception{

        try {

        String url = "https://api.currencyapi.com/v3/currencies";
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();

        } catch (RestClientException restClientException) {

            System.err.println("Error while fetching currency exchange data from the API" + restClientException.getMessage());
            throw new ApiServiceException("Error fetching currencies");
        }
    }
}
