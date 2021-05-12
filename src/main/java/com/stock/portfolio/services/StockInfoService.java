package com.stock.portfolio.services;

import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class StockInfoService {

    public Stock getStockInfo(String ticker) throws Exception {
        return YahooFinance.get(ticker);
    }

    public BigDecimal getStockPrice(String ticker) throws Exception {
        Stock stock = YahooFinance.get(ticker);
        return stock.getQuote(true).getPrice();
    }

    public Map<String, Stock> getListOfStocks(String[] tickers) throws Exception {
        return YahooFinance.get(tickers);
    }
}
