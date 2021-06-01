package ua.tqs.cito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product c) {
		return productRepository.save(c);
	}
}

