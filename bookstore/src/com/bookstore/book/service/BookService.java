package com.bookstore.book.service;

import java.util.List;

import com.bookstore.book.dao.BookDao;
import com.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	public List<Book> findAllBook(){
		return bookDao.findAllBook();
	}
	
	public List<Book> findCategoryBook(String cid){
		return bookDao.findCategoryBook(cid);
	}
	
	public Book load(String bid){
		return bookDao.load(bid);
	}

	public void add(Book book) {
		if(book != null) {
			bookDao.add(book);
		}
		
	}

	public void delete(String bid) {
		bookDao.delete(bid);
	}

	public void update(Book book) {
		bookDao.update(book);
	}
}
