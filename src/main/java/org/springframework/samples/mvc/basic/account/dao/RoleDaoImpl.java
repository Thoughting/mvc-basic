package org.springframework.samples.mvc.basic.account.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.samples.mvc.basic.account.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl extends SqlSessionDaoSupport implements RoleDao {

	public Role getById(Long primaryKey) {
		return (Role) getSqlSession().selectOne(
				"org.springframework.samples.mvc.basic.account.model.mapper.RoleMapper.getRoleById", primaryKey);
	}

	public int deleteById(Long id) {
		return getSqlSession().delete(
				"org.springframework.samples.mvc.basic.account.model.mapper.RoleMapper.deleteRoleById", id);
	}

	public int save(Role entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().insert(
				"org.springframework.samples.mvc.basic.account.model.mapper.RoleMapper.insertRole", entity);
	}

	public int update(Role entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().update(
				"org.springframework.samples.mvc.basic.account.model.mapper.RoleMapper.updateRole", entity);
	}

	public int updateAuthoritys(Role entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().update(
				"org.springframework.samples.mvc.basic.account.model.mapper.RoleMapper.updateRole", entity);
	}

	public int saveOrUpdate(Role entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().insert("Role", entity);
	}

	public List<Role> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean isUnique(Role entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}

	/**
	 *	获取全部对象,支持排序.
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAll(String orderBy, boolean isAsc) {
		return getSqlSession().selectList(
				"org.springframework.samples.mvc.basic.account.model.mapper.RoleMapper.getAll", null);
	}

}
