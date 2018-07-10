package com.jay.eshop.auth.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.hamcrest.Matchers.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jay.eshop.auth.domain.RoleDO;
import com.jay.eshop.auth.domain.RoleQuery;
import com.jay.eshop.common.util.DateProvider;

/**
 * 角色管理模块dao组件单元测试类
 * @author Jay
 *
 */
@RunWith(SpringRunner.class) 
@SpringBootTest
@Transactional 
@Rollback(true)
public class RoleDAOTest {
	
	/**
	 * 角色管理模块DAO组件
	 */
	@Autowired
	private RoleDAO roleDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	
	/**
	 * 测试新增角色
	 */
	@Test
	public void testSave() throws Exception {
		RoleDO role = createRole("测试角色","TEST_CODE");
		assertNotNull(role.getId());
		assertThat(role.getId(), greaterThan(0L));
	}

	/**
	 * 测试分页查询
	 * @throws Exception
	 */
	@Test
	public void testListByPage() throws Exception {
		String namePrefix = "测试角色";
		String codePrefix = "TEST_CODE";
		String otherName = "别的角色";
		String otherCode = "OTHER_CODE";
		Integer offset = 2;
		Integer size = 2;
		
		// 构造6个角色出来
		// 其中5个角色都是以“测试角色”打头
		Map<Long, RoleDO> roleMap = new HashMap<Long, RoleDO>();
		
		RoleDO role = null;
		
		for(int i = 0; i < 5; i++) {
			role = createRole(namePrefix + i, codePrefix + i);
			roleMap.put(role.getId(), role);
		}
		
		role = createRole(otherName, otherCode); 
		
		RoleQuery query = new RoleQuery();
		query.setOffset(offset);
		query.setSize(size);
		query.setName(namePrefix);
		query.setCode(codePrefix);
		
		List<RoleDO> roles = roleDAO.listByPage(query);
		
		assertEquals((int)size, roles.size());
		
		for(RoleDO resultRole : roles) {
			assertEquals(roleMap.get(resultRole.getId()), resultRole);
		}
	}
	
	/**
	 * 测试根据id查询角色
	 * @throws Exception
	 */
	@Test
	public void testGetById() throws Exception {
		RoleDO role = createRole("测试角色", "TEST_CODE");
		RoleDO resultRole = roleDAO.getById(role.getId());
		assertEquals(role, resultRole);
	}
	
	/**
	 * 测试更新角色
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		RoleDO role = createRole("测试角色", "TEST_CODE");
		role.setName(role.getName() + "_修改");
		role.setCode(role.getCode() + "_modified");
		roleDAO.update(role);
		
		RoleDO resultRole = roleDAO.getById(role.getId());
		
		assertEquals(role, resultRole);
	}
	
	/**
	 * 测试删除角色
	 * @throws Exception
	 */
	@Test
	public void testRemove() throws Exception{
		RoleDO role = createRole("测试角色", "TEST_CODE");
		roleDAO.delete(role.getId());
		
		RoleDO resultRole = roleDAO.getById(role.getId());
		
		assertNull(resultRole);
	}
	
	/**
	 * 创建角色
	 * @param name 用户名
	 * @param code 用户编号
	 * @return
	 */
	private RoleDO createRole(String name, String code) throws Exception{
		RoleDO role = new RoleDO();
		role.setName(name);
		role.setCode(code);
		role.setRemark(name);
		role.setGmtCreate(dateProvider.getCurrentTime());
		role.setGmtModified(dateProvider.getCurrentTime());
		
		roleDAO.save(role);
		
		return role;
	}
}
