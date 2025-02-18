package com.finartz.investtrack.service;

import com.finartz.investtrack.controller.response.WatchlistResponse;
import com.finartz.investtrack.model.Stock;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.model.Watchlist;
import com.finartz.investtrack.repository.StockRepository;
import com.finartz.investtrack.repository.WatchlistRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private ObjectMapper objectMapper;


    public WatchlistResponse createWatchlist(User user, String name) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setName(name);
        watchlist.setStock(new ArrayList<>());
        return WatchlistResponse.fromEntity(watchlistRepository.save(watchlist));
    }

    @Transactional
    public WatchlistResponse addStockToWatchlist(int watchlistId, String stockSymbol) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new EntityNotFoundException("Watchlist not found"));

        Stock stock = stockRepository.findBySymbol(stockSymbol.toUpperCase(Locale.US))
                .orElseGet(() -> createStockFromAPI(stockSymbol));

        if (!watchlist.getStock().contains(stock)) {
            watchlist.getStock().add(stock);
            return WatchlistResponse.fromEntity(watchlistRepository.save(watchlist));
        }
        return WatchlistResponse.fromEntity(watchlist);
    }

    @Transactional
    public WatchlistResponse removeStockFromWatchlist(int watchlistId, String stockSymbol) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new EntityNotFoundException("Watchlist not found"));

        Stock stock = stockRepository.findBySymbol(stockSymbol.toUpperCase(Locale.US))
                .orElseThrow(() -> new EntityNotFoundException("Stock not found"));

        watchlist.getStock().remove(stock);
        return WatchlistResponse.fromEntity(watchlistRepository.save(watchlist));
    }

    @Transactional
    public WatchlistResponse renameWatchlist(int watchlistId, String newName) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new EntityNotFoundException("Watchlist not found"));

        watchlist.setName(newName);
        return WatchlistResponse.fromEntity(watchlistRepository.save(watchlist));
    }

    private Stock createStockFromAPI(String symbol) {
        try {
            String companyInfo = financeService.getCompanyInfo(symbol);
            JsonNode jsonNode = objectMapper.readTree(companyInfo);
            
            if (jsonNode.isArray() && jsonNode.size() > 0) {
                JsonNode companyData = jsonNode.get(0);
                
                Stock stock = new Stock();
                stock.setSymbol(companyData.get("symbol").asText());
                stock.setName(companyData.get("companyName").asText());
                stock.setExchange(companyData.get("exchange").asText());
                stock.setImageUrl(companyData.get("image").asText());
                
                return stockRepository.save(stock);
            }
            throw new RuntimeException("Company information not found");
        } catch (Exception e) {
            throw new RuntimeException("Error creating stock: " + e.getMessage());
        }
    }

    public List<WatchlistResponse> getUserWatchlist(User user) {
        return watchlistRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Watchlist not found"))
                .stream()
                .map(WatchlistResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteWatchlist(int watchlistId) {
        watchlistRepository.deleteById(watchlistId);
    }

    public WatchlistResponse getWatchlistDetails(int watchlistId) {
        return WatchlistResponse.fromEntity(
                watchlistRepository.findById(watchlistId)
                        .orElseThrow(() -> new EntityNotFoundException("Watchlist not found"))
        );
    }

}
