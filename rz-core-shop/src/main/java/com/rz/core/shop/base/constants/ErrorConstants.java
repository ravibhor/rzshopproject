package com.rz.core.shop.base.constants;

import java.io.Serializable;

import com.rz.core.shop.base.enums.Messages;

public interface ErrorConstants extends Serializable{

	public Messages getMessage();

	public int getStatusCode();

}