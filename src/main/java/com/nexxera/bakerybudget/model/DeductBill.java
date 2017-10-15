package com.nexxera.bakerybudget.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "tb_deduct_pay")
public class DeductBill extends BaseEntity {
	private Date datePayment;
	private BigDecimal value;
	private BillPay billPay;

	@Column(name = "date_payment")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	@NumberFormat(pattern="###,###.00")
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@ManyToOne
    @JoinColumn(name = "bill_pay_id")
	public BillPay getBillPay() {
		return billPay;
	}

	public void setBillPay(BillPay billPay) {
		this.billPay = billPay;
	}
}