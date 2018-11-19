package com.bookstore.user.dao;
/*
 * 数据访问层-》负责对数据库的增删查改
 */

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bookstore.user.domain.User;
import com.jdbc.JdbcUtils_DBCP;

public class UserDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils_DBCP.getDataSource());

	/*
	 * 按照用户名查询
	 */
	public User FindByUsername(String username) {
		String sql = "select * from user where username=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * 按照邮箱查询
	 */
	public User FindByEmail(String email) {
		String sql = "select * from user where email=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * 添加新用户
	 */
	public void add(User user) {
		String sql = "insert into user values(?,?,?,?,?,?)";
		Object[] params = { user.getUid(), user.getUsername(), user.getPassword(), user.getEmail(), user.getCode(),
				user.isState() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 更新激活码和激活状态
	 */
	public void updateActive(String code, String email) {
		String sql = "update user set code =?,state=1 where email=?";
		try {
			qr.update(sql, code, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
