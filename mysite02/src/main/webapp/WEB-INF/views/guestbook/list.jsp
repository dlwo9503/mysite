<%@page import="com.douzone.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="com.douzone.mysite.repository.GuestbookRepository"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("list");
Date date = new Date();
SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
String regDate = transFormat.format(date);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/assets/css/main.css" rel="stylesheet" type="text/css">
<title>방명록</title>
</head>
<body>

	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<form action="<%=request.getContextPath()%>/guestbook"
						method="post">
						<input type="hidden" name="a" value="add">
						<table border=1 width=500>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name"></td>
								<td>비밀번호</td>
								<td><input type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 등록 "></td>
							</tr>
						</table>
					</form>
					<br>

					<%
					int count = list.size();
					int index = 0;
					for (GuestbookVo vo : list) {
					%>
					<table width=510 border=1>
						<tr>
							<td>[<%=count - index++%>]
							</td>
							<td><%=vo.getName()%></td>
							<td><%=vo.getRegDate()%></td>
							<td><a
								href="<%=request.getContextPath()%>/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
						</tr>
						<tr>
							<td colspan=4><%=vo.getMessage().replaceAll("\n", "<br/>")%></td>
						</tr>
					</table>
					<%
					}
					%>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>

</body>
</html>

<!-- 항상 마지막은 리다이렉트 -->