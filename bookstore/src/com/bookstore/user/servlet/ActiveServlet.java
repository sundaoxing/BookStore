package com.bookstore.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.exception.UserException;
import com.bookstore.user.service.UserService;

@WebServlet("/ActiveServlet")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		try {
			userService.updateActive(code,email);
		} catch (UserException e) {
			response.getWriter().print(e.getMessage());
			return;
		}
		response.getWriter().print("恭喜你，账号已激活");
	}

}
