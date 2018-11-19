package com.bookstore.order.domain;

import com.bookstore.book.domain.Book;

/*
 * 订单条目
 * 注意：与订单强关联，订单存在，才有订单条目，没有订单，也就没有订单条目
 */
public class OrderItem {
	private String iid;
	private int count;//数量
	private double subtotal;//小计
	private Order order;//该订单条目所属的订单
	private Book book;//要购买的书
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subTotal) {
		this.subtotal = subTotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subTotal=" + subtotal + ", order=" + order + ", book="
				+ book + "]";
	}
}
