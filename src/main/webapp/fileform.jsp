<%--
  Created by IntelliJ IDEA.
  User: hansanghwa
  Date: 2023/11/17
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="fileupload_ok.jsp" method="post" enctype="multipart/form-data">
        대표이미지 선택 : <input type="file" name="photo"/>
        <input type="submit" value="upload"/>
    </form>
</body>
</html>
