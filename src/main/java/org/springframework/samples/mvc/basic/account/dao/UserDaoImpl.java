package org.springframework.samples.mvc.basic.account.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.samples.mvc.basic.account.Page;
import org.springframework.samples.mvc.basic.account.PageRequest;
import org.springframework.samples.mvc.basic.account.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	public User getById(Long primaryKey) {
		return (User) getSqlSession().selectOne(
				"org.springframework.samples.mvc.basic.account.model.mapper.UserMapper.getUserById", primaryKey);
	}

	public int deleteById(Long id) {
		return getSqlSession().delete(
				"org.springframework.samples.mvc.basic.account.model.mapper.UserMapper.deleteUserById", id);
	}

	public int save(User entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().insert(
				"org.springframework.samples.mvc.basic.account.model.mapper.UserMapper.insertUser", entity);
	}

	public int update(User entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().update(
				"org.springframework.samples.mvc.basic.account.model.mapper.UserMapper.updateUser", entity);
	}

	public int saveOrUpdate(User entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().insert("User", entity);
	}

	public List<User> findAll() {
		throw new UnsupportedOperationException();
	}

	public boolean isUnique(User entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.cssweb.common.dao.IBaseDAO#getObject(java.lang.Class, java.lang.Object)
	 */
	public User findUniqueBy(String parameterName, Object parameterValue) {
		//return 
		SqlSession ss = getSqlSession();
		User u = (User) ss.selectOne(
				"org.springframework.samples.mvc.basic.account.model.mapper.UserMapper.findUniqueByLoginname",
				parameterValue);
		return u;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}

	public String getCountStatementForPaging(String statementName) {
		return statementName + ".count";
	}

	protected Page<User> pageQuery(String statementName, PageRequest<User> pageRequest) {
		return pageQuery(getSqlSession(), statementName,
				"org.springframework.samples.mvc.basic.account.model.mapper.UserMapper.count", pageRequest);
	}

	public Page<User> pageQuery(SqlSession sqlSession, String statementName, String countStatementName,
			PageRequest<User> pageRequest) {

		Number totalCount = (Number) sqlSession.selectOne(countStatementName, pageRequest);
		if (totalCount == null || totalCount.longValue() <= 0) {
			return new Page<User>(pageRequest, 0);
		}

		Page<User> page = new Page<User>(pageRequest, totalCount.intValue());

		//其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());
		Map parameterObject = null;
		try {
			parameterObject = PropertyUtils.describe(pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		filters.putAll(parameterObject);
		//List list = sqlSessionTemplate.selectList(statementName, filters, page.getFirstResult(), page.getPageSize());
		List list = getSqlSession().selectList(statementName, filters,
				new RowBounds(page.getFirstResult(), page.getPageSize()));
		page.setResult(list);
		return page;
	}

	public Page<User> findPage(UserQuery query) {
		return pageQuery("org.springframework.samples.mvc.basic.account.model.mapper.UserMapper.findPage", query);
	}

}
