package com.bookstore.category.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.category.domain.Category;
import com.bookstore.category.service.CategoryService;
import com.bookstore.exception.CategoryException;
import com.bookstore.user.servlet.BaseServlet;
import com.utils.CommonUtils;

@WebServlet("/admin/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService = new CategoryService();
	
	public void findAllCategory(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<Category>categoryList = categoryService.FindAllCategory();
		req.setAttribute("cateList", categoryList);
		req.getRequestDispatcher("/admin/category/list.jsp").forward(req, res);
	}
	
	public void addCategory(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String name= req.getParameter("categoryname");
		Category cate = new Category();
		cate.setCid(CommonUtils.uuid());
		cate.setCname(name);
		try {
			categoryService.addCategory(cate);
		} catch (CategoryException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("catename",name);
			req.getRequestDispatcher("/admin/category/add.jsp").forward(req, res);
			return;
		}
		findAllCategory(req, res);
	}
	
	public void remove(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String cid = req.getParameter("cid");
		try {
			categoryService.remove(cid);
		} catch (CategoryException e) {
			req.setAttribute("msg", e.getMessage());
			findAllCategory(req, res);
			return;
		}
		findAllCategory(req, res);
	}
	
	public void findCategory(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String cid = req.getParameter("cid");
		Category category = categoryService.findCategory(cid);
		req.setAttribute("cate", category);
		req.getRequestDispatcher("/admin/category/update.jsp").forward(req, res);
	}
	
	public void update(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String cid = req.getParameter("cid");
		String cname= req.getParameter("cname");
		categoryService.update(cid,cname);
		findAllCategory(req, res);
	}
}
