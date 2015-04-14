<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/ex" prefix="ex"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<ex:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common/jscal2/css/jscal2.css" />
<link rel="stylesheet" type="text/css" href="common/jscal2/css/border-radius.css" /> 
<link rel="stylesheet" type="text/css" href="common/css/main.css" />
<script type="text/javascript" src="common/jscal2/js/jscal2.js"></script> 
<script type="text/javascript" src="common/jscal2/js/lang/cn.js"></script>
<title>测试8</title>
<style type="text/css">
	form div{margin:5px}
	.error{margin-top:20px}
</style>
<script type="text/javascript">
	function onready() {
		Calendar.setup({ 
			trigger:"birthday", 
			inputField:"birthday", 
			onSelect:function() {
				this.hide();
			} 
		}); 
	}
</script>
</head>
<body onload="onready()">
<div id="wrap">
	<div class="nav"><a href=".">主页</a>&gt;测试8</div>
	<form action="test8/submit.action" method="post">
		<div>　　账号：
			<input type="text" name="user.account" value="${user.account }" />
			<span class="error">${fieldErrors['user.account'][0] }</span>
		</div>
		<div>　　密码：
			<input type="password" name="user.password" />
			<span class="error">${fieldErrors['user.password'][0] }</span>
		</div>
		<div>确认密码：
			<input type="password" name="user.passwordAck" />
			<span class="error">${fieldErrors['user.passwordAck'][0] }</span>
		</div>
		<div>　　姓名：
			<input type="text" name="user.name" value="${user.name }" />
			<span class="error">${fieldErrors['user.name'][0] }</span>
		</div>
		<div>　　姓别：
			<c:choose>
				<c:when test="${user.gender eq 'M' }">
					<input type="radio" name="user.gender" value="M" checked="checked" />男
					<input type="radio" name="user.gender" value="F" />女
				</c:when>
				<c:otherwise>
					<input type="radio" name="user.gender" value="M" />男
					<input type="radio" name="user.gender" value="F" checked="checked" />女
				</c:otherwise>
			</c:choose>
			<span class="error">${fieldErrors['user.gender'][0] }</span>
		</div>
		<fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd" var="useBirthday" />
		<div>　　生日：&nbsp;<input type="text" id="birthday" name="user.birthday" value="${useBirthday }" />
			<span class="error">${fieldErrors['user.birthday'][0] }</span>
		</div>
		<div>电话号码：
			<input type="text" name="user.telephone" value="${user.telephone }" />
			<span class="error">${fieldErrors['user.telephone'][0] }</span>
		</div>
		<div>电子邮件：
			<input type="text" name="user.email" value="${user.email }" />
			<span class="error">${fieldErrors['user.email'][0] }</span>
		</div>
		<div>
			<input type="submit" value="提交" />
		</div>
	</form>
</div>
</body>
</html>