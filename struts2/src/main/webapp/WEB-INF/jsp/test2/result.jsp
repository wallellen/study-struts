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
<title>测试2</title>
<style type="text/css">
	.result{font-size:18px;font-weight:bold;}
</style>
</head>
<body>
<div id="wrap">
	<div class="nav"><a href=".">主页</a>&gt;<a href="test2/input.action">测试2</a>&gt;结果</div>
	<div class="result">
		<div>名称：${name }</div>
		<div>　值：${value }</div>
	</div>
</div>
</body>
</html>