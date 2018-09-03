package com.gh.app.util.tool.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午3:54:45
 * @Discription  应用上下文工具类
 */
public class ContextUtil {

	public static HttpServletRequest getRequest() {
		if(RequestContextHolder.getRequestAttributes() != null ) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			return request;
		}
		return null;
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static String getRemoteIp() {
		return getRequest().getRemoteAddr();
	}

	public static int getRemotePort() {
		return getRequest().getRemotePort();
	}

	public static String getRequestURL() {
		return getRequest().getRequestURL().toString();
	}
	
	public static Object getSessionAttr(String key) {
		if(getRequest() != null) {
			HttpSession session = getRequest().getSession(false);
			if (session == null) {
				return null;
			} else {
				return session.getAttribute(key);
			}
		}
		return null;
	}
	
	public static ServletContext getServletContext() {
		return getSession().getServletContext();
	}
	
	public static Object getSCAttr(String key) {
		return getServletContext().getAttribute(key);
	}
	public static void setSCAttr(String key, Object value) {
		getServletContext().setAttribute(key, value);
	}
	
	public static String getRealPath(String path) {
		return getServletContext().getRealPath(path);
	}
	
}

