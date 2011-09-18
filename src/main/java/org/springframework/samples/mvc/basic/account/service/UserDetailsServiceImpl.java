package org.springframework.samples.mvc.basic.account.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.mvc.basic.account.model.Authority;
import org.springframework.samples.mvc.basic.account.model.Role;
import org.springframework.samples.mvc.basic.account.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author calvin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountManager accountManager;
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = accountManager.findUserByLoginName(username);
		if (user == null) {
			//throw new UsernameNotFoundException("用户" + username + " 不存在");
			throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.notFound",
					new Object[] { username }, "Username {0} not found"), username);
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		//-- mini-web示例中无以下属性, 暂时全部设为true. --//

		//boolean enabled = true;
		//boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		//boolean accountNonLocked = true;

		UserDetails userdetails = new org.springframework.security.core.userdetails.User(user.getLoginName(), user
				.getPassword(), user.isEnabled(), !user.isExpired(), credentialsNonExpired, !user.isLocked(),
				grantedAuths);

		return userdetails;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoleList()) {
			for (Authority authority : role.getAuthorityList()) {
				authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
			}
		}
		return authSet;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
