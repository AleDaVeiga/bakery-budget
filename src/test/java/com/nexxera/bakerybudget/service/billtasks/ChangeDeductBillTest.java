package com.nexxera.bakerybudget.service.billtasks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nexxera.bakerybudget.model.DeductBill;
import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.service.DeductBillService;
import com.nexxera.bakerybudget.service.BillPayService;

@RunWith(MockitoJUnitRunner.class)
public class ChangeDeductBillTest {
	private BillPay billPay;
	
	private DeductBill deductBill;
	
	private ChangeDeductBill changeDeduct;
	
	@Mock
	private BillPayService billPayService;
	@Mock
	private DeductBillService deductBillService;
	
	@Before
	public void setUp() {
		deductBill = newDeductBill(1L, null);
		
		billPay = newBillPay(1L, null);
		deductBill.setBillPay(billPay);
		
		changeDeduct = new ChangeDeductBill(billPayService, deductBillService);
	}
	
	@Test
	public void shouldDeductTheBalanceOfBusiness() {
		deductBill = newDeductBill(null, null);
		BigDecimal deductValue = BigDecimal.valueOf(500);
		deductBill.setValue(deductValue);
		
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		billPay.getBusiness().setBalance(balanceInit);
		mockBillPay(billPay.getId(), billPay);
		
		DeductBill deductReturn = changeDeduct.change(billPay.getId(), deductBill);
		
		assertEquals("The bill should deduct the balance of business.", balanceInit.subtract(deductValue), deductReturn.getBillPay().getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		billPay.getBusiness().setBalance(balanceInit);
		mockBillPay(billPay.getId(), billPay);
		
		BigDecimal deductValue = BigDecimal.valueOf(200);
		deductBill.setValue(deductValue);
		
		DeductBill deductReturn = changeDeduct.change(billPay.getId(), deductBill);
		
		assertEquals("The deduct should update the balance of business.", balanceInit.subtract(deductValue), deductReturn.getBillPay().getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateWithTheLatestBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		billPay.getBusiness().setBalance(balanceInit);
		mockBillPay(billPay.getId(), billPay);
		
		BigDecimal deductValue = BigDecimal.valueOf(200);
		deductBill.setValue(deductValue);
		
		BigDecimal balanceActual = BigDecimal.valueOf(200);
		BillPay billPayActual = newBillPay(billPay.getId(), balanceActual);
		mockBillPay(billPay.getId(), billPayActual);
		
		DeductBill deductReturn = changeDeduct.change(billPay.getId(), deductBill);
		
		assertEquals("The deduct should update with the latest balance of business.", balanceActual.subtract(deductValue), deductReturn.getBillPay().getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateTheDifferenceInDeductValueForBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		billPay.getBusiness().setBalance(balanceInit);
		mockBillPay(billPay.getId(), billPay);

		BigDecimal deductValueActual = BigDecimal.valueOf(300);
		deductBill.setValue(deductValueActual);
		
		BigDecimal deductValueInit = BigDecimal.valueOf(400);
		DeductBill deductOld = newDeductBill(deductBill.getId(), deductValueInit);
		mockDeductBill(deductOld.getId(), deductOld);
		
		DeductBill deductReturn = changeDeduct.change(billPay.getId(), deductBill);
		
		assertEquals("The deduct should update the balance with the difference in deduct value.", balanceInit.add(deductValueInit).subtract(deductValueActual), deductReturn.getBillPay().getBusiness().getBalance());
	}

	private DeductBill newDeductBill(Long id, BigDecimal value) {
		DeductBill billPay = new DeductBill();
		billPay.setId(id);
		billPay.setValue(value);
		return billPay;
	}

	private BillPay newBillPay(Long id, BigDecimal balance) {
		BillPay bill = new BillPay();
		bill.setId(id);
		bill.setBusiness(newBusiness(id, balance));
		return bill;
	}

	private Business newBusiness(Long id, BigDecimal balance) {
		Business business = new Business();
		business.setId(id);
		business.setBalance(balance);
		return business;
	}

	private void mockBillPay(Long id, BillPay billPay) {
		when(billPayService.findOne(id)).thenReturn(billPay);
	}

	private void mockDeductBill(Long id, DeductBill deductBill) {
		when(deductBillService.findOne(id)).thenReturn(deductBill);
	}
}