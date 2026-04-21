package com.mulit.bookstore.orders.web.controllers;

import com.mulit.bookstore.orders.AbstractIT;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-orders.sql")
class OrderControllerTests extends AbstractIT {

    @Nested
    class CreateOrderTests {

         @Test
        void createOrder() {
            System.out.println("method stubbin");
        }
       /* @Test
        void shouldCreateOrderSuccessfully() {cd 
            mockGetProductByCode("P110", "Product 1", new BigDecimal("25.50"));
            var payload =
                    """
                            {
                                       "customer": {
                                         "name": "Sreevani",
                                         "email": "sreevanidumpala@gmail.com",
                                         "phone": "1234567890"
                                       },
                                       "deliveryAddress": {
                                         "addressLine1": "G.R.Reddy Nagar",
                                         "addressLine2": "Kapra",
                                         "city": "Hyderabad",
                                         "state": "Telangana",
                                         "zipCode": "500062",
                                         "country": "India"
                                       },
                                       "items": [
                                         {
                                           "code": "P110",
                                           "name": "Product 1",
                                           "price": 25.50,
                                           "quantity": 1
                                         }
                                       ]
                                     }
                        """;
            given().contentType(ContentType.JSON)
                    // .header("Authorization", "Bearer " + getToken())
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    // .header("Authorization", "Bearer " + getToken())
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    class GetOrdersTests {
        @Test
        void shouldGetOrdersSuccessfully() {
            List<OrderSummary> orderSummaries = given().when()
                    // .header("Authorization", "Bearer " + getToken())
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
                    // .header("Authorization", "Bearer " + getToken())
                    .get("/api/orders/{orderNumber}", orderNumber)
                    .then()
                    .statusCode(200)
                    .body("orderNumber", is(orderNumber))
                    .body("items.size()", is(2));
        }*/
    }
}
