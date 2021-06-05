package ua.tqs.cito.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.tqs.cito.model.*;
import ua.tqs.cito.repository.*;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    private static final String ORDER_NOT_FOUND = "{\"code\" : 404, \"message\" : \"Order not found.\"}";


    public Order save(Order o) {
        if (o.getAddress() == null || o.getOrderId() == null || o.getApp() == null || o.getPrice() == null || o.getEndConsumer() == null || o.getProductsList().size() == 0 || o.getRider() == null) {
            return null;
        }
        return orderRepository.save(o);
    }

    public Order getOrder(Long id) {
        return orderRepository.findByOrderId(id);
    }

    public Order parseAndSave(JsonNode payload) {
        App app1 = checkAppId(Long.parseLong(payload.path("info").path("appid").asText()));

        if (app1 == null)
            return null;

        Consumer c = checkConsumer(Long.parseLong(payload.path("info").path("userId").asText()));

        if (c == null)
            return null;

        Map<Product, Integer> prods = new HashMap<>();

        JsonNode payload_prods = payload.path("products");

        if (payload_prods.size() == 0) {
            return null;
        }

        int i = 0;
        for (JsonNode j : payload_prods) {
            Product p = checkProduct(Long.parseLong(j.get(i).path("id").asText()));
            if (p != null)
                prods.put(p, j.get(i).path("quantity").asInt());
        }

        String address = payload.path("info").path("deliveryAddress").asText();

        if (address == null)
            return null;

        return save(new Order(prods, c, STATUS.PENDING, app1, address));
    }

    public Order update(String status, Long appid, Long orderid) {
        Order orderUpdate = orderRepository.getById(orderid);

        switch (status) {
            case "GOING_TO_BUY":
                orderUpdate.setStatus(STATUS.GOING_TO_BUY);
                break;
            case "BUYING":
                orderUpdate.setStatus(STATUS.BUYING);
                break;
            case "DELIVERING":
                orderUpdate.setStatus(STATUS.DELIVERING);
                break;
            case "DELIVERED":
                orderUpdate.setStatus(STATUS.DELIVERED);
                break;
            default:
                return null;
        }

        return orderRepository.save(orderUpdate);

    }

    // Check if app exists
    private App checkAppId(Long appId) {
        return appRepository.findByAppid(appId);
    }

    // Check if product exists
    private Product checkProduct(Long id) {
        if (productRepository.findById(id).isEmpty())
            return null;
        return productRepository.findById(id).get();
    }

    // Check if consumer exists
    private Consumer checkConsumer(Long id) {
        if (consumerRepository.findByClientId(id) == null)
            return null;
        return consumerRepository.findByClientId(id);
    }

    public ResponseEntity<Object> getStatusByOrderId(Long orderId) {
        if (!checkOrder(orderId))
            return new ResponseEntity<>(ORDER_NOT_FOUND, HttpStatus.NOT_FOUND);

        Order order = orderRepository.findByOrderId(orderId);
        return new ResponseEntity<>(String.format("{\"orderId\": {0}, \"orderStatus\": {1}}", order.getOrderId(), order.getStatus()), HttpStatus.OK);
    }

    private boolean checkOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId) != null;
    }
}
