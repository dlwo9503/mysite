<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			url: "/mysite03/user/api/checkemail?email=" + email,
			type: "get",
			dateType: "json",
			error: function(xhr,status, e){
				console.log(e);
			},
			success: function(response){
				if(response.result != "success"){
					console.log("error");
					return;
				}
				
				if(response.exist){
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
				<form id="join-form" name="joinform" method="post"
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=""> <label
						class="block-label" for="email">이메일</label>
					<!-- <input id="email" name="email" type="text" value='${empty email } ? "" : ${email }'> -->
					<input id="email" name="email" type="text" value=''> 
					<input id="btn-check" type="button" value="중복체크">
					<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="width:18px; vertical-align:bottom; display:none"/> 
						<label class="block-label">패스워드</label> <input name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>