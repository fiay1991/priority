package com.jay.eshop.auth.dao;

import java.util.List;

import com.jay.eshop.auth.domain.PriorityDO;

/**
 * 权限中心DAO组件接口
 * @author jayjluo
 *
 */
public interface PriorityDAO {

	/**
	 * 查询根权限
	 * @return 跟权限集合
	 */
	List<PriorityDO> listRootPriorities();
	/**
	 * 根据父权限id，查询子权限
	 * @param parentId 父权限id
	 * @return 子权限集合
	 */
	List<PriorityDO> listChildPriorities(Long parentId);
	/**
	 * 根据id查询权限
	 * @param id 权限id
	 * @return 权限
	 */
	PriorityDO getPriorityById(Long id);
	/**
	 * 新增权限
	 * @param priorityDO 权限DO对象
	 */
	Long savePriority(PriorityDO priorityDO); 
	/**
	 * 更新权限
	 * @param priorityDO 权限DO对象
	 */
	Boolean updatePriority(PriorityDO priorityDO);
	/**
	 * 删除权限
	 * @param id 权限id
	 */
	Boolean removePriority(Long id);
	
	/**
	 * 查询账号被授权的根菜单
	 * @param accountId 账号id
	 * @param parentId 权限父id
	 * @return
	 */
	List<PriorityDO> listAuthorizedByAccountId(Long accountId, Long parentId);
	
	/**
	 * 根据权限id查询账户id
	 * @param priorityId 权限id
	 * @return 账号id集合
	 */
	List<Long> listAccountIdsByPriorityId(Long priorityId);
	
	/**
	 * 根据权限编号判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	Long countAuthorizedByCode(Long accountId, String code);
	
	/**
	 * 根据权限URL判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	Long countAuthorizedByUrl(Long accountId, String url);
}
