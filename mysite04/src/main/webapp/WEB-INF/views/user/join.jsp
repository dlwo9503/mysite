<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">

<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script> <!-- js 소스를 땡겨와서 이부분에서 사용할거임 -->
<script>
$(function(){ // $가 붙으면 jQuery라고 생각하면 됨
	btn = $('#btn-check'); // #을 붙여주면 id를 찾음
	btn.click(function(){ // btn을 click하면 함수를 실행해라
		var email = $("#email").val();
		if(email == ""){
			return;
		}
		$.ajax({
			url: "${pageContext.request.contextPath}/user/api/checkemail?email=" + email,
			type: "get",
			dateType: "json",
			error: function(xhr,status, e){
				console.error(status, e);
			},
			success: function(response){
				console.log(response);
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				
				if(response.data){
					alert("이미 존재하는 이메일 입니다. 다른 이메일을 사용하세요");
					$("#email").val(""); // 해당 id를 찾아 지우고
					$("#email").focus(); // 해당 id를 찾아 포커싱
					return;
				}
				
				$("#btn-check").hide(); // 숨기기
				$("#img-check").show(); // 보이기
			}
		});
	});
});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<%-- form 태그 사용, end 태그에도 같이 달아줘야 함 --%>
				<form:form 
					modelAttribute="userVo" 
				 	id="join-form" 
				 	name="joinform" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label> 
					
					<form:input path="name" /> <!-- 밑에 줄을 자동으로 만들어줌 -->
					<!-- <input id="name" name="name" type="text" value="${userVo.name }"> -->
					<p style="color:#f00; text-align:left; padding-left:0px">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('name') }">
								<spring:message 
	     							code="${errors.getFieldError( 'name' ).codes[0] }"
	     							text="${errors.getFieldError( 'name' ).defaultMessage }" />
							</c:if>
						</spring:hasBindErrors>
					</p>
					 
					<label class="block-label" for="email">이메일</label>
					<form:input path="email"/>
					<!-- <input id="email" name="email" type="text" value=''> --> 
					<input id="btn-check" type="button" value="중복체크">
					<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="width:18px; vertical-align:bottom; display:none"/> 
					<p style="color:#f00; text-align:left; padding-left:0px">
						<form:errors path="email" />
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password"/>
					<p style="color:#f00; text-align:left; padding-left:0px">
						<form:errors path="password" />
					</p>

					<label class="block-label">성</label>
					여<input type="radio" name="gender" value="female" checked="checked"> 
					남<input type="radio" name="gender" value="male">

					<label class="block-label">약관동의</label>
					<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
					<label>서비스 약관에 동의합니다.</label>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>