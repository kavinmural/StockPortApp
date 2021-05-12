package com.stock.portfolio.controllers;

import com.stock.portfolio.models.*;
import com.stock.portfolio.security.AuthenticationService;
import com.stock.portfolio.services.StockPositionService;
import com.stock.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/stock/position")
public class StockPositionController {

    @Autowired
    private StockPositionService stockPositionService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private StockPositionDtoMapper stockPositionDtoMapper;

    // User Permissions
    @GetMapping("/myself/{id}")
    public StockPosition getMyStockPositionById(Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            return stockPositionService.findStockPositionByUserId(currentUser.getId(), id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/myself/all")
    public List<StockPosition> getAllMyStockPositions() throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        return stockPositionService.findAllUserStockPositions(currentUser.getId());
    }

    @GetMapping("/myself/all/sorted")
    public List<StockPosition> getAllMyStockPositionsSorted() throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        List<StockPosition> stockPositions = stockPositionService.findAllUserStockPositions(currentUser.getId());
        Collections.sort(stockPositions, new StockPosition());
        return stockPositions;
    }

    @PutMapping("/myself/buy")
    public void buyStockPosition(@RequestBody MarketOrderDto marketOrderDto) throws Exception {
        if (marketOrderDto != null) {
            User currentUser = authenticationService.getCurrentUser();
            stockPositionService.buyStockPosition(currentUser.getId(), marketOrderDto);
        }
    }

    @PutMapping("/myself/sell")
    public void sellStockPosition(@RequestBody MarketOrderDto marketOrderDto) throws Exception {
        if (marketOrderDto != null) {
            User currentUser = authenticationService.getCurrentUser();
            stockPositionService.sellStockPosition(currentUser.getId(), marketOrderDto);
        }
    }

    @PutMapping("/myself/create")
    public void createStockPosition(@RequestBody StockPositionDto stockPositionDto) throws Exception {
        if (stockPositionDto != null) {
            User currentUser = authenticationService.getCurrentUser();
            stockPositionService.save(stockPositionDtoMapper.mapToDomain(stockPositionDto, currentUser.getId()));
        }
    }

    @PostMapping("/myself/update")
    public void updateMyStockPosition(@RequestBody StockPosition stockPosition) throws Exception {
        if (stockPosition != null) {
            User currentUser = authenticationService.getCurrentUser();
            StockPosition targetStockPosition = stockPositionService.findById(stockPosition.getId());

            if (targetStockPosition == null || targetStockPosition.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot update income");
            }

            stockPositionService.save(stockPosition);
        }
    }

    @DeleteMapping("/myself/delete/{id}")
    public void deleteMyExpense(@PathVariable Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            StockPosition targetStockPosition = stockPositionService.findById(id);

            if (targetStockPosition == null || targetStockPosition.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot delete income");
            }

            stockPositionService.deleteById(id);
        }
    }

    // Admin Permission

    @GetMapping("/{id}")
    public StockPosition getStockPositionById(@PathVariable Long id) throws Exception {
        if (id != null) {
            stockPositionService.findById(id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAll/{userId}")
    public List<StockPosition> getAllUserStockPositionsById(@PathVariable Long userId) throws Exception {
        if (userId != null) {
            stockPositionService.findAllUserStockPositions(userId);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAllById")
    public List<StockPosition> getAllById(List<Long> ids) throws Exception {
        if (ids != null) {
            return stockPositionService.findAllById(ids);
        }

        throw new ValidationException("Provided invalid IDs");
    }

    @GetMapping("/findAllIds")
    public List<Long> getAllIds() throws Exception {
        return stockPositionService.findAllIds();
    }

    @GetMapping("/findAll")
    public List<StockPosition> getAllStockPositions() {
        return stockPositionService.findAll();
    }

    @PutMapping("/save")
    public void saveExpense(@RequestBody StockPosition stockPosition) throws Exception {
        if (stockPosition != null) {
            stockPositionService.save(stockPosition);
        }
    }

    @PutMapping("/saveAll")
    public void saveAllStockPositions(@RequestBody List<StockPosition> stockPositions) throws Exception {
        if (stockPositions != null) {
            stockPositionService.saveAll(stockPositions);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStockPositions(@PathVariable Long id) throws Exception {
        if (id != null) {
            stockPositionService.deleteById(id);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() throws Exception {
        stockPositionService.deleteAll();
    }
}
