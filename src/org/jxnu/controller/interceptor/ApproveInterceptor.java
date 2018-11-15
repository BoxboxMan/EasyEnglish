package org.jxnu.controller.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jxnu.utils.ResourcesUtil;
import org.springframework.web.servlet.HandlerInterceptor;
/**
 * 定义用户认证拦截器
 * @author MR.S
 *
 */
public class ApproveInterceptor implements HandlerInterceptor{

	/**
	 * 进行url拦截处理
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
			throws Exception{
		//获取用户请求的url
		String url = request.getRequestURI();
		List<String> commonUrl = ResourcesUtil.gekeyList("commonUrl");
		for(String openUrl:commonUrl){
			if(url.lastIndexOf(openUrl)>0)
				return true;
		}
		//若请求的url非公开则检验身份
		HttpSession session = request.getSession();
		if(session.getAttribute("sysUser")!=null){
			return true;
		}
		//若session中检验不存在则要进行身份验证
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);;
		return false;
	}
	
}
