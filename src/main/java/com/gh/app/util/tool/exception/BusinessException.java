package com.gh.app.util.tool.exception;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:15:39
 * @Discription  业务层异常
 */
public class BusinessException extends Exception {
	private String key;
	private Object[] values;
	
	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BusinessException(String key,String message){
		super(message);
		this.key = key;
	}
	
	public BusinessException(String key,Object value,String message){
		super(message);
		this.key = key;
		this.values = new Object[]{value};
	}
	
	public BusinessException(String key,Object[] values,String message){
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
