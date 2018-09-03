package com.gh.app.util.tool.inteceptor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.gh.app.util.entities.Environment;
import com.gh.app.util.tool.log.Logs;



/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:00:16
 * @Discription  过滤非法字符过滤器
 */
public class ParametersFilter extends OncePerRequestFilter {
	private static Properties pp = null;
	private static boolean replace = false;
	static{
		  pp = Environment.getSensitive().getProperties();//获取转义字符
		  replace = "true".equals(pp.getProperty("replace"));//是否转义开关
	}
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	    String referer = request.getHeader("Referer") ;
	    try{
	    	String url = request.getRequestURL().toString();
			if (referer != null && referer.indexOf(request.getContextPath()) < 0 && url.indexOf("ssoLogon") < 0
					&& referer.indexOf("webapp") < 0) {
				request.setAttribute("errmsg", "对不起,您是从非法链接跳转到本系统的");
				request.getRequestDispatcher("/WEB-INF/pages/error/error-system.jsp").forward(request, response);
				return;
			}
			if(urlFilter(url)) {
				request.setAttribute("errmsg", "对不起,您请求的地址含有非法字符");
				request.getRequestDispatcher("/WEB-INF/pages/error/error-system.jsp").forward(request, response);
				return ;
			}
			String param = "";
			String paramValue = "";
			request.setCharacterEncoding("UTF-8");
			ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
			if(replace && !url.contains("phone")) {
				@SuppressWarnings("unchecked")
				Enumeration<String> params = request.getParameterNames();//获取界面元素中的每个name
				while (params.hasMoreElements()) {
					param = (String) params.nextElement();
					String[] values = request.getParameterValues(param);// 获得每个参数的value
					
					String value= "";
					boolean replaced = false;
					for (int i = 0; i < values.length; i++) {//循环遍历值，是否存在需要转义的字符。
						if(parameterFilter(values[i])) {
							request.setAttribute("errmsg", "对不起,您提交的参数中含有非法字符");
							request.getRequestDispatcher("/WEB-INF/pages/error/error-system.jsp").forward(request, response);
							return ;
						}
						paramValue = values[i];
						if(paramValue!=null && paramValue.equals(param)){//如果页面传来的name与value相同，则不需要转换，解决文本编辑器被拦截的bug。
							continue ;
						}
						for (Object oj : pp.keySet()) {
							String key = (String) oj;
							//敏感字符替换 自定义转换规则
							if(paramValue.contains(key)) {
								paramValue = paramValue.replace(key, pp.getProperty(key));
								replaced = true;
							}
						}
						value += paramValue;
					}
					if(Logs.isDebugEnabled()) {
						Logs.debug("param : " + param + ", value ---> " + value);
					}
					if(replaced) {
						requestWrapper.addParameter(param , value);
					}
				}
			}
			filterChain.doFilter(requestWrapper, response);
	    }catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean urlFilter(String url){
		boolean b = false;
		url=url.toLowerCase();
		if(null!=url && url.length()>0){
			if(url.contains("#")){
				return true;
			}
			if(url.contains("script")){
				return true;
			}
			if(url.contains("document")){
				return true;
			}
			if(url.contains("eval")){
				return true;
			}
			if(url.contains("alert")){
				return true;
			}
			if(url.contains("*")){
				return true;
			}
			if(url.contains("'")){
				return true;
			}
			if(url.contains("\"")){
				return true;
			}
			if(url.contains(")")){
				return true;
			}
			if(url.contains("<>")){
				return true;
			}
			if(url.contains("<")){
				return true;
			}
			if(url.contains(">")){
				return true;
			}
			if(url.contains("(")){
				return true;
			}
			if(url.contains("()")){
				return true;
			}
		}
		return b;
	}

	private boolean parameterFilter(String url){
		boolean b = false;
		url=url.toLowerCase();
		if(null!=url && url.length()>0){
			if(url.contains("script")){
				return true;
			}
			if(url.contains("document")){
				return true;
			}
			if(url.contains("alert")){
				return true;
			}
			if(url.contains("delete")){
				return true;
			}
			if(url.contains("drop")){
				return true;
			}
		}
		return b;
	}
	
}
