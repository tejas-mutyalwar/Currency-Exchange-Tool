package com.currency.CurrencyExchangeTool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CurrencyExchanger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyExchangerId;

    private Double baseAmount;

    private String baseCurrency;

    private String targetCurrency;

    private Double targetAmount;

    private LocalDateTime time;

    public CurrencyExchanger() {}

    public CurrencyExchanger(Long currencyExchangerId, Double baseAmount, String baseCurrency, String targetCurrency, Double targetAmount, LocalDateTime time) {
        this.currencyExchangerId = currencyExchangerId;
        this.baseAmount = baseAmount;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.targetAmount = targetAmount;
        this.time = time;
    }

    public Long getCurrencyExchangerId() {
        return currencyExchangerId;
    }

    public void setCurrencyExchangerId(Long currencyExchangerId) {
        this.currencyExchangerId = currencyExchangerId;
    }

    public Double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
