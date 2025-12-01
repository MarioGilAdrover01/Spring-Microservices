package com.microservices.products;

import org.springframework.boot.SpringApplication;

public class TestProductsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductsMicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
