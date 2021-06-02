package ua.tqs.cito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.tqs.cito.model.Product;
import ua.tqs.cito.service.ProductService;
import ua.tqs.cito.service.SearchService;


@Controller
@RequestMapping("/clientApi")
public class ClientController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private SearchService searchService;
	
	private static final String PRODUCT_NOT_SAVED = "{\"code\" : 400, \"message\" : \"Bad parameters for product.\"}";
	
	@PostMapping("product/register")
	public ResponseEntity<Object> registerProduct(@RequestBody Product p) {
		Product prod = productService.save(p);
		if (prod == null) {
			return new ResponseEntity<Object>(PRODUCT_NOT_SAVED,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(prod,HttpStatus.CREATED);
	}
	
	@GetMapping("product/{id}")
	public ResponseEntity<List<Product>> getCarById(@PathVariable Long id){
		return new ResponseEntity<List<Product>>(productService.getAllForApp(id),HttpStatus.OK);
	}

	// Client search products endpoint
	@GetMapping(value="search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProductsForSearchQuery(String query, Long appid) {
		ResponseEntity<Object> response = searchService.getProductsBySearchQuery(query, appid);
		return response;
	}
	
}
