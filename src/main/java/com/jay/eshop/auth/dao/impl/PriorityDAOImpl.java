package com.jay.eshop.auth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jay.eshop.auth.dao.PriorityDAO;
import com.jay.eshop.auth.domain.PriorityDO;
import com.jay.eshop.auth.mapper.PriorityMapper;

/**
 * 权限中心DAO组件
 * @author Jay
 *
 */
@Repository
public class PriorityDAOImpl implements PriorityDAO{

	
	/**
	 * 权限管理模块的mapper组件
	 */
	@Autowired
	private PriorityMapper priorityMapper;
	
	/**
	 * 查询根权限
	 * @return 根权限集合
	 */
	public List<PriorityDO> listRootPriorities() {
		return priorityMapper.listRootPriorities();
	}

	/**
	 * 根据父权限id查询子权限
	 * @param parentId 父权限id
	 * @return 子权限
	 */
	public List<PriorityDO> listChildPriorities(Long parentId) {
		return priorityMapper.listChildPriorities(parentId);
	}

	/**
	 * 根据id查询权限
	 * @param id 权限id
	 * @return 权限
	 */
	public PriorityDO getPriorityById(Long id) {
		return priorityMapper.getPriority(id);
	}

	/**
	 * 新增权限
	 * @param priorityDO 权限DO对象
	 */
	public Long savePriority(PriorityDO priorityDO) {
		priorityMapper.savePriority(priorityDO);
		return priorityDO.getId();
	}

	/**
	 * 更新全新
	 * @param priorityDO 权限DO对象
	 * @return 处理结果
	 */
	public Boolean updatePriority(PriorityDO priorityDO) {
		priorityMapper.updatePriority(priorityDO);
		return true;
	}
	
	/**
	 * 删除权限
	 * @param id 权限id
	 */
	public Boolean removePriority(Long id) {
		priorityMapper.removePriority(id);
		return true;
	}
	
	/**
	 * 查询账号被授权的根菜单
	 * @param accountId 账号id
	 * @param parentId 权限父id
	 * @return
	 */
	public List<PriorityDO> listAuthorizedByAccountId(Long accountId, Long parentId){
		return priorityMapper.listAuthorizedByAccountId(accountId, parentId);
	}
	
	/**
	 * 根据权限编号判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	public Long countAuthorizedByCode(Long accountId, String code) {
		return priorityMapper.countAuthorizedByCode(accountId, code);
	}
	
	/**
	 * 根据权限URL判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	public Long countAuthorizedByUrl(Long accountId, String url) {
		return priorityMapper.countAuthorizedByUrl(accountId, url);
	}

	/**
	 * 根据权限id查询账户id
	 * @param priorityId 权限id
	 * @return 账号id集合
	 */
	public List<Long> listAccountIdsByPriorityId(Long priorityId) {
		return priorityMapper.listAccountIdsByPriorityId(priorityId);
	}
}
