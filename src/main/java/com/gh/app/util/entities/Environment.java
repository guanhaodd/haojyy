package com.gh.app.util.entities;

import com.gh.app.util.tool.io.PropertiesLoader;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午3:59:29
 * @Discription  配置文件相关属性
 */
public class Environment {
    private static PropertiesLoader appconfig = null;
    private static PropertiesLoader sensitive = null;
    
    /**
     * 配置文件(appconfig.properties)
     */
    public static PropertiesLoader getAppConfig() {
    	if(appconfig == null){
    		appconfig = new PropertiesLoader("application.properties");
    	}
        return appconfig;
    }
    
    /**
     * 配置文件(sensitive.properties)
     */
    public static PropertiesLoader getSensitive() {
    	if(sensitive == null){
    		sensitive = new PropertiesLoader("sensitive.properties");
    	}
        return sensitive;
    }
    
    /**
     * jdbc url连接参数(默认:"").
     */
    public static String getJdbcUrl(){
    	return Environment.getAppConfig().getProperty("jdbc.url","");
    }
    /**
     * 获取是否是开发模式(默认:false).
     */
    public static boolean isDevMode(){
    	return getAppConfig().getBoolean("dev.mode",false);
    }
    
    
    
	/**
	 * 获得上传表单域的名称
	 * 
	 * @return
	 */
	public static final String getUploadFieldName() {
		return getAppConfig().getProperty("uploadFieldName", "filedata");
	}

	/**
	 * 获得上传文件的最大大小限制
	 * 
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		 String uploadFileMaxSize = getAppConfig().getProperty("uploadFileMaxSize", "20971520");
		 return Long.valueOf(uploadFileMaxSize);
	}

	/**
	 * 获得允许上传文件的扩展名
	 * 
	 * @return
	 */
	public static final String getUploadFileExts() {
		return getAppConfig().getProperty("uploadFileExts","txt,rar,zip,doc,docx,xls,xlsx,jpg,jpeg,gif,png,swf,wmv,avi,wma,mp3,mid");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return getAppConfig().getProperty("upload.files.base.path", "appfiles");
	}
    
	public static String getFileUrl() {
		return getAppConfig().getProperty("upload.files.base.url", "appfiles");
	}
}
