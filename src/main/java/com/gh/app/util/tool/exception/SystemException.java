package com.gh.app.util.tool.exception;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:16:31
 * @Discription  自定义系统层异常 
 */
public class SystemException extends RuntimeException {
	private String key;
	private Object[] values;
	
	private static final long serialVersionUID = 1L;

	public SystemException() {
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SystemException(String key,String message){
		super(message);
		this.key = key;
	}
	
	public SystemException(String key,Object value,String message){
		super(message);
		this.key = key;
		this.values = new Object[]{value};
	}
	
	public SystemException(String key,Object[] values,String message){
		super(message);
		this.key = key;
		this.values = values;
	}
	public String getKey() {
		return key;
	}
	public Object[] getValues() {
		return values;
	}

}
