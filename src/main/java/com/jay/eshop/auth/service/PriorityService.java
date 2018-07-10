package com.jay.eshop.auth.service;

import java.util.List;

import com.jay.eshop.auth.domain.PriorityDTO;
import com.jay.eshop.auth.service.impl.Priority;

/**
 * 权限管理模块service组件接口
 * @author jayjluo
 *
 */
public interface PriorityService {
	/**
	 * 查询根权限
	 * @return 根权限集合
	 */
	List<PriorityDTO> listRootPriorities() throws Exception;
	
	/**
	 * 根据父权限id查询子权限
	 * @param parentId 父权限id
	 * @return 子权限
	 */
	List<PriorityDTO> listChildPriorities(Long parentId) throws Exception;
	
	/**
	 * 根据id查询权限
	 * @param id 权限id
	 * @return 权限
	 */
	PriorityDTO getPriorityById(Long id) throws Exception;
	
	/**
	 * 新增权限
	 * @param priorityDO 权限DO对象
	 */
	Boolean savePriority(PriorityDTO priorityDTO) throws Exception; 
	
	/**
	 * 更新权限
	 * @param priorityDO 权限DO对象
	 */
	Boolean updatePriority(PriorityDTO priorityDTO) throws Exception;
	
	/**
	 * 删除权限
	 * @param id 权限id
	 * @return 处理结果
	 */
	Boolean removePriority(Long id) throws Exception;
	
	/**
	 * 查询账号被授权的根菜单
	 * @param accountId 账号id
	 * @param parentId 权限父id
	 * @return
	 */
	List<Priority> listAuthorizedByAccountId(Long accountId) throws Exception;
	
	/**
	 * 根据权限编号判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	Boolean existAuthorizedByCode(Long accountId, String code) throws Exception;
	
	/**
	 * 根据权限URL判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	Boolean existAuthorizedByUrl(Long accountId, String url) throws Exception;
}
