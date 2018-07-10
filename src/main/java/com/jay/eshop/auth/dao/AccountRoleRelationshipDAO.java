package com.jay.eshop.auth.dao;

import java.util.List;

import com.jay.eshop.auth.domain.AccountRoleRelationshipDO;

/**
 * 用户角色关系管理模块DAO组件接口
 * @author Jay
 *
 */
public interface AccountRoleRelationshipDAO {

	/**
	 * 根据角色id查询记录数
	 * @param roleId
	 * @return
	 */
	Long countByRoleId(Long roleId);
	
	/**
	 * 根据账号id查询账号和角色关联关系
	 * @param accountId 账号id
	 * @return 账号和角色关联关系
	 */
	List<AccountRoleRelationshipDO> listByAccountId(Long accountId); 
	
	/**
	 * 根据角色id查询账号id集合
	 * @param roleId 账号id
	 * @return 账号id集合
	 */
	List<Long> listAccountIdsByRoleId(Long roleId);
	
	/**
	 * 新增账号和角色的关联关系
	 * @param relation 账号和角色的关联关系
	 */
	void save(AccountRoleRelationshipDO relation);
	
	/**
	 * 根据账号id删除账号和角色的关联关系
	 * @param accountId 账号id
	 */
	void removeByAccountId(Long accountId);
}
