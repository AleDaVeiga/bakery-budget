package com.nexxera.bakerybudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexxera.bakerybudget.model.DeductBill;
import com.nexxera.bakerybudget.repository.DeductBillRepository;
import com.nexxera.bakerybudget.service.billtasks.ChangeDeductBill;

@Service
public class DeductBillServiceImpl extends BaseServiceImpl<DeductBill, Long> implements DeductBillService {
	@Autowired
	private DeductBillRepository deductBillRepository;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private BillPayService billPayService;

	@Override
	protected JpaRepository<DeductBill, Long> getRepository() {
		return deductBillRepository;
	}
	
	@Transactional
	public DeductBill createDeduct(Long billPayId, DeductBill deductBill) {
		new ChangeDeductBill(billPayService, this).change(billPayId, deductBill);
		businessService.update(deductBill.getBillPay().getBusiness());
		return super.create(deductBill);
	}
	
	@Transactional
	public DeductBill updateDeduct(Long billPayId, DeductBill deductBill) {
		new ChangeDeductBill(billPayService, this).change(billPayId, deductBill);
		businessService.update(deductBill.getBillPay().getBusiness());
		return super.update(deductBill);
	}
	
	@Transactional
	public void remove(Iterable<DeductBill> deducts) {
		deductBillRepository.delete(deducts);
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