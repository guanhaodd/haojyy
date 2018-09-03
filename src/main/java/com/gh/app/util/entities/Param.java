package com.gh.app.util.entities;

/**
 * remark: 查询参数辅助类 author: anhry
 */
public class Param {

	private String paramName;// 参数名称
	private Object paramValue;// 参数值
	private String operator;// 连接符(如：=,like,in)

	public Param() {
	}

	public Param(String paramName, String operator, Object paramValue) {
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}
}
