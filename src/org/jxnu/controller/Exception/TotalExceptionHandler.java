package org.jxnu.controller.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局一场处理器
 * @author MR.S
 *
 */
public class TotalExceptionHandler implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ec) {
		ModelAndView modelAndView = new ModelAndView("error");
		CustomException wholeException;
		if(ec instanceof CustomException){
			wholeException = (CustomException) ec;
		}else{
			wholeException = new CustomException("未知错误");
		}
		modelAndView.addObject("message", wholeException.getMessage());
		return modelAndView;
	}

}
