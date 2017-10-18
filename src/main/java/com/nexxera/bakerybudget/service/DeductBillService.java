package com.nexxera.bakerybudget.service;

import com.nexxera.bakerybudget.model.DeductBill;

public interface DeductBillService {
	DeductBill createDeduct(Long billPayId, DeductBill deductBill);

	DeductBill updateDeduct(Long billPayId, DeductBill deductBill);

	void remove(Iterable<DeductBill> deducts);

	DeductBill findOne(Long id);
	
	Iterable<DeductBill> findAll(Long billPayId);
}