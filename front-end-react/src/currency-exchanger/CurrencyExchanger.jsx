import React, { useState, useEffect } from "react";
import axios from 'axios';
import './CurrencyExchanger.css'
import { BASE_URL } from '../services/helper'

const CurrencyExchanger = () => {
  const [amount, setAmount] = useState("");
  const [result, setResult] = useState(undefined);
  const [tableData, setTableData] = useState(undefined);
  const [targetCurrency, setTargetCurrency] = useState("");
  const [currencyOptions, setCurrencyOptions] = useState([]);
  const [baseCurrency, setBaseCurrency] = useState("");
  const [isAmountValid, setIsAmountValid] = useState(false);

  useEffect(() => {
    console.log("get currencies called");
    getCurrencies();
  }, []);

  async function getCurrencies() {
    let response = await axios.get(BASE_URL + "/currency-exchanger/currencies");
    setCurrencyOptions(response.data);
    setBaseCurrency(response.data[69]);
    setTargetCurrency(response.data[154]);
  }

  const handleAmountChange = (e) => {
    const value = e.target.value;
    setAmount(value);
    if (value !== "") {
        setIsAmountValid(/^\d*\.?\d*$/.test(value));
    }
    else {
      setIsAmountValid(false);
    }
  };

  const handleSubmitForm =  async (e) => {
    e.preventDefault();
    
    let payload = {
        "baseAmount" : amount,
        "baseCurrency" : baseCurrency,
        "targetCurrency" : targetCurrency
    }
    let response = await axios.post(BASE_URL + "/currency-exchanger/new", payload);
    console.log("res->", response);
    setResult(response.data.targetAmount);
    let responseDash = await axios.get(BASE_URL + "/currency-exchanger/view-all");
    let data = responseDash.data;
    data.reverse();
    setTableData(data);

  };

  return (
    <div className="App" >

        <h1>Currency Exchange Tool</h1>

        <form onSubmit={handleSubmitForm} style={{display: 'inline-grid', textAlign: 'left'}}>

          <label >
            <strong> Amount: </strong> &nbsp;&nbsp;
            <input
              type="number"
              value={amount}
              onChange={handleAmountChange}
              required
            />
          </label>

          <label>
            <strong> Base Currency: </strong> &nbsp;&nbsp;
            <select
              value={baseCurrency}
              onChange={(e) => setBaseCurrency(e.target.value)}
              required
            >
              {currencyOptions.map((currency) => (
                <option key={currency} value={currency}>
                  {currency}
                </option>
              ))}
            </select>
          </label>

          <label>
            <strong> Exchange Currency:</strong> &nbsp;&nbsp;
            <select
              value={targetCurrency}
              onChange={(e) => setTargetCurrency(e.target.value)}
              required
            >
              {currencyOptions.map((currency) => (
                <option key={currency} value={currency}>
                  {currency}
                </option>
              ))}
            </select>
          </label>

          <button 
                  type="submit" 
                  disabled={!isAmountValid} 
                  style={{ marginLeft: 'auto', marginRight: 'auto', marginTop: '30px', width: '100px', display: 'block' }} >
            Convert
          </button>

        </form>

        {result && <div style={{marginTop: '20px', marginBottom: '50px'}}>
            <strong> Exchange Amount: </strong> &nbsp;&nbsp; {result}
        </div>}

        {tableData && 
            <table style={{margin: 'auto'}}>
                <thead>
                  <tr>
                    <th>Base Currency</th>
                    <th>Base Amount</th>   
                    <th>Exchange Currency</th>
                    <th>Exchange Amount</th>
                    <th>Time</th>
                  </tr>
                </thead>
                <tbody>
                    {tableData.map((row) => {
                        
                        return <tr key={row.currencyExchangerId}>
                        <td>{row.baseCurrency}</td>
                        <td>{row.baseAmount}</td>
                        <td>{row.targetCurrency}</td>
                        <td>{row.targetAmount}</td>
                        <td>{row.time}</td>
                      </tr>
                    })}
                  
                </tbody>
            </table>
        }

    </div>
  );
};

export default CurrencyExchanger;
