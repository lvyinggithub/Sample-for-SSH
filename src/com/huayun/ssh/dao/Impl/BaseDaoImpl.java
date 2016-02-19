package com.huayun.ssh.dao.Impl;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import com.huayun.ssh.dao.BaseDao;



@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private Class<T> clazz;
	
	@Resource
	protected HibernateTemplate hibernateTemplate;
	
	public BaseDaoImpl() {
		// 使用反射技术得到T的真实类型
		
		// 获取当前new的对象的泛型的父类类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); 
		// 获取第一个类型参数的真实类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; 
		
//		System.out.println(this.getClass().getName());
//		System.out.println(this.getClass().getSimpleName());
//		System.out.println("clazz ---> " + clazz);
	}

	public void add(T t) throws Exception{
		try {
			Date dateTime = new Date();
			Field field1 = t.getClass().getDeclaredField("createTime");
			field1.setAccessible(true);
			field1.set(t, dateTime);
			Field field2 = t.getClass().getDeclaredField("updateTime");
			field2.setAccessible(true);
			field2.set(t, dateTime);
		} catch (NoSuchFieldException e) {
		   //没有time字段
		}
		
		hibernateTemplate.save(t);
	}

	public T find(Long id) throws Exception{
		return hibernateTemplate.get(clazz, id);
	}

	public void modify(T t) throws Exception{
		try {
			Field field = t.getClass().getDeclaredField("updateTime");
			field.setAccessible(true);
			field.set(t, new Date());
		} catch (NoSuchFieldException e) {
		   //没有time字段
		}
		
		hibernateTemplate.update(t);
	}

	public void merge(T t) throws Exception{
		hibernateTemplate.merge(t);
	}

	public void delete(Long id) throws Exception{
		T t = hibernateTemplate.get(clazz, id);
		hibernateTemplate.delete(t);
	}

	public void delete(Long[] ids) throws Exception{
		for(Long id : ids){
			T t = hibernateTemplate.get(clazz, id);
			hibernateTemplate.delete(t);
		}
	}

	public List<T> listAll() throws Exception{
		return hibernateTemplate.find("FROM " + clazz.getSimpleName());
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List queryObjects(String hqlString) throws Exception {
		List list = getHibernateTemplate().find(hqlString);
		if (list.size() == 0) {
			return null;
		}
		return getHibernateTemplate().find(hqlString);
	}

	
	/**
	 * 根据条件查询数据库表中记录总数
	 * 
	 * @param hqlString
	 * @return
	 * @throws Exception
	 */
	public Long queryObjectsCount(String hqlString) throws Exception {
		return ((Long) getHibernateTemplate().find(
				"select count(*) " + hqlString).get(0)).longValue();
	}
	
	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery. 如果有“fetch”，则去掉
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos).replace("fetch", "");
	}
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	/**
	 * 根据hql语句查询对象信息(用分页)
	 * 
	 * @param hql
	 * @param first
	 * @param fetch
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getObjectByHql(String hql, Integer first, Integer fetch)
			throws Exception {
		final String finalhql = hql;
		final Integer finalfirst = first;
		final Integer finalfetch = fetch;
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalhql);
				query.setFirstResult(finalfirst);
				query.setMaxResults(finalfetch);
				List list = query.list();
				return list;
			}
		});
	}

	
	
}
