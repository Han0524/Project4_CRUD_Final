<%--
  Created by IntelliJ IDEA.
  User: hansanghwa
  Date: 2023/11/20
  Time: 12:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.example.dao.BoardDAO, com.example.bean.BoardVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%
    BoardDAO boardDAO = new BoardDAO();
    String id=request.getParameter("id");
    BoardVO u=boardDAO.getBoard(Integer.parseInt(id));
    request.setAttribute("vo", u);
%>
    <h1>회원 정보 보기</h1>
    <table id="edit">
        <tr>
            <td>User Id</td><td>${vo.getUserid()}</td>
        </tr>
        <tr>
            <td>User name</td><td>${vo.getUname()}</td>
        </tr>
        <tr>
            <td>User password</td><td>${vo.getPassword()}</td>
        </tr>
        <tr>
            <td>Email</td><td>${vo.getEmail()}</td>
        </tr>
        <tr>
            <td>Phone number</td><td>${vo.getPhone_num()}</td>
        </tr>
        <tr>
            <td>Photo</td><td><c:if test="${vo.getPhoto() ne ''}"><br />
            <img src="${pageContext.request.contextPath }/upload/${vo.getPhoto()}" class="photo"></c:if> </td>
        </tr>
    </table>
    <button type="button" onclick="history.back()">뒤로 가기</button>
    <button type="button" onclick="location.href='editform.jsp?id=${vo.getSid()}'">수정하기</button>
</body>
</html>
