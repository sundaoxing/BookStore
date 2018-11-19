package com.bookstore.category.service;

import java.util.List;

import com.bookstore.category.dao.CategoryDao;
import com.bookstore.category.domain.Category;
import com.bookstore.exception.CategoryException;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();

	public List<Category> FindAllCategory() {
		return categoryDao.FindAllCategory();
	}

	public void addCategory(Category category) throws CategoryException {
		List<Category> cateList = FindAllCategory();
		for (Category cate : cateList) {
			if (cate.getCname().equalsIgnoreCase(category.getCname())) {
				throw new CategoryException("分类名称已存在,请重新定义分类");
			}
		}
		categoryDao.addCategory(category);

	}

	public void remove(String cid) throws CategoryException {
		int count = categoryDao.findBookCount(cid);
		if (count == 0) {
			categoryDao.remove(cid);
		} else {
			throw new CategoryException("该分类下有图书存在，无法删除，请先删除该分类下图书");
		}
	}

	public void update(String cid, String cname) {
		categoryDao.update(cid, cname);
	}
	
	public Category findCategory(String cid) {
		return categoryDao.findCategory(cid);
	}
}
