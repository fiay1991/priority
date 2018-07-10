package com.jay.eshop.auth.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jay.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.jay.eshop.auth.domain.AccountPriorityRelationshipDO;
import com.jay.eshop.auth.mapper.AccountPriorityRelationshipMapper;

/**
 * 账户和权限关系模块DAO组件
 * @author jayjluo
 *
 */
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {

	public static final Logger logger = LoggerFactory.getLogger(AccountPriorityRelationshipDAOImpl.class);
	
	/**
	 * 账户和权限关系模块mapper组件
	 */
	@Autowired
	private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;
	
	/**
	 * 根据权限id查询记录数
	 * @param priorityId 权限id
	 * @return 记录数
	 */
	public Long getCountByPriorityId(Long priorityId) {
		try {
			return accountPriorityRelationshipMapper.getCountByPriorityId(priorityId);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * 新增账号和权限的关联关系
	 * @param accountPriorityRelationshipDO
	 */
	public Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO) {
		try {
			accountPriorityRelationshipMapper.save(accountPriorityRelationshipDO);
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
		return true;
	}

	/**
	 * 根据账号id查询账号和权限的关联关系
	 * @param accountId 账号id
	 * @return 账号和权限的关联关系
	 */
	public List<AccountPriorityRelationshipDO> listByAccountId(Long accountId) {
		return accountPriorityRelationshipMapper.listByAccountId(accountId);
	}	
	
	/**
	 * 根据账号id删除账号和权限的关联关系
	 * @param accountId 账号id
	 */
	public void removeByAccountId(Long accountId) {
		accountPriorityRelationshipMapper.removeByAccountId(accountId);
	}
}
