package com.bookstore.user.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.bookstore.user.domain.User;
import com.utils.CommonUtils;

@WebFilter("/UserServlet")
public class RegistFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		if (request.getParameter("method").equals("regist")) {

			User form = CommonUtils.toBean(request.getParameterMap(), User.class);
			HashMap<String, String> errors = new HashMap<>();
			if (form.getUsername() == null) {
				errors.put("username", "用户名不能为空");
			} else if (form.getUsername().length() < 1 || form.getUsername().length() > 12) {
				errors.put("username", "用户名必须为4-12位字符");
			}

			if (form.getPassword() == null) {
				errors.put("password", "密码不能为空");
			} else if (form.getPassword().length() < 2 || form.getPassword().length() > 8) {
				errors.put("password", "密码必须为4-8为字符");
			}

			if (form.getEmail() == null) {
				errors.put("email", "邮箱不能为空");
			} else if (!form.getEmail().matches("\\w+@\\w+\\.\\w+")) {
				errors.put("email", "邮箱格式不正确");
			}

			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				request.setAttribute("form", form);
				request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
