package com.bookstore.user.service;

import com.bookstore.exception.UserException;
import com.bookstore.user.dao.UserDao;
import com.bookstore.user.domain.User;
import com.bookstore.user.email.SendMail;

/*
 * 业务层-》负责业务逻辑，承接DAO层，给Web层提供服务
 */
public class UserService {
	private UserDao userdao = new UserDao();

	public void regist(User user) throws UserException {
		if (userdao.FindByUsername(user.getUsername()) != null) {
			throw new UserException("用户名已存在");
		}
		if (userdao.FindByEmail(user.getEmail()) != null) {
			throw new UserException("邮箱已存在");
		}
		SendMail sm = new SendMail();
		sm.sendMail(user.getEmail());
		userdao.add(user);
	}

	public void updateActive(String code, String email) throws UserException {
		User user = userdao.FindByEmail(email);
		if (user.isState()) {
			throw new UserException("您已激活，请勿重复激活");
		}
		userdao.updateActive(code, email);
	}

	public User login(String username, String password) throws UserException {
		User user = userdao.FindByUsername(username);
		if (user != null && (user.getPassword().equals(password))) {
			if (user.isState()) {
				return user;
			}else {
				throw new UserException("你未到邮箱中激活，请先激活");
			}
		}
		throw new UserException("用户名或密码错误");
		
	}
}
