package com.bookstore.order.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.order.domain.Order;
import com.bookstore.order.domain.OrderItem;
import com.jdbc.JdbcUtils_DBCP;

public class OrderDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	
	public void addOrder(Order order) {
		String sql= "insert into orders values(?,?,?,?,?,?)";
		Object []params = {order.getOid(),order.getOrdertime(),order.getTotal(),order.getState()
				,order.getUser().getUid(),order.getAddress()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrderItemList(List<OrderItem> orderItemList) {
		String sql="insert into orderitem values(?,?,?,?,?)";
		Object [][] params = new Object[orderItemList.size()][];
		for(int i=0;i<orderItemList.size();i++) {
			OrderItem orderItem = orderItemList.get(i);
			params[i]= new Object[]{orderItem.getIid(),orderItem.getCount()
					,orderItem.getSubtotal(),orderItem.getOrder().getOid()
					,orderItem.getBook().getBid()};
		}
		try {
			qr.batch(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Order> findOrderByUid(String uid){
		String sql = "select * from orders where uid=?";
		List<Order> orderList=null;
		try {
			orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	
	public List<Map<String,Object>> findOrderItem(Order order){
		String sql = "select * from orderitem i ,book b where i.bid=b.bid and oid=?";
		List<Map<String,Object>> orderitem_bookList=null;
		try {
			orderitem_bookList=qr.query(sql, new MapListHandler(), order.getOid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderitem_bookList;
	}

	public Order findOrder(String oid) {
		String sql = "select * from orders where oid=?";
		Order order=null;
		try {
			order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	public int getState(String oid) {
		String sql ="select state from orders where oid=?";
		int state=3;
		try {
			state =qr.query(sql, new ScalarHandler<Integer>(), oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public void updateState(String oid,int state) {
		String sql = "update orders set state = ? where oid=?";
		try {
			qr.update(sql, state,oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeOrder(String oid) {
		String sql = "delete from orders where oid=?";
		try {
			qr.update(sql,oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeOrderItem(String iid) {
		String sql = "delete from orderitem where iid=?";
		try {
			qr.update(sql,iid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Order> findAllOrder() {
		String sql = "select * from orders";
		List<Order> orderList=null;
		try {
			orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	
	public List<Order> findCategoryOrder(int state) {
		String sql = "select * from orders where state=?";
		List<Order> orderList=null;
		try {
			orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
}
