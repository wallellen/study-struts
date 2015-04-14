<%@ tag import="java.net.URLDecoder"%>
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="value" required="true" type="java.lang.String" rtexprvalue="true"%>
<%@ attribute name="charset" required="false" type="java.lang.String" rtexprvalue="false"%>

<%
	String value = (String) jspContext.getAttribute("value");
	String charset = (String) jspContext.getAttribute("charset");
	if (charset == null || charset.isEmpty()) {
		charset = "UTF-8";
	}
	value = URLDecoder.decode(value, charset);
%>
<%=value %>