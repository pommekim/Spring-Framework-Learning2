<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>

	<h2>회원 정보 입력</h2>
	
	<form action="<c:url value='/member/insert' />" method="post">
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userId" id="userId"></td>
				<td><button id="checkBtn">중복검사</button></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address"></td>
			</tr>
		</table>
		
		<input type="submit" value="입력">
		<input type="reset" value="취소">
	
	</form>


	<div id="loading">
		<img src="resources/images/loading.gif" width="100"/>
	</div>
	
	
	<script>
		var check = false;
		var id = $("#userId").val();
		$(function() {
			$("#loading").hide();
			$("#checkBtn").on("click", function() {
				if($("#userId").val()) {
					$.ajax({
						url : "/myapp/member/check",
						type : "post",
						headers : {"content-type":"application/json"},
						dataType : "text",
						data : {userId:id, "${_csrf.parameterName}":"${_csrf.token}"},
						success : function(result) {
							if(result) {
								alert("아이디가 중복되지 않습니다.");
								$("#checkBtn").remove();
								$("#userId").attr("readonly", true);
								check = !check; //true로 변경시켜 밑에 함수를 실행시키지 못하도록!
							} else {
								alert("아이디가 중복됩니다.");
							}
							return false;
						},
						error : function() {
							alert("다시 시도해 주세요.");
							return false;
						}
					});
				} else {
					alert("아이디를 입력하세요.");
				}
				return false;
			});
		});
		
		
		$(document).ajaxStart(function() {
			$("#loading").show();
		});
		
		$(document).ajaxStop(function() {
			$("#loading").hide();
		});
		
		
		function check() {
			if(!check) {
				alert("아이디 중복 검사를 해주세요.");
				return false;
			}
		}
		
	</script>
	

</body>
</html>