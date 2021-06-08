package ua.tqs.cito.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.tqs.cito.CitoApplication;
import ua.tqs.cito.controller.ClientController;
import ua.tqs.cito.model.*;
import ua.tqs.cito.repository.AppRepository;
import ua.tqs.cito.repository.ConsumerRepository;
import ua.tqs.cito.repository.ProductRepository;
import ua.tqs.cito.service.OrderService;
import ua.tqs.cito.utils.HttpResponses;
import ua.tqs.cito.utils.OrderStatusEnum;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CitoApplication.class)
@AutoConfigureMockMvc
public class ClientControllerITest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private AppRepository appRepository;

    @MockBean
    private ConsumerRepository consumerRepository;

    @MockBean
    private ProductRepository productRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void whenRegisterOrder_thenReturnCreated( ) throws Exception {
        //Rider r1 = new Rider(1L,"João","Laranjo","919234527",0);
        App app1 = new App(1L,2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
        Consumer c1 = new Consumer(1L,"Tiago","Oliveira","912318734","Rua do Corvo 455",app1);
        Product p1 = new Product(1L,"Benuron","Farmácia Geral","Great for small pains!",app1,13.00,"somebase64string");
        Product p2 = new Product(2L,"Brufen","Farmácia Geral","Great for small pains!",app1,5.00,"somebase64string");

        List<ProductListItem> products = new ArrayList<>();
        ProductListItem pli1 = new ProductListItem(p1,2);
        ProductListItem pli2 = new ProductListItem(p2,3);
        products.add(pli1);
        products.add(pli2);

        Order o1 = new Order(products,c1, OrderStatusEnum.PENDING,app1,"Rua do Corvo 455");
        //o1.setRider(r1);

        RestAssuredMockMvc.mockMvc(mvc);

        String response = "{\"products\":[{\"id\":\""+p1.getId()+"\",\"quantity\":2},{\"id\":\""+p2.getId()+"\",\"quantity\":3}],\"info\":{\"appid\":\""+c1.getApp().getAppid()+"\",\"userId\":\""+c1.getConsumerId()+"\",\"deliveryAddress\":\""+o1.getAddress()+"\",\"deliverInPerson\":true}}";

        when(orderService.registerOrder(c1.getConsumerId(), app1.getAppid(), mapper.readTree(response)) ).thenReturn(new ResponseEntity<>(mapper.readTree(HttpResponses.ORDER_SAVED), HttpStatus.CREATED));

        RestAssuredMockMvc
                .given()
                    .header("Content-type","application/json")
                    .body(response).post("http://localhost:8080/clientApi/1/order/register?appid=1")
                .then()
                    .assertThat()
                        .and().body("code",equalTo(201))
                        .and().body("message",equalTo("Order saved."));

        Mockito.verify(orderService, VerificationModeFactory.times(1)).registerOrder(c1.getConsumerId(), app1.getAppid(),mapper.readTree(response));
    }

    /*@Test
    public void whenGetAllProductsFromAppIdReturnThem( ) throws Exception {
        ArrayList<Product> ar = new ArrayList<>();
        App app1 = new App(1L,2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
        Product p1 = new Product("Benuron","Farmácia Geral","Great for small pains!",app1,13.00,"somebase64string");
        Product p2 = new Product("Brufen","Farmácia Geral","Great for small pains!",app1,5.00,"somebase64string");
        ar.add(p1);
        ar.add(p2);

        RestAssuredMockMvc.mockMvc(mvc);

        when( productService.getAllProducts(any(), any())).thenReturn(ar);


        RestAssuredMockMvc
                .given()
                .get("http://localhost:8000/product/1")
                .then()
                .statusCode(200)
                .body("[0].name",equalTo("Benuron"))
                .body("[1].name",equalTo("Brufen"));

    }*/

    /*@Test
    public void whenGetOrders_thenReturnOrders( ) throws Exception {
        Rider r1 = new Rider(1L,"João","Laranjo","919234527",0);
        App app1 = new App(1L,2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase64Image");
        Consumer c1 = new Consumer(1L,"Tiago","Oliveira","912318734","Rua do Corvo 455",app1);
        Product p1 = new Product(1L,"Benuron","Farmácia Geral","Great for small pains!",app1,13.00,"somebase64string");
        Product p2 = new Product(2L,"Brufen","Farmácia Geral","Great for small pains!",app1,5.00,"somebase64string");

        Map<Product,Integer> products1 = new HashMap<>();
        products1.put(p1,2);
        products1.put(p2,3);

        Map<Product,Integer> products2 = new HashMap<>();
        products2.put(p1,5);

        Order o1 = new Order(products1,c1, OrderStatusEnum.PENDING,app1,"Rua do Corvo 455");
        Order o2 = new Order(products2,c1, OrderStatusEnum.PENDING,app1,"Rua do Corvo 455");

        o1.setRider(r1);
        List<Order> l = new ArrayList<>();
        l.add(o1);
        l.add(o2);
        c1.setOrders(l);

        RestAssuredMockMvc.mockMvc(mvc);

        when(orderService.getOrders(c1.getConsumerId(), app1.getAppid()) ).thenReturn(new ResponseEntity<>(l, HttpStatus.NOT_FOUND));
        Mockito.verify(orderService, VerificationModeFactory.times(1)).getOrders(c1.getConsumerId(), app1.getAppid());
        RestAssuredMockMvc.get("http://127.0.0.1:8080/clientApi/1/orders?appid=1").then().assertThat().statusCode(404);
    }*/

    /*@Test
    public void whenGetingOrder_thenReturnUpdated( ) throws Exception {
        Rider r1 = new Rider(1L, "João", "Laranjo", "919234527", 0);
        App app1 = new App(1L, 2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
        Consumer c1 = new Consumer(1L, "Tiago", "Oliveira", "912318734", "Rua do Corvo 455", app1);
        Product p1 = new Product(1L, "Benuron", "Farmácia Geral", "Great for small pains!", app1, 13.00, "somebase64string");
        Product p2 = new Product(2L, "Brufen", "Farmácia Geral", "Great for small pains!", app1, 5.00, "somebase64string");

        Map<Product, Integer> products = new HashMap<>();
        products.put(p1, 2);
        products.put(p2, 3);

        Order o1 = new Order(products, c1, OrderStatusEnum.PENDING, app1, "Rua do Corvo 455");
        o1.setRider(r1);

        RestAssuredMockMvc.mockMvc(mvc);

        when(orderService.updateOrder(any(), any(), any(), any())).thenReturn(new ResponseEntity<>(mapper.readTree(HttpResponses.ORDER_UPDATED), HttpStatus.OK));

        RestAssuredMockMvc.get("http://localhost:8000/order/update").then().assertThat().statusCode(200);
    }*/


}
