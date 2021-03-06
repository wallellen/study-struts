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
<title>测试5</title>
<style type="text/css">
	.result{font-size:18px;font-weight:bold;}
</style>
</head>
<body>
<div id="wrap">
	<!-- func参数，指定要调用的控制器方法 -->
	<div class="nav"><a href=".">主页</a>&gt;<a href="test5/input.action">测试5</a>&gt;结果</div>
	<div class="result">
		<div>　　账号：${account }</div>
		<div>　　密码：${password }</div>
		<div>　　姓名：${name }</div>
		<div>　　性别：
			<c:choose>
				<c:when test="${gender eq 'M' }">男</c:when>
				<c:otherwise>女</c:otherwise>
			</c:choose>
		</div>
		<div>　　年龄：${age }</div>
		<div>　　电话：
			<c:choose>
				<c:when test="${empty telephone}">无</c:when>
				<c:otherwise>${telephone }</c:otherwise>
			</c:choose>
		</div>
		<div>电子邮件：
			<c:choose>
				<c:when test="${empty email or email eq '@'}">无</c:when>
				<c:otherwise>${email }</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
</body>
</html>