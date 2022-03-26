package com.rz.shoppingcart.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rz.shoppingcart.model.OrderItem;
import com.rz.shoppingcart.repositories.OrderItemRepository;
import com.rz.shoppingcart.service.OrderItemService;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemsRepository;

	@Override
	public void addOrderedProducts(OrderItem orderItem) {
		orderItemsRepository.save(orderItem);
	}

}
