package com.gh.app.util.entities;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午4:24:08
 * @Discription  用于封装分页参数的值对象 
 */
public class Pager {
	private Long totalRows; // 总行数
	private int pageSize = 20; // 每页显示的行数
	private int pageNo = 1; // 当前页号
	private int totalPages = 0; // 总页数
	private int startRow; // 当前页在数据库中的起始行
	private String name; //按名称查询
	
	public Pager(int pageNo,int pageSize) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	
	public Pager() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
}
