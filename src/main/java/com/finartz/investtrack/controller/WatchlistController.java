package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.response.WatchlistResponse;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.service.UserService;
import com.finartz.investtrack.service.WatchlistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<WatchlistResponse> createWatchlist(
            HttpServletRequest request,
            @RequestParam String name) {
        Integer uid = (Integer) request.getAttribute("uid");
        User user = userService.getUserById(uid);
        return ResponseEntity.ok(watchlistService.createWatchlist(user, name));
    }

    @GetMapping
    public ResponseEntity<List<WatchlistResponse>> getUserWatchlists(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        User user = userService.getUserById(uid);
        return ResponseEntity.ok(watchlistService.getUserWatchlist(user));
    }

    @PostMapping("/{watchlistId}/stock/{symbol}")
    public ResponseEntity<WatchlistResponse> addStockToWatchlist(
            @PathVariable int watchlistId,
            @PathVariable String symbol
    ) {
        return ResponseEntity.ok(watchlistService.addStockToWatchlist(watchlistId, symbol));
    }

    @DeleteMapping("/{watchlistId}/stock/{symbol}")
    public ResponseEntity<WatchlistResponse> removeStockFromWatchlist(
            @PathVariable int watchlistId,
            @PathVariable String symbol
    ) {
        return ResponseEntity.ok(watchlistService.removeStockFromWatchlist(watchlistId, symbol));
    }

    @PutMapping("/{watchlistId}")
    public ResponseEntity<WatchlistResponse> updateWatchlist(
            @PathVariable int watchlistId,
            @RequestParam String name
    ) {
        return ResponseEntity.ok(watchlistService.renameWatchlist(watchlistId, name));
    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<WatchlistResponse> getWatchlistDetails(@PathVariable int watchlistId) {
        return ResponseEntity.ok(watchlistService.getWatchlistDetails(watchlistId));
    }

    @DeleteMapping("/{watchlistId}")
    public ResponseEntity<Void> deleteWatchlist(@PathVariable int watchlistId) {
        watchlistService.deleteWatchlist(watchlistId);
        return ResponseEntity.ok().build();
    }

}
