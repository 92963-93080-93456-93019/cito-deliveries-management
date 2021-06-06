package ua.tqs.cito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.tqs.cito.service.OrderService;

@Controller
@RequestMapping("/riderApi")
public class RiderController {

    @Autowired
    private OrderService orderService;

    // Rider updates order state
    @GetMapping("{riderId}/order/update")
    public ResponseEntity<Object> updateOrder(@PathVariable Long riderId, Long appid, Long orderId, String status){
        return orderService.updateOrder(riderId, appid, orderId, status);
    }
}
