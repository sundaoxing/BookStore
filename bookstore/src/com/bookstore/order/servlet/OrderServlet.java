package com.bookstore.order.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.cart.domain.Cart;
import com.bookstore.cart.domain.CartItem;
import com.bookstore.exception.OrderException;
import com.bookstore.order.domain.Order;
import com.bookstore.order.domain.OrderItem;
import com.bookstore.order.service.OrderService;
import com.bookstore.user.domain.User;
import com.bookstore.user.servlet.BaseServlet;
import com.utils.CommonUtils;

/**
 * 
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	
	public void add(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Cart cart = (Cart) req.getSession().getAttribute("session_cart");
		User user = (User) req.getSession().getAttribute("session_user");
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setUser(user);
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartitem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setIid(CommonUtils.uuid());
			orderItem.setCount(cartitem.getCount());
			orderItem.setSubtotal(cartitem.getSubTotal());
			orderItem.setOrder(order);
			orderItem.setBook(cartitem.getBook());
			orderItemList.add(orderItem);
		}
		
		order.setOrderItemList(orderItemList);
		orderService.add(order);
		req.setAttribute("order", order);
		cart.clear();
		req.getRequestDispatcher("/jsps/order/desc.jsp").forward(req, res);
	}
	public void findMyOrders(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		User user = (User) req.getSession().getAttribute("session_user");
		List<Order> orderList =orderService.findOrderByUid(user.getUid());
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("/jsps/order/list.jsp").forward(req, res);
	}
	
	public void load(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String oid = req.getParameter("oid");
		Order order =orderService.load(oid);
		req.setAttribute("order", order);
		req.getRequestDispatcher("/jsps/order/desc.jsp").forward(req, res);
	}
	
	public void confirm(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String oid = req.getParameter("oid");
		try {
			orderService.confirm(oid);
		} catch (OrderException e) {
			res.sendRedirect("http://www.baidu.com");
		}
		User user = (User) req.getSession().getAttribute("session_user");
		List<Order> orderList =orderService.findOrderByUid(user.getUid());
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("/jsps/order/list.jsp").forward(req, res);
	}
	
	public void remove(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String oid= req.getParameter("oid");
		String iid= req.getParameter("iid");
		Order order=null;
		if(orderService.remove(oid,iid)) {
			order =orderService.load(oid);
		}
		req.setAttribute("order", order);
		req.getRequestDispatcher("/jsps/order/desc.jsp").forward(req, res);
	}
}
