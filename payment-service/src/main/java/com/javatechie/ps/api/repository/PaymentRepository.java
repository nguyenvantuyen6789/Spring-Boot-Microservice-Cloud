package com.javatechie.ps.api.repository;

import com.javatechie.ps.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByOrderId(int orderId);

}
