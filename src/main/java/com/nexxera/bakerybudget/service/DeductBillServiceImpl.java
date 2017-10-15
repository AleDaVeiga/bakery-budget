package com.nexxera.bakerybudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexxera.bakerybudget.model.DeductBill;
import com.nexxera.bakerybudget.repository.DeductBillRepository;

@Service
public class DeductBillServiceImpl extends BaseServiceImpl<DeductBill, Long> implements DeductBillService {
	@Autowired
	private DeductBillRepository deductBillRepository;

	@Override
	protected JpaRepository<DeductBill, Long> getRepository() {
		return deductBillRepository;
	}

	@Transactional(readOnly=true)
	public DeductBill findOne(Long id) {
		return deductBillRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<DeductBill> findAll(Long billPayId) {
		return deductBillRepository.findByBillPay_id(billPayId);
	}
}