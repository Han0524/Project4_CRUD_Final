<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.example.dao.BoardDAO, com.example.bean.BoardVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Form</title>
</head>
<body>

<%
	BoardDAO boardDAO = new BoardDAO();
	String id=request.getParameter("id");	
	BoardVO u=boardDAO.getBoard(Integer.parseInt(id));
	request.setAttribute("vo", u);
%>

<h1>Edit Form</h1>
<form action="editpost.jsp" method="post" enctype="multipart/form-data">
<input type="hidden" name="seq" value="<%=u.getSid() %>"/>
<table>
<tr><td>UserId:</td><td><input type="text" name="userid" value="<%= u.getUserid()%>"/></td></tr>
<tr><td>UserName:</td><td><input type="text" name="uname" value="<%= u.getUname()%>" /></td></tr>
	<tr><td>Password:</td><td><input type="text" name="password" value="<%= u.getPassword()%>"/></td></tr>
	<tr><td>Email:</td><td><input type="text" name="email" value="<%= u.getEmail()%>"/></td></tr>
	<tr><td>Phone Number:</td><td><input type="text" name="phone_num" value="<%= u.getPhone_num()%>"/></td></tr>
	<tr><td>Photo:</td><td><input type="file" name="photo" value="${u.getPhoto()}"></td></tr>
<tr><td colspan="2"><input type="submit" value="upload"/>
<input type="button" value="Cancel" onclick="history.back()"/></td></tr>
</table>
</form>

</body>
</html>