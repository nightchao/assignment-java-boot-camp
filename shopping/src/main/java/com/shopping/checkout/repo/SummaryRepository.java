package com.shopping.checkout.repo;

import com.shopping.checkout.db.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SummaryRepository extends JpaRepository<Summary, Integer> {

    Optional<Summary> findById(int invoiceNo);
}
