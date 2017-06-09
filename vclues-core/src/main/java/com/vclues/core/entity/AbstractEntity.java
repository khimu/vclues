package com.vclues.core.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

/*
 * ID is declared in AbstractPersistable
 * 
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "created")
	@CreatedDate
	protected LocalDateTime createdDate;
	
	@Column(name = "updated")
	@LastModifiedDate
	protected LocalDateTime modifiedDate;
	
	@Version
	@Column(name = "version", columnDefinition = "SMALLINT")
	//@Type(type = "org.hibernate.type.NumericBooleanType")	
	protected int version;
	
	@Column(name = "active", columnDefinition = "SMALLINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	protected boolean active;
	
	@Override
	public void setId(final Long id) {
		super.setId(id);
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
