package com.utils;

import java.util.Map;
import java.util.UUID;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author sx
 *		工具包
 */
public class CommonUtils {
	/**
	 * 生成不重复的32为大写字符串
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-","").toUpperCase();
	}
	
	/**
	 * 把map集合封装到bean对象中
	 * map的key键的值和bean对象的属性值保持一致
	 */
	public static <T> T toBean(Map<String,?> map,Class<T> classBean) {
		//创建指定类型的javaBean对象
		try {
			T bean =  classBean.newInstance();
			//把map集合中的数据封装到Bean对象中
			BeanUtils.populate(bean,map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	} 
}

