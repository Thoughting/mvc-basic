package org.springframework.samples.mvc.basic.account.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.mvc.basic.account.model.Authority;
import org.springframework.samples.mvc.basic.account.model.AuthorityPropertyEditor;
import org.springframework.samples.mvc.basic.account.model.Role;
import org.springframework.samples.mvc.basic.account.service.AccountManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色管理Action.
 * 
 * 演示不分页的简单管理界面.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/account/role")
public class RoleAction {

	private static final long serialVersionUID = 1L;

	private AccountManager accountManager;

	//-- 页面属性 --//
	//private List<Role> allRoleList;//角色列表
	//	private List<Long> checkedAuthIds;//页面中钩选的权限id列表

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//dateFormat.setLenient(false);
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		AuthorityPropertyEditor ape = new AuthorityPropertyEditor();
		ape.setAccountManager(accountManager);
		binder.registerCustomEditor(Authority.class, "authorityList", ape);
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	//-- ModelDriven 与 Preparable函数 --//
	//	public Role getModel() {
	//		return entity;
	//	}

	//	public void setId(Long id) {
	//		this.id = id;
	//	}

	//	@Override
	//	protected void prepareModel() throws Exception {
	//		if (id != null) {
	//			entity = accountManager.getRole(id);
	//		} else {
	//			entity = new Role();
	//		}
	//	}

	@RequestMapping
	public String index(Model model) {
		List<Role> allRoleList = accountManager.getAllRole();
		model.addAttribute("allRoleList", allRoleList);
		return "account/role/role";
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String input(Model model) {
		//checkedAuthIds = entity.getAuthIds();
		List<Authority> allAuthorityList = accountManager.getAllAuthority();
		model.addAttribute("allAuthorityList", allAuthorityList);
		Role role = new Role();
		model.addAttribute("role", role);
		return "account/role/role-input";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid Role role, BindingResult result, Model model) {
		//根据页面上的checkbox 整合Role的Authorities Set.
		//		HibernateUtils.mergeByCheckedIds(entity.getAuthorityList(), checkedAuthIds, Authority.class);
		//保存用户并放入成功信息.
		//		accountManager.saveRole(entity);
		//		addActionMessage("保存角色成功");
		//		return RELOAD;
		if (result.hasErrors()) {
			List<Authority> allAuthorityList = accountManager.getAllAuthority();
			model.addAttribute("allAuthorityList", allAuthorityList);
			model.addAttribute("role", role);
			//return "redirect:/account/role/new";
			return "account/role/role-input";
		}
		accountManager.saveRole(role);
		//		this.role.put(role.assignId(), role);
		//return "redirect:/role/" + role.getId();
		return "redirect:/account/role";
	}

	/** 编辑 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String edit(@PathVariable java.lang.Long id, Model model) throws Exception {
		Role role = (Role) accountManager.getRoleById(id);
		model.addAttribute("role", role);
		List<Authority> allAuthorityList = accountManager.getAllAuthority();
		model.addAttribute("allAuthorityList", allAuthorityList);
		//return new ModelAndView("/user/edit", "userInfo", userInfo);
		List<Authority> authorityList = role.getAuthorityList();
		for (Authority authority : allAuthorityList) {

			if (authorityList.contains(authority)) {
				System.out.println(">>" + authority.getName());
			}
		}
		return "account/role/role-input";
	}

	/** 保存更新 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String update(@PathVariable java.lang.Long id, @Valid Role role, BindingResult result, Model model)
			throws Exception {
		if (result.hasErrors()) {
			//throw new BindException(result);
			List<Authority> allAuthorityList = accountManager.getAllAuthority();
			model.addAttribute("allAuthorityList", allAuthorityList);
			model.addAttribute("role", role);
			return "account/role/role-input";
		}
		accountManager.updateRole(role);
		return "redirect:/account/role";
	}

	/** 删除 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable java.lang.Long id) {
		try {
			accountManager.deleteRole(id);
			return "{ok: 'true'}";
		} catch (Exception e) {
			return "{ok: 'false'}";
		}
		//		addActionMessage("删除角色成功");
		//		return RELOAD;
		//return "redirect:/account/role";
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示所有角色列表.
	 */
	//	public List<Role> getAllRoleList() {
	//		return allRoleList;
	//	}

	/**
	 * input页面显示所有授权列表.
	 */
	//	public List<Authority> getAllAuthorityList() {
	//		return accountManager.getAllAuthority();
	//	}

	/**
	 * input页面显示角色拥有的授权.
	 */
	//	public List<Long> getCheckedAuthIds() {
	//		return checkedAuthIds;
	//	}

	/**
	 * input页面提交角色拥有的授权.
	 */
	//	public void setCheckedAuthIds(List<Long> checkedAuthIds) {
	//		this.checkedAuthIds = checkedAuthIds;
	//	}
}