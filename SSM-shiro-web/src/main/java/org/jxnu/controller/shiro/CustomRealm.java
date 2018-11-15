package org.jxnu.controller.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jxnu.controller.exception.CustomException;
import org.jxnu.po.ActiveUser;
import org.jxnu.po.SysPermission;
import org.jxnu.po.SysUser;
import org.jxnu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm{
	
	@Autowired
	private LoginService loginService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//定义权限匹配器
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		//获取用户主身份信息
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		
		//获得用户权限链表
		List<SysPermission> permissions = activeUser.getPermissions();
		
		//把当前用户信息的权限链表填充进匹配器中
		for(SysPermission permission_list:permissions){
			String permission = permission_list.getPercode();
			simpleAuthorizationInfo.addStringPermission(permission);
		}
		
		return simpleAuthorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String usercode = (String) token.getPrincipal();
		SysUser sysUser = null;
		try {
			sysUser = loginService.findSysUser(usercode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(sysUser==null){
			return null;
		}
		
		//往ActiverUser填充数据（用户认证授权使用）
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(sysUser.getId());
		activeUser.setUsercode(sysUser.getUsercode());
		activeUser.setUsername(sysUser.getUsername());
		
		//查询用户权限对应拥有的菜单
		List<SysPermission> menus = loginService.findUserMenuList(sysUser.getId());
		activeUser.setMenus(menus);
		
		//查询用户对应的权限
		List<SysPermission> permissions = loginService.findSysPermission(sysUser.getId());
		activeUser.setPermissions(permissions);
		
		//将查询到的用户信息、身份凭证、以及realm名放入 认证匹配器中
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, sysUser.getPassword(), this.getName());
		return simpleAuthenticationInfo;
		
	}

}
