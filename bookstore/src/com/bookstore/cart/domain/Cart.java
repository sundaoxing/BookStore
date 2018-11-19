package com.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 购物车-》多条CartItem组成
 * 存放在session中
 * cookies中
 * 数据库中
 */
public class Cart {
	//多个购物车条目->便于查找，使用key-value,有序
	private Map<String, CartItem> map = new LinkedHashMap<>();
	/*
	 * 添加一个条目
	 * 判断：如果添加相同条目，则合并，购买数量增加
	 * 	         否则，直接添加
	 */
	public void add(CartItem cartItem) {
		String bid = cartItem.getBook().getBid();
		if(map.containsKey(bid)) {
			CartItem old_cartitem =map.get(bid);
			old_cartitem.setCount(cartItem.getCount()+old_cartitem.getCount());
		}else {
			map.put(bid, cartItem);
		}
	}
	/*
	 * 清空所有
	 */
	public void clear() {
		map.clear();
	}
	/*
	 * 删除指定条目
	 */
	public void remove(String bid) {
		map.remove(bid);
	}
	/*
	 * 总计金额
	 */
	public double getTotal() {
		BigDecimal sum = new BigDecimal("0");
		for(CartItem cartItem : map.values()) {
			BigDecimal subtotal = new BigDecimal(cartItem.getSubTotal()+"");
			sum=sum.add(subtotal);
		}
		return sum.doubleValue();
	}
	/*
	 * 获取所有条目
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}