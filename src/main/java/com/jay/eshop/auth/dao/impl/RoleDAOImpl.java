package com.jay.eshop.auth.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jay.eshop.auth.dao.RoleDAO;
import com.jay.eshop.auth.domain.RoleDO;
import com.jay.eshop.auth.domain.RoleQuery;
import com.jay.eshop.auth.mapper.RoleMapper;

/**
 * 角色管理模块DAO组件
 * @author Jay
 *
 */
@Repository
public class RoleDAOImpl implements RoleDAO{

	public Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);
	
	/**
	 * 角色管理模块mapper组件
	 */
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 分页查询角色
	 * @param query 查询条件
	 * @return 角色DO对象集合
	 */
	public List<RoleDO> listByPage(RoleQuery query){
		try {
			return roleMapper.listByPage(query);
		} catch (Exception e) {
			logger.error("error", e);
			return null;
		}
	}
	
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	public RoleDO getById(Long id) {
		try {
			return roleMapper.getById(id);
		} catch (Exception e) {
			logger.error("error", e);
			return null;
		}
	}
	
	/**
	 * 新增角色
	 * @param role 角色
	 */
	public Long save(RoleDO role) {
		try {
			roleMapper.save(role);
			return role.getId();
		} catch (Exception e) {
			logger.error("error", e);
			return null;
		}
	}
	
	/**
	 * 更新角色
	 * @param role 角色对象 
	 */
	public Boolean update(RoleDO role) {
		try {
			roleMapper.update(role);
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 删除角色
	 * @param id 角色id
	 */
	public Boolean delete(Long id) {
		try {
			roleMapper.delete(id);
		} catch (Exception e) {
			logger.error("error", e);
			return false;
		}
		return true;			
	}
}
