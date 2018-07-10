package com.jay.eshop.auth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jay.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.jay.eshop.auth.domain.AccountRoleRelationshipDO;
import com.jay.eshop.auth.mapper.AccountRoleRelationshipMapper;

/**
 * 用户角色关系管理模块DAO组件
 * @author Jay
 *
 */
@Repository
public class AccountRoleRelationshipDAOImpl implements AccountRoleRelationshipDAO{


	/**
	 * 账号角色关系管理模块mapper组件
	 */
	@Autowired
	private AccountRoleRelationshipMapper accountRoleRelationMapper;
	
	/**
	 * 根据角色id来查询记录数
	 * @param roleId 角色id
	 * @return 记录数
	 */
	public Long countByRoleId(Long roleId) {
		return accountRoleRelationMapper.countByRoleId(roleId);
	}

	/**
	 * 根据账号id查询账号和角色关联关系
	 * @param accountId 账号id
	 * @return 账号和角色关联关系
	 */
	public List<AccountRoleRelationshipDO> listByAccountId(Long accountId) {
		return accountRoleRelationMapper.listByAccountId(accountId);
	}
	
	/**
	 * 根据角色id查询账号id集合
	 * @param roleId 账号id
	 * @return 账号id集合
	 */
	public List<Long> listAccountIdsByRoleId(Long roleId){
		return accountRoleRelationMapper.listAccountIdsByRoleId(roleId);
	}
	
	/**
	 * 新增账号和角色的关联关系
	 * @param relation 账号和角色的关联关系
	 */
	public void save(AccountRoleRelationshipDO relation) {
		accountRoleRelationMapper.save(relation);
	}
	
	/**
	 * 根据账号id删除账号和角色的关联关系
	 * @param accountId 账号id
	 */
	public void removeByAccountId(Long accountId) {
		accountRoleRelationMapper.removeByAccountId(accountId);;
	}
}
