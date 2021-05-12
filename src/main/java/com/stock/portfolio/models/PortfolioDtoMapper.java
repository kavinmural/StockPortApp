package com.stock.portfolio.models;

import com.stock.portfolio.security.AuthenticationService;
import com.stock.portfolio.services.StockInfoService;
import com.stock.portfolio.services.StockPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PortfolioDtoMapper {

    @Autowired
    private StockPositionService stockPositionService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private StockInfoService stockInfoService;

    public Portfolio mapToDomain(PortfolioDto portfolioDto, Long userId) throws Exception {
        Portfolio port = new Portfolio();
        if (portfolioDto.getId() != null) {
            port.setId(portfolioDto.getId());
        }
        port.setName(portfolioDto.getName());
        port.setDescription(portfolioDto.getDescription());
        port.setCost(portfolioDto.getCost());

        if (portfolioDto.getId() != null) {
            List<StockPosition> stockPositionList = stockPositionService.
                    findAllPortfolioStockPositions(portfolioDto.getId(), authenticationService.getCurrentUser().getId());
            Float equityValue = (float) 0;
            for(StockPosition sp : stockPositionList) {
                equityValue = equityValue + stockInfoService.getStockPrice(sp.getTicker()).floatValue();
            }
            port.setEquityValue(equityValue);
        } else {
            port.setEquityValue((float)0);
        }

        port.setCashPosition(portfolioDto.getCashPosition());
        port.setCreatedDate(LocalDate.now().toString());
        port.setUserId(userId);
        port.setNumOfPositions(portfolioDto.getNumOfPos());

        return port;
    }

    public PortfolioDto mapToDto(Portfolio port) throws Exception {
        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setId(port.getId());
        portfolioDto.setName(port.getName());
        portfolioDto.setDescription(port.getDescription());
        portfolioDto.setCost(port.getCost());

        List<StockPosition> stockPositionList = stockPositionService.
                findAllPortfolioStockPositions(portfolioDto.getId(), authenticationService.getCurrentUser().getId());
        Float equityValue = (float) 0;
        for(StockPosition sp : stockPositionList) {
            equityValue = equityValue + stockInfoService.getStockPrice(sp.getTicker()).floatValue();
        }
        port.setEquityValue(equityValue);

        portfolioDto.setCashPosition(port.getCashPosition());
        portfolioDto.setNumOfPos(port.getNumOfPositions());

        return portfolioDto;
    }
}
