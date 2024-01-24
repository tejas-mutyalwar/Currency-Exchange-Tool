package com.currency.CurrencyExchangeTool.service.serviceImpl;

import com.currency.CurrencyExchangeTool.entity.CurrencyExchanger;
import com.currency.CurrencyExchangeTool.repository.CurrencyExchangerRepository;
import com.currency.CurrencyExchangeTool.service.CurrencyExchangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyExchangerServiceImpl implements CurrencyExchangerService {

    @Autowired
    private CurrencyExchangerRepository currencyExchangerRepository;

    @Override
    public CurrencyExchanger createCurrencyExchangeRecord(CurrencyExchanger currencyExchanger) {

        return this.currencyExchangerRepository.save(currencyExchanger);
    }

    @Override
    public List<CurrencyExchanger> getCurrencyExchangeRecords() {

        return this.currencyExchangerRepository.findAll();
    }


}
