<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/ex" prefix="ex"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<ex:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common/css/main.css" />
<title>测试3</title>
<style type="text/css">
	form div{margin:5px}
	.error{margin-top:20px}
</style>
</head>
<body>
<div id="wrap">
	<div class="nav"><a href=".">主页</a>&gt;测试3</div>
	<form action="test3/action.do" method="post">
		<input type="hidden" name="func" value="submit" />	<%--指定要调用的控制器方法 --%>
		<div>名称：<input type="text" name="name" value="${valueForm.name }" /></div>
		<div>　值：<input type="text" name="value" value="${valueForm.value }" /></div>
		<div><input type="submit" value="提交" /></div>
	</form>
	<c:if test="${not empty errors }">
		<ul class="error">
			<c:forEach items="${errors }" var="err">
				<li>${err.value }</li>
			</c:forEach>			
		</ul>
	</c:if>
</div>
</body>
</html>