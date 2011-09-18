package org.springframework.samples.mvc.basic.account.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.samples.mvc.basic.account.model.Authority;
import org.springframework.stereotype.Component;

@Component
public class AuthorityDaoImpl extends SqlSessionDaoSupport implements AuthorityDao {

	public Authority getById(Long primaryKey) {
		return (Authority) getSqlSession().selectOne(
				"org.springframework.samples.mvc.basic.account.model.mapper.AuthorityMapper.getAuthorityById",
				primaryKey);
	}

	public int deleteById(Long id) {
		return getSqlSession().delete(
				"org.springframework.samples.mvc.basic.account.model.mapper.AuthorityMapper.deleteAuthorityById", id);
	}

	public int save(Authority entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().insert(
				"org.springframework.samples.mvc.basic.account.model.mapper.AuthorityMapper.insertAuthority", entity);
	}

	public int update(Authority entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().update(
				"org.springframework.samples.mvc.basic.account.model.mapper.AuthorityMapper.updateAuthority", entity);
	}

	public int saveOrUpdate(Authority entity) {
		//prepareObjectForSaveOrUpdate(entity);
		return getSqlSession().insert("Authority", entity);
	}

	@SuppressWarnings("unchecked")
	public List<Authority> findAll() {
		return getSqlSession().selectList(
				"org.springframework.samples.mvc.basic.account.model.mapper.AuthorityMapper.findAll", null);
		//throw new UnsupportedOperationException();
	}

	public boolean isUnique(Authority entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}
}
