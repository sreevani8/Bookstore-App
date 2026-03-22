package com.mulit.bookstore.orders.web.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import com.mulit.bookstore.orders.AbstractIT;
import com.mulit.bookstore.orders.TestSecurityConfig;
import com.mulit.bookstore.orders.clients.catalog.Product;
import com.mulit.bookstore.orders.clients.catalog.ProductServiceClient;
import com.mulit.bookstore.orders.testdata.TestDataFactory;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
// @Sql("/test-orders.sql")
class OrderControllerTests extends AbstractIT {

    @MockBean
    private ProductServiceClient productServiceClient;

    private BigDecimal price = new BigDecimal("100");

    @BeforeEach
    void setupMock() {
        Product product = new Product("P100", "Book", "Sample book", "img.png", price);

        Mockito.when(productServiceClient.getProductByCode("P100")).thenReturn(java.util.Optional.of(product));
    }

    @Nested
    class CreateOrderTests {

        @Test
        void shouldCreateOrderSuccessfully() {

            var payload =
                    """
                    {
                      "items": [
                        {
                          "code": "P100",
                          "name": "Product 1",
                          "price": 100,
                          "quantity": 1
                        }
                      ],
                      "customer": {
                        "name": "Vani",
                        "email": "sreevani@gmail.com",
                        "phone": "9999999999"
                      },
                      "deliveryAddress": {
                        "addressLine1": "HNO 149",
                        "addressLine2": "ECIL",
                        "city": "Hyderabad",
                        "state": "Telangana",
                        "zipCode": "500062",
                        "country": "India"
                      }
                    }
                    """;

            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .log()
                    .all()
                    .statusCode(201)
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {

            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();

            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    /*@Nested
    class GetOrdersTests {
        @Test
        void shouldGetOrdersSuccessfully() {
            List<OrderSummary> orderSummaries = given().when()
                    .get("/api/orders")
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(new TypeRef<>() {});

            assertThat(orderSummaries).hasSize(2);
        }
    }

    @Nested
    class GetOrderByOrderNumberTests {
        String orderNumber = "order-123";

        @Test
        void shouldGetOrderSuccessfully() {
            given().when()
                    .get("/api/orders/{orderNumber}", orderNumber)
                    .then()
                    .statusCode(200)
                    .body("orderNumber", is(orderNumber))
                    .body("items.size()", is(2));
        }
    }*/
}
