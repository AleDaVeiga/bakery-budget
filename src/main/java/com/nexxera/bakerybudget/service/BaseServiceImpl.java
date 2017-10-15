package com.nexxera.bakerybudget.service;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexxera.bakerybudget.model.BaseEntity;
import com.nexxera.bakerybudget.model.datatype.Created;
import com.nexxera.bakerybudget.model.datatype.Updated;
import com.nexxera.bakerybudget.util.DateUtil;

public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> {
	protected abstract JpaRepository<T, Long> getRepository();

	@Transactional
	public T create(T baseEntity) {
		Created created = new Created();
		created.setCreatedAt(DateUtil.newDateFrom(DateUtil.newZonedDateTime()));
		baseEntity.setEntityCreated(created);
		return getRepository().save(baseEntity);
	}

	@Transactional
	public T update(T baseEntity) {
		Updated updated = new Updated();
		updated.setUpdatedAt(DateUtil.newDateFrom(DateUtil.newZonedDateTime()));
		baseEntity.setEntityUpdated(updated);
		return getRepository().save(baseEntity);
	}
}