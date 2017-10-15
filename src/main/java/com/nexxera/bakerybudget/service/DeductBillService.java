package com.nexxera.bakerybudget.service;

import com.nexxera.bakerybudget.model.DeductBill;

public interface DeductBillService {
	DeductBill create(DeductBill deductBill);

	DeductBill update(DeductBill deductBill);

	DeductBill findOne(Long id);
	
	Iterable<DeductBill> findAll(Long billPayId);
}