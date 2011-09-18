package org.springframework.samples.mvc.basic.account.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.mvc.basic.account.Page;
import org.springframework.samples.mvc.basic.account.dao.UserQuery;
import org.springframework.samples.mvc.basic.account.model.Role;
import org.springframework.samples.mvc.basic.account.model.RolePropertyEditor;
import org.springframework.samples.mvc.basic.account.model.User;
import org.springframework.samples.mvc.basic.account.service.AccountManager;
import org.springframework.samples.mvc.basic.account.service.ServiceException;
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
 * 用户管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/account/user")
public class UserAction {

	private static final long serialVersionUID = 1L;
	//private Map<Long, User> users = new ConcurrentHashMap<Long, User>();
	private AccountManager accountManager;

	//-- 页面属性 --//
	private Long id;
	private User entity;
	private Page<User> page = null;//new Page<User>(5);//每页5条记录
	private List<Long> checkedRoleIds; //页面中钩选的角色id列表

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		RolePropertyEditor rpe = new RolePropertyEditor();
		rpe.setAccountManager(accountManager);
		binder.registerCustomEditor(Role.class, "roleList", rpe);
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public User getModel() {
		return entity;
	}

	//	@Override
	//	protected void prepareModel() throws Exception {
	//		if (id != null) {
	//			entity = accountManager.getUser(id);
	//		} else {
	//			entity = new User();
	//		}
	//	}

	//	public static PageRequest bindPageRequest(HttpServletRequest request, PageRequest pageRequest,
	//			String defaultSortColumns) {
	//		return PageRequestFactory.bindPageRequest(pageRequest, request, defaultSortColumns);
	//	}

	/** 列表 */
	@RequestMapping
	public String index(Model model, UserQuery query) {
		//		List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//		//设置默认排序方式
		//		if (!page.isOrderBySetted()) {
		//			page.setOrderBy("id");
		//			page.setOrder(Page.ASC);
		//		}
		//		page = accountManager.searchUser(page, filters);
		//		Enumeration e = request.getAttributeNames();
		//		String intObj;
		//		while (e.hasMoreElements()) {
		//			intObj = (String) e.nextElement();
		//			System.out.println(intObj);
		//		}
		//
		//		String pageNumber = request.getParameter("query.pageNumber");
		//		String pageSize = request.getParameter("query.pageSize");
		//		query.setPageNumber(StringUtils.isEmpty(pageNumber) ? 1 : Integer.parseInt(pageNumber));
		//		query.setPageSize(StringUtils.isEmpty(pageSize) ? 4 : Integer.parseInt(pageSize));

		Page<User> page = accountManager.findPage(query);
		model.addAttribute("page", page);
		return "account/user/user";
	}

	/** 列表 */
	//	@RequestMapping(value = "list", method = RequestMethod.GET)
	//	@ResponseBody
	//	public String list(Model model, UserQuery query) {
	//		Page<User> page = accountManager.findPage(query);
	//		model.addAttribute("page", page);
	//		return "account/user/UserIndex";
	//	}
	//	
	//	private List<User> getUserList() {
	//		SecurityFactory sf = SecurityConfiguration.getSecurityFactory();
	//		UserManager um = sf.getUserManager();
	//		List<User> userList = new ArrayList<User>();
	//		Iterator<User> it = um.searchUsers("where USER_TYPE=1"); 
	//		while (it.hasNext()) {
	//			User user = it.next();
	//			userList.add(user);
	//		}
	//		return userList;
	//	}

	/** 进入新增 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String input(Model model) throws Exception {
		//checkedRoleIds = entity.getRoleIds();
		//model.addAttribute(checkedRoleIds);
		User user = new User();
		model.addAttribute("user", user);
		List<Role> allRoleList = accountManager.getAllRole();
		model.addAttribute("allRoleList", allRoleList);
		return "account/user/user-input";
	}

	/** 保存新增 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid User user, BindingResult result, Model model) {
		//根据页面上的checkbox选择 整合User的Roles Set
		//		HibernateUtils.mergeByCheckedIds(entity.getRoleList(), checkedRoleIds, Role.class);
		//

		//		addActionMessage("保存用户成功");
		//		return RELOAD;
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			List<Role> allRoleList = accountManager.getAllRole();
			model.addAttribute("allRoleList", allRoleList);
			return "account/user/user-input";
		}
		accountManager.saveUser(user);
		//this.users.put(user.assignId(), user);
		return "redirect:/account/user";
	}

	/** 编辑 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String edit(@PathVariable java.lang.Long id, Model model) throws Exception {
		User user = (User) accountManager.getUser(id);
		model.addAttribute("user", user);
		List<Role> allRoleList = accountManager.getAllRole();
		model.addAttribute("allRoleList", allRoleList);
		//return new ModelAndView("/user/edit", "userInfo", userInfo);
		//return "user/edit";
		return "account/user/user-input";
	}

	/** 保存更新 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String update(@PathVariable java.lang.Long id, @Valid User user, BindingResult result, Model model)
			throws Exception {
		if (result.hasErrors()) {
			//throw new BindException(result);
			model.addAttribute("user", user);
			List<Role> allRoleList = accountManager.getAllRole();
			model.addAttribute("allRoleList", allRoleList);
			return "account/user/user-input";
		}
		accountManager.updateUser(user);
		return "redirect:/account/user";
	}

	/** 删除 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable java.lang.Long id) throws Exception {
		try {
			accountManager.deleteUser(id);
			//addActionMessage("删除用户成功");
			return "{ok: 'true'}";
		} catch (ServiceException e) {
			return "{ok: 'false'}";
			//logger.error(e.getMessage(), e);
			//addActionMessage("删除用户失败");
		}
		//return RELOAD;
		//return "redirect:/user/";
	}

	//-- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkLoginName() {
		//		HttpServletRequest request = ServletActionContext.getRequest();
		//		String newLoginName = request.getParameter("loginName");
		//		String oldLoginName = request.getParameter("oldLoginName");
		//
		//		if (accountManager.isLoginNameUnique(newLoginName, oldLoginName)) {
		//			Struts2Utils.renderText("true");
		//		} else {
		//			Struts2Utils.renderText("false");
		//		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<User> getPage() {
		return page;
	}

	/**
	 * input页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return accountManager.getAllRole();
	}

	/**
	 * input页面显示用户拥有的角色.
	 */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	/**
	 * input页面提交用户拥有的角色.
	 */
	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}
}
