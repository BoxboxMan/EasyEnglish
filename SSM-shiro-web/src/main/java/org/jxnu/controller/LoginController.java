package org.jxnu.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.jxnu.controller.exception.CustomException;
import org.jxnu.po.ActiveUser;
import org.jxnu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 到主页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/first")
	public String first(Model model)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		model.addAttribute("activeUser", activeUser);
		return "forward:/items/listItems.action";
	}

	/**
	 * 若不为公开地址则进行session验证，若用户已存在session中就放行。
	 * 若用户不存在sessioon中就对用户进行身份认证
	 * @param session
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,String username,String password) throws Exception{
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if(UnknownAccountException.class.getName().equals(exceptionClassName)){
				throw new CustomException("账号不存在");
			}else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
				throw new CustomException("账号或密码不正确");
			}else{
				throw new Exception();
			}
		}
		//当登陆成功时shiro自动帮我们跳转到登陆时上一个url请求（我们也可以在securityManager中配置登陆成功后的跳转url，但不推荐)
		//请求失败返回url
		return "login";
	}
	
	
}
