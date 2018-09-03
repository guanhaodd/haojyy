package com.gh.app.manage.entity;

import com.gh.app.util.entities.Environment;
import com.gh.app.util.tool.io.PropertiesLoader;

/**
 * @author AezGenholmes
 * @Date 2018年7月27日下午2:38:51
 * @Discription  应用相关配置常量
 */
public class AppConfig {
	/**
	 * 读取application.properties 获取应用程序相关配置
	 */
	private static PropertiesLoader appconfig = Environment.getAppConfig();
	/**
	 * 应用名
	 */
	public static String APP_TITLE  = null;
	/**
	 * 注册版权
	 */
	public static String CORP_RIGHT  = null;
	/**
	 * 技术支持
	 */
	public static String SUPPORT  = null;
	/**
	 * 联系电话
	 */
	public static String PHONE  = null;
	/**
	 * 用户提示
	 */
	public static String TIP  = null;
	/**
	 * 区域编码
	 */
	public static String DISTRICE_CODE  = null;
	/**
	 * 应用上传POST地址
	 */
	public static String APP_FILE_BASE_URL  = null;
	/**
	 * 应用下载物理路径
	 */
	public static String UPLOAD_FILE_BASE_PATH  = null;
	
	/**
	 * 微信APPID
	 */
	public static String WXSP_APPID = null;
	
	/**
	 * 微信app secret
	 */
	public static String WXSP_SECRET = null;
	
	/**
	 * 微信授权类型
	 */
	public static String GRANT_TYPE = null;
	
	static {
		APP_TITLE = appconfig.getProperty("app.title");
		CORP_RIGHT = appconfig.getProperty("app.corpright");
		SUPPORT = appconfig.getProperty("app.technology.support");
		PHONE = appconfig.getProperty("app.contact.phone");
		TIP = appconfig.getProperty("app.user.tip");
		APP_FILE_BASE_URL= appconfig.getProperty("upload.files.base.url");
		UPLOAD_FILE_BASE_PATH= appconfig.getProperty("upload.files.base.path");
		WXSP_APPID=appconfig.getProperty("wxspAppid");
		WXSP_SECRET=appconfig.getProperty("wxspSecret");
		GRANT_TYPE=appconfig.getProperty("grant_type");
	}
}
