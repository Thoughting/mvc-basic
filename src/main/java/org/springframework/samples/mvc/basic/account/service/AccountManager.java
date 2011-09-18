package org.springframework.samples.mvc.basic.account.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.mvc.basic.account.Page;
import org.springframework.samples.mvc.basic.account.SpringSecurityUtils;
import org.springframework.samples.mvc.basic.account.dao.AuthorityDao;
import org.springframework.samples.mvc.basic.account.dao.RoleDao;
import org.springframework.samples.mvc.basic.account.dao.UserDao;
import org.springframework.samples.mvc.basic.account.dao.UserQuery;
import org.springframework.samples.mvc.basic.account.model.Authority;
import org.springframework.samples.mvc.basic.account.model.Role;
import org.springframework.samples.mvc.basic.account.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author calvin
 */
//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AccountManager {

	private static Logger logger = LoggerFactory.getLogger(AccountManager.class);

	private UserDao userDao;
	private RoleDao roleDao;
	private AuthorityDao authorityDao;

	//-- User Manager --//
	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.getById(id);
	}

	public void saveUser(User entity) {
		userDao.save(entity);
	}

	public void updateUser(User entity) {
		userDao.update(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", SpringSecurityUtils.getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userDao.deleteById(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 使用属性过滤条件查询用户.
	 */
	@Transactional(readOnly = true)
	public Page<User> searchUser(final UserQuery query) {
		return userDao.findPage(query);
	}

	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		System.out.println("loginName>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + loginName);
		return userDao.findUniqueBy("loginName", loginName);
	}

	/**
	 * 检查用户名是否唯一.
	 *
	 * @return loginName在数据库中唯一或等于oldLoginName时返回true.
	 */
	@Transactional(readOnly = true)
	public boolean isLoginNameUnique(String newLoginName, String oldLoginName) {
		return userDao.isPropertyUnique("loginName", newLoginName, oldLoginName);
	}

	//-- Role Manager --//
	@Transactional(readOnly = true)
	public Role getRoleById(Long id) {
		return roleDao.getById(id);
	}

	@Transactional(readOnly = true)
	public List<Role> getAllRole() {
		return roleDao.getAll("id", true);
	}

	public void saveRole(Role entity) {
		int result = roleDao.save(entity);
		System.out.println(result);
		//throw new UnsupportedOperationException();
	}

	public void updateRole(Role entity) {
		//先更新角色-权限关系表，再更新角色表
		int result = roleDao.update(entity);
		System.out.println(result);
	}

	public void deleteRole(Long id) {
		int result = roleDao.deleteById(id);
		System.out.println(result);
	}

	//-- Authority Manager --//
	public List<Authority> getAllAuthority() {
		return authorityDao.findAll();
	}

	public Authority getAuthorityById(Long id) {
		return authorityDao.getById(id);
	}

	public void saveAuthority(Authority entity) {
		int result = authorityDao.save(entity);
		System.out.println(result);
		//throw new UnsupportedOperationException();
	}

	public void updateAuthority(Authority entity) {
		//先更新角色-权限关系表，再更新角色表
		int result = authorityDao.update(entity);
		System.out.println(result);
	}

	public void deleteAuthority(Long id) {
		int result = authorityDao.deleteById(id);
		System.out.println(result);
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	@Transactional(readOnly = true)
	public Page<User> findPage(UserQuery query) {
		Assert.notNull(query, "'query' must be not null");
		return userDao.findPage(query);
	}
}
