package org.springframework.samples.mvc.basic.account.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.mvc.basic.account.model.Authority;

public interface AuthorityDao {

	public Authority getById(Long id) throws DataAccessException;

	public int deleteById(Long id) throws DataAccessException;

	/** 插入数据 */
	public int save(Authority entity) throws DataAccessException;

	/** 更新数据 */
	public int update(Authority entity) throws DataAccessException;

	/** 根据id检查是否插入或是更新数据 */
	public int saveOrUpdate(Authority entity) throws DataAccessException;

	public boolean isUnique(Authority entity, String uniquePropertyNames) throws DataAccessException;

	public List<Authority> findAll() throws DataAccessException;

}
