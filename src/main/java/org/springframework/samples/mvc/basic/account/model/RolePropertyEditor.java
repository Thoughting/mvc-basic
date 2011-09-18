package org.springframework.samples.mvc.basic.account.model;

import java.beans.PropertyEditorSupport;

import org.springframework.samples.mvc.basic.account.service.AccountManager;

public class RolePropertyEditor extends PropertyEditorSupport {
	private String format;
	private AccountManager accountManager;

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setAsText(String text) {
		if (format != null && format.equals("upperCase")) {
			text = text.toUpperCase();
		}
		Role role = (Role) accountManager.getRoleById(Long.parseLong(text));
		setValue(role);
	}

}
