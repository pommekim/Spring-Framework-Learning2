<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
</head>
<body>

	<h2>회원 상세 정보</h2>
	
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${member.userId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.name}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.email}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${member.address}</td>
		</tr>
	</table>
	
	<a href="<c:url value='/member/update?userId=${member.userId}'/>">회원정보수정</a> <br>
	
	<form action="<c:url value='/member/delete' />">
		비밀번호 입력: <input type="password" name="password">
		<input type="hidden" value="${member.userId}">
		<input type="submit" value="회원탈퇴">
	</form>
	

</body>
</html>