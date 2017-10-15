package com.nexxera.bakerybudget.service;

import com.nexxera.bakerybudget.model.Business;

public interface BusinessService {
	Business create(Business business);

	Business update(Business business);

	void remove(Business business);

	Business findOne(Long id);
	
	Iterable<Business> findAll();
}