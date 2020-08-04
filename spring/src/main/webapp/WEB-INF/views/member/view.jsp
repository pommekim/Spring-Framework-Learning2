<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
		<tr>
			<th>권한</th>
			<td>${member.authorities}</td>
		</tr>
		</sec:authorize>
	</table>
	
	
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
	<form action=enabled method=post>
		<sec:csrfInput/>
		<input name="userId" type=hidden value=${member.userId}>
		<c:choose>
			<c:when test="${member.enabled == true}">
				<input type=submit value="비활성화">
			</c:when>
			<c:when test="${member.enabled == false}">
				<input type=submit value="활성화">
			</c:when>
		</c:choose>
	</form>
	</sec:authorize>
	
	
	<sec:authorize access="hasRole('ROLE_MASTER')">
	<form action=auth method="post">
		<sec:csrfInput/>
		<input name="userId" type=hidden value=${member.userId}>
			<select name="auth">
				<option value="ROLE_USER">USER</option>
				<option value="ROLE_ADMIN">ADMIN</option>
				<option value="ROLE_MASTER">MASTER</option>
			</select>
		<input type="submit" value="권한변경">
	</form>
	</sec:authorize>
	
	
	<a href="<c:url value='/member/update/${member.userId}' />">회원정보수정</a> <br>
	
	
	<form action="<c:url value='/member/delete' />">
		<sec:csrfInput/>
		비밀번호 입력: <input type="password" name="password">
		<input type="hidden" value="${member.userId}">
		<input type="submit" value="회원탈퇴">
	</form>
	

</body>
</html>