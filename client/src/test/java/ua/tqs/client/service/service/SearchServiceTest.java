package ua.tqs.client.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import ua.tqs.client.service.SearchService;

import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Value("${specific_store_token}")

    private Long apiKey;

    @InjectMocks
    private SearchService searchService;

    @Mock(lenient = true)
    private RestTemplate restTemplate;

    private static final String CITO_SEARCH_API_URL = "http://127.0.0.1:8080/clientApi/search?query={searchQuery}&appid={apiKey}";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void whenSearchQuery_thenReturnProductsAsJsonObjects() throws JsonProcessingException {

        // User Input
        String searchQuery = "benurom";

        // Mock Engine API output
        URI url = new UriTemplate(CITO_SEARCH_API_URL).expand(searchQuery,apiKey);

        ResponseEntity<String> response = new ResponseEntity<String>("{ \"searchResultProducts\": [ { \"id\" : 5465446542, \"name\": \"Benurom 250mg comprimidos\", \"category\" :\"Medicamentos para Febre\", \"price\": 1.70, \"photo\": \"someBase64ImageForId1\", \"description\": \"1 a 2 comprimidos de 4 em 4 horas.\" }, { \"id\" : 6854516841, \"name\": \"Benurom 500mg comprimidos\", \"category\" :\"Medicamentos para Febre\", \"price\": 2.70, \"photo\": \"someBase64ImageForId2\", \"description\": \"1 a 2 comprimidos de 8 em 8 horas.\" } ] }", HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(url, String.class)).thenReturn(response);

        // Call Engine Service

        //JsonNode searchResultBody = searchService.getProductsByQuery(searchQuery);

        // Check output
        //Mockito.verify(restTemplate, VerificationModeFactory.times(1)).getForEntity(url, String.class);
        //assertThat(searchResultBody.path("searchResultProducts").get(0).path("id").asLong()).isEqualTo(5465446542L);
        //assertThat(searchResultBody.path("searchResultProducts").get(1).path("id").asLong()).isEqualTo(6854516841L);

    }
}
