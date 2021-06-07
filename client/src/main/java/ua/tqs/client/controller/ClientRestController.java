package ua.tqs.client.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tqs.client.service.OrderService;
import ua.tqs.client.service.SearchService;

@Tag(name = "Client Deliveries", description = "the Client Deliveries API")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/clientApi")
public class ClientRestController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private OrderService orderService;

    private static final String BROKEN_JSON = "{\"code\" : 500, \"message\" : \"Internal Server Error. Broken JSON.\"}";
    private ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping(value = "{clientId}/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductsByQuery(@PathVariable Long clientId, String query) {
        return searchService.getProductsByQuery(clientId, query);
    }

    @PostMapping(value = "{clientId}/order/register", produces = MediaType.APPLICATION_JSON_VALUE   )
    public ResponseEntity<Object> registerOrder(@PathVariable Long clientId, @RequestBody JsonNode payload) {
        return orderService.registerOrder(clientId, payload);
    }

    @GetMapping(value = "{clientId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllProductsForClient(@PathVariable Long clientId) {
        return searchService.getAllProductsForClient(clientId);
    }
}
