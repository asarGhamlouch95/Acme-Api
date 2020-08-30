package com.inteloom.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AcmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeApplication.class, args);
	}

	@Bean
    ApplicationRunner init(ProductRepository repository) {
        return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/products.json");
			try {
				List<Product> products = mapper.readValue(inputStream,typeReference);
				repository.saveAll(products);
				System.out.println("Products Saved!");
			} catch (IOException e){
				System.out.println("Unable to save Products: " + e.getMessage());
			}
           
            repository.findAll().forEach(System.out::println);
        };
    }

}
