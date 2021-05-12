package com.stock.portfolio.repositories;

import com.stock.portfolio.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query("SELECT p.id FROM Portfolio p")
    List<Long> findAllIds();

    @Query("SELECT p FROM Portfolio p WHERE p.userId = :userId")
    List<Portfolio> findAllUserPortfolios(@Param("userId") Long userId);

    @Query("SELECT p FROM Portfolio p WHERE p.userId = :userId AND p.id = :id")
    Portfolio findPortfolioByUserId(@Param("userId") Long userId, @Param("id") Long id);
}
