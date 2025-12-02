package com.microservices.orders;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.mysql.MySQLContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
// Run tests in a random port so they do not conflict with the application
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrdersMicroserviceApplicationTests {

	@ServiceConnection
	private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldCreateOrder() {
		String requestBody = """
				{
					"skuCode": "iPhone_15",
					"quantity": 1,
					"price": 1000
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("orderNumber", Matchers.notNullValue())
				.body("skuCode", Matchers.equalTo("iPhone_15"))
				.body("quantity", Matchers.equalTo(1))
				.body("price", Matchers.equalTo(1000));
	}

}
