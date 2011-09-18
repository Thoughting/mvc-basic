package org.springframework.samples.mvc.basic.account.dao;

import java.io.Serializable;

import org.springframework.samples.mvc.basic.account.PageRequest;
import org.springframework.samples.mvc.basic.account.model.User;

public class UserQuery extends PageRequest<User> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1541466769305112252L;
	public static final int DEFAULT_PAGE_SIZE = 4;

	public UserQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}
}
