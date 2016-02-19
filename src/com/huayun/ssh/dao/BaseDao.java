package com.huayun.ssh.dao;


import java.util.List;


public interface BaseDao<T> {
	
	public void add(T t) throws Exception;
	
	public T find(Long id) throws Exception;
	
	public void modify(T t) throws Exception;
	
	
	public void delete(Long id) throws Exception;
	
	public List<T> listAll() throws Exception;
	
	public List queryObjects(String hqlString) throws Exception;
	
}
