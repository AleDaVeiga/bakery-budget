package com.nexxera.bakerybudget.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.nexxera.bakerybudget.model.datatype.Created;
import com.nexxera.bakerybudget.model.datatype.Updated;

@MappedSuperclass
public abstract class BaseEntity {
	private Long id;
	private Created entityCreated;
	private Updated entityUpdated;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Embedded
	public Created getEntityCreated() {
		return entityCreated;
	}

	public void setEntityCreated(Created entityCreated) {
		this.entityCreated = entityCreated;
	}

	@Embedded
	public Updated getEntityUpdated() {
		return entityUpdated;
	}

	public void setEntityUpdated(Updated entityUpdated) {
		this.entityUpdated = entityUpdated;
	}
}