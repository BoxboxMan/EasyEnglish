package org.jxnu.controller.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jxnu.po.ActiveUser;
import org.jxnu.po.SysPermission;
import org.jxnu.utils.ResourcesUtil;
import org.springframework.web.servlet.HandlerInterceptor;
/**
 * 定义授权拦截器
 * @author MR.S
 *
 */
public class PermissionInterceptor implements HandlerInterceptor{

	/**
	 * 进行url拦截处理
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
			throws Exception{
		//获取用户请求的url
		String url = request.getRequestURI();
		//获取公开url
		List<String> commonUrl = ResourcesUtil.gekeyList("commonUrl");
		for(String openUrl:commonUrl){
			if(url.lastIndexOf(openUrl)>0)
				return true;
		}
		//获取公共url（只要登陆既能使用，例如：主页面、退出等功能）
		List<String> openUrl = ResourcesUtil.gekeyList("openUrl");
		for(String openUrl2:openUrl){
			if(url.lastIndexOf(openUrl2)>0)
				return true;
		}
		HttpSession session = request.getSession();
		//若访问的连接为需要权限的url则进行url验证
		//获取用户具有权限的所有url
		ActiveUser activeUser =  (ActiveUser) session.getAttribute("activeUser");
		List<SysPermission> permissions = activeUser.getPermissions();
		for(SysPermission permission:permissions){
			String permission_url = permission.getUrl();
			if(url.lastIndexOf(permission_url)>0)
				return true;
		}
		
		//若权限不够则跳入页面提醒用户没有权限
		request.getRequestDispatcher("/WEB-INF/view/inform.jsp").forward(request, response);;
		return false;
	}
	
}
