package com.jay.eshop.auth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jay.eshop.auth.dao.AccountDAO;
import com.jay.eshop.auth.domain.AccountDO;
import com.jay.eshop.auth.domain.AccountQuery;
import com.jay.eshop.auth.mapper.AccountMapper;

/**
 * 账户管理模块DAO组件
 * @author Jay
 *
 */
@Repository
public class AccountDAOImpl implements AccountDAO {
	
	/**
	 * 账号管理mapper组件
	 */
	@Autowired
	private AccountMapper accountMapper;
	
	/**
	 * 分页查询账号
	 * @param query 账号查询条件
	 * @return 账号
	 */
	public List<AccountDO> listByPage(AccountQuery query) {
		return accountMapper.listByPage(query);
	}
	
	/**
	 * 根据id查询账号
	 * @param id 账号id 
	 * @return 账号
	 */
	public AccountDO getById(Long id) {
		return accountMapper.getById(id);
	}
	
	/**
	 * 新增账号
	 * @param account 账号
	 */
	public Long save(AccountDO account) {
		accountMapper.save(account);
		return account.getId();
	}
	
	/**
	 * 更新账号
	 * @param account 账号
	 */
	public void update(AccountDO account) {
		accountMapper.update(account);
	}
	
	/**
	 * 删除账号
	 * @param account 账号
	 */
	public void remove(Long id) {
		accountMapper.remove(id);
	}
	
	/**
	 * 更新密码
	 * @param account 账号
	 */
	public void updatePassword(AccountDO account) {
		accountMapper.updatePassword(account);
	}
}
