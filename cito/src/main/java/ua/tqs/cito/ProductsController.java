package ua.tqs.cito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductsController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("product/register")
	public void registerProduct(Product product) {
		
	}
}
