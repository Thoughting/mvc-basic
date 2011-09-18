package org.springframework.samples.mvc.basic.account.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.samples.mvc.basic.account.utils.ReflectionUtils;

import com.google.common.collect.Lists;

/**
 * 用户.
 * 
 * 
 * @author 
 */

public class User {

	private Long id;
	@NotEmpty
	@Size(min = 1, max = 25)
	private String loginName;
	@NotNull
	@Size(min = 4, max = 20)
	private String password;//为简化演示使用明文保存的密码
	private String name;

	/** Whether this user's account is enabled. */
	private boolean enabled = false;

	/** Whether this user's account is locked. */
	private boolean locked = false;

	/** The expiration date/time for this user's account. */
	private Date expirationTime = new Date(Long.MAX_VALUE);

	@NotEmpty
	@Email
	private String email;
	private List<Role> roleList = Lists.newArrayList();//有序的关联对象集合

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * {@inheritDoc}
	 */
	public void disable() {
		enabled = false;
		//logger.info(Logger.SECURITY_SUCCESS, "Account disabled: " + getAccountName());
	}

	/**
	 * {@inheritDoc}
	 */
	public void enable() {
		this.enabled = true;
		//logger.info(Logger.SECURITY_SUCCESS, "Account enabled: " + getAccountName());
	}

	/**
	 * {@inheritDoc}
	 */
	public void lock() {
		this.locked = true;
		//logger.info(Logger.SECURITY_SUCCESS, "Account locked: " + getAccountName());
	}

	/**
	 * {@inheritDoc}
	 */
	public void unlock() {
		this.locked = false;
		//this.failedLoginCount = 0;
		//logger.info(Logger.SECURITY_SUCCESS, "Account unlocked: " + getAccountName());
	}

	/**
	 * {@inheritDoc}
	 */
	public Date getExpirationTime() {
		return (Date) this.expirationTime.clone();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = new Date(expirationTime.getTime());
		//		logger.info(Logger.SECURITY_SUCCESS, "Account expiration time set to " + expirationTime + " for " + getAccountName());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isExpired() {
		return getExpirationTime().before(new Date());

		// If expiration should happen automatically or based on lastPasswordChangeTime?
		//		long from = lastPasswordChangeTime.getTime();
		//		long to = new Date().getTime();
		//		double difference = to - from;
		//		long days = Math.round((difference / (1000 * 60 * 60 * 24)));
		//		return days > 60;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isLocked() {
		return locked;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return ReflectionUtils.convertElementPropertyToString(roleList, "name", ", ");
	}

	/**
	 * 用户拥有的角色id字符串, 多个角色id用','分隔.
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getRoleIds() {
		return ReflectionUtils.convertElementPropertyToList(roleList, "id");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
