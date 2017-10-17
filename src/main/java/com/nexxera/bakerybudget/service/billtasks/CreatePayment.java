package com.nexxera.bakerybudget.service.billtasks;

import java.math.BigDecimal;
import java.util.Optional;

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.service.BillPayService;
import com.nexxera.bakerybudget.service.BusinessService;

public class CreatePayment {
	
	private BusinessService businessService;
	private BillPayService billPayService;
	
	public CreatePayment(BusinessService businessService, BillPayService billPayService) {
		this.businessService = businessService;
		this.billPayService = billPayService;
	}

	public BillPay create(BillPay billPay) {
		BigDecimal billPayValue = getBillPayValue(billPay);
		BillPay billPayOld = getBillPay(billPay);
		BigDecimal billPayOldValue = getBillPayValue(billPayOld);
		Business business = getBusiness(billPay);
		BigDecimal balance = getBusinessBalance(business);
		
		business.setBalance(calculeBalance(billPayValue, billPayOldValue, balance));
		billPay.setBusiness(business);
		return billPay;
	}

	private Business getBusiness(BillPay billPay) {
		return businessService.findOne(billPay.getBusiness().getId());
	}

	private BigDecimal getBusinessBalance(Business business) {
		Optional<BigDecimal> balance = Optional.ofNullable(business.getBalance());
		if(balance.isPresent()) {
			return balance.get();
		}
		return BigDecimal.ZERO;
	}

	private BillPay getBillPay(BillPay billPay) {
		return billPayService.findOne(billPay.getId());
	}

	private BigDecimal getBillPayValue(BillPay billPay) {
		Optional<BillPay> bill = Optional.ofNullable(billPay);
		if(bill.isPresent()) {
			return bill.get().getValue();
		}
		return BigDecimal.ZERO;
	}

	private BigDecimal calculeBalance(BigDecimal billPayValue, BigDecimal billPayOldValue, BigDecimal balanceActual) {
		return balanceActual.subtract(billPayOldValue).add(billPayValue);
	}
}