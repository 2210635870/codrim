<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	request.setAttribute("vEnter", "\n");
	
	TbAdmin user = (TbAdmin) session.getAttribute("user");
	String account = "";
	int type = 0;
	if (user == null) {
		 response.getWriter().write("<script>window.parent.location.href='Http://os.codrim.net';</script>");
	} else {
		account = user.getAccount();
		type = user.getType();
	}
%>
