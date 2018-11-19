package com.bookstore.category.servlet;

import com.bookstore.category.domain.Category;
import com.bookstore.category.service.CategoryService;
import com.bookstore.user.servlet.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService = new CategoryService();
	
	public void findAllCategory(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<Category>categoryList = categoryService.FindAllCategory();
		req.setAttribute("cateList", categoryList);
		req.getRequestDispatcher("/jsps/left.jsp").forward(req, res);
	}
}
