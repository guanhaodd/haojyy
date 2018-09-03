package com.gh.app.util.entities;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:22:38
 * @Discription  对AJAX请求 返回JSON格式的数据. 
 */
public class JsonView implements java.io.Serializable {

	private static final long serialVersionUID = -4341053569992118004L;
	private boolean success = false;   //操作是否成功
	private String msg = "";           //操作提示信息
	private Object obj = null;		   //传值信息

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public JsonView(boolean success, String msg, Object obj) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}

	public JsonView() {
		super();
	}
	
}
