<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/loginUser" method="post">
    <input type="text" name="phone"/><br/>
    <input type="password" name="password"/><br/>
    <input type="submit" value="登录"/>
</form>

</body>
</html>
