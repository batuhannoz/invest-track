package com.finartz.investtrack.controller.response;

import com.finartz.investtrack.model.Stock;
import java.util.Date;
import java.util.List;

public class WatchlistResponse {
    private int id;
    private String name;
    private List<Stock> stocks;
    private Date createdAt;
    private Date updatedAt;

    public static WatchlistResponse fromEntity(com.finartz.investtrack.model.Watchlist watchlist) {
        WatchlistResponse dto = new WatchlistResponse();
        dto.setId(watchlist.getId());
        dto.setName(watchlist.getName());
        dto.setStocks(watchlist.getStock());
        dto.setCreatedAt(watchlist.getCreatedAt());
        dto.setUpdatedAt(watchlist.getUpdatedAt());
        return dto;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Stock> getStocks() { return stocks; }
    public void setStocks(List<Stock> stocks) { this.stocks = stocks; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
