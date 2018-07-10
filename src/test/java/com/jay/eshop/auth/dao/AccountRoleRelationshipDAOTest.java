package com.jay.eshop.auth.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jay.eshop.auth.domain.AccountRoleRelationshipDO;
import com.jay.eshop.common.util.DateProvider;


/**
 * 账户和角色关联关系DAO单元测试
 * @author Jay
 *
 */
@RunWith(SpringRunner.class) 
@SpringBootTest
@Transactional 
@Rollback(true)
public class AccountRoleRelationshipDAOTest {

	/**
	 * 账号和角色关系管理模块的DAO组件
	 */
	@Autowired
	private AccountRoleRelationshipDAO accountRoleRelationshipDAO;
	
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 测试新增账号和角色的关联关系
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {
		Long accountId = 1L;
		Long roleId = 1L;
		AccountRoleRelationshipDO accountRoleRelationshipDO = 
				createAccountRoleRelationshipDO(accountId, roleId);
		assertNotNull(accountRoleRelationshipDO.getId()); 
		assertThat(accountRoleRelationshipDO.getId(), greaterThan(0L));  
	}
	
	/**
	 * 测试根据角色id查询记录数
	 * @throws Exception
	 */
	@Test
	public void testCountByRoleId() throws Exception {
		Long roleId = 1L;
		
		Long accountId1 = 1L;
		createAccountRoleRelationshipDO(accountId1, roleId);
		
		Long accountId2 = 2L;
		createAccountRoleRelationshipDO(accountId2, roleId);
		
		Long resultCount = accountRoleRelationshipDAO.countByRoleId(roleId);
		
		assertEquals(2L, resultCount.longValue());  
	}
	
	/**
	 * 测试根据账号id查询账号和角色的关联关系
	 * @throws Exception
	 */
	@Test
	public void testListByAccountId() throws Exception {
		Long accountId = 1L;
		int count = 20;
		Map<Long, AccountRoleRelationshipDO> relationMap = 
				createRelations(accountId, count);
		
		List<AccountRoleRelationshipDO> resultRelations = 
				accountRoleRelationshipDAO.listByAccountId(accountId);
		
		compareRelations(relationMap, resultRelations); 
	}
	
	/**
	 * 测试根据账号id删除账号和角色的关联关系
	 * @throws Exception
	 */
	@Test
	public void testRemoveByAccountId() throws Exception {
		Long accountId = 1L;
		int count = 20;
		createRelations(accountId, count);
		
		accountRoleRelationshipDAO.removeByAccountId(accountId); 
		
		List<AccountRoleRelationshipDO> resultRelations = 
				accountRoleRelationshipDAO.listByAccountId(accountId);
		
		assertEquals(0, resultRelations.size()); 
	}
	
	/**
	 * 比较两个关联关系集合
	 * @param relationMap
	 * @param resultRelations
	 * @throws Exception
	 */
	private void compareRelations(Map<Long, AccountRoleRelationshipDO> relationMap,
			List<AccountRoleRelationshipDO> resultRelations) throws Exception {
		assertThat(resultRelations.size(), greaterThanOrEqualTo(relationMap.size())); 
		
		for(AccountRoleRelationshipDO relation : resultRelations) {
			AccountRoleRelationshipDO targetRelation = relationMap.get(relation.getId());
			if(targetRelation == null) {
				continue;
			}
			assertEquals(targetRelation, relation);
		}
	}
	
	/**
	 * 创建账号和角色关联关系的集合
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	private Map<Long, AccountRoleRelationshipDO> createRelations(
			Long accountId, int count) throws Exception {
		Map<Long, AccountRoleRelationshipDO> relationMap = 
				new HashMap<Long, AccountRoleRelationshipDO>();
	
		for(int i = 0; i < count; i++) {
			AccountRoleRelationshipDO relation = 
					createAccountRoleRelationshipDO(accountId, (long)i);
			relationMap.put(relation.getId(), relation);
		}
		
		return relationMap;
	}
	
	/**
	 * 创建账号和角色关系DO对象
	 * @return 账号和角色关系DO对象
	 * @throws Exception
	 */
	private AccountRoleRelationshipDO createAccountRoleRelationshipDO(
			Long accountId, Long roleId) throws Exception {
		AccountRoleRelationshipDO accountRoleRelationshipDO = 
				new AccountRoleRelationshipDO();
		accountRoleRelationshipDO.setAccountId(accountId);
		accountRoleRelationshipDO.setRoleId(roleId); 
		accountRoleRelationshipDO.setGmtCreate(dateProvider.getCurrentTime()); 
		accountRoleRelationshipDO.setGmtModified(dateProvider.getCurrentTime()); 
		
		accountRoleRelationshipDAO.save(accountRoleRelationshipDO);
		
		return accountRoleRelationshipDO;
	}
}
