package com.rz.core.shop.base.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Anup Chaudhari
 * 
 * @since 12 November 2020
 *
 */
@JsonInclude(Include.NON_NULL)
public class Response {

	private ResponseHeader header;
	private Object payload;

	public ResponseHeader getHeader() {
		return header;
	}
	public void setHeader(ResponseHeader header) {
		this.header = header;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
