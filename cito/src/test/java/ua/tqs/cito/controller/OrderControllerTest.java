package ua.tqs.cito.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.cito.model.*;
import ua.tqs.cito.service.OrderService;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService service;

    @Test
    public void whenGetOrderStatus_thenReturnStatus() {
        Order order = new Order();
        order.setStatus(STATUS.DELIVERED);

        RestAssuredMockMvc.mockMvc(mvc);

        when(service.getStatusByOrderId(any())).thenReturn(new ResponseEntity<>(String.format("{\"orderId\": {0}, \"orderStatus\": {1}}", order.getOrderId(), order.getStatus()), HttpStatus.OK));

        RestAssuredMockMvc.get("http://127.0.0.1:8000/order/status?order_id=1").then().assertThat().statusCode(200);
        RestAssuredMockMvc.given().header("Content-type", "application/json").get("http://127.0.0.1:8000/order/status?order_id=1").then().assertThat().body("orderId", equalTo("1")).body("orderStatus", equalTo("DELIVERED"));
    }

}