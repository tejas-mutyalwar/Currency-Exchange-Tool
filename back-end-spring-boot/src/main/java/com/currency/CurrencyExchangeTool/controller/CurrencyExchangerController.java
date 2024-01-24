package com.currency.CurrencyExchangeTool.controller;

import com.currency.CurrencyExchangeTool.entity.CurrencyExchanger;
import com.currency.CurrencyExchangeTool.exception.ApiServiceException;
import com.currency.CurrencyExchangeTool.repository.CurrencyExchangerRepository;
import com.currency.CurrencyExchangeTool.service.CurrencyExchangerService;
import com.currency.CurrencyExchangeTool.service.serviceImpl.ApiService;
import org.apache.catalina.LifecycleState;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/currency-exchanger")
public class CurrencyExchangerController {

    @Autowired
    private CurrencyExchangerService currencyExchangerService;

    @Autowired
    private ApiService apiService;

    @PostMapping(path = "/new")
    public ResponseEntity<?> getCurrencyExchangeResult(@RequestBody CurrencyExchanger currencyExchanger) {

        try {

            String response = this.apiService.getCurrencyExchangeData(currencyExchanger.getBaseCurrency(), currencyExchanger.getTargetCurrency());
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            double exchangeRate = data.getJSONObject(currencyExchanger.getTargetCurrency()).getDouble("value");
            currencyExchanger.setTargetAmount(currencyExchanger.getBaseAmount() * exchangeRate);
            currencyExchanger.setTime(LocalDateTime.now());

        } catch (ApiServiceException exception) {

            System.err.println("Error calling API to get currency exchange data: " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("An error occurred while fetching currency exchange data from the API");
        } catch (Exception e) {

            System.err.println("Unexpected error during currency exchange: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("An unexpected error occurred while fetching currency exchange data");
        }

        try {

            CurrencyExchanger currencyExchangeRecord = this.currencyExchangerService.createCurrencyExchangeRecord(currencyExchanger);
            return new ResponseEntity<>(currencyExchangeRecord, HttpStatus.CREATED);

        } catch (DataAccessException e) {

            System.err.println("Error saving currency exchange record to the database: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("An error occurred while saving the currency exchange record");

        } catch (Exception e) {

            System.err.println("Unexpected error during currency exchange record saving: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("An unexpected error occurred while saving in MySQL");
        }

    }

    @GetMapping(path = "/view-all")
    public ResponseEntity<?> getCurrencyExchangerRecords() {

        try {

            List<CurrencyExchanger> currencyExchangeRecords = this.currencyExchangerService.getCurrencyExchangeRecords();
            return new ResponseEntity<>(currencyExchangeRecords, HttpStatus.OK);

        }
        catch (DataAccessException e) {

        System.err.println("Error fetching currency exchange records from the database: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body("An error occurred while fetching currency exchange records");

        } catch (Exception e) {

            System.err.println("Unexpected error during currency exchange records fetching: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("An unexpected error occurred while fetching records");
        }
    }

    @GetMapping(path = "/currencies")
    public ResponseEntity<?> getCurrencies() {

        try {

            String response = this.apiService.getCurrencies();
            JSONObject data = new JSONObject(response).getJSONObject("data");
            Set<String> currencies = data.keySet();
            return new ResponseEntity<>(currencies, HttpStatus.OK);

        } catch (ApiServiceException exception) {

            System.err.println("Error fetching currencies " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("An error occurred while fetching currencies from the API");

        } catch (Exception exception) {

            System.err.println("Unexpected error while fetching currencies from API" + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body("An unexpected error occurred while fetching currencies");
        }
    }

    @GetMapping(path = "/test")
    public String test() {
        return "Welcome to Currency Exchanger Tool";
    }

}
