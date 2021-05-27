<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String no2 = request.getParameter("no");
Long no = Long.parseLong(no2);
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
					<form method="post"
						action="<%=request.getContextPath()%>/guestbook">
						<input type="hidden" name="a" value="delete"> <input
							type='hidden' name="no" value=<%=no%>>
						<!-- hidden으로 숨기고 벨류값을 받아와야 함 -->
						<table>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="password"></td>
								<td><input type="submit" value="확인"></td>
							</tr>
						</table>
					</form>
					<br /> <a href="<%=request.getContextPath()%>/guestbook">메인으로
						돌아가기</a>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>