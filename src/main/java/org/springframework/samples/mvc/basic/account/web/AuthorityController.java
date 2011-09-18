package org.springframework.samples.mvc.basic.account.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.mvc.basic.account.model.Authority;
import org.springframework.samples.mvc.basic.account.service.AccountManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限管理Action.
 * 
 * 
 * @author rubyol
 */
@Controller
@RequestMapping(value = "/account/authority")
public class AuthorityController {

	private AccountManager accountManager;

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@RequestMapping
	public String index(Model model) {
		List<Authority> allAuthorityList = accountManager.getAllAuthority();
		model.addAttribute("allAuthorityList", allAuthorityList);
		return "account/authority/authority";
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String input(Model model) {
		Authority authority = new Authority();
		model.addAttribute("authority", authority);
		return "account/authority/authority-input";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid Authority authority, BindingResult result) {
		if (result.hasErrors()) {
			return "account/authority/authority-input";
		}
		accountManager.saveAuthority(authority);
		//		this.role.put(role.assignId(), role);
		//return "redirect:/role/" + role.getId();
		return "redirect:/account/authority";
	}

	/** 编辑 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String edit(@PathVariable java.lang.Long id, Model model) throws Exception {
		Authority authority = (Authority) accountManager.getAuthorityById(id);
		model.addAttribute("authority", authority);
		return "account/authority/authority-input";
	}

	/** 保存更新 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String update(@PathVariable java.lang.Long id, @Valid Authority authority, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			return "account/authority/authority-input";
		}
		accountManager.updateAuthority(authority);
		return "redirect:/account/authority";
	}

	/** 删除 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable java.lang.Long id) {
		try {
			accountManager.deleteAuthority(id);
			return "{ok: 'true'}";
		} catch (Exception e) {
			return "{ok: 'false'}";
		}
		//		addActionMessage("删除角色成功");
		//		return RELOAD;
		//return "redirect:/account/role";
	}
}
