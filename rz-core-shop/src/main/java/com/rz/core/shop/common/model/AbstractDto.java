package com.rz.core.shop.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.base.models.response.ResponseBody;

public abstract class AbstractDto extends ResponseBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date createdAt;
	
	private String createdBy;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date updatedAt;
	
	private String updatedBy;

	@Enumerated(EnumType.STRING)
    private StatusEnum status;
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@PrePersist
	public void prePersist() {
		setCreatedAt(new Date());
	}

	@PreUpdate
	public void preUpdateAndRemove() {
		setUpdatedAt(new Date());
	}

}
