package ua.tqs.cito.controller;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ua.tqs.cito.model.Order;
import ua.tqs.cito.model.Product;
import ua.tqs.cito.service.OrderService;


@Controller
@RequestMapping("/cito/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final String ORDER_NOT_SAVED = "{\"code\" : 400, \"message\" : \"Bad parameters for order.\"}";
    private static final String ORDER_SAVED = "{\"code\" : 201, \"message\" : \"Order saved.\"}";
    private static final String ORDER_NOT_FOUND = "{\"code\" : 404, \"message\" : \"Order not found.\"}";
    private static final String ORDER_UPDATED = "{\"code\" : 200, \"message\" : \"Order updated.\"}";

    @PostMapping("register")
    public ResponseEntity<Object> registerOrder(@RequestBody JsonNode payload) {
        Order o1 = orderService.parseAndSave(payload);
        if (o1 == null) {
            return new ResponseEntity<>(ORDER_NOT_SAVED,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ORDER_SAVED,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long id){
        Order o1 = orderService.getOrder(id);
        if (o1 == null) {
            return new ResponseEntity<>(ORDER_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(o1,HttpStatus.OK);
    }

    @GetMapping("update")
    public ResponseEntity<Object> updateOrder(String status, Long appid, Long orderid){
        Order o1 = orderService.update(status,appid,orderid);
        if (o1 == null) {
            return new ResponseEntity<>(ORDER_NOT_SAVED,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ORDER_UPDATED,HttpStatus.OK);
    }

    @GetMapping(value="status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrderStatus(Long orderId) {
        ResponseEntity<Object> response = orderService.getStatusByOrderId(orderId);
        if (response == null) {
            return new ResponseEntity<>(ORDER_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
