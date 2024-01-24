import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CurrencyExchanger from './currency-exchanger/CurrencyExchanger';

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<CurrencyExchanger />} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
