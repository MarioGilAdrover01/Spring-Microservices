package com.microservices.orders;

import org.springframework.boot.SpringApplication;

public class TestOrdersMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrdersMicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
