package com.gh.app.util.dao.impl;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gh.app.util.entities.Entities;
import com.gh.app.util.entities.Pager;
import com.gh.app.util.entities.Param;
import com.gh.app.util.entities.QueryResult;
import com.gh.app.util.dao.UtilDao;



@Repository
public class UtilDaoImpl implements UtilDao {
	public static final Logger LOG = LoggerFactory.getLogger(UtilDaoImpl.class);

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void detach(Object o) {
		em.detach(o);
	}
	
	public void delete(Object obj) {
		em.remove(obj);
	}

	public <T> void delete(Class<T> clazz, Serializable... entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(clazz, id));
		}
	}

	public <T> T  getObjectById(Class<T> clazz, Serializable id) {
		return em.find(clazz, id);
	}

	public void save(Object obj) {
		em.persist(obj);
	}

	public void update(Object obj) {
		em.merge(obj);
	}

	public void merge(Object obj) {
		em.merge(obj);
	}

	
	public List getHqlQuery(String hql,Object...params) {
		Query query = em.createQuery(hql);
		if(params!=null && params.length>0){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		return query.getResultList();
	}

	public List getHqlQuery(String hql, final int start, final int count)
			{
		Query query = em.createQuery(hql);
		if (count > 0) {
			query.setFirstResult(start);
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public Long getHqlQuerySize(String hql,Object...params) {
		Query query = em.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		if(query.getResultList().size()==0 ){
            return 0L;
        }else{
            return  (Long)(query.getSingleResult());
        }

	}

	public List getObjectBySql(String sql, int start, int count) {
		Query query = em.createNativeQuery(sql);
		if (count > 0) {
			query.setFirstResult(start);
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public int getCountBySql(String sql,Object... params) {
		Query query = em.createNativeQuery(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		if(query.getResultList().size()==0 ){
            return 0;
        }else{
            return  (Integer)query.getSingleResult();
        }
	}

	public <T> T getObject(Class<T> clazz, Serializable ID) {
		String className = clazz.getName();
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		List<T> list = (List<T>) getHqlQuery("from " + simpleClassName+ " o where o.id=?",ID);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Object getObject(String className, Serializable ID) {
		List list = (List) getHqlQuery("from " + className+ " o where o.id = ? ",ID);
		
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Long count(String hql) {
		hql = "select count(*) " + hql.substring(hql.indexOf("from"));
		Query q = em.createQuery(hql);
		List list=q.getResultList();
		if(list!=null && list.size()==0 ){
            return 0L;
        }
		return list.get(0)!=null?Long.parseLong(list.get(0).toString()):0L;
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		hql = "select count(*) " + hql.substring(hql.indexOf("from"));
		if(LOG.isDebugEnabled()) {
			LOG.debug("Get count hql ---> "  + hql);
		}
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List list=q.getResultList();
		if(list!=null && list.size()==0 ){
            return 0L;
        }
		return list.get(0)!=null?Long.parseLong(list.get(0).toString()):0L;
	}

	@Override
	public List find(String hql, Map<String, Object> params, int start, 	int pagesize) {
		return find(hql, params, start, pagesize, null);
	}

	protected static String buildOrderby(Map<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("o.").append(key).append(" ").append(
						orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}

	@Override
	public void deleteAll(String hql, Map<String, Object> params) {
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.executeUpdate();
	}

	@Override
	public List find(String hql, int start, int pagesize) {
		return find(hql, null, start,pagesize);
	}

	@Override
	public List find(String hql, int start, int pagesize,Map<String, String> orderby) {
		return find(hql,null, start, pagesize, orderby);
	}

	@Override
	public List find(String hql, Map<String, Object> params, int start,
			int pagesize, Map<String, String> orderby) {
		if(orderby != null) {
			hql += buildOrderby(orderby);
		}
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((start - 1) * pagesize).setMaxResults(
				pagesize).getResultList();
	}

	public QueryResult getPagingData(String hql, Map<String, Object> params,
			int start, int pagesize) {
		return getPagingData(hql, params, start, pagesize,null);
	}
	
	public QueryResult getPagingData(String hql, Map<String, Object> params,
			int start, int pagesize, Map<String, String> orderby) {
		QueryResult qr = new QueryResult();
		qr.setRows(find(hql, params, start, pagesize, orderby));
		qr.setTotal(count(hql, params));
		
//		qr.setTotal(count(hql, params)); //如果分页查询，查询对象的总数为0，则没必要进行分页查询语句。修改人：张松  时间：20150116
//		if(qr.getTotal()!=null && qr.getTotal()>0){
//			qr.setRows(find(hql, params, start, pagesize, orderby));
//		}
		qr.setPager(new Pager(start,pagesize));
		if(LOG.isDebugEnabled()) {
			LOG.debug("Query Result's rows ---> " + qr.getRows().size());
			LOG.debug("Query Result's total ---> " + qr.getTotal());
		}
		return qr;
	}

	public QueryResult getPagingData(String hql, int start, int pagesize) {
		return getPagingData(hql, null, start, pagesize);
	}

	public QueryResult getPagingData(String hql, int start, int pagesize,
			Map<String, String> orderby) {

		 return getPagingData(hql, null, start, pagesize,orderby);
	}

	@Override
	public void updateAll(String hql, Map<String, Object> params) {
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.executeUpdate();
	}
	
	public String getObjectBySql(String sql)  {
			Query query =em.createNativeQuery(sql);
			String size = (String)query.getSingleResult();
			return size;
	}
	
	public void updateAll(String objName, Map sets, List<Param> params) {
		Query query = null;
		String strSQL = null;
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("update ");
		strBuff.append(objName);
		strBuff.append(" d ");
		if (sets != null && !sets.isEmpty()) {
			strBuff.append(" set ");
			for (Iterator it = sets.keySet().iterator(); it.hasNext();) {
				String setName = (String) it.next();
				strBuff.append(" d.");
				strBuff.append(setName);
				strBuff.append("='");
				strBuff.append(sets.get(setName));
				strBuff.append("',");
			}
			strBuff.setLength(strBuff.length() - 1); // 去,号
		}
		if (params != null && params.size() > 0) {
			strBuff.append(" where ");
			for (Iterator it = params.iterator(); it.hasNext();) {
				Param param = (Param) it.next();
				strBuff.append("d.");
				strBuff.append(param.getParamName());
				if (param.getOperator().trim().equals("in")) {
					strBuff.append(" " + param.getOperator() + "(");
					strBuff.append(param.getParamValue());
					strBuff.append(") and ");
				} else if (param.getOperator().trim().equals("like")) {
					strBuff.append(" " + param.getOperator());
					strBuff.append(" '%" + param.getParamValue());
					strBuff.append("%' and ");
				} else {
					strBuff.append(param.getOperator());
					strBuff.append(":" + param.getParamName());
					strBuff.append(" and ");
				}
			}
			strBuff.setLength(strBuff.length() - 5); // 去" and "
		}
		strSQL = new String(strBuff);
		if(LOG.isDebugEnabled()) {
			LOG.debug("Update hql is : " + strSQL);
		}
		query = em.createQuery(strSQL);
		if (params != null && !params.isEmpty()) {
			for (Iterator it = params.iterator(); it.hasNext();) {
				Param param = (Param) it.next();
				if (!param.getOperator().trim().equals("in")
						&& !param.getOperator().trim().equals("like")) {
					query.setParameter(param.getParamName(), param
							.getParamValue());
				}
			}
		}
		query.executeUpdate();
	}

	public int executeHql(String hql, Object... objects) {
		Query q = em.createQuery(hql);
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				q.setParameter(i+1, objects[i]) ;
			}
		}
		int affectRows = q.executeUpdate();
		LOG.info("Rows affectd : " + affectRows + " by hql --->" + hql);
		return affectRows;
	}

	public int executeSql(String sql, Object... params) {
		Query query = em.createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		LOG.info("Delete sql ---> " + sql);
		return query.executeUpdate();
		
	}

	public List getHqlList(String hql, Object... params) {
		Query query = em.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		return query.getResultList();
	}

	public Long getSqlQuerySize(String sql, Object... params) {
		Query query = em.createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		if(query.getResultList().size()==0 ){
            return 0L;
        }else{
            return  Long.parseLong(query.getSingleResult()==null?"0":query.getSingleResult().toString());
        }
	}

	public List getSqlList(String sql, Object... params) {
		Query query = em.createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		return query.getResultList();
	}
	public Object getSqlSingle(String sql, Object... params){
		Query query = em.createNativeQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		return query.getSingleResult();
	}
	public void deleteBySql(String sql) {
		Query query = em.createNativeQuery(sql);
		LOG.info("Delete sql ---> " + sql);
		query.executeUpdate();
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	
	 
}
