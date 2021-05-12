package com.stock.portfolio.services;

import com.stock.portfolio.models.Portfolio;
import com.stock.portfolio.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio findPortfolioByUserId(Long userId, Long id) { return portfolioRepository.findPortfolioByUserId(userId, id); }

    public Portfolio findById(Long id) { return portfolioRepository.findById(id).get(); }

    public List<Portfolio> findAllById(List<Long> ids) { return portfolioRepository.findAllById(ids); }

    public List<Portfolio> findAllUserPortfolios(Long userId) { return portfolioRepository.findAllUserPortfolios(userId); }

    public List<Long> findAllIds() { return portfolioRepository.findAllIds(); }

    public List<Portfolio> findAll() { return portfolioRepository.findAll(); }

    public void save(Portfolio portfolio) { portfolioRepository.save(portfolio); }

    public void saveAll(List<Portfolio> portfolios) { portfolioRepository.saveAll(portfolios); }

    public void deleteById(Long id) { portfolioRepository.deleteById(id); }

    public void deleteAll() { portfolioRepository.deleteAll(); }
}
