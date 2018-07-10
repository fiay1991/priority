package com.jay.eshop.auth.dao;

import java.util.List;

import com.jay.eshop.auth.domain.RolePriorityRelationshipDO;

/**
 * 角色和权限关系模块的DAO组件
 * @author jayjluo
 *
 */
public interface RolePriorityRelationshipDAO {
	
	/**
	 * 新增角色和权限的关联关系
	 * @param rolePriorityRelationshipDO
	 */
	Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO);
	
	/**
	 * 根据权限id，查询记录总数
	 * @param priorityId 权限id
	 * @return
	 */
	Long getCountByPriorityId(Long priorityId);
	
	/**
	 * 根据角色id查询角色和权限的关系
	 * @param roleId 角色id
	 * @return 角色权限关系DO对象集合
	 */
	List<RolePriorityRelationshipDO> listByRoleId(Long roleId);
	
	/**
	 * 根据角色id删除角色权限关联关系
	 * @param roleId 角色id
	 */
	Boolean removeByRoleId(Long roleId);
}
