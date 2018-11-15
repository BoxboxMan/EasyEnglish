package org.jxnu.controller.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm{

    public void setName(String name) {
		super.setName("customRealm");
	}
	
	/**
	 * 用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		//从token中获取用户信息
//		String userCode = (String) token.getPrincipal();
//		//在数据库中进行查询...
//		//将查询到的信息得出
//		
//		//如果数据库中根据账号信息查询为空则返回null
//		if(!userCode.equals("asfas")){
//			return null;
//		}
//		String password="123456";
//		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, this.getName());
//		return simpleAuthenticationInfo;
		return null;
	}

}
