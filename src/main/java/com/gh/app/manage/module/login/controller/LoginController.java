package com.gh.app.manage.module.login.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.gh.app.manage.entity.ApplicationConstants;
import com.gh.app.manage.module.sys.user.bean.TbUser;
import com.gh.app.manage.module.sys.user.service.UserService;
import com.gh.app.util.constants.Constants;
import com.gh.app.util.tool.charactor.MD5Util;
import com.gh.app.util.tool.endecode.Base64Decoder;
import com.gh.app.util.tool.http.HttpRequestUtil;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Resource
	private UserService userService;
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/to_login")
	public String toLogin() {
		return "forward:/WEB-INF/pages/login/login.jsp";
	}
	
	@RequestMapping("/logon")
	public String login(TbUser user, Model model, String validcode, 
			HttpServletRequest request, HttpSession session){
			//尝试从session中获取登录用户
			TbUser sessionUser = (TbUser)request.getSession().getAttribute(Constants.ADMIN_USER);
			if(sessionUser != null) {
				return "forward:/main/index";
			} 
			if(StringUtils.isBlank(user.getUserName())){
				model.addAttribute("tip", "* 请输入您的用户名");
				return "forward:/WEB-INF/pages/login/login.jsp";
			}
			request.getSession().removeAttribute(Constants.VALID_CODE);
			String username = user.getUserName();
			String password = new String(Base64Decoder.decode(user.getPassword()));
			user.setPassword(password);
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), MD5Util.md5(user.getPassword()));
			try {  
	            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
	            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
	            //所以这一步在调用login(token)方法时,它会走到ShiroDbRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
	            currentUser.login(token);  
	        }catch(UnknownAccountException uae){  
	            LOG.debug("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
	            request.setAttribute("tip", "* 对不起,您输入的用户名/密码不正确");  
	        }catch(IncorrectCredentialsException ice){  
	            LOG.debug("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
	            request.setAttribute("tip", "* 对不起,您输入的用户名/密码不正确");  
	        }catch(LockedAccountException lae){  
	            LOG.debug("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
	            request.setAttribute("tip", "* 账户已锁定");  
	        }catch(ExcessiveAttemptsException eae){  
	            LOG.debug("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
	            request.setAttribute("tip", "* 用户名或密码错误次数过多，请稍后再试");  
	        }catch(AuthenticationException ae){  
	            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
	            LOG.debug("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
	            ae.printStackTrace();  
	            request.setAttribute("tip", "* 对不起,您输入的用户名/密码不正确");  
	        }  
	        //验证是否登录成功  currentUser.
	        if(currentUser.isAuthenticated()){  
	            LOG.debug("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
	            LOG.debug("用户[" + currentUser.getPrincipal() + "] logged in successfully! ");  
	            user = (TbUser)currentUser.getSession().getAttribute(Constants.ADMIN_USER); // 从shiro的session 中获取用户的登录信息 
	            /**
				 * 记录上线用户
				 */
				user.setLastLoginTime(new Date());
				try{
					user.setLastLoginIp(HttpRequestUtil.getIpAddr(request));
				}catch(Exception e){
					LOG.error("登录时获取所处IP地址出错：" + e.getMessage());
				}
				ApplicationConstants.ONLIE_USERS.add(user);
				userService.update(user);
				/**
				 * 记录日志信息
				 */
				//saveLogonLog(user, "用户登录", request);
	            token.setRememberMe(true);
	        }else{  
	            token.clear();  
	        	request.getSession().removeAttribute(Constants.ADMIN_USER);
	        	model.addAttribute("username", user.getUserName());
	            return "forward:/WEB-INF/pages/login/login.jsp";
	        } 
		    if(LOG.isDebugEnabled()) {
		    	//LOG.debug("Users funs is ---> " + userFuns);
		    }
			return "forward:/main/index";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		 TbUser admin = (TbUser)session.getAttribute(Constants.ADMIN_USER);
         ApplicationConstants.SESSION_MAP.remove(session.getId());
         ApplicationConstants.ONLIE_USERS.remove(admin);
         SecurityUtils.getSubject().logout();  
         return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/login/to_login";  
	}
}
