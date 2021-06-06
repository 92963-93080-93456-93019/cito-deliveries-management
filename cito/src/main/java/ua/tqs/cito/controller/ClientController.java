package ua.tqs.cito.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.tqs.cito.model.Order;
import ua.tqs.cito.service.OrderService;
import ua.tqs.cito.service.SearchService;


@Controller
@RequestMapping("/clientApi")
public class ClientController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private OrderService orderService;

	/*
	@Autowired
	private ProductService productService;

	private static final String PRODUCT_NOT_SAVED = "{\"code\" : 400, \"message\" : \"Bad parameters for product.\"}";

	@GetMapping("product/{id}")
	public ResponseEntity<List<Product>> getCarById(@PathVariable Long id){
		return new ResponseEntity<List<Product>>(productService.getAllForApp(id),HttpStatus.OK);
	}
    */

	// Client searches for products
	@GetMapping(value="{clientId}/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProductsByQuery(@PathVariable Long clientId, Long appid, String query) {
		return searchService.getProductsByQuery(clientId, appid, query);
    }

    // Client registers an order
	@PostMapping("{clientId}/order/register")
	public ResponseEntity<Object> registerOrder(@PathVariable Long clientId, Long appid, @RequestBody JsonNode payload) {
		return orderService.registerOrder(clientId, appid, payload);
	}

	// Client gets his orders
	@GetMapping("{clientId}/orders")
	public ResponseEntity<Object> getOrders(@PathVariable Long clientId, Long appid){
		System.out.println(clientId);
		return orderService.getOrders(clientId, appid);
	}

	
}
