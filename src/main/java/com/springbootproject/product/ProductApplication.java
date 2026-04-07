package com.springbootproject.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Category-Product REST API Documentation",
				description = "Category-Product Service REST API",
				version = "v1",
				contact = @Contact(
						name = "Ganesh Kumar",
						email = "Ganeshkumar@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Share Point URL Category-Product Service REST API",
				url = "example.com"
		)
)
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		System.out.println("YOURS PROJECTS IS RUNNING.........");

	}

}
