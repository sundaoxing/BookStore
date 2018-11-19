package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils_DBCP {
	/*
	 * 使用数据库连接池获取连接对象
	 */
	private static ComboPooledDataSource cp =new ComboPooledDataSource();
	
	//返回连接池对象DataSource
	public static DataSource getDataSource() {
		return cp;
	}
	
	//返回连接对象Connection
	public static Connection getConnection() throws SQLException {
		return cp.getConnection();
	}
}
