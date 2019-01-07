<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<head></head>
<body>
<shiro:authenticated>
    您好：<shiro:principal></shiro:principal>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/logout">退出</a>
    <ul>
        <li>轮播图</li>
        <li>专辑</li>
        <li>章节</li>
        <li>用户</li>
        <shiro:hasPermission name="user:add">
            增
        </shiro:hasPermission>
        <shiro:hasPermission name="user:delete">
            删
        </shiro:hasPermission>
        <shiro:hasPermission name="user:update">
            改
        </shiro:hasPermission>
        <shiro:hasPermission name="user:query">
            查
        </shiro:hasPermission>
        <shiro:hasRole name="super">
            <li>管理员</li>
        </shiro:hasRole>
    </ul>
</shiro:authenticated>
<shiro:notAuthenticated>
    <a href="${pageContext.request.contextPath}/main/login1.jsp">您当前未登录,登陆之后可以浏览更多精彩内容</a>
</shiro:notAuthenticated>
</body>