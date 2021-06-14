<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%-- <%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("newline", "\n");
/* pageContext.setAttribute("left", "<"); */
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css"
	rel="stylesheet" type="text/css">
<title>방명록</title>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<form:form
						action="${pageContext.request.contextPath }/guestbook/add"
						method="post">
						<table border=1 width=500>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name"></td>
								<td>비밀번호</td>
								<td><input type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="message" cols=60 rows=5></textarea>
								</td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 등록 "></td>
							</tr>
						</table>
						<spring:hasBindErrors name="guestbookVo">
							<c:if test="${errors.hasFieldErrors('name') }">
								<spring:message
									code="${errors.getFieldError( 'name' ).codes[0] }"
									text="${errors.getFieldError( 'name' ).defaultMessage }" />
							</c:if>
						</spring:hasBindErrors>
						<spring:hasBindErrors name="guestbookVo">
							<c:if test="${errors.hasFieldErrors('password') }">
								<spring:message
									code="${errors.getFieldError( 'password' ).codes[0] }"
									text="${errors.getFieldError( 'password' ).defaultMessage }" />
							</c:if>
						</spring:hasBindErrors>
						<spring:hasBindErrors name="guestbookVo">
							<c:if test="${errors.hasFieldErrors('message') }">
								<spring:message
									code="${errors.getFieldError( 'message' ).codes[0] }"
									text="${errors.getFieldError( 'message' ).defaultMessage }" />
							</c:if>
						</spring:hasBindErrors>
					</form:form>
					<br> <br>

					<c:set var="index" value="0" />
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<table width=510 border=1>
							<tr>
								<td>[${count - status.index }]</td>
								<!--  -->
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<td><a
									href="${pageContext.request.contextPath }/guestbook/deleteform/${vo.no }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>${fn:replace(vo.message, newline, "<br/>") }</td>
								<%-- 								<td colspan=4>${fn:replace(fn:replace(vo.message, newline, "<br/>"),left,"&lt;") }</td> --%>
							</tr>
						</table>
						<br>
					</c:forEach>
					<table border="1" cellspacing="0" cellpadding="5">
						<c:forEach begin="1" end="${row }" step="1" var="r">
							<!-- 0 ~ 9 까지 var값 step만큼 증가시키면서 반복 -->
							<tr>
								<c:forEach begin="1" end="${col }" step="1" var="c">
									<td>cell(${r }, ${c })</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>

<!-- 항상 마지막은 리다이렉트 -->