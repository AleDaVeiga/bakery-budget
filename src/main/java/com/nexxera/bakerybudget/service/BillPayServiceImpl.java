package com.nexxera.bakerybudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.repository.BillPayRepository;

@Service
public class BillPayServiceImpl extends BaseServiceImpl<BillPay, Long> implements BillPayService {
	@Autowired
	private BillPayRepository billPayRepository;

	@Override
	protected JpaRepository<BillPay, Long> getRepository() {
		return billPayRepository;
	}

	@Transactional
	public void remove(BillPay billPay) {
		billPayRepository.delete(billPay);
	}

	@Transactional(readOnly=true)
	public BillPay findOne(Long id) {
		return billPayRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<BillPay> findAll() {
		return billPayRepository.findAll(orderByBusiness_NameAsc().and(orderByDateLaunchAsc()));
	}
	
	private Sort orderByBusiness_NameAsc() {
        return new Sort(Direction.ASC, "business.name");
    }
	
	private Sort orderByDateLaunchAsc() {
        return new Sort(Direction.DESC, "dateLaunch");
    }
}