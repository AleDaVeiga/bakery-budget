package com.nexxera.bakerybudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexxera.bakerybudget.model.BillPay;

public interface BillPayRepository extends JpaRepository<BillPay, Long> {
}