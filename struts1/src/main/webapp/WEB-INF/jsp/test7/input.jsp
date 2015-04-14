<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
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
<title>测试7</title>
<style type="text/css">
	form div{margin:5px}
	.error{margin-top:20px}
	.btn{padding:3px 5px}
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
	<div class="nav"><a href=".">主页</a>&gt;测试7</div>
	<form action="test7/submit.do" method="post">
		<div>　　账号：
			<input type="text" name="account" value="${userForm3.map.account }" />
			<span class="error"><html:errors property="account" /></span>
		</div>
		<div>　　密码：
			<input type="password" name="password" />
			<span class="error"><html:errors property="password" /></span>
		</div>
		<div>确认密码：
			<input type="password" name="passwordAck" />
			<span class="error"><html:errors property="passwordAck" /></span>
		</div>
		<div>　　姓名：
			<input type="text" name="name" value="${userForm3.map.name }" />
			<span class="error"><html:errors property="name" /></span>
		</div>
		<div>　　姓别：
			<c:choose>
				<c:when test="${userForm3.map.gender eq 'M' }">
					<input type="radio" name="gender" value="M" checked="checked" />男
					<input type="radio" name="gender" value="F" />女
				</c:when>
				<c:otherwise>
					<input type="radio" name="gender" value="M" />男
					<input type="radio" name="gender" value="F" checked="checked" />女
				</c:otherwise>
			</c:choose>
			<span class="error"><html:errors property="gender" /></span>
		</div>
		<fmt:formatDate value="${userForm3.map.birthday }" pattern="yyyy-MM-dd" var="birthday" />
		<div>　　生日：&nbsp;<input type="text" id="birthday" name="birthday" value="${birthday }" />
			<span class="error"><html:errors property="birthday" /></span>
		</div>
		<div>电话号码：
			<input type="text" name="telephone" value="${userForm3.map.telephone }" />
			<span class="error"><html:errors property="telephone" /></span>
		</div>
		<div>电子邮件：
			<input type="text" name="email" value="${userForm3.map.email }" />
			<span class="error"><html:errors property="email" /></span>
		</div>
		<div>
			<input type="submit" value="提交" />
		</div>
	</form>
</div>
</body>
</html>