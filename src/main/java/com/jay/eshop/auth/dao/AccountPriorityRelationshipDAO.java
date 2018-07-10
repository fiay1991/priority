package com.jay.eshop.auth.dao;

import java.util.List;

import com.jay.eshop.auth.domain.AccountPriorityRelationshipDO;

/**
 * 账号和权限关系管理模块的dao组件
 * @author jayjluo
 *
 */
public interface AccountPriorityRelationshipDAO {

	/**
	 * 新增账号和权限的关联关系
	 * @param accountPriorityRelationshipDO
	 */
	Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);
	
	/**
	 * 根据权限id，查询记录总数
	 * @param priorityId 权限id
	 * @return
	 */
	Long getCountByPriorityId(Long priorityId);
	
	/**
	 * 根据账号id查询账号和权限的关联关系
	 * @param accountId 账号id
	 * @return 账号和权限的关联关系
	 */
	List<AccountPriorityRelationshipDO> listByAccountId(Long accountId);
	
	/**
	 * 根据账号id删除账号和权限的关联关系
	 * @param accountId 账号id
	 */
	void removeByAccountId(Long accountId);
}
