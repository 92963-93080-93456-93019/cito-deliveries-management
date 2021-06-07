package ua.tqs.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class SearchService {

    @Autowired
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String CITO_SEARCH_API_URL = "http://127.0.0.1:8081/clientApi/{clientId}/search?query={searchQuery}&appid={apiKey}";
    private static final String CITO_PRODUCTS_API_URL = "http://127.0.0.1:8081/clientApi/{clientId}/products?appid={apiKey}";

    public ResponseEntity<Object> getProductsByQuery(Long clientId, String searchQuery) {
        URI url = new UriTemplate(CITO_SEARCH_API_URL).expand(clientId,searchQuery,1);
        return restTemplate.getForEntity(url, Object.class);
    }

    public ResponseEntity<Object> getAllProductsForClient(Long clientId) {
        URI url = new UriTemplate(CITO_PRODUCTS_API_URL).expand(clientId, 1);
        return restTemplate.getForEntity(url, Object.class);
    }
}
