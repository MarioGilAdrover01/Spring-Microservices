package com.microservices.inventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

import io.restassured.RestAssured;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryMicroserviceApplicationTests {

	@Container
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateOrder() {
		Boolean positiveResponse = RestAssured.given()
				.when()
				.get("/api/inventory?skuCode=iphone_13&quantity=100")
				.then()
				.statusCode(200)
				.extract().as(Boolean.class);
		assertTrue(positiveResponse);

		Boolean negativeResponse = RestAssured.given()
				.when()
				.get("/api/inventory?skuCode=iphone_13&quantity=101")
				.then()
				.statusCode(200)
				.extract().as(Boolean.class);
		assertFalse(negativeResponse);
	}
}
