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
<title>测试1</title>
<style type="text/css">
	#result{font-size:22px;font-weight:bold;text-align:center;margin:20px}
</style>
</head>
<body>
<div id="wrap">
	<div class="nav"><a href=".">主页</a>&gt;测试1</div>
	<div id="result">
		<div>测试Action控制器，跳转正常</div>
		<div style="font-size:smaller;"><fmt:formatDate value="${now }" pattern="yyyy年MM月dd日 HH时mm分"/></div>
	</div>
</div>
</body>
</html>