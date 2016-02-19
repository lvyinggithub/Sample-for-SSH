<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>	
	 <tr>
          <td><input type="checkbox" id="checkboxAll"/></td>
          <th>ID</th>
          <th>UserName</th>
          <th>Operate</th>
        </tr>
        <s:iterator value="userList" var="user">
        <tr>
          <td><input type="checkbox" name="poolIds" value="${pool.id}"></td>
          <td>${user.userId}</td>
          <td>${user.userName}</td>
 		  <td>
 		  	<a href="">modify</a>|
 		  	<a href="deleteUser.action?UserId=${user.userId}">delete</a>
 		  </td>
        <tr>
        </s:iterator>
	</table>
	
</body>
</html>