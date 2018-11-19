package com.bookstore.cart.domain;

import java.math.BigDecimal;

import com.bookstore.book.domain.Book;
/*
 * 购物车条目
 */
public class CartItem {
	private Book book;//商品
	private int count;//数量
	
	public double getSubTotal() {
		BigDecimal price = new BigDecimal(book.getPrice()+"");
		BigDecimal co = new BigDecimal(count+"");
		return price.multiply(co).doubleValue();
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
