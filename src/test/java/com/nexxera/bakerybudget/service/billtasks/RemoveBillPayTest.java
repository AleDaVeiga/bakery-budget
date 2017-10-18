package com.nexxera.bakerybudget.service.billtasks;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.model.DeductBill;
import com.nexxera.bakerybudget.service.BillPayService;
import com.nexxera.bakerybudget.service.BusinessService;
import com.nexxera.bakerybudget.service.DeductBillService;

@RunWith(MockitoJUnitRunner.class)
public class RemoveBillPayTest {
	private Long businessId = 3L;
	
	private Long billId = 2L;
	
	private RemoveBillPay removeBill;
	
	@Mock
	private BillPayService billPayService;
	@Mock
	private BusinessService businessService;
	@Mock
	private DeductBillService deductBillService;
	
	@Before
	public void setUp() {
		removeBill = new RemoveBillPay(businessService, billPayService, deductBillService);
	}
	
	@Test
	public void shouldRemoveBillPayValueForBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		Business business = newBusiness(businessId, balanceInit);
		mockBusiness(business.getId(), business);
		
		BigDecimal billValue = BigDecimal.valueOf(200);
		BillPay bill = newBillPay(billId, billValue, business);
		mockBillPay(billId, bill);
		
		BillPay billReturn = removeBill.remove(billId);
		
		assertEquals("The bill should remove the balance of business.", balanceInit.subtract(billValue), billReturn.getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateDeductBillValueForBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		Business business = newBusiness(businessId, balanceInit);
		mockBusiness(business.getId(), business);
		
		BigDecimal billValue = BigDecimal.valueOf(200);
		BillPay bill = newBillPay(billId, billValue, business);
		mockBillPay(billId, bill);
		
		BigDecimal deductValue = BigDecimal.valueOf(200);
		DeductBill deductBill = newDeductBill(1L, deductValue);
		mockDeductBill(billId, deductBill);
		
		BillPay billReturn = removeBill.remove(billId);
		
		assertEquals("The deduct should update the balance of business.", balanceInit, billReturn.getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateDeductBillListValueForBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		Business business = newBusiness(businessId, balanceInit);
		mockBusiness(business.getId(), business);
		
		BigDecimal billValue = BigDecimal.valueOf(600);
		BillPay bill = newBillPay(billId, billValue, business);
		mockBillPay(billId, bill);
		
		BigDecimal deductValue = BigDecimal.valueOf(200);
		DeductBill deductBillUm = newDeductBill(1L, deductValue);
		DeductBill deductBillDois = newDeductBill(2L, deductValue);
		mockDeductBill(billId, deductBillUm, deductBillDois);
		
		BillPay billReturn = removeBill.remove(billId);
		
		assertEquals("The list of deduct should update the balance of business.", BigDecimal.valueOf(300), billReturn.getBusiness().getBalance());
	}

	private Business newBusiness(Long id, BigDecimal balance) {
		Business business = new Business();
		business.setId(id);
		business.setBalance(balance);
		return business;
	}

	private void mockBusiness(Long id, Business business) {
		when(businessService.findOne(id)).thenReturn(business);
	}

	private BillPay newBillPay(Long id, BigDecimal value, Business business) {
		BillPay billPay = new BillPay();
		billPay.setId(id);
		billPay.setValue(value);
		billPay.setBusiness(business);
		return billPay;
	}

	private void mockBillPay(Long id, BillPay billPay) {
		when(billPayService.findOne(id)).thenReturn(billPay);
	}

	private DeductBill newDeductBill(Long id, BigDecimal value) {
		DeductBill deduct = new DeductBill();
		deduct.setId(id);
		deduct.setValue(value);
		return deduct;
	}

	private void mockDeductBill(Long billPayId, DeductBill... deducts) {
		when(deductBillService.findAll(billPayId)).thenReturn(asList(deducts));
	}
}