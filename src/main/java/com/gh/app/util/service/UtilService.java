package com.gh.app.util.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.gh.app.util.entities.QueryResult;
import com.gh.app.util.entities.QuiPager;

public interface UtilService{
	
	/**
	 * 
	 * 方法描述 : 将一个对象从SESSION中清除
	 *
	 *  @param o
	 * 创建人 :  luyong
	 * 创建时间: 2015-3-2 下午06:12:05
	 *
	 */
	public void detach(Object o);
	
	/**
	 * 
	 * 方法描述 : 根据实体类名 及属性名称和值， 获取实体对象 ，不包含指定主键值的实体对象(没删除)
	 *
	 *  @param entityName 实体名
	 *  @param propertyNameAndValue Map对象，不接受null。属性名为key,值为value
	 *  @param id 主键值，可为null
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-20 下午12:07:15
	 *
	 */
	public Object getNotDeleObjectByPropertiesNotId(String entityName, 
			Map<String,Object> propertyNameAndValue, Integer id);
	
	/**
	 * 
	 * 方法描述 : 根据实体类名 及属性名称和值， 获取实体对象 ，不包含指定主键值的实体对象
	 *
	 *  @param entityName 实体名
	 *  @param propertyName 属性名
	 *  @param propertyValue 属性值 
	 *  @param id 主键值，可为null
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-8-20 下午05:45:08
	 *
	 */
	public Object getObjectByPropertyNotId(String entityName, String propertyName,
			Object propertyValue, Integer id);
	
	/**
	 * 
	 * 方法描述 : 根据实体类名 及属性名称和值， 获取实体对象 ，不包含指定主键值的实体对象(没删除)
	 *
	 *  @param entityName 实体名
	 *  @param propertyName 属性名
	 *  @param propertyValue 属性值 
	 *  @param id 主键值，可为null
	 * 创建人：HeGuifang
	 * 创建时间：2014-11-17 下午02:38:13
	 */
	public Object getNotDeleObjectByPropertyNotId(String entityName, String propertyName,
			Object propertyValue, Integer id);
	
	/**
	 * 保存对象
	 * @param o
	 */
	public void save(Object o);
	
	/**
	 * 更新对象 
	 * @param o
	 */
	public void update(Object o);
	
	/**
	 * 删除对象
	 * @param o
	 */
	public void delete(Object o);
	
	/**
	 * 根据实体类名 及属性名称和值, 获取实体对象 
	 * @param entityName  实体名
	 * @param propertyName 属性名
	 * @param propertyValue 属性值 
	 * @return
	 */
	public Object getObjectByProperty(String entityName, String propertyName,
			Object propertyValue);
	
	/**
	 * 根据实体类名 及属性名称和值, 获取实体对象 （加isDele=0）
	 * @param entityName  实体名
	 * @param propertyName 属性名
	 * @param propertyValue 属性值 
	 * @return
	 */
	public Object getNotDeleObjectByProperty(String entityName, String propertyName,
			Object propertyValue,Integer id);
	
	/**
	 * 根据实体类名 及属性名称和值, 获取实体对象  （加isDele=0）
	 * @param entityName  实体名
	 * @param propertyName 属性名
	 * @param propertyValue 属性值 
	 * @return
	 */
	public Object getNotDeleObjectByProperty(String entityName, String propertyName,
			Object propertyValue);
	/**
	 * 根据数据库标识查询到实体类
	 * @param <T>
	 * @param clazz
	 * @param ID
	 * @return
	 */
	public <T> T getObjectById(Class<T> clazz, Serializable ID);
	
	/**
	 * 根据类名称查询实体对象 
	 * @param className
	 * @param ID
	 * @return
	 */
	public Object getObject(String className, Serializable ID);
	
	/**
	 * 用hql语句查询所有记录
	 * @param hql
	 * @param params ?占位符的值
	 * @return
	 */
	public abstract List getHqlList(String hql,Object... params);
	
	/**
	 * 用Sql语句查询所有记录
	 * @param hql
	 * @param params ?占位符的值
	 * @return
	 */
	public abstract List getSqlList(String sql,Object... params);
	/**
	 * 用hql语句分页查询
	 * @param hql
	 * @param start 起始数
	 * @param count 页大小
	 * @return
	 */		
	public abstract List getHqlQuery(String hql,int start,int count);
	
	/**
	 * 用Sql语句分页查询
	 * @param hql
	 * @param start 起始数
	 * @param count 页大小
	 * @return
	 */		
	public abstract List getSqlQuery(String sql,int start,int count);
	
	/**
	 * 根据Hql查询总记录数
	 * @param hql
	* @param params ?占位符的值
	 * @return Long
	 */
	public abstract Long getHqlQuerySize(String hql,Object... params);

	
	/**
	 * 根据SQL查询总记录数
	 * @param hql
	 * @param params ?占位符的值
	 * @return Long
	 */
	public abstract Long getSqlQuerySize(String Sql,Object... params);
	
	/**
	 * 执行HQL 更新语句 
	 * @param params ?占位符的值
	 * @return
	 */
	public int executeHql(String hql,Object... params);
	
	/**
	 * 根据对象分页查询对象结果集
	 * @param  obj查询对象
	 * @param  p 分页对象
	 * @param  cond 其他特殊的查询条件 索引导航用 o，例如 o.name="zhangsan"
	 * @return
	 */
	public QueryResult getObjectList(Object obj,QuiPager p,String cond) throws Exception;
	
	/**
	 * 根据对象分页查询对象结果集
	 * @param  obj查询对象
	 * @param  p 分页对象
	 * @return
	 */
	public QueryResult getObjectList(Object obj,QuiPager p) throws Exception;
	
	/**
	 * 执行SQL 更新语句 
	 * @param params ?占位符的值
	 * @return
	 */
	public int executeSql(String sql,Object... params);

	/**
	 * 根据Hql,查询参数 和分页参数, 查询对象列表 
	 * @param hql
	 * @param params hql 命名参数
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult getPagingData(String hql, Map<String, Object> params, int start, int pagesize);
	
	/**
	 * 根据Hql,查询参数 和分页参数, 查询对象列表 
	 * @param hql   hql 查询语句 中的对象别名要设置为 o
	 * @param params hql 命名参数
	 * @param start
	 * @param pagesize
	 * @param orderby
	 * @return
	 */
	public QueryResult getPagingData(String hql, Map<String, Object> params, int start, int pagesize, Map<String, String> orderby);
	
	/**
	 * 根据Hql 分页参数, 查询对象列表 
	 * @param hql
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult getPagingData(String hql,  int start, int pagesize);
	
	/**
	 * 根据Hql 获取记录数
	 * @param hql
	 * @return
	 */
	public Long countHqlList(String hql);
}
