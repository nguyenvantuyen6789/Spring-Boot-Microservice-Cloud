package com.javatechie.os.api.service;

import com.javatechie.os.api.common.Payment;
import com.javatechie.os.api.common.TransactionRequest;
import com.javatechie.os.api.common.TransactionResponse;
import com.javatechie.os.api.entity.Order;
import com.javatechie.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request) {
        String response = "";

        Order order = request.getOrder();
        repository.save(order);

        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getQty());

        System.out.println("hh1");
        System.out.println(payment);
        Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);

        System.out.println("hh2");
        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successfully and order placed" : "there is a failure in payment api, order added to cart";

        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
