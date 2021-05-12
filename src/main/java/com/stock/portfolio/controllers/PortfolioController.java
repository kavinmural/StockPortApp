package com.stock.portfolio.controllers;

import com.stock.portfolio.models.*;
import com.stock.portfolio.security.AuthenticationService;
import com.stock.portfolio.services.PortfolioService;
import com.stock.portfolio.services.StockPositionService;
import com.stock.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/stock/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService PortfolioService;

    @Autowired
    private StockPositionService stockPositionService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PortfolioDtoMapper PortfolioDtoMapper;

    // User Permissions
    @GetMapping("/myself/{id}")
    public Portfolio getMyPortfolioById(@PathVariable Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            return PortfolioService.findPortfolioByUserId(currentUser.getId(), id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/myself/all")
    public List<Portfolio> getAllMyPortfolios() throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        return PortfolioService.findAllUserPortfolios(currentUser.getId());
    }

    @GetMapping("/myself/stocks/{id}")
    public List<StockPosition> getMyPortfolioStockPositions(@PathVariable Long id) throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        return stockPositionService.findAllPortfolioStockPositions(id, currentUser.getId());
    }


    @PutMapping("/myself/create")
    public void createPortfolio(@RequestBody PortfolioDto portfolioDto) throws Exception {
        if (portfolioDto != null) {
            User currentUser = authenticationService.getCurrentUser();
            PortfolioService.save(PortfolioDtoMapper.mapToDomain(portfolioDto, currentUser.getId()));
        }
    }

    @PostMapping("/myself/update")
    public void updateMyPortfolio(@RequestBody Portfolio Portfolio) throws Exception {
        if (Portfolio != null) {
            User currentUser = authenticationService.getCurrentUser();
            Portfolio targetPortfolio = PortfolioService.findById(Portfolio.getId());

            if (targetPortfolio == null || targetPortfolio.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot update Portfolio");
            }
            PortfolioService.save(Portfolio);
        }
    }

    @DeleteMapping("/myself/delete/{id}")
    public void deleteMyPortfolio(@PathVariable Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            Portfolio targetPortfolio = PortfolioService.findById(id);

            if (targetPortfolio == null || targetPortfolio.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot delete Portfolio");
            }

            PortfolioService.deleteById(id);
        }
    }

    // Admin Permissions

    @GetMapping("/{id}")
    public Portfolio getPortfolioById(@PathVariable Long id) throws Exception {
        if (id != null) {
            return PortfolioService.findById(id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAll/{userId}")
    public List<Portfolio> getAllUserPortfolioById(@PathVariable Long userId) throws Exception {
        if (userId != null) {
            return PortfolioService.findAllUserPortfolios(userId);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAllById")
    public List<Portfolio> getAllById(List<Long> ids) throws Exception {
        if (ids != null) {
            return PortfolioService.findAllById(ids);
        }

        throw new ValidationException("Provided invalid IDs");
    }

    @GetMapping("/findAllIds")
    public List<Long> getAllIds() throws Exception {
        return PortfolioService.findAllIds();
    }

    @GetMapping("/findAll")
    public List<Portfolio> getAllPortfolios() {
        return PortfolioService.findAll();
    }

    @PutMapping("/save")
    public void savePortfolio(@RequestBody Portfolio Portfolio) throws Exception {
        if (Portfolio != null) {
            PortfolioService.save(Portfolio);
        }
    }

    @PutMapping("/saveAll")
    public void saveAllPortfolio(@RequestBody List<Portfolio> Portfolios) throws Exception {
        if (Portfolios != null) {
            PortfolioService.saveAll(Portfolios);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePortfolio(@PathVariable Long id) throws Exception {
        if (id != null) {
            PortfolioService.deleteById(id);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() throws Exception {
        PortfolioService.deleteAll();
    }
}
