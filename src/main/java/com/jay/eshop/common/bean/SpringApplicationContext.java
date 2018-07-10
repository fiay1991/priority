package com.jay.eshop.common.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * spring容器组件
 * @author Jay
 *
 */
@Component
public class SpringApplicationContext {

	/**
	 * spring容器
	 */
	private ApplicationContext context;

	/**
	 * 构造方法
	 * @param context
	 */
	@Autowired
	public SpringApplicationContext(ApplicationContext context) {
		this.context = context;
	}
	
	/**
	 * 获取bean
	 * @param clazz bean类型
	 * @return bean实例
	 */
	public <T> T getBean(Class<? extends T > clazz) {
		return context.getBean(clazz);
	}
}
