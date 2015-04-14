<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="href" required="false" type="java.lang.String" rtexprvalue="true"%>

<%	String href = (String) jspContext.getAttribute("href");
	if (href == null) {	%>
	<base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/" />
<%	} else { %>
	<base href="<%=href %>" />
<%	}%>