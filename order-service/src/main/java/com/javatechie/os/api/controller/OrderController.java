package com.javatechie.os.api.controller;

import com.javatechie.os.api.entity.Order;
import com.javatechie.os.api.service.OrderSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderSerive serive;

    @PostMapping("/bookOrder")
    public Order bookOrder(@RequestBody Order order) {
        return serive.saveOrder(order);
    }

}
