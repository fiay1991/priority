package com.jay.eshop.auth.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jay.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.jay.eshop.auth.domain.RolePriorityRelationshipDO;
import com.jay.eshop.auth.mapper.RolePriorityRelationshipMapper;

/**
 * 角色和权限关系模块DAO组件
 * @author jayjluo
 *
 */
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO{

	private static final Logger logger = LoggerFactory.getLogger(RolePriorityRelationshipDAOImpl.class);
	
	/**
	 * 角色和权限关系管理模块的mapper组件
	 */
	@Autowired
	private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;
	
	/**
	 * 根据权限id查询记录数
	 * @param priorityId 权限id
	 * @return 记录数
	 */
	@Override
	public Long getCountByPriorityId(Long priorityId) {
		try {
			return rolePriorityRelationshipMapper.getCountByPriorityId(priorityId);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return 0L;
	}

	/**
	 * 新增账号和权限的关联关系
	 * @param accountPriorityRelationshipDO
	 */
	@Override
	public Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO) {
		try {
			rolePriorityRelationshipMapper.save(rolePriorityRelationshipDO); 
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
		return true;
	}

	/**
	 * 根据角色id查询角色和权限的关系
	 * @param roleId 角色id
	 * @return 角色权限关系DO对象集合
	 */
	public List<RolePriorityRelationshipDO> listByRoleId(Long roleId){
		try {
			return rolePriorityRelationshipMapper.listByRoleId(roleId);
		} catch (Exception e) {
			logger.error("error", e);
			return null;
		}
	}
	
	/**
	 * 根据角色id删除角色权限关联关系
	 * @param roleId 角色id
	 */
	public Boolean removeByRoleId(Long roleId) {
		try {
			rolePriorityRelationshipMapper.removeByRoleId(roleId); 
		} catch (Exception e) {
			logger.error("error", e);
		}
		return true;
	}

}
