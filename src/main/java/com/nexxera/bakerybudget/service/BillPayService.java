package com.nexxera.bakerybudget.service;

import com.nexxera.bakerybudget.model.BillPay;

public interface BillPayService {
	BillPay createBill(BillPay billPay);

	BillPay update(BillPay billPay);

	void remove(BillPay billPay);

	BillPay findOne(Long id);
	
	Iterable<BillPay> findAll();
}