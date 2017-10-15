package com.nexxera.bakerybudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.repository.BusinessRepository;

@Service
public class BusinessServiceImpl extends BaseServiceImpl<Business, Long> implements BusinessService {
	@Autowired
	private BusinessRepository businessRepository;

	@Override
	protected JpaRepository<Business, Long> getRepository() {
		return businessRepository;
	}

	@Transactional
	public void remove(Business business) {
		businessRepository.delete(business);
	}

	@Transactional(readOnly=true)
	public Business findOne(Long id) {
		return businessRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Business> findAll() {
		return businessRepository.findAll(orderByNameAsc());
	}
	
	private Sort orderByNameAsc() {
        return new Sort(Direction.ASC, "name");
    }
}