package com.gharibi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.gharibi")
@EnableJpaRepositories("com.gharibi")
@EntityScan("com.gharibi.web.model")
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{
				AuthApplication.class, SecurityConfig.class, WebMvcConfig.class}, args);
	}
}
