package com.jay.eshop.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jay.eshop.auth.dao.PriorityDAO;
import com.jay.eshop.auth.domain.PriorityDO;

/**
 * 权限树节点删除访问者
 * @author jayjluo
 *
 */
@Component
@Scope("prototype") 
public class RemovePriorityOperation implements PriorityOperation<Boolean>{
	
	/**
	 * 权限管理模块的DAO组件
	 */
	@Autowired
	private PriorityDAO priorityDAO;


	/**
	 * 权限树节点访问者
	 */
	public Boolean doExecute(Priority node) throws Exception {
		List<PriorityDO> priorityDOs = priorityDAO.listChildPriorities(node.getId());
		if(priorityDOs != null && priorityDOs.size() > 0) {
			for(PriorityDO priorityDO : priorityDOs) {
				Priority priorityNode = priorityDO.clone(Priority.class);
				priorityNode.execute(this);
			}
		}
		
		removePriority(node);
		return true;
	}

	/**
	 * 删除权限
	 * @param node
	 */
	private void removePriority(Priority node) {
		priorityDAO.removePriority(node.getId());
	}
	
	
}
