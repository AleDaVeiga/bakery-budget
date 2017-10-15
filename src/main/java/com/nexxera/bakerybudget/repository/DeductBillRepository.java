package com.nexxera.bakerybudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexxera.bakerybudget.model.DeductBill;

public interface DeductBillRepository extends JpaRepository<DeductBill, Long> {
	Iterable<DeductBill> findByBillPay_id(Long billPayId);
}