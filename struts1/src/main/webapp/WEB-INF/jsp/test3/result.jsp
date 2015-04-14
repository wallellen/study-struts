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
	.result{font-size:18px;font-weight:bold;}
</style>
</head>
<body>
<div id="wrap">
	<!-- func参数，指定要调用的控制器方法 -->
	<div class="nav"><a href=".">主页</a>&gt;<a href="test3/action.do?func=input">测试3</a>&gt;结果</div>
	<div class="result">
		<div>名称：${valueForm.name }</div>
		<div>　值：${valueForm.value }</div>
	</div>
	<%
		session.removeAttribute("valueForm");
	%>
</div>
</body>
</html>