package com.huayun.ssh.service;

import java.util.List;

import com.huayun.ssh.beans.User;

public interface UserService {
	public void add(User t) throws Exception;
	
	public User find(Long id) throws Exception;
	
	public void modify(User t) throws Exception;
	
	
	public void delete(Long id) throws Exception;
	
	public List<User> listAll() throws Exception;
	

}
