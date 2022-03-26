package com.rz.core.shop.base;

import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class ListObjectModel<T extends ResponseBody> extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> results;

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

}
