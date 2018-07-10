package com.jay.eshop.auth.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.eshop.auth.dao.AccountDAO;
import com.jay.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.jay.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.jay.eshop.auth.domain.AccountDO;
import com.jay.eshop.auth.domain.AccountDTO;
import com.jay.eshop.auth.domain.AccountPriorityRelationshipDO;
import com.jay.eshop.auth.domain.AccountPriorityRelationshipDTO;
import com.jay.eshop.auth.domain.AccountQuery;
import com.jay.eshop.auth.domain.AccountRoleRelationshipDO;
import com.jay.eshop.auth.domain.AccountRoleRelationshipDTO;
import com.jay.eshop.auth.service.AccountService;
import com.jay.eshop.common.util.DateProvider;
import com.jay.eshop.common.util.ObjectUtils;

/**
 * 
 * @author Jay
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	/**
	 * 账号管理DAO组件
	 */
	@Autowired
	private AccountDAO accountDAO;
	/**
	 * 账号和角色关系管理DAO组件
	 */
	@Autowired
	private AccountRoleRelationshipDAO roleRelationDAO;
	/**
	 * 账号和权限关系管理DAO组件
	 */
	@Autowired
	private AccountPriorityRelationshipDAO priorityRelationDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 权限缓存管理组件
	 */
	@Autowired
	private PriorityCacheManager priorityCacheManager;
	
	/**
	 * 分页查询账号
	 * @param query 查询条件
	 * @return 账号
	 */
	public List<AccountDTO> listByPage(AccountQuery query) throws Exception {
		List<AccountDO> accounts = accountDAO.listByPage(query);
		List<AccountDTO> resultAccounts = 
				ObjectUtils.convertList(accounts, AccountDTO.class);
		return resultAccounts;
	}
	
	/**
	 * 根据id查询账号
	 * @param id 账号id
	 * @return 账号
	 */
	public AccountDTO getById(Long id) throws Exception {
		AccountDO account = accountDAO.getById(id);
		AccountDTO resultAccount = account.clone(AccountDTO.class);
		List<AccountRoleRelationshipDO> roleRelaitons = 
				roleRelationDAO.listByAccountId(id);
		List<AccountRoleRelationshipDTO> resultRoleRelations =
				ObjectUtils.convertList(roleRelaitons, AccountRoleRelationshipDTO.class);
		resultAccount.setRoleRelations(resultRoleRelations);
		
		List<AccountPriorityRelationshipDO> priorityRelaitons =
				priorityRelationDAO.listByAccountId(id);
		List<AccountPriorityRelationshipDTO> resultPriorityRelations =
				ObjectUtils.convertList(priorityRelaitons, AccountPriorityRelationshipDTO.class);
		resultAccount.setPriorityRelations(resultPriorityRelations);
		
		return resultAccount;
	}
 	
	/**
	 * 新增账号
	 * @param account 账号
	 * @return 处理结果
	 */
	public void save(AccountDTO account) throws Exception {
		account.setGmtCreate(dateProvider.getCurrentTime());
		account.setGmtModified(dateProvider.getCurrentTime());
		Long accountId = accountDAO.save(account.clone(AccountDO.class));
		
		account.setId(accountId);
		
		saveRelations(account);
		
	}
	
	/**
	 * 更新账号
	 * @param account 账号
	 * @return 处理结果
	 */
	public void update(AccountDTO account) throws Exception {
		account.setGmtModified(dateProvider.getCurrentTime());
		accountDAO.update(account.clone(AccountDO.class));
		
		roleRelationDAO.removeByAccountId(account.getId());
		priorityRelationDAO.removeByAccountId(account.getId());
		
		saveRelations(account);
		
		priorityCacheManager.remove(account.getId());
	}
	
	/**
	 * 更新密码
	 * @param account 账号
	 */
	public void updatePassword(AccountDTO account) throws Exception {
		account.setGmtModified(dateProvider.getCurrentTime());
		accountDAO.updatePassword(account.clone(AccountDO.class));
		
	}
	
	/**
	 * 删除账号
	 * @param id 账号id
	 * @return 处理结果
	 */
	public void remove(Long id) throws Exception {
		roleRelationDAO.removeByAccountId(id);
		priorityRelationDAO.removeByAccountId(id);
		accountDAO.remove(id);
		priorityCacheManager.remove(id);
	}
	
	/**
	 * 新增关联关系
	 * @param account 账户信息
	 */
	private void saveRelations(AccountDTO account) throws Exception {
		// 保存账号角色关联关系
		for(AccountRoleRelationshipDTO roleRelation : account.getRoleRelations()) {
			roleRelation.setAccountId(account.getId());
			roleRelation.setGmtCreate(dateProvider.getCurrentTime());
			roleRelation.setGmtModified(dateProvider.getCurrentTime());
			roleRelationDAO.save(roleRelation.clone(AccountRoleRelationshipDO.class));
		}
		
		// 保存账号权限关联关系
		for(AccountPriorityRelationshipDTO priorityRelation : account.getPriorityRelations()) {
			priorityRelation.setAccountId(account.getId());
			priorityRelation.setAccountId(account.getId());
			priorityRelation.setGmtCreate(dateProvider.getCurrentTime());
			priorityRelation.setGmtModified(dateProvider.getCurrentTime());
			priorityRelationDAO.save(priorityRelation.clone(AccountPriorityRelationshipDO.class));
		}
	}
}
