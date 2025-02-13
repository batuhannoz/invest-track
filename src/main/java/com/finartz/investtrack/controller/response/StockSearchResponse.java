package com.finartz.investtrack.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class StockSearchResponse {

    public static class Stock {
        private String symbol;
        private String name;
        private String type;
        private String region;
        private String marketOpen;
        private String marketClose;
        private String timezone;
        private String currency;
        private String matchScore;

        @JsonProperty("1. symbol")
        public void setSymbol(String symbol) { this.symbol = symbol; }
        @JsonProperty("symbol")
        public String getSymbol() { return symbol; }

        @JsonProperty("2. name")
        public void setName(String name) { this.name = name; }
        @JsonProperty("name")
        public String getName() { return name; }

        @JsonProperty("3. type")
        public void setType(String type) { this.type = type; }
        @JsonProperty("type")
        public String getType() { return type; }

        @JsonProperty("4. region")
        public void setRegion(String region) { this.region = region; }
        @JsonProperty("region")
        public String getRegion() { return region; }

        @JsonProperty("5. marketOpen")
        public void setMarketOpen(String marketOpen) { this.marketOpen = marketOpen; }
        @JsonProperty("marketOpen")
        public String getMarketOpen() { return marketOpen; }

        @JsonProperty("6. marketClose")
        public void setMarketClose(String marketClose) { this.marketClose = marketClose; }
        @JsonProperty("marketClose")
        public String getMarketClose() { return marketClose; }

        @JsonProperty("7. timezone")
        public void setTimezone(String timezone) { this.timezone = timezone; }
        @JsonProperty("timezone")
        public String getTimezone() { return timezone; }

        @JsonProperty("8. currency")
        public void setCurrency(String currency) { this.currency = currency; }
        @JsonProperty("currency")
        public String getCurrency() { return currency; }

        @JsonProperty("9. matchScore")
        public void setMatchScore(String matchScore) { this.matchScore = matchScore; }
        @JsonProperty("matchScore")
        public String getMatchScore() { return matchScore; }

        @Override
        public String toString() {
            return "StockMatch{" +
                    "symbol='" + symbol + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", region='" + region + '\'' +
                    ", marketOpen='" + marketOpen + '\'' +
                    ", marketClose='" + marketClose + '\'' +
                    ", timezone='" + timezone + '\'' +
                    ", currency='" + currency + '\'' +
                    ", matchScore='" + matchScore + '\'' +
                    '}';
        }
    }

    @JsonProperty("bestMatches")
    private List<Stock> bestMatches;

    public List<Stock> getBestMatches() {
        return bestMatches;
    }

    public void setBestMatches(List<Stock> bestMatches) {
        this.bestMatches = bestMatches;
    }
    
}