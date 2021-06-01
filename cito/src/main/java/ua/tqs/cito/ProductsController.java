package ua.tqs.cito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ProductsController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping("product/register")
	public Product registerProduct(@RequestBody Product p) {
		Product pa = service.save(p);
		return pa;
	}
	
	@GetMapping("product/{id}")
	public List<Product> getCarById(@PathVariable Long id){
		return null;
	}
	
}
