package com.stock.portfolio.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Table(name = "stockposition")
public class StockPosition implements Comparator<StockPosition> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "ticker")
    @NotNull
    private String ticker;

    @Column(name = "cost")
    @NotNull
    private Float cost;

    @Column(name = "avg_cost")
    @NotNull
    private Float avgCost;

    @Column(name = "equity_value")
    @NotNull
    private Float equityValue;

    @Column(name = "quantity")
    @NotNull
    private Long quantity;

    @Column(name = "initiated_date")
    private String initiatedDate;

    @Column(name = "last_date")
    private String lastDate;

    @Column(name = "port_id")
    @NotNull
    private Long portId;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) { this.Id = id; }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(Float avgCost) {
        this.avgCost = avgCost;
    }

    public Float getEquityValue() {
        return equityValue;
    }

    public void setEquityValue(Float equityValue) {
        this.equityValue = equityValue;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getInitiatedDate() {
        return initiatedDate;
    }

    public void setInitiatedDate(String initiatedDate) {
        this.initiatedDate = initiatedDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int compare(StockPosition stockPosition1, StockPosition stockPosition2) {
        return (int) (stockPosition1.equityValue - stockPosition2.equityValue);
    }
}
