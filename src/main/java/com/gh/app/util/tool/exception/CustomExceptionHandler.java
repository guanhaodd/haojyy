package com.gh.app.util.tool.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.gh.app.util.entities.Environment;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:20:33
 * @Discription  自定义异常处理器   统一的异常处理 
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@Autowired
	protected ResourceBundleMessageSource res;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object handler,	Exception ex) {
		boolean isDevMode = Environment.isDevMode(); 
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		
		LOG.error(ex.getMessage());
		
		if(isDevMode) {
			ex.printStackTrace();
		}
		String errorMsg = null;
		// 根据不同错误转向不同页面
		if(ex instanceof BusinessException) {
			BusinessException be = (BusinessException)ex;
			String key = be.getKey();
			if(key != null){
				if(be.getValues() != null){
					errorMsg = getMessage(key, be.getValues());
				}else{
					errorMsg = getMessage(key);
				}
				model.put("errorMsg", errorMsg);
			}
			
			return new ModelAndView("error/error-business", model);
			
		}else if(ex instanceof SystemException) {
			SystemException se = (SystemException)ex;
			String key = se.getKey();
			if(key != null){
				if(se.getValues() != null){
					errorMsg = getMessage(key, se.getValues());
				}else{
					errorMsg = getMessage(key);
				}
				model.put("errorMsg", errorMsg);
			}
			return new ModelAndView("error/error-system", model);
		} else if(ex instanceof org.apache.shiro.authz.UnauthorizedException) {
			model.put("tip", "对不起, 您没有权限访问该页面!");
			return new ModelAndView("share/notAutorization", model);
		} else {
			model.put("errorMsg", "对不起, 系统出现异常,请联系管理员或稍后再试!");
			return new ModelAndView("error/error", model);
		} 
	}
	
	protected String getMessage(String code) {
		return this.getMessage(code, new Object[] {});
	}
	
	protected String getMessage(String code, Object[] args) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = localeResolver.resolveLocale(request);
		////System.out.println("Current local is ---> " + locale.toString());
		return res.getMessage(code, args, locale);
	}
}
