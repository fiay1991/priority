package com.jay.eshop.auth.service.impl;

public interface PriorityOperation<T> {

	/**
	 * 访问权限树节点
	 * @param nopriorityde
	 */
	 T doExecute(Priority priority) throws Exception;
	
}
