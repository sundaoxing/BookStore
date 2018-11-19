package com.bookstore.book.servlet.admin;

import com.bookstore.book.domain.Book;
import com.bookstore.book.service.BookService;
import com.bookstore.category.domain.Category;
import com.bookstore.category.service.CategoryService;
import com.bookstore.user.servlet.BaseServlet;
import com.utils.CommonUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	public void findAllBook(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		List<Book> bookList = bookService.findAllBook();
		req.setAttribute("bookList", bookList);
		req.getRequestDispatcher("/admin/book/list.jsp").forward(req, res);
	}
	
	public void load(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String bid = req.getParameter("bid");
		Book book = bookService.load(bid);
		req.setAttribute("book", book);
		List<Category> cateList =categoryService.FindAllCategory();
		req.setAttribute("cateList", cateList);
		req.getRequestDispatcher("/admin/book/desc.jsp").forward(req, res);
	}

	public void findAllCategory(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		List<Category> cateList =categoryService.FindAllCategory();
		req.setAttribute("cateList", cateList);
		req.getRequestDispatcher("/admin/book/add.jsp").forward(req, res);
	}
	
	public void delete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String bid = req.getParameter("bid");
		bookService.delete(bid);
		findAllBook(req, res);
	}
	public void update(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Category cate = CommonUtils.toBean(req.getParameterMap(), Category.class);
		Book book = CommonUtils.toBean(req.getParameterMap(), Book.class);
		book.setCategory(cate);
		bookService.update(book);
		findAllBook(req, res);
	}
}
