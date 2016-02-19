package com.huayun.ssh.beans;



public class User implements java.io.Serializable{

    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 5007578993739381395L;
    
  
    private Long userId;
    
    private String userName;

   private String password; 
   
   private Integer gender;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getGender() {
		return gender;
	}
	
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	   
	   
	}