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
<title>测试7</title>
<style type="text/css">
	form div{margin:5px}
	.result{font-size:16px;font-weight:bold;margin-top:20px}
</style>
</head>
<body>
<div id="wrap">
	<div class="nav"><a href=".">主页</a>&gt;测试7</div>
	<form action="test7/submit.action" method="post">
		<input type="hidden" name="raise" />
		<input type="submit" value="不引发异常" onclick="raise.value=false;alert(raise.value);" />
		<input type="submit" value="引发异常" onclick="raise.value=true;" />
	</form>
	<c:if test="${not empty actionMessages }">
		<div class="result">${actionMessages[0] }</div>
	</c:if>
</div>
</body>
</html>