package com.devops.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.devops.crud"})
@EnableJpaRepositories(basePackages = "com.devops.crud.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.devops.crud.entities")
public class DemoApplication {

    @Bean(name = "reviewsRestTemplate")
    public RestTemplate getReviewsRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/movies/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
