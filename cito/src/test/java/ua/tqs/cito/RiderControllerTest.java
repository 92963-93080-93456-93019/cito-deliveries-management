package ua.tqs.cito;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.tqs.cito.controller.ClientController;
import ua.tqs.cito.model.*;
import ua.tqs.cito.service.OrderService;
import ua.tqs.cito.service.ProductService;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebMvcTest(ClientController.class)
public class RiderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService service;

    @Test
    public void whenPostinOrderReturnIt() throws Exception {
        Rider r1 = new Rider(1L, "João", "Laranjo", "919234527", 0);
        App app1 = new App(1L, 2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
        Consumer c1 = new Consumer(1L, "Tiago", "Oliveira", "912318734", "Rua do Corvo 455", app1);
        Product p1 = new Product(1L, "Benuron", "Farmácia Geral", "Great for small pains!", app1, 13.00, "somebase64string");
        Product p2 = new Product(2L, "Brufen", "Farmácia Geral", "Great for small pains!", app1, 5.00, "somebase64string");

        Map<Product, Integer> products = new HashMap<>();
        products.put(p1, 2);
        products.put(p2, 3);

        Order o1 = new Order(products, c1, STATUS.PENDING, app1, "Rua do Corvo 455");
        o1.setRider(r1);

        RestAssuredMockMvc.mockMvc(mvc);

        when(service.save(any())).thenReturn(o1);

        RestAssuredMockMvc.given().header("Content-type", "application/json").body("data:\n" +
                "{\n" +
                "    \"products\": [\n" +
                "        {\n" +
                "                \"id\" : \"" + p1.getId() + "\",\n" +
                "                \"name\": \"" + p1.getName() + "\",\n" +
                "                \"categorty\" :\"" + p1.getCategory() + "\",\n" +
                "                \"price\": \"" + p1.getPrice() + "\",\n" +
                "                \"photo\": \"" + p1.getImage() + "\",\n" +
                "                \"description\": \"" + p1.getDescription() + "\",\n" +
                "                \"quantity\": \"" + products.get(p1) + "\"\n" +
                "        },\n" +
                "        {\n" +
                "                \"id\" : \"" + p2.getId() + "\",\n" +
                "                \"name\": \"" + p2.getName() + "\",\n" +
                "                \"categorty\" :\"" + p2.getCategory() + "\",\n" +
                "                \"price\": \"" + p2.getPrice() + "\",\n" +
                "                \"photo\": \"" + p2.getImage() + "\",\n" +
                "                \"description\": \"" + p2.getDescription() + "\",\n" +
                "                \"quantity\": \"" + products.get(p2) + "\"\n" +
                "        }\n" +
                "    ],\n" +
                "\n" +
                "    \"info\": {\n" +
                "        \"appid\": \"" + c1.getApp().getAppid() + "\",\n" +
                "        \"userId\": \"" + c1.getClientId() + "\",\n" +
                "        \"deliveryAddress\": \"" + o1.getAddress() + "\",\n" +
                "        \"deliverInPerson\": true\n" +
                "    }\n" +
                "}").post("http://localhost:8000/order/register").then().assertThat().statusCode(201).and().body(String.valueOf(products.size()), equalTo(2)).and().body("userId", equalTo(c1.getClientId())).and().body("deliveryAddress", equalTo(o1.getAddress()));
    }

    @Test
    public void whenUpdatingOrderReturnOk() throws Exception {
        Rider r1 = new Rider(1L, "João", "Laranjo", "919234527", 0);
        App app1 = new App(1L, 2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
        Consumer c1 = new Consumer(1L, "Tiago", "Oliveira", "912318734", "Rua do Corvo 455", app1);
        Product p1 = new Product(1L, "Benuron", "Farmácia Geral", "Great for small pains!", app1, 13.00, "somebase64string");
        Product p2 = new Product(2L, "Brufen", "Farmácia Geral", "Great for small pains!", app1, 5.00, "somebase64string");

        Map<Product, Integer> products = new HashMap<>();
        products.put(p1, 2);
        products.put(p2, 3);

        Order o1 = new Order(products, c1, STATUS.PENDING, app1, "Rua do Corvo 455");
        o1.setRider(r1);

        RestAssuredMockMvc.mockMvc(mvc);

        when(service.getOrder(any())).thenReturn(o1);


        RestAssuredMockMvc.get("http://localhost:8000/order/" + o1.getOrderId()).then().assertThat().statusCode(200);
    }

    @Test
    public void whenGetingOrderReturnIt() throws Exception {
        Rider r1 = new Rider(1L, "João", "Laranjo", "919234527", 0);
        App app1 = new App(1L, 2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
        Consumer c1 = new Consumer(1L, "Tiago", "Oliveira", "912318734", "Rua do Corvo 455", app1);
        Product p1 = new Product(1L, "Benuron", "Farmácia Geral", "Great for small pains!", app1, 13.00, "somebase64string");
        Product p2 = new Product(2L, "Brufen", "Farmácia Geral", "Great for small pains!", app1, 5.00, "somebase64string");

        Map<Product, Integer> products = new HashMap<>();
        products.put(p1, 2);
        products.put(p2, 3);

        Order o1 = new Order(products, c1, STATUS.PENDING, app1, "Rua do Corvo 455");
        o1.setRider(r1);

        RestAssuredMockMvc.mockMvc(mvc);

        when(service.update(any(), any(), any())).thenReturn(o1);


        RestAssuredMockMvc.get("http://localhost:8000/order/update").then().assertThat().statusCode(200);
    }


}
