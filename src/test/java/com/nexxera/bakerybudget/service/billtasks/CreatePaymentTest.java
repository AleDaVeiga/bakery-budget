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
import com.nexxera.bakerybudget.service.BusinessService;

@RunWith(MockitoJUnitRunner.class)
public class CreatePaymentTest {

	private BillPay bill;
	
	private CreatePayment payment;
	
	@Mock
	private BusinessService businessService;
	
	@Before
	public void setUp() {
		bill = new BillPay();
		bill.setBusiness(newBusiness(1L));
		payment = new CreatePayment(businessService);
	}
	
	@Test
	public void shouldInitBalanceOfBusiness() {
		BigDecimal billValue = BigDecimal.valueOf(500);
		bill.setValue(billValue);
		
		BillPay billReturn = payment.create(bill);
		
		assertEquals("The bill should init the balance of business.", billValue, billReturn.getBusiness().getBalance());
	}
	
	@Test
	public void shouldUpdateBalanceOfBusiness() {
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		BigDecimal billValue = BigDecimal.valueOf(200);

		Business business = newBusiness(2L);
		business.setBalance(balanceInit);
		bill.setBusiness(business);
		bill.setValue(billValue);
		
		BillPay billReturn = payment.create(bill);
		
		assertEquals("The bill should update the balance of business.", balanceInit.add(billValue), billReturn.getBusiness().getBalance());
	}
	
	@Test
	public void shouldNotUpdateBalanceOfBusinessWhenBillValueIsNotPresent() {
		bill.setValue(null);
		
		BigDecimal balanceInit = BigDecimal.valueOf(500);
		Business business = newBusiness(2L);
		business.setBalance(balanceInit);
		bill.setBusiness(business);
		
		BillPay billReturn = payment.create(bill);
		
		assertEquals("The bill should not update the balance of business.", balanceInit, billReturn.getBusiness().getBalance());
	}

	private Business newBusiness(Long id) {
		Business business = new Business();
		business.setId(id);
		mockNewBusiness(id, business);
		return business;
	}

	private void mockNewBusiness(Long id, Business business) {
		when(businessService.findOne(id)).thenReturn(business);
	}
}