package com.bookstore.order.servlet.admin;

import com.bookstore.order.domain.Order;
import com.bookstore.order.service.OrderService;
import com.bookstore.user.servlet.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminOrderServlet
 */
@WebServlet("/admin/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	public void findAllOrder(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<Order> orderList =orderService.findAllOrder();
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("/admin/order/list.jsp").forward(req, res);
	}
	
	public void findCategoryOrder(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String state = req.getParameter("state");
		List<Order> orderList =orderService.findCategoryOrder(Integer.parseInt(state));
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("/admin/order/list.jsp").forward(req, res);
	}
	
	public void delivery(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String oid = req.getParameter("oid");
		orderService.updateState(oid, 3);
		List<Order> orderList =orderService.findCategoryOrder(3);
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("/admin/order/list.jsp").forward(req, res);
	}
}
