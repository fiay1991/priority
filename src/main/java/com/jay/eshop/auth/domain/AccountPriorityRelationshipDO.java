package com.jay.eshop.auth.domain;

import java.util.Date;

import com.jay.eshop.common.util.AbstractObject;

/**
 * 账号和权限的关联关系DO类
 * @author jay
 *
 */
public class AccountPriorityRelationshipDO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 账号id
	 */
	private Long accountId;
	/**
	 * 权限id
	 */
	private Long priorityId;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
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
	
}
