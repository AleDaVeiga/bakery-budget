package com.nexxera.bakerybudget.service;

import com.nexxera.bakerybudget.model.BillPay;

public interface BillPayService {
	BillPay createBill(BillPay billPay);

	BillPay updateBill(BillPay billPay);

	void removeBill(Long id);

	BillPay findOne(Long id);
	
	Iterable<BillPay> findAll();
}