package com.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.category.domain.Category;
import com.jdbc.JdbcUtils_DBCP;

public class CategoryDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils_DBCP.getDataSource()); 
	
	public List<Category> FindAllCategory() {
		String sql = "select * from category";
		List<Category> categoryList =null;
		try {
			categoryList = qr.query(sql,new BeanListHandler<>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public void addCategory(Category cate) {
		String sql = "insert into category values(?,?)";
		try {
			qr.update(sql, cate.getCid(),cate.getCname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remove(String cid) {
		String sql="delete from category where cid=?";
		try {
			qr.update(sql, cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(String cid,String cname) {
		String sql="update category set cname=? where cid=?";
		try {
			qr.update(sql, cname,cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int findBookCount(String cid){
		String sql = "select count(*) from book where cid=?";
		int count =0;
		try {
			Number cou = qr.query(sql, new ScalarHandler<>(), cid);
			count = cou.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public Category findCategory(String cid) {
		String sql="select * from category where cid=?";
		Category cate = null;
		try {
			cate = qr.query(sql, new BeanHandler<Category>(Category.class), cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cate;
	}
	
}
