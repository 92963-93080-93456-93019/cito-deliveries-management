package ua.tqs.cito;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.tqs.cito.controller.ClientController;
import ua.tqs.cito.model.App;
import ua.tqs.cito.model.Product;
import ua.tqs.cito.service.ProductService;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

@WebMvcTest(ClientController.class)
public class ProductControllerTests {
	
		@Autowired
	    private MockMvc mvc;

	    @MockBean
	    private ProductService service;
	    
	    @Test
	    public void whenPostinProductReturnIt( ) throws Exception {
	    	App app1 = new App(1L,2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
	    	Product p = new Product("Benuron","Farmácia Geral","Great for small pains!",app1,13.00,"somebase64string");
	    	
	    	RestAssuredMockMvc.mockMvc(mvc);

	        when( service.save(any()) ).thenReturn(p);

	        
	        RestAssuredMockMvc.given().header("Content-type","application/json").body("{\"name\":\"Benuron\",\"category\":\"Farmácia Geral\",\"description\":\"Great for small pains!\",\"appId\":1,\"price\":13.00,\"image\":\"somebase64string\"}").post("http://localhost:8000/product/register").then().statusCode(201).body("name",equalTo("Benuron"));
	    }
	    
	    @Test
	    public void whenPostInvalidProductReturnBadRequest( ) throws Exception {
	    	
	    	RestAssuredMockMvc.mockMvc(mvc);

	        when( service.save(any()) ).thenReturn(null);

	        RestAssuredMockMvc.given().header("Content-type","application/json").body("{\"name\":null,\"category\":\"Farmácia Geral\",\"description\":null,\"appId\":1,\"price\":13.00,\"image\":\"somebase64string\"}").post("http://localhost:8000/product/register").then().statusCode(400);
	    }
	    

	    @Test
	    public void whenGetAllProductsFromAppIdReturnThem( ) throws Exception {
	    	ArrayList<Product> ar = new ArrayList<>();
			App app1 = new App(1L,2.40, "Farmácia Armando", "Rua do Cabeço", "8-19h", "someBase&4Image");
	    	Product p1 = new Product("Benuron","Farmácia Geral","Great for small pains!",app1,13.00,"somebase64string");
	    	Product p2 = new Product("Brufen","Farmácia Geral","Great for small pains!",app1,5.00,"somebase64string");
	    	ar.add(p1);
	    	ar.add(p2);
	    	
	    	RestAssuredMockMvc.mockMvc(mvc);

	        when( service.getAllForApp(any()) ).thenReturn(ar);

	        
	        RestAssuredMockMvc.given().get("http://localhost:8000/product/1").then().statusCode(200).body("[0].name",equalTo("Benuron")).body("[1].name",equalTo("Brufen"));

	    }

}
