package com.stock.portfolio.models;

public class MarketOrderDto {

    private MarketOrderType type;
    private String ticker;
    private Long quantity;
    private Long portId;

    public MarketOrderType getType() {
        return type;
    }

    public void setType(MarketOrderType type) {
        this.type = type;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
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
