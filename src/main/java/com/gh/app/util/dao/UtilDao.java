package com.gh.app.util.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.gh.app.util.entities.Param;
import com.gh.app.util.entities.QueryResult;

public interface UtilDao{
	
	/**
	 * 
	 * 方法描述 : 将一个对象从SESSION中清除
	 *
	 *  @param o
	 * 创建人 :  luyong
	 * 创建时间: 2015-3-2 下午06:11:18
	 *
	 */
	public void detach(Object o);
	
	/**
	 * @Description: 保存实体 并且在TbActionLog表记录操作日志
	 * @param obj   
	 * @return void  
	 * @throws
	 * @author liuhui
	 * @date 2015-11-3下午04:43:32
	 */
	public abstract void save(Object obj);
	
	/**
	 * @Description: 修改实体 并且在TbActionLog中记录操作日志(包括逻辑删除日志)
	 * @param obj   
	 * @return void  
	 * @throws
	 * @author liuhui
	 * @date 2015-11-3下午04:44:14
	 */
	public abstract void update(Object obj);
	/**
	 * 修改merge
	 * @param obj 实体类对象
	 * @throws Exception
	 */
	public abstract void merge(Object obj);
	
	/**
	 * @Description: 物理删除实体 并且在TbActionLog中记录物理删除日志
	 * @param obj   
	 * @return void  
	 * @throws
	 * @author liuhui
	 * @date 2015-11-3下午04:45:42
	 */
	public abstract void delete(Object obj);

	/**
	 * 根据ID数组,删除多个实体类对象 
	 * @param clazz
	 * @param entityids
	 */
	public <T> void  delete(Class<T> clazz, Serializable ... entityids);
	
	/**
	 * 根据ID查询对象
	 * @param classes 实体类
	 * @param id　字符串
	 * @return
	 * @throws Exception
	 */
	public abstract <T> T  getObjectById(Class<T> clazz,Serializable id);
	
	/**
	 * 用hql语句查询所有记录
	 * @param hql
	 * @return
	 */
	public abstract List getHqlQuery(String hql,Object...objects);
	/**
	 * 用hql语句分页查询
	 * @param hql
	 * @param start 起始数
	 * @param count 页大小
	 * @return
	 */		
	public abstract List getHqlQuery(String hql,int start,int count);
	
	/**
	 * 查询总记录数
	 * @param hql
	 * @return int
	 * @
	 */
	public abstract Long getHqlQuerySize(String hql,Object...objects);
	
	/**
	 * 根据条件删除对象
	 * @param hql  
	 * @param params 
	 */
	public void deleteAll(String hql,  Map<String, Object> params);
	
	/**
	 * 根据hql 参数 更新对象 
	 * @param hql
	 * @param params
	 */
	public void updateAll(String hql, Map<String, Object> params);
	
	/**
	 * @author ZJB
	 * 用Sql语句分页查询
	 * @param hql
	 * @param start 起始数
	 * @param count 页大小
	 * @return
	 */	
	public List getObjectBySql(String sql,int start,int count) ;
	
	/**
	 * 根据sql取得数据count
	 * @param sql
	 * @return
	 * @
	 */
	public int getCountBySql(String sql,Object...objects);
	
	/**
	 * 根据ID 查询实体对象 
	 * @param <T>
	 * @param clazz
	 * @param ID
	 * @return
	 */
	public <T> T getObject(Class<T> clazz, Serializable ID);
	
	public Object getObject(String className, Serializable ID);
	
	/**
	 * 根据hql 查询总数 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * 根据hql 和参数 查询总数 
	 * @param hql
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 执行HQL 更新语句 
	 * @param hql
	 * @return
	 */
	public int executeHql(String hql,Object...objects);
	
	/**
	 * 根据Hql 和分页参数查询对象列表 
	 * @param hql  
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public List find(String hql, int start, int pagesize);
	
	/**
	 * 根据Hql 和分页参数,排序属性, 查询对象列表 
	 * @param hql 注意: hql中的对象别名一律命名为o.  否则orderby 语句不能正常构建 会报错 
	 * @param start  开始页
	 * @param pagesize  页大小 
	 * @param orderby 排序属性 例如 {key:"name", value:"desc"}  属性名:升序/降序 
	 * @return
	 */
	public List find(String hql, int start, int pagesize, Map<String, String> orderby);
	
	/**
	 * 根据Hql,查询参数 和分页参数, 查询对象列表 
	 * @param hql  "from CorpInfo o where o.name=:name "
	 * @param params  命名查询参数 
	 * @param start   开始页 
	 * @param pagesize 页大小 
	 * @return
	 */
	public List find(String hql, Map<String, Object> params, int start, int pagesize);
	
	/**
	 *  根据Hql, 查询参数 和分页参数 排序字段, 查询对象列表 
	 * @param hql hql 查询语句 中的对象别名要设置为 o
	 * @param params hql 命名参数
	 * @param start
	 * @param pagesize
	 * @param orderby
	 * @return
	 */
	public List find(String hql, Map<String, Object> params, int start, int pagesize,Map<String, String> orderby);
	
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
	 * 根据Hql,查询参数 和分页参数, 查询对象列表 
	 * @param hql   hql 查询语句 中的对象别名要设置为 o
	 * @param start
	 * @param pagesize
	 * @param orderby 
	 * @return
	 */
	public QueryResult getPagingData(String hql,  int start, int pagesize, Map<String, String> orderby);
	
	/**
	 * 按条件更新对象
	 * @param objName 对象名
	 * @param sets	要更新的字段
	 * @param params 条件集合
	 * @
	 */
	public void updateAll(String objName, Map sets, List<Param> params);

	/**
	 * 根据sql语句执行更新或删除
	 * @param sql
	 * @param params ?占位符，可以不传或者传null
	 * @
	 */
	public int executeSql(String sql,Object...params);

	public List getHqlList(String hql, Object... params);

	public Long getSqlQuerySize(String sql, Object... params);

	public List getSqlList(String sql, Object... params);
	/**
	 * 根据SQL获取一条记录,未确认null值(支持带名称的占位符)
	 * @param sql
	 * @param params
	 * @author nie.yingtong
	 * @return
	 */
	public Object getSqlSingle(String sql, Object... params);
	/**
	 * 根据sql语句批量删除数据
	 * @param sql
	 * @
	 */
	public void deleteBySql(String sql);
	
	/**
	 * 在JPA环境下获取原生的Connection 对象,
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() ;

}