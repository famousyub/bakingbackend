package com.webapp.bankingportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching // Add this annotation to enable caching support
@EnableAsync
@EnableJpaRepositories
@ComponentScan
public class BankingportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingportalApplication.class, args);
	}

}
