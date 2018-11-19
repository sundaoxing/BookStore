package com.bookstore.order.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bookstore.book.domain.Book;
import com.bookstore.exception.OrderException;
import com.bookstore.order.dao.OrderDao;
import com.bookstore.order.domain.Order;
import com.bookstore.order.domain.OrderItem;
import com.jdbc.JdbcUtils_Transaction;
import com.utils.CommonUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public void add(Order order) {
		try {
			JdbcUtils_Transaction.beginTransaction();
			orderDao.addOrder(order);
			orderDao.addOrderItemList(order.getOrderItemList());
			JdbcUtils_Transaction.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils_Transaction.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> findOrderByUid(String uid){
		List<Order> orderList = orderDao.findOrderByUid(uid);
		for(Order order :orderList) {
			loadOrderItem(order);
		}
		return orderList;
	}

	private void loadOrderItem(Order order) {
		List<Map<String,Object>>orderitem_bookList = orderDao.findOrderItem(order);
		List<OrderItem> orderItemList = toOrderItemList(orderitem_bookList);
		order.setOrderItemList(orderItemList);
	}

	private List<OrderItem> toOrderItemList(List<Map<String, Object>> orderitem_bookList) {
		List<OrderItem> orderItemList = new ArrayList<>();
		for(Map<String, Object> map :orderitem_bookList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	public Order load(String oid) {
		Order order =orderDao.findOrder(oid);
		loadOrderItem(order);
		return order;
	}

	public void confirm(String oid) throws OrderException {
		int state =orderDao.getState(oid);
		if(state != 3) {
			throw new OrderException("请诚信购物");
		}
		orderDao.updateState(oid, 4);
	}

	public boolean remove(String oid,String iid) {
		orderDao.removeOrderItem(iid);
		Order order = load(oid);
		if(order.getOrderItemList() ==null || order.getOrderItemList().size()==0) {
			orderDao.removeOrder(oid);
			return false;
		}
		return true;
	}

	public List<Order> findAllOrder() {
		List<Order> orderList = orderDao.findAllOrder();
		for(Order order :orderList) {
			loadOrderItem(order);
		}
		return orderList;
	}
	public List<Order> findCategoryOrder(int state) {
		List<Order> orderList = orderDao.findCategoryOrder(state);
		for(Order order :orderList) {
			loadOrderItem(order);
		}
		return orderList;
	}
	
	public void updateState(String oid,int state) {
		orderDao.updateState(oid, state);
	}
}
