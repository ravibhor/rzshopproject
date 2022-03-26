package com.rz.shoppingcart.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.core.shop.user.model.User;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.product.model.Product;
import com.rz.shoppingcart.dto.OrderDto;
import com.rz.shoppingcart.dto.ShoppingCartItemDto;
import com.rz.shoppingcart.model.Order;
import com.rz.shoppingcart.model.OrderItem;
import com.rz.shoppingcart.model.ShoppingCart;
import com.rz.shoppingcart.repositories.CartItemRepository;
import com.rz.shoppingcart.repositories.OrderItemRepository;
import com.rz.shoppingcart.repositories.OrderRepository;
import com.rz.shoppingcart.service.OrderItemService;
import com.rz.shoppingcart.service.OrderService;
import com.rz.shoppingcart.service.ShoppingCartItemService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ShoppingCartItemService cartItemService;

	@Autowired
	private OrderItemService orderItemsService;

	@Autowired
	private CustomUserDetailsService userDetailService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void placeOrder(long userId) throws Exception {

		User user = new User();
		user.setId(userId);
		Order order = new Order();
		order.setUser(user);
		Order userOrder = orderRepository.findByUserId(userId);
		Long orderId;
		if (userOrder != null) {
			orderId = saveOrder(userOrder, user.getId());
		} else {
			orderId = saveOrder(order, user.getId());
		}

		Product product = new Product();
		List<ShoppingCartItemDto> cartItemDtoList = cartItemService.getCartByUserId(userId);
		for (ShoppingCartItemDto cartItemDto : cartItemDtoList) {
			product.setProductId(cartItemDto.getProductId());
			order.setId(orderId);

			OrderItem item = orderItemRepository.findByProductId(cartItemDto.getProductId());
			if (item == null) {
				OrderItem orderItem = new OrderItem(cartItemDto.getQuantity(), cartItemDto.getProductPrice(), order,
						product);
				orderItemsService.addOrderedProducts(orderItem);
			} else {
				long productId = cartItemDto.getProductId();
				orderItemRepository.updateOrderItem(cartItemDto.getQuantity() + item.getQuantity(),
						cartItemDto.getProductPrice(), productId);
			}
			updateOrder(orderId, cartItemDto.getTotal());
		}
		cartItemService.deleteAllCartByUserId(userId);
	}

	@Override
	public Long saveOrder(Order order, long userId) throws Exception {
		return orderRepository.save(order).getId();
	}

	@Override
	public List<OrderDto> getOrdersByUserId(long userId) throws Exception {
		List<Order> orderList = orderRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
		List<OrderDto> listDto = new ArrayList<OrderDto>();
		for (Order order : orderList) {
			OrderDto dto = modelMapper.map(order, OrderDto.class);
			listDto.add(dto);
		}
		return listDto;
	}

	@Override
	public void updateOrder(long orderId, BigDecimal totalPrice) throws Exception {
		Optional<Order> userOrder = orderRepository.findById(orderId);
		
		Order order = new Order();
		double total = totalPrice.doubleValue()+userOrder.get().getTotalPrice();
		order.setId(orderId);
		order.setTotalPrice(total);
		orderRepository.updateTotalPrice(orderId, total);
		System.out.println("Total :" + total);
	}

}
