package com.iksen.chessCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = "com.iksen.chessCore.model")
public class ChessCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessCoreApplication.class, args);
	}
	@Bean(name = "transactionManager")
	public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
