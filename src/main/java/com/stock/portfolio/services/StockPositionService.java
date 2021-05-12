package com.stock.portfolio.services;

import com.stock.portfolio.models.*;
import com.stock.portfolio.repositories.StockPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class StockPositionService {

    @Autowired
    private StockPositionRepository stockPositionRepository;

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private StockPositionDtoMapper stockPositionDtoMapper;

    public StockPosition findStockPositionByUserId(Long userId, Long id) { return stockPositionRepository.findStockPositionByUserId(userId, id); }

    public StockPosition findStockPositionByPortId(Long portId, Long id) { return stockPositionRepository.findStockPositionByPortId(portId, id); }

    public List<StockPosition> findAllPortfolioStockPositions(Long portId, Long userId) { return stockPositionRepository.findAllPortfolioStockPositions(portId, userId); }

    public StockPosition findById(Long id) { return stockPositionRepository.findById(id).get(); }

    public List<StockPosition> findAllById(List<Long> ids) { return stockPositionRepository.findAllById(ids); }

    public List<StockPosition> findAllUserStockPositions(Long userId) { return stockPositionRepository.findAllUserStockPositions(userId); }

    public List<Long> findAllIds() { return stockPositionRepository.findAllIds(); }

    public List<StockPosition> findAll() { return stockPositionRepository.findAll(); }

    public void save(StockPosition stockPosition) { stockPositionRepository.save(stockPosition); }

    public void saveAll(List<StockPosition> stockPositions) { stockPositionRepository.saveAll(stockPositions); }

    public void deleteById(Long id) { stockPositionRepository.deleteById(id); }

    public void deleteAll() { stockPositionRepository.deleteAll(); }

    public void buyStockPosition(Long id, MarketOrderDto dto) throws Exception {
        StockPosition sp = stockPositionRepository.findStockPositionByTicker(dto.getPortId(), id, dto.getTicker());
        Portfolio p = portfolioService.findById(dto.getPortId());

        if (sp == null) {
            StockPositionDto stockPositionDto = new StockPositionDto();
            stockPositionDto.setPortId(dto.getPortId());
            stockPositionDto.setQuantity(dto.getQuantity());
            stockPositionDto.setTicker(dto.getTicker());
            stockPositionDto.setAvgCost(stockInfoService.getStockPrice(stockPositionDto.getTicker()).floatValue());
            stockPositionDto.setCost(stockPositionDto.getAvgCost() * stockPositionDto.getQuantity());

            if (stockPositionDto.getCost() > p.getCashPosition()) {
                throw new ValidationException("Not enough buying power!");
            }

            stockPositionDto.setEquityValue(stockPositionDto.getCost());
            stockPositionRepository.save(stockPositionDtoMapper.mapToDomain(stockPositionDto, id));
            p.setNumOfPositions(p.getNumOfPositions() + 1);
            p.setEquityValue(p.getEquityValue() + stockPositionDto.getEquityValue());
            p.setCashPosition(p.getCashPosition() - stockPositionDto.getCost());
            p.setCost(p.getCost() + stockPositionDto.getCost());
            portfolioService.save(p);

        } else {
            sp.setQuantity(sp.getQuantity() + dto.getQuantity());
            Float price = stockInfoService.getStockPrice(dto.getTicker()).floatValue();
            sp.setCost(sp.getCost() + price * dto.getQuantity());

            if (sp.getCost() >  p.getCashPosition()) {
                throw new ValidationException("Not enough buying power!");
            }

            sp.setAvgCost(sp.getCost() / sp.getQuantity());
            sp.setEquityValue(stockInfoService.getStockPrice(dto.getTicker()).floatValue() * sp.getQuantity());
            stockPositionRepository.save(sp);
            p.setEquityValue(p.getEquityValue() + price * dto.getQuantity());
            p.setCashPosition(p.getCashPosition() - price * dto.getQuantity());
            p.setCost(p.getCost() + price * dto.getQuantity());
            portfolioService.save(p);
        }
    }

    public void sellStockPosition(Long id, MarketOrderDto dto) throws Exception {
        StockPosition sp = stockPositionRepository.findStockPositionByTicker(dto.getPortId(), id, dto.getTicker());
        Portfolio p = portfolioService.findById(dto.getPortId());

        if (sp != null) {

            Float price = stockInfoService.getStockPrice(dto.getTicker()).floatValue();

            if (sp.getQuantity() < dto.getQuantity()) {
                throw new ValidationException("Not enough shares to sell!");
            }

            sp.setQuantity(sp.getQuantity() - dto.getQuantity());
            if (sp.getQuantity() == 0) {
                stockPositionRepository.delete(sp);
            } else {
                sp.setCost(sp.getCost() - price * dto.getQuantity());
                sp.setAvgCost(sp.getCost() / sp.getQuantity());
                sp.setEquityValue(sp.getEquityValue() - stockInfoService.getStockPrice(dto.getTicker()).floatValue() * sp.getQuantity());
                stockPositionRepository.save(sp);
            }

            p.setEquityValue(p.getEquityValue() - price * dto.getQuantity());
            p.setCashPosition(p.getCashPosition() + price * dto.getQuantity());
            p.setCost(p.getCost() - price * dto.getQuantity());
            if (sp.getQuantity() == 0) {
                p.setNumOfPositions(p.getNumOfPositions() - 1);
            }
            portfolioService.save(p);
        }
    }
}
