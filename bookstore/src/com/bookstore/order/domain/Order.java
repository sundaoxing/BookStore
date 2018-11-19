package com.bookstore.order.domain;

import java.util.Date;
import java.util.List;

import com.bookstore.user.domain.User;

/*
 * 订单
 */
public class Order {
	private String oid;//订单号
	private Date ordertime;//订单时间
	private double total;//订单金额总计
	//1.未付款  2.已付款未发货  3.已发货未确认收货  4.确认收货，交易成功
	private int state;//订单状态
	private String address;//收货地址
	private User user;//收货人
	private List<OrderItem> orderItemList;//当前订单下的所有订单条目
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", address="
				+ address + ", user=" + user + ", orderItemList=" + orderItemList + "]";
	}
}
