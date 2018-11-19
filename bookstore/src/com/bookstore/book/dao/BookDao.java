package com.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.bookstore.book.domain.Book;
import com.bookstore.category.domain.Category;
import com.jdbc.JdbcUtils_DBCP;
import com.utils.CommonUtils;

public class BookDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	public List<Book> findAllBook(){
		String sql = "select * from book where del=false";
		List<Book> bookList = null;
		try {
			bookList = qr.query(sql, new BeanListHandler<>(Book.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}
	
	public List<Book> findCategoryBook(String cid){
		String sql = "select * from book where cid=? and del=false";
		List<Book> bookList = null;
		try {
			bookList = qr.query(sql, new BeanListHandler<>(Book.class),cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}
	
	public Book load(String bid){
		String sql = "select * from book where bid=?";
		Book book = null;
		try {
			Map<String,Object> map=qr.query(sql, new MapHandler(), bid);
			Category cate = CommonUtils.toBean(map, Category.class);
			book = CommonUtils.toBean(map, Book.class);
			book.setCategory(cate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	public void add(Book book) {
		String sql="insert into book values(?,?,?,?,?,?,?)";
		Object [] params= {book.getBid(),book.getBname()
				,book.getPrice(),book.getAuthor(),book.getImage()
				,book.getCategory().getCid(),false};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(String bid) {
		String sql = "update book set del=true where bid=?";
		try {
			qr.update(sql, bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Book book) {
		String sql="update book set bname=?,price=?,author=?,image=?,cid=? where bid=?";
		Object [] params= {book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCategory().getCid(),book.getBid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
