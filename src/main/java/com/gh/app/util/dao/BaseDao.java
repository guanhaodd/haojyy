package com.gh.app.util.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.gh.app.util.entities.QueryResult;;


/**
 * 基础数据库操作类 支持泛型
 * 
 * 其他DAO继承此类获取常用的数据库操作方法
 * 
 */
public interface BaseDao<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void save(T o);
	
	/**
	 * 
	 * 方法描述 : 讲一个对象从SESSION中清除
	 * 
	 * @param o
	 * 创建人 :  yangzhuo
	 * 创建时间: 2014-8-29 下午04:36:22
	 *
	 */
	public void detach(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void update(T o);

	/**
	 * 通过主键获得对象
	 * 
	 * @param c
	 *            类名.class
	 * @param id
	 *            主键
	 * @return 对象
	 */
	public T get(Serializable id);

	/**
	 * 通过主键获得对象(全对象)
	 * 
	 * @param c
	 *            类名.class
	 * @param id
	 *            主键
	 * @return 对象
	 */
	public T load(Serializable id);
	
	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @param hql
	 *            HQL语句
	 * @return 对象
	 */
	public T get(String hql);

	/**
	 * 通过HQL语句获取一个对象
	 * 
	 * @param hql
	 *            HQL语句   例: "from Object o where o.name = :name";
	 * @param params  命名参数 
	 *            参数  Map<name,value>   
	 * @return 对象
	 */
	public T get(String hql, Map<String, Object> params);
	
	/**
	 * 通过HQL语句获取一个对象
	 * @param hql	   例: "from Object o where o.name = ?1"
	 * @param params  位置参数  对象数组 ["name"]
	 * @return   对象 
	 */
	public T get(String hql, Object ... params);
	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句 (from Object or select o from Object o)
	 * @return long
	 */
	public Long count(String hql);

	/**
	 * 统计数目
	 * 
	 * @param hql
	 *            HQL语句( from T where xx = :xx or select t from Tt where xx = :xx)
	 * @param params  命名参数 
	 *            参数   Map<name,value>  
	 * @return long
	 */
	public Long count(String hql, Map<String, Object> params);
	
	/**
	 * 根据Hql  统计数目 
	 * @param hql
	 * @param params 位置参数值 
	 * @return
	 */
	public Long count(String hql, Object ... params );

	/**
	 * 执行一条HQL语句
	 * 
	 * @param hql
	 *            HQL语句
	 * @return 响应结果数目
	 */
	public int executeHql(String hql);

	/**
	 * 执行一条HQL语句
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return 响应结果数目
	 */
	public int executeHql(String hql, Map<String, Object> params);
	
	/**
	 * 
	 * @param hql
	 * @param params  位置参数 
	 * @return
	 */
	public int executeHql(String hql, Object ... params );

	
	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param start
	 *            开始页数
	 * @param pagesize
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, int start, int pagesize);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params);

	/**
	 * 获得结果集
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @param start
	 *            开始页数
	 * @param pagesize
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params, int start, int pagesize);

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 响应行数
	 */
	public int executeSql(String sql);

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 响应行数
	 */
	public int executeSql(String sql, Map<String, Object> params);

	/**
	 * 统计
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 数目
	 */
	public Long countBySql(String sql);

	/**
	 * 统计
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 数目
	 */
	public Long countBySql(String sql, Map<String, Object> params);
	
	/**
	 * 获取记录总数
	 * @param entityClass 实体类
	 * @return
	 */
	public long getCount();
	/**
	 * 清除一级缓存的数据
	 */
	public void clear();
	
	/**
	 * 删除实体
	 * @param entityClass 实体类
	 * @param entityids 实体id数组
	 */
	public void delete(Serializable ... entityids);

	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @return List
	 */
	public List<T> find(String hql);
	/**
	 * 根据HQL语句查询所有记录
	 * @param hql
	 * @return
	 */
	public QueryResult<T> getPagingData(String hql);

	/**
	 * 获得对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return List
	 */
	public List<T> find(String hql, Map<String, Object> params);
	
	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public QueryResult<T> getPagingData(String hql, Map<String, Object> params);
	
	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, Object ... params);
	public QueryResult<T> getPagingData(String hql, Object ... params);
	
	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param page
	 *            开始页数
	 * @param rows
	 *            每页显示多少条
	 * @return List
	 */
	public List<T> find(String hql, int start, int pagesize);
	
	/**
	 * 
	 * @param hql
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult<T> getPagingData(String hql, int start, int pagesize);

	/**
	 * 获得分页后的对象列表
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @param start
	 *            开始页数
	 * @param pagesize
	 *            每页显示多少条
	 * @return List
	 */
	public List<T> find(String hql, Map<String, Object> params, int start, int pagesize);
	/**
	 * 
	 * @param hql
	 * @param params
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult<T> getPagingData(String hql, Map<String, Object> params, int start, int pagesize);

	public List<T> pagingfind(String hql,  int start, int pagesize , Object ... params);
	
	/**
	 * 
	 * @param hql
	 * @param start
	 * @param pagesize
	 * @param params
	 * @return
	 */
	public QueryResult<T> getPagingData(String hql,  int start, int pagesize , Object ... params);
	/**
	 * 获取分页数据
	 * @param <T>
	 * @param entityClass 实体类
	 * @param firstindex 开始索引
	 * @param maxresult 需要获取的记录数
	 * @return
	 */
	public List<T> find( String wherejpql, Object[] queryParams,Map<String, String> orderby, int start, int pagesize);
	
	/**
	 * 根据条件查询获取分页数据
	 * @param wherejpql   条件JPQL 例"o.name = :name and o.test = :test"
	 * @param queryParams
	 * @param orderby
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult<T> getPagingData( String wherejpql, Object[] queryParams,Map<String, String> orderby, int start, int pagesize);
	/**
	 * 
	 * @param wherejpql
	 * @param queryParams
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public List<T> find(String wherejpql, Object[] queryParams, int start, int pagesize);
	/**
	 * 
	 * @param wherejpql
	 * @param queryParams
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult<T> getPagingData(String wherejpql, Object[] queryParams, int start, int pagesize);
	
	/**
	 * 
	 * @param orderby
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public List<T> find(Map<String, String> orderby, int start, int pagesize);
	/**
	 * 
	 * @param orderby
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult<T> getPagingData(Map<String, String> orderby, int start, int pagesize);
	
	/**
	 * 
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public List<T> find(int start, int pagesize);
	/**
	 * 
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public QueryResult<T> getPagingData(int start, int pagesize);
	
	/**
	 * 
	 * @return
	 */
	public List<T> find();
	
	/**
	 * 
	 * @return
	 */
	public QueryResult<T> getPagingData();
	
	/**
	 * 
	 * @param wherejpql
	 * @param queryParams
	 * @return
	 */
	public Long totalCount(String wherejpql, Object[] queryParams) ;
	
	/**
	 * 根据属性和属性值进行查询对象 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T findByProperty(String propertyName, Object value) ;

}