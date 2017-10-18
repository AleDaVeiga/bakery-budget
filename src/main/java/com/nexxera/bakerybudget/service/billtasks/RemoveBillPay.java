package com.nexxera.bakerybudget.service.billtasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.model.DeductBill;
import com.nexxera.bakerybudget.service.BillPayService;
import com.nexxera.bakerybudget.service.BusinessService;
import com.nexxera.bakerybudget.service.DeductBillService;

public class RemoveBillPay {
	private BusinessService businessService;
	private BillPayService billPayService;
	private DeductBillService deductBillService;
	
	public RemoveBillPay(BusinessService businessService, BillPayService billPayService, DeductBillService deductBillService) {
		this.businessService = businessService;
		this.billPayService = billPayService;
		this.deductBillService = deductBillService;
	}

	public BillPay remove(Long billPayId) {
		BillPay billPay = getBillPay(billPayId);
		BigDecimal billPayValue = getBillPayValue(billPay);
		BigDecimal deductBillSumValue = getDeductBillSumValue(billPay.getDeducts());
		Business business = getBusiness(billPay);
		BigDecimal balance = getBusinessBalance(business);
		
		business.setBalance(calculeBalance(deductBillSumValue, billPayValue, balance));
		billPay.setBusiness(business);
		return billPay;
	}

	private Iterable<DeductBill> getDeductBillList(Long billPayId) {
		Optional<Iterable<DeductBill>> deducts = Optional.ofNullable(deductBillService.findAll(billPayId));
		if(deducts.isPresent()) {
			return deducts.get();
		}
		return new ArrayList<DeductBill>();
	}

	private BigDecimal getDeductBillSumValue(Set<DeductBill> deducts) {
		BigDecimal sum = BigDecimal.ZERO;
		for (DeductBill deductBill : deducts) {
			sum = sum.add(getDeductBillValue(deductBill));
		}
		return sum;
	}

	private BigDecimal getDeductBillValue(DeductBill billPay) {
		Optional<BigDecimal> billValue = Optional.ofNullable(billPay.getValue());
		if(billValue.isPresent()) {
			return billValue.get();
		}
		return BigDecimal.ZERO;
	}

	private BillPay getBillPay(Long billPayId) {
		BillPay billPay = billPayService.findOne(billPayId);
		billPay.setDeducts(new HashSet<DeductBill>((Collection<DeductBill>)getDeductBillList(billPayId)));
		return billPay;
	}

	private BigDecimal getBillPayValue(BillPay billPay) {
		Optional<BigDecimal> billValue = Optional.ofNullable(billPay.getValue());
		if(billValue.isPresent()) {
			return billValue.get();
		}
		return BigDecimal.ZERO;
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

	private BigDecimal calculeBalance(BigDecimal deductBillValue, BigDecimal billPayValue, BigDecimal balanceActual) {
		return balanceActual.add(deductBillValue).subtract(billPayValue);
	}
}