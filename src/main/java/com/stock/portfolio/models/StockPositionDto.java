package com.stock.portfolio.models;

public class StockPositionDto {

    private Long id;
    private String ticker;
    private Float cost;
    private Float avgCost;
    private Float equityValue;
    private Long quantity;
    private Long portId;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

}
