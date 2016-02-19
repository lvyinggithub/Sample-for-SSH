package com.huayun.ssh.action;




import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.huayun.ssh.dao.UserDao;
import com.huayun.ssh.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

    private static final long serialVersionUID = -3012692002920025182L;


    public static final String USER = "user";
    public static final String ROLE_ID = "roleId";


    protected HttpServletRequest request = null;
    protected HttpServletResponse response = null;

    protected String currentPage = "1";
    protected String totalPage = "0";
    protected int pageSize = 10;
    protected String totalCount = "0";

    protected String message;
    protected String asynJsonResult;


    @Resource
    protected UserService		userService;
    


    
    protected HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }

    protected static HttpServletResponse getResponse(){
        return ServletActionContext.getResponse();
    }

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		 this.request = request;
		
	}


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response =response;
		
	}
	
    protected ActionContext getContext(){
        return ServletActionContext.getContext();
    }

    
    protected HttpSession getSession(){
        return request.getSession();
    }

    
    

   
}
