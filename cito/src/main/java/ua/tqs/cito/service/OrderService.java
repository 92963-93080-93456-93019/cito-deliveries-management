package ua.tqs.cito.service;

import java.util.ArrayList;
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
import ua.tqs.cito.utils.HttpResponses;
import ua.tqs.cito.utils.OrderStatusEnum;

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

    @Autowired
    private RiderRepository riderRepository;

    public boolean save(Order o) {
        if (o.getAddress()==null || o.getApp()==null || o.getPrice()==null || o.getEndConsumer()==null || o.getProductListItems().size()==0) {
            System.out.println(o);
            return false;
        }
        System.out.println(orderRepository.save(o));
        return true;
    }

    public ResponseEntity<Object> getOrders(Long clientId, Long appid) {
        if (checkAppId(appid))
            return new ResponseEntity<>(HttpResponses.INVALID_APP, HttpStatus.FORBIDDEN);

        Consumer c = consumerRepository.findByConsumerId(clientId);
        if (c == null) {
            return new ResponseEntity<>(HttpResponses.INVALID_CONSUMER, HttpStatus.FORBIDDEN);
        }

        List<Order> orders = orderRepository.findOrdersByEndConsumer(c);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Object> registerOrder( Long clientId, Long appid, JsonNode payload ){

        // OLD JSON FIELDS (REMOVE THEM FROM JSON IN FRONTEND)

        //App app1 = checkAppId(Long.parseLong(payload.path("info").path("appid").asText()));
        // Consumer c = checkConsumer(Long.parseLong(payload.path("info").path("userId").asText()));

        App app = appRepository.findByAppid(appid);
        if (app == null)
            return new ResponseEntity<>(HttpResponses.INVALID_APP, HttpStatus.FORBIDDEN);

        Consumer c = consumerRepository.findByConsumerId(clientId);
        if (c == null) {
            return new ResponseEntity<>(HttpResponses.INVALID_CONSUMER, HttpStatus.FORBIDDEN);
        }

        List<ProductListItem> prods = new ArrayList<>();
        JsonNode payload_prods = payload.path("products");

        if(payload_prods.size()==0){
            return new ResponseEntity<>(HttpResponses.INSUFFICIENT_PRODUCTS, HttpStatus.FORBIDDEN);
        }


        for(JsonNode j:payload_prods){
            Long prodId = Long.parseLong(j.path("id").asText());
            Product p = checkAndGetProduct(prodId);
            if( p != null ){
                ProductListItem pli = new ProductListItem(p, j.path("quantity").asInt());
                prods.add(pli);
            System.out.println(p);}
            else{
                return new ResponseEntity<>(HttpResponses.INVALID_PRODUCT.replace("#", prodId.toString()), HttpStatus.FORBIDDEN);}
        }

        String address = payload.path("info").path("deliveryAddress").asText();
        if(address==null)
            return new ResponseEntity<>(HttpResponses.INVALID_ADDRESS, HttpStatus.FORBIDDEN);

        if (save(new Order(prods,c, OrderStatusEnum.PENDING,app,address)))
            return new ResponseEntity<>(HttpResponses.ORDER_SAVED, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpResponses.ORDER_NOT_SAVED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Object> updateOrder(Long riderId, Long appid, Long orderid, String status) {

        if (checkAppId(appid))
            return new ResponseEntity<>(HttpResponses.INVALID_APP, HttpStatus.FORBIDDEN);

        if (!checkRiderId(riderId))
            return new ResponseEntity<>(HttpResponses.INVALID_RIDER, HttpStatus.FORBIDDEN);

        Order orderUpdate = orderRepository.getById(orderid);

        switch(status){
            case "GOING_TO_BUY":
                orderUpdate.setOrderStatusEnum(OrderStatusEnum.GOING_TO_BUY);
                break;
            case "BUYING":
                orderUpdate.setOrderStatusEnum(OrderStatusEnum.BUYING);
                break;
            case "DELIVERING":
                orderUpdate.setOrderStatusEnum(OrderStatusEnum.DELIVERING);
                break;
            case "DELIVERED":
                orderUpdate.setOrderStatusEnum(OrderStatusEnum.DELIVERED);
                break;
            default:
                return null;
        }

        orderRepository.save(orderUpdate);
        return new ResponseEntity<>(HttpResponses.ORDER_UPDATED.replace("#", "state"), HttpStatus.OK);

    }

    // Check if app exists
    private boolean checkAppId(Long appId) {
        return appRepository.findByAppid(appId) == null;
    }

    // Check if rider exists
    private boolean checkRiderId(Long riderId) {
        return riderRepository.findByRiderId(riderId) != null;
    }

    // Check if client exists
    private boolean checkConsumerId(Long consumerId) {
        return consumerRepository.findByConsumerId(consumerId) != null;
    }

    // Check and return product if exists, null otherwise
    public Product checkAndGetProduct(Long id) {
       if(productRepository.findById(id).isEmpty())
           return null;
       return productRepository.findById(id).get();
    }

}
