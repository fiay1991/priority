package com.jay.eshop.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.eshop.auth.dao.PriorityDAO;
import com.jay.eshop.auth.domain.PriorityDO;
import com.jay.eshop.auth.domain.PriorityDTO;
import com.jay.eshop.auth.service.PriorityService;
import com.jay.eshop.common.bean.SpringApplicationContext;
import com.jay.eshop.common.util.DateProvider;

/**
 * 权限管理模块service组件
 * @author Jay
 *
 */
@Service
@Transactional
public class PriorityServiceImpl implements PriorityService{

	/**
	 * 权限管理DAO组件 
	 */
	@Autowired
	private PriorityDAO priorityDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * spring容器组件
	 */
	@Autowired
	private SpringApplicationContext context;
	/**
	 * 权限缓存管理组件
	 */
	@Autowired
	private PriorityCacheManager priorityCacheManager;
	
	/**
	 * 查询根权限
	 */
	public List<PriorityDTO> listRootPriorities() throws Exception {
		List<PriorityDO> priorityDOs = priorityDAO.listRootPriorities();
		if(priorityDOs == null) {
			return null;
		}
		
		List<PriorityDTO> priorityDTOs = new ArrayList<PriorityDTO>(priorityDOs.size());
		for(PriorityDO priorityDO : priorityDOs) {
			priorityDTOs.add(priorityDO.clone(PriorityDTO.class));
		}
		
		return priorityDTOs;
	}
	
	/**
	 * 根据父权限id，查询子权限
	 * @param parentId 父权限id
	 * @return 子权限集合
	 */
	public List<PriorityDTO> listChildPriorities(Long parentId) throws Exception {
		List<PriorityDO> priorityDOs = priorityDAO.listChildPriorities(parentId);
		if(priorityDOs == null) {
			return null;
		}
		
		List<PriorityDTO> priorityDTOs = new ArrayList<PriorityDTO>(priorityDOs.size());
		for(PriorityDO priorityDO : priorityDOs) {
			priorityDTOs.add(priorityDO.clone(PriorityDTO.class));
		}
		
		return priorityDTOs;
			
	}

	/**
	 * 根据id查询权限
	 * @param id 权限id
	 * @return 权限
	 */
	public PriorityDTO getPriorityById(Long id) throws Exception {
		PriorityDO priorityDO = priorityDAO.getPriorityById(id);
		if(priorityDO == null) {
			return null;
		}
		
		return priorityDO.clone(PriorityDTO.class);
	}

	/**
	 * 查询账号被授权的根菜单
	 * @param accountId 账号id
	 * @param parentId 权限父id
	 * @return
	 */
	public List<Priority> listAuthorizedByAccountId(Long accountId) throws Exception {
		QueryAuthorizedPriorityOperation operation = context.getBean(QueryAuthorizedPriorityOperation.class);
		
		List<Priority> authorizedTree = priorityCacheManager.getAuthorizedPriorityTree(accountId);
		
		if(authorizedTree != null) {
			return authorizedTree;
		}
		
		authorizedTree = new ArrayList<Priority>();
		
		List<PriorityDO> authorizedRoots = priorityDAO
				.listAuthorizedByAccountId(accountId, null);
		
		for(PriorityDO root : authorizedRoots) {
			Priority targetRoot = root.clone(Priority.class);
			targetRoot.execute(operation);
			authorizedTree.add(targetRoot);
		}
		
		priorityCacheManager.cacheAuthorizedPriorityTree(accountId, authorizedTree);;
		
		return authorizedTree;
	}
	
	/**
	 * 根据权限编号判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	public Boolean existAuthorizedByCode(Long accountId, 
			String code) throws Exception {
		Boolean authorized = priorityCacheManager.getAuthorizedByCode(accountId, code);
		if(authorized != null) {
			return authorized;
		}
		
		Long count = priorityDAO.countAuthorizedByCode(accountId, code);
		authorized = count > 0 ? true : false;
		priorityCacheManager.cacheAuthorizedByCode(accountId, code, authorized);
		return authorized;
	}
	
	/**
	 * 根据权限URL判断账号是否对这个权限有授权记录
	 * @param accountId 账号id
	 * @param code 权限编号 
	 * @return 是否有授权记录
	 */
	public Boolean existAuthorizedByUrl(Long accountId, String url) throws Exception {
		Boolean authorized = priorityCacheManager.getAuthorizedByUrl(accountId, url);
		if(authorized != null) {
			return authorized;
		}
		
		Long count = priorityDAO.countAuthorizedByUrl(accountId, url);
		authorized = count > 0 ? true : false;
		priorityCacheManager.cacheAuthorizedByCode(accountId, url, authorized);
		return authorized;
	}
	
	/**
	 * 新增权限
	 * @param priorityDTO 权限DTO对象
	 * 
	 */
	public Boolean savePriority(PriorityDTO priorityDTO) throws Exception {
		priorityDTO.setGmtCreate(dateProvider.getCurrentTime());
		priorityDTO.setGmtModified(dateProvider.getCurrentTime());
		priorityDAO.savePriority(priorityDTO.clone(PriorityDO.class));
		return true;
	}

	/**
	 * 更新权限
	 * @param priorityDTO 权限DTO对象
	 */
	public Boolean updatePriority(PriorityDTO priorityDTO) throws Exception {
		priorityDTO.setGmtModified(dateProvider.getCurrentTime());
		priorityDAO.updatePriority(priorityDTO.clone(PriorityDO.class));
		
		List<Long> accountIds = priorityDAO.listAccountIdsByPriorityId(priorityDTO.getId());
		for(Long accountId : accountIds ) {
			priorityCacheManager.remove(accountId);
		}
		return true;
	}

	/**
	 * 删除权限
	 * @param id 权限id
	 */
	public Boolean removePriority(Long id) throws Exception  {
		//根据id查权限
		PriorityDO priorityDO = priorityDAO.getPriorityById(id);
		Priority priority= priorityDO.clone(Priority.class);
		
		//检查这个权限及其下任何一个子权限，是否有被角色或者账户关联着
		RelatedCheckPriorityOperation relatedCheckOperation = context.getBean(RelatedCheckPriorityOperation.class);
		Boolean relateCheckReslut = priority.execute(relatedCheckOperation);
		
		if(relateCheckReslut) {
			return false;
		}
		
		//递归删除当前权限以及其下所有子权限
		RemovePriorityOperation removeOperation = context.getBean(RemovePriorityOperation.class);
		priority.execute(removeOperation);
		return true;
	}

}
