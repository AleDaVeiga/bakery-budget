package com.nexxera.bakerybudget.service.billtasks;

import java.math.BigDecimal;
import java.util.Optional;

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.service.BusinessService;

public class CreatePayment {
	
	private BusinessService businessService;
	
	public CreatePayment(BusinessService businessService) {
		this.businessService = businessService;
	}

	public BillPay create(BillPay billPay) {
		Optional<BigDecimal> billValue = Optional.ofNullable(billPay.getValue());
		if(billValue.isPresent()) {
			Business business = businessService.findOne(billPay.getBusiness().getId());
			Optional<BigDecimal> balance = Optional.ofNullable(business.getBalance());
			if(balance.isPresent()) {
				business.setBalance(balance.get().add(billValue.get()));
			} else {
				business.setBalance(billValue.get());
			}
		}
		return billPay;
	}
}
