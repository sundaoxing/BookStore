package com.bookstore.cart.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.book.domain.Book;
import com.bookstore.book.service.BookService;
import com.bookstore.cart.domain.Cart;
import com.bookstore.cart.domain.CartItem;
import com.bookstore.user.servlet.BaseServlet;

/**
 * 购物车
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public void add(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		Cart cart = (Cart) req.getSession().getAttribute("session_cart");
		String bid = req.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.valueOf(req.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		cart.add(cartItem);
		req.getRequestDispatcher("/jsps/cart/list.jsp").forward(req, res);
	}
	
	public void clear(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Cart cart = (Cart) req.getSession().getAttribute("session_cart");
		cart.clear();
		req.getRequestDispatcher("/jsps/cart/list.jsp").forward(req, res);
	}
	
	public void remove(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		Cart cart = (Cart) req.getSession().getAttribute("session_cart");
		String bid = req.getParameter("bid");
		cart.remove(bid);
		req.getRequestDispatcher("/jsps/cart/list.jsp").forward(req, res);
	}
	
}
