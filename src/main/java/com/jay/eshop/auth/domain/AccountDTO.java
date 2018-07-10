package com.jay.eshop.auth.domain;

import java.util.Date;
import java.util.List;

import com.jay.eshop.common.util.AbstractObject;

/**
 * 用户账号DTO类
 * @author Jay
 *
 */
public class AccountDTO extends AbstractObject{

	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 员工姓名
	 */
	private String name;
	/**
	 * 账号备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 用户账号和角色关联关系
	 */
	private List<AccountRoleRelationshipDTO> roleRelations;
	/**
	 * 
	 */
	private List<AccountPriorityRelationshipDTO> priorityRelations;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public List<AccountRoleRelationshipDTO> getRoleRelations() {
		return roleRelations;
	}
	public void setRoleRelations(List<AccountRoleRelationshipDTO> roleRelations) {
		this.roleRelations = roleRelations;
	}
	public List<AccountPriorityRelationshipDTO> getPriorityRelations() {
		return priorityRelations;
	}
	public void setPriorityRelations(List<AccountPriorityRelationshipDTO> priorityRelations) {
		this.priorityRelations = priorityRelations;
	}
	
}
