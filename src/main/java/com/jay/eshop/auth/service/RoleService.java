package com.jay.eshop.auth.service;

import java.util.List;

import com.jay.eshop.auth.domain.RoleDTO;
import com.jay.eshop.auth.domain.RoleQuery;

/**
 * 角色管理模块service组件接口
 * @author Jay
 *
 */
public interface RoleService {
	
	/**
	 * 分页查询角色
	 * @param query 查询条件
	 * @return 角色DO对象集合
	 */
	List<RoleDTO> listByPage(RoleQuery query) throws Exception ;
	
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	RoleDTO getById(Long id) throws Exception ;
	
	/**
	 * 新增角色
	 * @param role 角色
	 */
	Boolean save(RoleDTO role) throws Exception ;
	
	/**
	 * 更新角色
	 * @param role 角色对象 
	 */
	Boolean update(RoleDTO role) throws Exception ;
	
	/**
	 * 删除角色
	 * @param id 角色id
	 */
	Boolean remove(Long id) throws Exception ;
}
