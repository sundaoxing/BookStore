package com.bookstore.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.cart.domain.Cart;
import com.bookstore.exception.UserException;
import com.bookstore.user.domain.User;
import com.bookstore.user.service.UserService;
import com.utils.CommonUtils;

/*
 * Web层-》负责处理客户端的请求和转发
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	public void regist(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");

		User form = CommonUtils.toBean(req.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
		System.out.println(form);
		try {
			userService.regist(form);
			req.setAttribute("msgemail", "激活码已发送到邮箱，请激活");
		} catch (UserException e) {
			req.setAttribute("error", e.getMessage());
			req.setAttribute("form", form);
			req.getRequestDispatcher("/jsps/user/regist.jsp").forward(req, res);
			return;
		}
		req.setAttribute("form", form);
		req.setAttribute("msg", "注册成功,请登陆");
		req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, res);
	}

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User form = new User();
		form.setUsername(username);
		form.setPassword(password);
		User userjdbc = null;
		try {
			userjdbc = userService.login(username, password);
		} catch (UserException e) {
			req.setAttribute("form", form);
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, res);
			return;
		}
		req.setAttribute("msg", "登陆成功");
		req.getSession().setAttribute("session_user", userjdbc);
		req.getSession().setAttribute("session_cart", new Cart());
		res.sendRedirect(req.getContextPath() + "/index.jsp");
	}

	public void quit(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		if (session != null) {
			session.invalidate();
		}
		res.sendRedirect(req.getContextPath() +"/index.jsp");
	}
}
