package com.shopping.checkout.repo;

import com.shopping.checkout.db.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
