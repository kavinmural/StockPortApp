package com.stock.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.stock.portfolio.repositories"})
public class StockPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPortfolioApplication.class, args);
	}

}
