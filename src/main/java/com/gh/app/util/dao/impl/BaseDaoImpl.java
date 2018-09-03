package com.gh.app.util.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gh.app.util.dao.BaseDao;
import com.gh.app.util.tool.reflect.GenericsUtils;
import com.gh.app.util.entities.Pager;
import com.gh.app.util.entities.QueryResult;

/**
 * 基础数据库操作类实现
 * 
 * @author AezGenholmes
 * 
 */
@SuppressWarnings("unchecked")
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	private static final Logger LOG = LoggerFactory.getLogger(BaseDaoImpl.class);
	
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());

	@PersistenceContext
	protected EntityManager em;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void save(T o) {
		if (o != null) {
			em.persist(o);
		}
	}
	
	@Override
	public T get(String hql) {
		Query q = em.createQuery(hql);
		List<T> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(T o) {
		if (o != null) {
			em.remove(em.merge(o));
		}
	}

	@Override
	public List<T> find(String hql) {
		Query q = em.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.getResultList();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int start,
			int pagesize) {
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		if(start > 0 && pagesize > 0) {
			return q.setFirstResult((start - 1) * pagesize).setMaxResults(
					pagesize).getResultList();
		}
		return q.getResultList();
		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<T> find(String hql, int start, int pagesize) {
		Query q = em.createQuery(hql);
		if(start > 0 && pagesize > 0) {
			return q.setFirstResult((start - 1) * pagesize).setMaxResults(
					pagesize).getResultList();
		}
		return q.getResultList();
	}

	@Override
	public Long count(String hql) {
		hql = "select count(*) " + hql.substring(hql.indexOf("from"));
		Query q = em.createQuery(hql);
		if(q.getResultList().size()==0 ){
            return 0L;
        }
		return (Long) q.getSingleResult();
	}
	
	public Long getCount(String wherejpql, Object[] queryParams) {
		String entityname = getEntityName(this.entityClass);
		Query query  = this.em.createQuery("select count("
				+ getCountField(this.entityClass)
				+ ") from "
				+ entityname
				+ " o "
				+ (((wherejpql == null) || ("".equals(wherejpql.trim()))) ? ""
						: new StringBuilder("where ").append(wherejpql)
								.toString()));
		setQueryParams(query, queryParams);
		if(query.getResultList().size()==0 ){
            return 0L;
        }
		return (Long) query.getSingleResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		hql = "select count(*) " + hql.substring(hql.indexOf("from"));
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		if(q.getResultList().size()==0 ){
            return 0L;
        }
		return (Long) q.getSingleResult();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int executeHql(String hql) {
		Query q = em.createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = em.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Object[]> findBySql(String sql) {
		Query q = em.createNativeQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<Object[]> findBySql(String sql, int start, int pagesize) {
		Query q = em.createNativeQuery(sql);
		if(start > 0 && pagesize > 0) {
			return q.setFirstResult((start - 1) * pagesize).setMaxResults(
					pagesize).getResultList();
		}
		return q.getResultList();
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		Query q = em.createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.getResultList();
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params,
			int start, int pagesize) {
		Query q = em.createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		if(start > 0 && pagesize > 0) {
			return q.setFirstResult((start - 1) * pagesize).setMaxResults(
					pagesize).getResultList();
		}
		return q.getResultList();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int executeSql(String sql) {
		Query q = em.createNativeQuery(sql);
		return q.executeUpdate();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int executeSql(String sql, Map<String, Object> params) {
		Query q = em.createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Long countBySql(String sql) {
		Query q = em.createNativeQuery(sql);
		if(q.getResultList().size()==0 ){
            return 0L;
        }
		return (Long) q.getSingleResult();
	}

	@Override
	public Long countBySql(String sql, Map<String, Object> params) {
		Query q = em.createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		if(q.getResultList().size()==0 ){
            return 0L;
        }
		return (Long) q.getSingleResult();
	}

	public void clear() {
		em.clear();
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(Serializable... entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(this.entityClass, id));
		}
	}
	
	public T get(Serializable entityId) {
		if (entityId == null)
			throw new RuntimeException(this.entityClass.getName()
					+ ":传入的实体id不能为空");
		return em.find(this.entityClass, entityId);
	}

	public long getCount() {
		return (Long) em.createQuery(
				"select count(" + getCountField(this.entityClass) + ") from "
						+ getEntityName(this.entityClass) + " o")
				.getSingleResult();
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void update(T entity) {
		em.merge(entity);
	}

	public List<T> find(Map<String, String> orderby, int start, int pagesize) {
		return find(null, null, orderby, start, pagesize);
	}

	public List<T> find(String wherejpql,	Object[] queryParams, int start, int pagesize) {
		return find(wherejpql, queryParams, null, start, pagesize);
	}

	public List<T> find(int start, int pagesize) {
		return find(null, null, null,start, pagesize);
	}

	public List<T> find() {
		return find(-1, -1);
	}

	public List<T> find(String wherejpql,	Object[] queryParams, Map<String, String> orderby, int start, int pagesize) {
		String entityname = getEntityName(this.entityClass);
		String jpql = "select o from "
			+ entityname
			+ " o "
			+ (wherejpql == null || "".equals(wherejpql.trim()) ? ""
					: "where " + wherejpql) + buildOrderby(orderby);
		Query query = em.createQuery(jpql);
		if(LOG.isDebugEnabled()) {
			LOG.debug("Jpa query ---> " + jpql);
		}
		setQueryParams(query, queryParams);
		
		if (start != -1 && pagesize != -1) {
			query.setFirstResult((start - 1) * pagesize).setMaxResults(pagesize);
		}
			
		return query.getResultList();
		
	}
	
	public Long totalCount(String wherejpql, Object[] queryParams) {
		String entityname = getEntityName(this.entityClass);
		Query query = em.createQuery("select count("
				+ getCountField(this.entityClass)
				+ ") from "
				+ entityname
				+ " o "
				+ (wherejpql == null || "".equals(wherejpql.trim()) ? ""
						: "where " + wherejpql));
		setQueryParams(query, queryParams);
		
		if(query.getResultList().size()==0 ){
            return 0L;
        }else{
            return  (Long)(query.getSingleResult());
        }
	}

	protected static void setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
				if(LOG.isDebugEnabled()) {
					LOG.debug("parameter [" + i +"] --->  " + queryParams[i]);
				}
			}
		}
	}

	/**
	 * 组装order by语句
	 * 
	 * @param orderby
	 * @return
	 */
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

	/**
	 * 获取实体的名称
	 * 
	 * @param <E>
	 * @param clazz
	 *            实体类
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> clazz) {
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name())) {
			entityname = entity.name();
		}
		return entityname;
	}

	/**
	 * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx
	 * o语句BUG而增加,hibernate对此jpql解析后的sql为select
	 * count(field1,field2,...),显示使用count()统计多个字段是错误的
	 * 
	 * @param <E>
	 * @param clazz
	 * @return
	 */
	protected static <E> String getCountField(Class<E> clazz) {
		String out = "o";
		/*try {
			PropertyDescriptor[] propertyDescriptors = Introspector
					.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor propertydesc : propertyDescriptors) {
				Method method = propertydesc.getReadMethod();
				if (method != null
						&& method.isAnnotationPresent(EmbeddedId.class)) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(
							propertydesc.getPropertyType())
							.getPropertyDescriptors();
					out = "o."
							+ propertydesc.getName()
							+ "."
							+ (!ps[1].getName().equals("class") ? ps[1]
									.getName() : ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}*/
		return out;
	}

	@Override
	public Long count(String hql, Object... params) {
		hql = "select count(*) " + hql.substring(hql.indexOf("from"));
		Query query = em.createQuery(hql);
		setQueryParams(query, params);
		if(query.getResultList().size()==0 ){
            return 0L;
        }else{
            return  (Long)(query.getSingleResult());
        }
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public int executeHql(String hql, Object... params) {
		Query query = em.createQuery(hql);
		setQueryParams(query, params);
		return query.executeUpdate();
	}

	@Override
	public List<T> find(String hql, Object... params) {
		return pagingfind(hql, -1,-1, params);
	}

	@Override
	public List<T> pagingfind(String hql, int start, int pagesize, Object... params) {
		Query query = em.createQuery(hql);
		setQueryParams(query, params);
		if (start != -1 && pagesize != -1) {
			query.setFirstResult((start - 1) * pagesize).setMaxResults(pagesize);
		}
		return query.getResultList();
	}

	@Override
	public T get(String hql, Object... params) {
		Query query = em.createQuery(hql);
		setQueryParams(query, params);
		List<T> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public QueryResult<T> getPagingData(String hql) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(hql));
		qr.setTotal(count(hql));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(String hql, Map<String, Object> params) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(hql, params));
		qr.setTotal(count(hql, params));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(String hql, Object... params) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(hql, params));
		qr.setTotal(count(hql,params));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(String hql, int start, int pagesize) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(hql,start,pagesize));
		qr.setTotal(count(hql));
		qr.setPager(new Pager(start,pagesize));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(String hql, Map<String, Object> params,
			int start, int pagesize) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(hql, params, start, pagesize));
		qr.setTotal(count(hql, params));
		qr.setPager(new Pager(start,pagesize));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(String hql, int start, int pagesize,
			Object... params) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(hql, start, pagesize, params));
		qr.setTotal(count(hql, params));
		qr.setPager(new Pager(start,pagesize));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(String wherejpql, Object[] queryParams,
			Map<String, String> orderby, int start, int pagesize) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(wherejpql, queryParams, orderby, start, pagesize));
		qr.setTotal(getCount(wherejpql, queryParams));
		qr.setPager(new Pager(start,pagesize));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(String wherejpql, Object[] queryParams,
			int start, int pagesize) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(wherejpql, queryParams, start, pagesize));
		qr.setTotal(getCount(wherejpql, queryParams));
		qr.setPager(new Pager(start,pagesize));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(Map<String, String> orderby, int start,
			int pagesize) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(null, null, orderby, start, pagesize));
		qr.setTotal(getCount(null,null));
		qr.setPager(new Pager(start,pagesize));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData(int start, int pagesize) {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find(null, null,null, start, pagesize));
		qr.setTotal(getCount(null,null));
		qr.setPager(new Pager(start,pagesize));
		return qr;
	}

	@Override
	public QueryResult<T> getPagingData() {
		QueryResult<T> qr = new QueryResult<T>();
		qr.setRows(find());
		qr.setTotal(getCount(null,null));
		return qr;
	}

	@Override
	public T load(Serializable id) {
		if (id == null)
			throw new RuntimeException(this.entityClass.getName()
					+ ":传入的实体id不能为空");
		return em.getReference(this.entityClass, id);
	}



	@Override
	public T findByProperty(String propertyName, Object value) {
		String entityname = getEntityName(this.entityClass);
		LOG.debug("finding "+ entityname +" instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String hql = "from " +entityname+"  as model where model."
					+ propertyName + "= ?1";
			return  get(hql, value);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public void detach(T o) {
		em.detach(o);
	}
	
}