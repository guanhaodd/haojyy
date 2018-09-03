package com.gh.app.util.service.realm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.gh.app.manage.module.sys.user.bean.TbUser;
import com.gh.app.manage.module.sys.user.service.UserService;
import com.gh.app.util.constants.Constants;
import com.gh.app.util.tool.log.Logs;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:02:23
 * @Discription  用于校验和授权的Shiro realm
 */
public class ShiroDbRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;
//
//	@Resource
//	private UserViService userViService;
//
//	@Resource
//	private AclService aclService;
//
//	@Resource
//	private SysFunService sysFunService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		TbUser user = userService.findByUserName(token.getUsername());
		if (user != null) {
			// 要放在作用域中的东西，请在这里进行操作
			setSession(Constants.ADMIN_USER, user); 
			return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), user.getRealName());
		} else {
			return null;
		}
	}

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		// 获取当前登录的用户名
//		String account = (String) super.getAvailablePrincipal(principals);
//
//		List<String> userroles = new ArrayList<String>();
//		List<String> permissions = new ArrayList<String>();
//		TbUser user = userTbService.findByUserName(account);
//		if (user != null) {
//			// 如果用户是超级管理员 默认情况下拥有所有的权限
//			if (Constants.USER_NAME_SUPER_ADMIN.equals(user.getUsername())) {// 超管
//				List<TbSysFun> sysFuns = sysFunService.find();
//				for (TbSysFun fun : sysFuns) {
//					if (!StringUtils.isEmpty(fun.getPermission())) {
//						permissions.add(fun.getPermission());
//					}
//				}
//			} else if (Constants.USER_NAME_ADMIN.equals(user.getUsername())) {// 管理员
//				String adminMenuType = "1"; // 一般管理员可以访问的菜单类型
//				List<TbSysFun> sysFuns = sysFunService.findByType(adminMenuType);
//				for (TbSysFun fun : sysFuns) {
//					if (!StringUtils.isEmpty(fun.getPermission())) {
//						permissions.add(fun.getPermission());
//					}
//				}
//			} else {
//				//没有角色，只有用户本身权限
//				List<TbAcl> acls = aclService.findAcls(TbAcl.TYPE_USER, user.getId());
//				for (TbAcl acl : acls) {
//					if (!StringUtils.isEmpty(acl.getPermission())) {
//						permissions.add(acl.getPermission());
//					}
//				}
//			}
//		} else {
//			throw new AuthorizationException();
//		}
//		// 给当前用户设置角色
//		info.addRoles(userroles);
//		// 给当前用户设置权限
//		info.addStringPermissions(permissions);
//		return info;
		return null;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			Logs.debug("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}

