package com.huayun.ssh.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huayun.ssh.beans.User;
import com.huayun.ssh.dao.UserDao;
import com.huayun.ssh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	protected UserDao                        userDao;
	

	@Override
	public void add(User t) throws Exception {
		
		userDao.add(t);
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public User find(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listAll() throws Exception {
		// TODO Auto-generated method stub
		return userDao.listAll();
	}

	@Override
	public void modify(User t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

	
}
