package com.rz.core.shop.base.enums;

public enum StatusEnum {
	INACTIVE(0), ACTIVE(1), DELETED(2), ARCHIVED(3);

	private Integer value;

	private StatusEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

}
