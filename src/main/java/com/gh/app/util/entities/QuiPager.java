package com.gh.app.util.entities;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:24:55
 * @Discription  用于封装QUI的分页参数以及排序字段 
 */
public class QuiPager {
	
	private Pager pager;
	private String sort;// 排序字段
	private String direction;// asc/desc

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
