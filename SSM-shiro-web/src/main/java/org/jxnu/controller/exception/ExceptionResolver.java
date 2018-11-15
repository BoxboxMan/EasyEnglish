package org.jxnu.controller.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * @author MR.S
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		CustomException customException = null;
		if (ex!=null) {
			if(ex instanceof CustomException){
				customException = (CustomException) ex;
			}else if(ex instanceof UnauthorizedException){
				customException = new CustomException("无权限操作");
			}else{
				customException = new CustomException("未知错误");
			}
		}
		modelAndView.addObject("message", customException.getMessage());
		return modelAndView;
	}

}
