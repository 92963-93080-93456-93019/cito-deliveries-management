package ua.tqs.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock(lenient = true)
    private RestTemplate restTemplate;

    private static final String CITO_ORDER_STATUS_API_URL = "http://127.0.0.1:8080/cito/order/status?order_id={order_id}";

    @Test
    void whenGetOrderStatus_thenReturnStatusAsJsonObject() {
        // User's order
        Long orderId = Long.valueOf(1);

        // Mock Engine API output
        URI url = new UriTemplate(CITO_ORDER_STATUS_API_URL).expand(orderId);

        String orderStatus = "DELIVERED";

        ResponseEntity<String> response = new ResponseEntity<>(String.format("{\"orderId\": \"%x\", \"orderStatus\": \"%s\"}", orderId, orderStatus), HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(url, String.class)).thenReturn(response);

        // Call Engine Service
        JsonNode searchResultBody = orderService.getOrderStatusByOrderId(orderId);

        // Check output
        Mockito.verify(restTemplate, VerificationModeFactory.times(1)).getForEntity(url, String.class);
        assertThat(searchResultBody.path("orderId").asLong()).isEqualTo(orderId);
        assertThat(searchResultBody.path("orderStatus").asText()).isEqualTo(orderStatus);
    }

    // testar estado que não exista ??

}