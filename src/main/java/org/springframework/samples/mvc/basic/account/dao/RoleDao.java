package org.springframework.samples.mvc.basic.account.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.mvc.basic.account.model.Role;

public interface RoleDao {

	public Role getById(Long id) throws DataAccessException;

	public int deleteById(Long id) throws DataAccessException;

	/** 插入数据 */
	public int save(Role entity) throws DataAccessException;

	/** 更新数据 */
	public int update(Role entity) throws DataAccessException;

	/** 根据id检查是否插入或是更新数据 */
	public int saveOrUpdate(Role entity) throws DataAccessException;

	public boolean isUnique(Role entity, String uniquePropertyNames) throws DataAccessException;

	public List<Role> findAll() throws DataAccessException;

	public List<Role> getAll(String orderBy, boolean isAsc) throws DataAccessException;

}
