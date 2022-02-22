package com.shopping.checkout.repo;

import com.shopping.checkout.db.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Integer> {

}
