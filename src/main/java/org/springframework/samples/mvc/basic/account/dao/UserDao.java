package org.springframework.samples.mvc.basic.account.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.mvc.basic.account.Page;
import org.springframework.samples.mvc.basic.account.model.User;
import org.springframework.stereotype.Component;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component
public interface UserDao {

	public User getById(Long id) throws DataAccessException;

	public int deleteById(Long id) throws DataAccessException;

	/** 插入数据 */
	public int save(User entity) throws DataAccessException;

	/** 更新数据 */
	public int update(User entity) throws DataAccessException;

	/** 根据id检查是否插入或是更新数据 */
	public int saveOrUpdate(User entity) throws DataAccessException;

	public boolean isUnique(User entity, String uniquePropertyNames) throws DataAccessException;

	public List<User> findAll() throws DataAccessException;

	public User findUniqueBy(String parameterName, Object parameterValue) throws DataAccessException;

	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue)
			throws DataAccessException;

	public Page<User> findPage(UserQuery query);

}
