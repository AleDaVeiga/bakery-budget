package com.nexxera.bakerybudget.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "tb_bill_pay")
public class BillPay extends BaseEntity {
	private String name;
	private Date dateLaunch;
	private BigDecimal value;
	private Business business;
	private Set<DeductBill> deducts;

	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "date_launch")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDateLaunch() {
		return dateLaunch;
	}

	public void setDateLaunch(Date dateLaunch) {
		this.dateLaunch = dateLaunch;
	}

	@NumberFormat(pattern="###,###.00")
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@ManyToOne
    @JoinColumn(name = "business_id")
	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	@OneToMany(mappedBy = "billPay", targetEntity = DeductBill.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<DeductBill> getDeducts() {
		return deducts;
	}

	public void setDeducts(Set<DeductBill> deducts) {
		this.deducts = deducts;
	}
}