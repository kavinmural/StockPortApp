package com.stock.portfolio.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Table(name = "portfolio")
public class Portfolio implements Comparator<Portfolio> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    @NotNull
    private Float cost;

    @Column(name = "equity_value")
    @NotNull
    private Float equityValue;

    @Column(name = "cash_position")
    @NotNull
    private Float cashPosition;

    @Column(name = "num_of_positions")
    @NotNull
    private Long numOfPositions;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {  this.Id = id; }

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

    public Float getCost() {
        return cost;
    }

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

    public Long getNumOfPositions() {
        return numOfPositions;
    }

    public void setNumOfPositions(Long numOfPositions) {
        this.numOfPositions = numOfPositions;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int compare(Portfolio p1, Portfolio p2) {
        return Math.round(p1.equityValue - p2.equityValue);
    }
}
