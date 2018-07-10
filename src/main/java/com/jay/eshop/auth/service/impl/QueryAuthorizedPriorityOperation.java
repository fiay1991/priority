package com.jay.eshop.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jay.eshop.auth.dao.PriorityDAO;
import com.jay.eshop.auth.domain.PriorityDO;

/**
 * 查询授权权限的操作
 * @author Jay
 *
 */
@Component
@Scope("prototype")
public class QueryAuthorizedPriorityOperation implements PriorityOperation<Boolean> {
	
	/**
	 * 账户id
	 */
	private Long accountId;
	/**
	 * 权限管理DAO组件
	 */
	@Autowired
	private PriorityDAO priorityDAO;
	
	/**
	 * 构造方法
	 * @param accountId 账户id
	 */
	public QueryAuthorizedPriorityOperation(Long accountId) {
		this.accountId = accountId;
	}


	/**
	 * 执行操作
	 */
	public Boolean doExecute(Priority priority) throws Exception {
		List<Priority> targetChildren = new ArrayList<Priority>();
		
		List<PriorityDO> children = priorityDAO.listAuthorizedByAccountId(accountId, priority.getId());
		for(PriorityDO child : children) {
			Priority targetChild = child.clone(Priority.class);
			targetChild.execute(this);
			targetChildren.add(targetChild);
		}
		priority.setChildrenPriority(targetChildren);
		return true;
	}

}
