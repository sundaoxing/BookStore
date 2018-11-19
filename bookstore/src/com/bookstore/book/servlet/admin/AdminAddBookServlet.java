package com.bookstore.book.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bookstore.book.domain.Book;
import com.bookstore.book.service.BookService;
import com.bookstore.category.domain.Category;
import com.utils.CommonUtils;

/**
 * 处理图片上传
 */
@WebServlet("/admin/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book book = getBook(req,resp);
		bookService.add(book);
		req.getRequestDispatcher("/admin/AdminBookServlet?method=findAllBook").forward(req, resp);
	}
	private Book getBook(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException {
		/*
		 * 获取表单文件
		 * 1.获得工厂
		 * 2.根据工厂获取解析器
		 * 3.解析request
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setFileSizeMax(25*1024);
		List<FileItem> fileItemList=null;
		try {
			fileItemList =fileUpload.parseRequest(req);
		} catch (FileUploadException e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				try {
					req.setAttribute("msg", "图片大小不超过25kb");
					req.getRequestDispatcher("/admin/AdminBookServlet?method=load").forward(req, resp);
					return null;
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		HashMap<String, String>map = new HashMap<>();
		for(FileItem fItem :fileItemList){
			if(fItem.isFormField()) {
				map.put(fItem.getFieldName(),fItem.getString("UTF-8"));
			}
		}
		Category cate = CommonUtils.toBean(map, Category.class);
		Book book = CommonUtils.toBean(map, Book.class);
		book.setCategory(cate);
		book.setBid(CommonUtils.uuid());
		/*
		 * 保存上传的文件
		 * 1.保存目录
		 * 2.保存文件名称
		 */
		String saveName = "F:\\EclipseJava\\bookstore\\WebContent\\book_img";
		System.out.println(saveName);
		String name = new String(fileItemList.get(1).getName().getBytes(),"UTF-8");
		String fileName = CommonUtils.uuid()+"_"+name;
		File destFile = new File(saveName,fileName);
		System.out.println(destFile.getAbsolutePath());
		try {
			fileItemList.get(1).write(destFile);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		book.setImage("book_img/"+fileName);
		
		return book;
	}
}
