package ua.tqs.cito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ProductsController {
	
	@Autowired
	private ProductService service;
	
	private static final String PRODUCT_NOT_SAVED = "{\"code\" : 400, \"message\" : \"Bad parameters for product.\"}";
	
	@PostMapping("product/register")
	public ResponseEntity<Object> registerProduct(@RequestBody Product p) {
		Product prod = service.save(p);
		if (prod == null) {
			return new ResponseEntity<Object>(PRODUCT_NOT_SAVED,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(prod,HttpStatus.CREATED);
	}
	
	@GetMapping("product/{id}")
	public ResponseEntity<List<Product>> getCarById(@PathVariable Long id){
		return new ResponseEntity<List<Product>>(service.getAllForApp(id),HttpStatus.OK);
	}
	
}
