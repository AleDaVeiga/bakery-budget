package com.nexxera.bakerybudget.service.billtasks;

import java.math.BigDecimal;
import java.util.Optional;

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.model.DeductBill;
import com.nexxera.bakerybudget.service.BillPayService;
import com.nexxera.bakerybudget.service.DeductBillService;

public class ChangeDeductBill {
	private BillPayService billPayService;
	private DeductBillService deductBillService;
	
	public ChangeDeductBill(BillPayService billPayService, DeductBillService deductBillService) {
		this.billPayService = billPayService;
		this.deductBillService = deductBillService;
	}

	public DeductBill create(Long billPayId, DeductBill deductBill) {
		BillPay billPay = getBillPay(billPayId);
		Business business = billPay.getBusiness();
		BigDecimal balance = getBusinessBalance(business);
		
		BigDecimal deductBillValue = getDeductBillValue(deductBill);
		DeductBill deductBillOld = getDeductBill(deductBill);
		BigDecimal deductBillOldValue = getDeductBillValue(deductBillOld);
		
		business.setBalance(calculeBalance(deductBillValue, deductBillOldValue, balance));
		billPay.setBusiness(business);
		deductBill.setBillPay(billPay);
		return deductBill;
	}

	private BillPay getBillPay(Long billPayId) {
		Optional<Long> billId = Optional.ofNullable(billPayId);
		if (billId.isPresent()) {
			return billPayService.findOne(billId.get());
		}
		return null;
	}

	private BigDecimal getBusinessBalance(Business business) {
		Optional<BigDecimal> balance = Optional.ofNullable(business.getBalance());
		if(balance.isPresent()) {
			return balance.get();
		}
		return BigDecimal.ZERO;
	}

	private BigDecimal getDeductBillValue(DeductBill deductBill) {
		Optional<DeductBill> deduct = Optional.ofNullable(deductBill);
		if(deduct.isPresent()) {
			return deduct.get().getValue();
		}
		return BigDecimal.ZERO;
	}

	private DeductBill getDeductBill(DeductBill deductBill) {
		Optional<Long> deductBillId = Optional.ofNullable(deductBill.getId());
		if (deductBillId.isPresent()) {
			return deductBillService.findOne(deductBillId.get());
		}
		return null;
	}

	private BigDecimal calculeBalance(BigDecimal deductBillValue, BigDecimal deductBillOldValue, BigDecimal balanceActual) {
		return balanceActual.add(deductBillOldValue).subtract(deductBillValue);
	}
}