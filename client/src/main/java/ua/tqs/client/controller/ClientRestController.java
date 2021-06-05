package ua.tqs.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import ua.tqs.client.service.SearchService;

import java.net.URI;

@Tag(name = "Client Deliveries", description = "the Client Deliveries API")
@RestController
@RequestMapping("/client/clientApi")
public class ClientRestController {

    @Autowired
    private SearchService searchService;

    private static final String BROKEN_JSON = "{\"code\" : 500, \"message\" : \"Internal Server Error. Broken JSON.\"}";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Operation(summary = "Show current day air quality based on given address.")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductsForSearchParams(String query) throws JsonProcessingException {

        //LOGGER.log(Level.INFO,"New GET /today request.");

        JsonNode searchResult = searchService.getProductsBySearchQuery(query);

        if (searchResult == null){
            return new ResponseEntity<>(BROKEN_JSON, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
