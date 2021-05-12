package com.stock.portfolio.repositories;

import com.stock.portfolio.models.StockPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface StockPositionRepository extends JpaRepository<StockPosition, Long> {

    @Query("SELECT sp.id FROM StockPosition sp")
    List<Long> findAllIds();

    @Query("SELECT sp FROM StockPosition sp WHERE sp.userId = :userId")
    List<StockPosition> findAllUserStockPositions(@Param("userId") Long userId);

    @Query("SELECT sp FROM StockPosition sp WHERE sp.portId = :portId AND sp.userId = :userId")
    List<StockPosition> findAllPortfolioStockPositions(@Param("portId") Long portId, @Param("userId") Long userId);

    @Query("SELECT sp FROM StockPosition sp WHERE sp.userId = :userId AND sp.id = :id")
    StockPosition findStockPositionByUserId(@Param("userId") Long userId, @Param("id") Long id);

    @Query("SELECT sp FROM StockPosition sp WHERE sp.portId = :portId AND sp.id = :id")
    StockPosition findStockPositionByPortId(@Param("portId") Long portId, @Param("id") Long id);

    @Query("SELECT sp FROM StockPosition sp WHERE sp.portId = :portId AND sp.userId = :userId AND sp.ticker = :ticker")
    StockPosition findStockPositionByTicker(@Param("portId") Long portId, @Param("userId") Long userId, @Param("ticker") String ticker);
}
