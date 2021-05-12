package com.stock.portfolio.models;

import com.stock.portfolio.services.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class StockPositionDtoMapper {

    @Autowired
    private StockInfoService stockInfoService;

    public StockPosition mapToDomain(StockPositionDto stockPositionDto, Long userId) throws Exception {
        StockPosition stockPosition = new StockPosition();
        if (stockPositionDto.getId() != null) {
            stockPosition.setId(stockPositionDto.getId());
        }
        stockPosition.setTicker(stockPositionDto.getTicker());
        stockPosition.setCost(stockPositionDto.getCost());
        stockPosition.setAvgCost(stockPositionDto.getAvgCost());
        Float equityValue = stockInfoService.getStockPrice(stockPosition.getTicker()).floatValue() * stockPositionDto.getQuantity();
        stockPosition.setEquityValue(equityValue);
        stockPosition.setQuantity(stockPositionDto.getQuantity());
        stockPosition.setInitiatedDate(LocalDate.now().toString());
        stockPosition.setLastDate(LocalDate.now().toString());
        stockPosition.setPortId(stockPositionDto.getPortId());
        stockPosition.setUserId(userId);

        return stockPosition;
    }

    public StockPositionDto mapToDto(StockPosition stockPosition) throws Exception {
        StockPositionDto stockPositionDto = new StockPositionDto();
        stockPosition.setId(stockPosition.getId());
        stockPosition.setTicker(stockPosition.getTicker());
        stockPosition.setCost(stockPosition.getCost());
        stockPosition.setAvgCost(stockPosition.getAvgCost());
        Float equityValue = stockInfoService.getStockPrice(stockPosition.getTicker()).floatValue() * stockPosition.getQuantity();
        stockPosition.setEquityValue(equityValue);
        stockPosition.setQuantity(stockPosition.getQuantity());
        stockPosition.setPortId(stockPosition.getPortId());

        return stockPositionDto;
    }
}
