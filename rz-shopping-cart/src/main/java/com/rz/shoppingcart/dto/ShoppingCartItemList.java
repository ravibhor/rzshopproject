package com.rz.shoppingcart.dto;

import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class ShoppingCartItemList extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = -207342618755531020L;
	List<ShoppingCartItemDto> shoppingCartItemList;

	public List<ShoppingCartItemDto> getShoppingCartItemList() {
		return shoppingCartItemList;
	}

	public void setShoppingCartItemList(List<ShoppingCartItemDto> shoppingCartItemList) {
		this.shoppingCartItemList = shoppingCartItemList;
	}

}
