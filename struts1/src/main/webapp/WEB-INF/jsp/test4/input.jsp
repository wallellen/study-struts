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
<link rel="stylesheet" type="text/css" href="common/css/main.css" />
<title>测试4</title>
<style type="text/css">
	form div{margin:5px}
	.error{margin-top:20px}
	.btn{padding:3px 5px}
</style>
</head>
<body>
<div id="wrap">
	<div class="nav"><a href=".">主页</a>&gt;测试4</div>
	<form action="test4/action.do" method="post">
		<input type="hidden" name="func" value="submit" />	<%--指定要调用的控制器方法 --%>
		<div>数值1：<input type="text" name="number1" value="${numberForm.number1 }" /></div>
		<div>
			<html:submit styleClass="btn" property="method"><bean:message key="submit.addtion" /></html:submit>
			<%--相当于 <input type="submit" name="method" value="加" class="btn" /> --%>
			<html:submit styleClass="btn" property="method"><bean:message key="submit.subtraction" /></html:submit>
			<html:submit styleClass="btn" property="method"><bean:message key="submit.multiplication" /></html:submit>
			<html:submit styleClass="btn" property="method"><bean:message key="submit.division" /></html:submit>
		</div>
		<div>数值2：<input type="text" name="number2" value="${numberForm.number2 }" /></div>
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