package com.huayun.ssh.action;




import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huayun.ssh.beans.User;
import com.huayun.ssh.service.UserService;


public class UserAction extends BaseAction{

    /**
     * 
     */
    private static final long serialVersionUID = -3294078339393590050L;
    private List<User> userList;
    private User user;
    private Long userId;
    private String name;
    private String UserPwd;
    


 
    public String listUsersPage(){

        try{
        	userList = userService.listAll();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return SUCCESS;
    }


    public String deleteUser(){
    	 try{
         	userService.delete(userId);
         }
         catch(Exception e){
             e.printStackTrace();
         }
    	return SUCCESS;
    }

	public List<User> getUserList() {
		return userList;
	}



	public void setUserList(List<User> userList) {
		this.userList = userList;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUserPwd() {
		return UserPwd;
	}



	public void setUserPwd(String userPwd) {
		UserPwd = userPwd;
	}

   
    


}
