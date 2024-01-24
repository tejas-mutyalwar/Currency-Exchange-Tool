package com.currency.CurrencyExchangeTool.service;

import com.currency.CurrencyExchangeTool.entity.CurrencyExchanger;

import java.util.List;

public interface CurrencyExchangerService {

    CurrencyExchanger createCurrencyExchangeRecord(CurrencyExchanger currencyExchanger);

    List<CurrencyExchanger> getCurrencyExchangeRecords();

}
