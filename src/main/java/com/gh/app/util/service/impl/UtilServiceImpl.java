package com.gh.app.util.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gh.app.util.dao.UtilDao;
import com.gh.app.util.service.UtilService;
import com.gh.app.util.entities.QueryResult;
import com.gh.app.util.entities.QuiPager;

@Service("utilService")
public class UtilServiceImpl implements UtilService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UtilServiceImpl.class);
	@Resource
	private UtilDao utilDao;

	@Override
	public void detach(Object o) {
		this.utilDao.detach(o);
	}
	
	@Override
	public Object getNotDeleObjectByPropertiesNotId(String entityName, 
			Map<String,Object> propertyNameAndValue, Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select o from ").append(entityName).append(" o where o.isDele='0'");
		for (String name : propertyNameAndValue.keySet()) {
			hql.append(" and o.").append(name).append(" = '").append(propertyNameAndValue.get(name)).append("'");
		}
		if(id!=null){//除去指定主键值的记录
			hql.append(" and o.id != "+id);
		}
		List objs = utilDao.getHqlQuery(hql.toString());
		if(LOG.isDebugEnabled()) {
			LOG.debug("Ajax Hql is : " + hql);
			LOG.debug("objs' size is : " + objs.size());
		}
 		if(objs != null && objs.size() > 0) {
			return objs.get(0);
		}
		return null;
	}
	
	@Override
	public Object getObjectByPropertyNotId(String entityName, String propertyName,
			Object propertyValue, Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select o from ").append(entityName).append(" o where o.")
			.append(propertyName).append("='").append(propertyValue).append("'");
		if(id!=null){//除去指定主键值的记录
			hql.append(" and o.id != "+id);
		}
		List objs = utilDao.getHqlQuery(hql.toString());
		if(LOG.isDebugEnabled()) {
			LOG.debug("Ajax Hql is : " + hql);
			LOG.debug("objs' size is : " + objs.size());
		}
 		if(objs != null && objs.size() > 0) {
			return objs.get(0);
		}
		return null;
	}
	
	@Override
	public Object getNotDeleObjectByPropertyNotId(String entityName, String propertyName,
			Object propertyValue, Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select o from ").append(entityName).append(" o where o.isDele=0 and o.")
			.append(propertyName).append("='").append(propertyValue).append("'");
		if(id!=null){//除去指定主键值的记录
			hql.append(" and o.id != "+id);
		}
		List objs = utilDao.getHqlQuery(hql.toString());
		if(LOG.isDebugEnabled()) {
			LOG.debug("Ajax Hql is : " + hql);
			LOG.debug("objs' size is : " + objs.size());
		}
 		if(objs != null && objs.size() > 0) {
			return objs.get(0);
		}
		return null;
	}
	
	@Override
	public Object getNotDeleObjectByProperty(String entityName, String propertyName,
			Object propertyValue){
		return this.getNotDeleObjectByProperty(entityName, propertyName, propertyValue, null);
	}
	
	@Override
	public Object getNotDeleObjectByProperty(String entityName, String propertyName,
			Object propertyValue,Integer id){
		StringBuilder hql = new StringBuilder();
		hql.append("select o from ").append(entityName).append(" o where o.")
			.append(propertyName).append("='").append(propertyValue).append("'")
			.append(" and o.isDele='0'");
		if(id!=null){//除去指定主键值的记录
			hql.append(" and o.id != "+id);
		}
		List objs = utilDao.getHqlQuery(hql.toString());
		if(LOG.isDebugEnabled()) {
			LOG.debug("Ajax Hql is : " + hql);
			LOG.debug("objs' size is : " + objs.size());
		}
 		if(objs != null && objs.size() > 0) {
			return objs.get(0);
		}
		return null;
	}
	
	
	@Override
	public Object getObjectByProperty(String entityName, String propertyName,
			Object propertyValue) {
//		StringBuilder hql = new StringBuilder();
//		hql.append("select o from ").append(entityName).append(" o where o.")
//				.append(propertyName).append("='").append(propertyValue).append("'");
//		
//		List objs = utilDao.getHqlQuery(hql.toString());
//		if(LOG.isDebugEnabled()) {
//			LOG.debug("Ajax Hql is : " + hql);
//			LOG.debug("objs' size is : " + objs.size());
//		}
// 		if(objs != null && objs.size() > 0) {
//			return objs.get(0);
//		}
//		return null;
		return this.getObjectByPropertyNotId(entityName, propertyName, propertyValue, null);
	}

	@Override
	public <T> T getObjectById(Class<T> clazz, Serializable ID) {
		return this.utilDao.getObjectById(clazz, ID);
	}

	@Override
	public Object getObject(String className, Serializable ID) {
		return this.utilDao.getObject(className, ID);
	}


	@Override
	public QueryResult getPagingData(String hql, Map<String, Object> params,int start, int pagesize) {
		return utilDao.getPagingData(hql, params, start, pagesize);
	}

	@Override
	public QueryResult getPagingData(String hql, Map<String, Object> params, int start, int pagesize, Map<String, String> orderby) {
		return utilDao.getPagingData(hql, params, start, pagesize, orderby);
	}

	@Override
	public QueryResult getPagingData(String hql, int start, int pagesize) {
		return utilDao.getPagingData(hql, start, pagesize);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(Object o) {
		utilDao.save(o);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(Object o) {
		utilDao.update(o);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public int executeHql(String hql,Object... params) {
		return utilDao.executeHql(hql,params) ;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public int executeSql(String sql, Object... params) {
		return utilDao.executeSql(sql,params);
	}

	public List getHqlList(String hql, Object... params) {
		return utilDao.getHqlList(hql, params);
	}
	
	public List getHqlQuery(String hql, int start, int count) {
		return utilDao.getHqlQuery(hql, start, count) ;
	}

	public Long getHqlQuerySize(String hql, Object... params) {
		return utilDao.getHqlQuerySize(hql, params);
	}

	public List getSqlList(String sql, Object... params) {
		return utilDao.getSqlList(sql, params) ;
	}

	public List getSqlQuery(String sql, int start, int count) {
		return utilDao.getObjectBySql(sql, start, count) ;
	}

	public Long getSqlQuerySize(String sql, Object... params) {
		return utilDao.getSqlQuerySize(sql, params) ;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(Object obj) {
		this.utilDao.delete(obj) ;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(Class obj, Serializable... id) {
		this.utilDao.delete(obj, id) ;
	}
	
	public QueryResult getObjectList(Object obj, QuiPager p) throws Exception {
		return this.getObjectList(obj, p, null) ;
	}
	
	public QueryResult getObjectList(Object obj, QuiPager p,String cond) throws Exception {
		Class clazz = obj.getClass() ;
		StringBuilder hql = new StringBuilder("from ") ;
		hql.append(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)).append(" o where 1=1 ") ;
		Method methods[] = clazz.getDeclaredMethods() ;
		for(Method m:methods){
			if(m.getName().startsWith("get") || m.getName().startsWith("is")){//get方法
				Object val = m.invoke(obj, null) ;
				if(val !=null){
					String fieldName = m.getName().substring(2) ;
					fieldName = fieldName.substring(0, 1).toLowerCase()+fieldName.substring(1) ;
					hql.append(" and ").append(fieldName).append("='").append(val).append("'") ;
				}
			}
		}
		if(cond!=null && !"".equals(cond)){
			hql.append(cond) ;
		}
		if (p != null && p.getSort() != null) {
			hql.append(" order by ").append(p.getSort()).append(" ").append(p.getDirection());
		} else {
			hql.append(" order by o.id desc");
		}
		return utilDao.getPagingData(hql.toString(), p.getPager().getPageNo(), p.getPager().getPageSize());
	}
	
	@Override
	public Long countHqlList(String hql){
		return utilDao.count(hql);
	}
}
