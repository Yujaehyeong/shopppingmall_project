package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.OrderDao;
import com.cafe24.shoppingmall.vo.OrderProductVo;
import com.cafe24.shoppingmall.vo.OrderVo;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public boolean add(OrderVo orderVo) {

		System.out.println(orderVo);
		if (1 == orderDao.insertOrder(orderVo)) {
			for (OrderProductVo orderProductVo : orderVo.getOrderProducts()) {
				orderProductVo.setOrderNo(orderVo.getNo());
				if (orderDao.insertOrderProduct(orderProductVo) != 1)
					return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	public List<OrderVo> getOrderListByUserId(String userId) {

		return orderDao.selectOrderListByUserId(userId);
	}

	public OrderVo getOrderByOrderNo(Long orderNo) {

		return orderDao.selectOrderByOrderNo(orderNo);
	}

	public List<OrderVo> getOrderList() {

		return orderDao.getOrderList();
	}

}
