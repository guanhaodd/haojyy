package com.gh.app.util.entities;


import java.util.List;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:24:22
 * @Discription  对数据库查询的结果集进行封装 
 * @param <T>
 */
public class QueryResult<T> {
	/**
	 * 分页查询结果集
	 */
	private List<T> rows;

	/**
	 * 总记录数 
	 */
	private Long total;   
	
	private Pager pager;
	private String sort;// 排序字段
	private String direction;// asc/desc

	public List<T> getRows() {
		return this.rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
 
}
