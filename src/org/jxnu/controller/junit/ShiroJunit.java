package org.jxnu.controller.junit;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;


public class ShiroJunit {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		//通过创建ini文件来创建securityManager的工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
		//创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		//将securityManager设置在当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//在securityManager内创建一个subject
		Subject subject = SecurityUtils.getSubject();
		//在提交认证之前准备token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1234561");
		//提交认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//验证是否通过
		boolean isAuthenticated = subject.isAuthenticated();
		System.out.println("验证是否通过："+ isAuthenticated);
		//退出
		subject.logout();
	}
	/**
	 * 认证测试
	 */
	@Test
	public void testCustomRealm() {
		//通过创建ini文件来创建securityManager的工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
		//创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		//将securityManager设置在当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//在securityManager内创建一个subject
		Subject subject = SecurityUtils.getSubject();
		//在提交认证之前准备token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
		//提交认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//验证是否通过
		boolean isAuthenticated = subject.isAuthenticated();
		System.out.println("验证是否成功:"+isAuthenticated);
		//退出
		subject.logout();
	}
	
	/**
	 * 授权测试
	 */
	@Test
	public void testAuthorizier(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("验证是否通过："+subject.isAuthenticated());
		
		boolean isPermission = subject.isPermitted("user:update");
		System.out.println("用户授权是否通过："+isPermission);
	}

}
