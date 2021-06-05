package ua.tqs.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String CITO_ORDER_STATUS_API_URL = "http://127.0.0.1:8080/cito/order/status?order_id={order_id}";

    public JsonNode getOrderStatusByOrderId(Long orderId) {
        URI url = new UriTemplate(CITO_ORDER_STATUS_API_URL).expand(orderId);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        try {
            return objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
