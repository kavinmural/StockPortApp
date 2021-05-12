package com.stock.portfolio.models;

public class PortfolioDto {

    private Long id;
    private String name;
    private String description;
    private Float cost;
    private Float equityValue;
    private Float cashPosition;
    private Long numOfPos;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getCost() { return cost; }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getEquityValue() {
        return equityValue;
    }

    public void setEquityValue(Float equityValue) {
        this.equityValue = equityValue;
    }

    public Float getCashPosition() {
        return cashPosition;
    }

    public void setCashPosition(Float cashPosition) {
        this.cashPosition = cashPosition;
    }

    public Long getNumOfPos() {
        return numOfPos;
    }

    public void setNumOfPos(Long numOfPos) {
        this.numOfPos = numOfPos;
    }
}
