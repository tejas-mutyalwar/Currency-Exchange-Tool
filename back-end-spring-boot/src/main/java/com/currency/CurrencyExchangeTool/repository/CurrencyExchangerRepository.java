package com.currency.CurrencyExchangeTool.repository;

import com.currency.CurrencyExchangeTool.entity.CurrencyExchanger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangerRepository extends JpaRepository<CurrencyExchanger, Long> {

}
