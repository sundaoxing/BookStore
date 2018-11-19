package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils_Transaction {
	private static ComboPooledDataSource dp = new ComboPooledDataSource();
	/*每一次从新获取con连接时
	 * con为null，说明没有开启事务
	 * con不为null，说明开启了事务
	 */
	private static ThreadLocal<Connection> tl=new ThreadLocal<>();//事务专用连接

	//返回连接池对象
	public static DataSource getDataSource() {
		return dp;
	}

	//返回连接池连接对象
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		if(con !=null) {//判断有没有开始事务，因为开启事务，必定获取了con连接，con必不为null
			return con;
		}
		return dp.getConnection();
	}

	//开启事务
	/*获取一个Connection，设置为手动提交事务
	 * 保证dao层中的使用的连接con是我们刚刚创建的
	 */
	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();
		if(con != null) throw new SQLException("重复开启事务");
		con = getConnection();//开启事务，必初始化con连接
		con.setAutoCommit(false);
		tl.set(con);
	}

	//提交事务，con还是beginTransaction()方法中的con连接
	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if(con == null) throw new SQLException("未开启事务");
		con.commit();
		con.close();
		//System.out.println(con);
//		con= null;//显示赋值为null，说明该事务结束，下次获取新的连接
		tl.remove();
	}

	//回滚事务，con还是beginTransaction()方法中的con连接
	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if(con == null) throw new SQLException("未开启事务");
		con.rollback();
		con.close();
		tl.remove();
	}
	
	//释放con连接资源
	public static void releaseConnection(Connection connection) throws SQLException {
		Connection con = tl.get();
		if(con == null) {//con为null，说明没有开启事务，可以关闭该连接
			connection.close();
		}
		if(con != connection) {//con不为null，判断其是不是事务专用连接，若不是，则关闭
			connection.close();
		}
	}
}
