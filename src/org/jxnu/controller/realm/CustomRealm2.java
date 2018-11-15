package org.jxnu.controller.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jxnu.po.ActiveUser;
import org.jxnu.po.SysPermission;
import org.jxnu.po.SysUser;
import org.jxnu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm2 extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;

	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		//获取用户权限链表
		List<SysPermission> permissionsList = activeUser.getPermissions();
		//用来放入权限匹配器中（待装入数据）
		List<String> permissions = new ArrayList<>();
		for(SysPermission permission:permissionsList){
			permissions.add(permission.getPercode());
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户身份
		String userCode = (String) token.getPrincipal();
		// ..去数据库中根据身份查凭证（密码），若数据库中查不到身份信息则返回null
		// 拿到的身份信息去数据库查询权限
		SysUser sysUser = null;
		try {
			sysUser = loginService.findSysUser(userCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sysUser==null){
			return null;
		}
		// 装填activeUser（存储在session中用于取值）
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(sysUser.getId());
		activeUser.setUsercode(sysUser.getUsercode());
		activeUser.setUsername(sysUser.getUsername());
		// 数据库中查询用户的权限
		List<SysPermission> permissions = loginService.findSysPermission(sysUser.getId());
		// 数据库中查询用户的菜单
		List<SysPermission> menus = loginService.findUserMenuList(sysUser.getId());
		activeUser.setPermissions(permissions);
		activeUser.setMenus(menus);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, sysUser.getPassword(),
				this.getName());
		return simpleAuthenticationInfo;
	}

}
