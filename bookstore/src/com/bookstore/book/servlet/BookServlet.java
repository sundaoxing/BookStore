package com.bookstore.book.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.book.domain.Book;
import com.bookstore.book.service.BookService;
import com.bookstore.user.servlet.BaseServlet;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	public void findAllBook(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		List<Book> bookList = bookService.findAllBook();
		req.setAttribute("bookList", bookList);
		req.getRequestDispatcher("/jsps/book/list.jsp").forward(req, res);
	}
	
	public void findCategoryBook(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String cid = req.getParameter("cid");
		List<Book> bookList = bookService.findCategoryBook(cid);
		req.setAttribute("bookList", bookList);
		req.getRequestDispatcher("/jsps/book/list.jsp").forward(req, res);
	}
	
	public void load(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String bid = req.getParameter("bid");
		Book book = bookService.load(bid);
		req.setAttribute("book", book);
		req.getRequestDispatcher("/jsps/book/desc.jsp").forward(req, res);
	}
}
