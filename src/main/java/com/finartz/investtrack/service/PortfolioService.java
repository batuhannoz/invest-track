package com.finartz.investtrack.service;

import com.finartz.investtrack.model.Portfolio;
import com.finartz.investtrack.model.User;
import com.finartz.investtrack.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    private final UserService userService;

    public PortfolioService(PortfolioRepository portfolioRepository, UserService userService) {
        this.portfolioRepository = portfolioRepository;
        this.userService = userService;
    }

    public List<Portfolio> getUserPortfolio(int userId) {
        User user = userService.getUserById(userId);
        List<Portfolio> portfolios = portfolioRepository.findByUser(user);

        return portfolios.stream()
                .filter(portfolio -> portfolio.getQuantity().compareTo(BigDecimal.ZERO) != 0)
                .collect(Collectors.toList());
    }

}
