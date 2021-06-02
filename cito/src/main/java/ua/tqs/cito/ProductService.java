package ua.tqs.cito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product p) {
		if (p.getName() == null || p.getAppId() == null || p.getDescription() == null || p.getImage() == null || p.getPrice() == null || p.getCategory() == null) {
			return null;
		}
		return productRepository.save(p);
	}
	
	public List<Product> getAllForApp(Long l){
		return productRepository.findByAppId(l);
	}
}

