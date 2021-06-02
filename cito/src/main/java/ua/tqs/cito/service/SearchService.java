package ua.tqs.cito.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.tqs.cito.repository.AppRepository;
import ua.tqs.cito.model.Product;
import ua.tqs.cito.repository.ProductRepository;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AppRepository appRepository;

    private static final String INVALID_CLIENT_API = "{\"code\" : 403, \"message\" : \"Invalid client API.\"}";
    private ObjectMapper objectMapper = new ObjectMapper();

    // Client gets products when searching by name
    public ResponseEntity<Object> getProductsBySearchQuery(String query, Long appid) {
        if (!checkAppId(appid))
            return new ResponseEntity<>(INVALID_CLIENT_API, HttpStatus.FORBIDDEN);
        List<Product> products = productRepository.findByNameLike("%" + query + "%");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Check if client exists
    private boolean checkAppId(Long appId) {
        return appRepository.findByAppid(appId) != null;
    }
}


