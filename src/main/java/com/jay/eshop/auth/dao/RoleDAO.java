package com.jay.eshop.auth.dao;

import java.util.List;

import com.jay.eshop.auth.domain.RoleDO;
import com.jay.eshop.auth.domain.RoleQuery;

/**
 * 角色管理模块DAO组件接口
 * @author Jay
 *
 */
public interface RoleDAO {
	
	/**
	 * 分页查询角色
	 * @param query 查询条件
	 * @return 角色DO对象集合
	 */
	List<RoleDO> listByPage(RoleQuery query);
	
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	RoleDO getById(Long id);
	
	/**
	 * 新增角色
	 * @param role 角色
	 */
	Long save(RoleDO role);
	
	/**
	 * 更新角色
	 * @param role 角色对象 
	 */
	Boolean update(RoleDO role);
	
	/**
	 * 删除角色
	 * @param id 角色id
	 */
	Boolean delete(Long id);
}
