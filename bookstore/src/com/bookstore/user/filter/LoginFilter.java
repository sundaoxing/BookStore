package com.bookstore.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.user.domain.User;

@WebFilter("/CartServlet")
public class LoginFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("session_user");
		System.out.println("----");
		if(user != null) {
			chain.doFilter(req, response);
		}else {
			req.setAttribute("msg", "你还未登陆");
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
