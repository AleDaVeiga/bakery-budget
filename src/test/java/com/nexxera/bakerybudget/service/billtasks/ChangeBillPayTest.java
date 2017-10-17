package com.nexxera.bakerybudget.service.billtasks;

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
import com.nexxera.bakerybudget.service.BillPayService;
import com.nexxera.bakerybudget.service.BusinessService;

@RunWith(MockitoJUnitRunner.class)
public class ChangeBillPayTest {
	private BillPay bill;
	
	private Business business;
	
	private ChangeBillPay changeBill;
	
	@Mock
	private BillPayService billPayService;
	@Mock
	private BusinessService businessService;
	
	@Before
	public void setUp() {
		bill = newBillPay(3L, null);
		
		business = newBusiness(1L, null);
		bill.setBusiness(business);
		
		changeBill = new ChangeBillPay(businessService, billPayService);
	}
	
	@Test
	public void shouldInitBalanceOfBusiness() {
		bill = newBillPay(null, null);
		BigDecimal billValue = BigDecimal.valueOf(500);
		bill.setValue(billValue);
		
		bill.setBusiness(business);
		mockBusiness(business.getId(), business);
		
		BillPay billReturn = changeBill.change(bill);
		
		assertEquals("The bill should init the balance of business.", billValue, billReturn.getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		business.setBalance(balanceInit);
		mockBusiness(business.getId(), business);
		
		BigDecimal billValue = BigDecimal.valueOf(200);
		bill.setValue(billValue);
		
		BillPay billReturn = changeBill.change(bill);
		
		assertEquals("The bill should update the balance of business.", balanceInit.add(billValue), billReturn.getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateWithTheLatestBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		business.setBalance(balanceInit);
		mockBusiness(business.getId(), business);
		
		BigDecimal billValue = BigDecimal.valueOf(200);
		bill.setValue(billValue);
		
		BigDecimal balanceActual = BigDecimal.valueOf(200);
		Business businessActual = newBusiness(business.getId(), balanceActual);
		mockBusiness(businessActual.getId(), businessActual);
		
		BillPay billReturn = changeBill.change(bill);
		
		assertEquals("The bill should update with the latest balance of business.", balanceActual.add(billValue), billReturn.getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateTheDifferenceInBillValueForBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(300);
		business.setBalance(balanceInit);
		mockBusiness(business.getId(), business);

		BigDecimal billValueActual = BigDecimal.valueOf(500);
		bill.setValue(billValueActual);
		
		BigDecimal billValueInit = BigDecimal.valueOf(400);
		BillPay billOld = newBillPay(bill.getId(), billValueInit);
		mockBillPay(billOld.getId(), billOld);
		
		BillPay billReturn = changeBill.change(bill);
		
		assertEquals("The bill should update the balance with the difference in bill value.", balanceInit.subtract(billValueInit).add(billValueActual), billReturn.getBusiness().getBalance());
	}

	private BillPay newBillPay(Long id, BigDecimal value) {
		BillPay billPay = new BillPay();
		billPay.setId(id);
		billPay.setValue(value);
		return billPay;
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

	private void mockBillPay(Long id, BillPay billPay) {
		when(billPayService.findOne(id)).thenReturn(billPay);
	}
}