package com.microservices.inventory;

import org.springframework.boot.SpringApplication;

public class TestInventoryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryMicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
