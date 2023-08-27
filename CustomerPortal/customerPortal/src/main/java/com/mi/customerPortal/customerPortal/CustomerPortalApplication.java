package com.mi.customerPortal.customerPortal;

import ch.qos.logback.core.CoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CustomerPortalApplication {


	public static void main(String[] args) {
		SpringApplication.run(CustomerPortalApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			System.out.println("Hi Lilac");
		};
	}
}
