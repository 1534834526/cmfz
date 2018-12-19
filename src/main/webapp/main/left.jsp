<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function () {

    });
</script>


<!-- 分类基础标签div-->
<c:forEach items="${list}" var="p">
    <div id="accordion" class="easyui-accordion" datagrid="title:''">
        <c:forEach items="${p.list}" var="p2">
            <div title="">
                <a href="${pageContext.request.contextPath}/">${p2.}</a>
            </div>
        </c:forEach>
    </div>
</c:forEach>
	
