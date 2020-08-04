<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 ${message eq 'insert' ? '가입' : '정보 수정'}</title>
</head>
<body>

	<h2>회원 ${message eq 'insert' ? '가입' : '정보 수정'}</h2>
	
	<form action="/myapp/member/${message eq 'insert' ? 'insert' : 'update'}" method="post">
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userId" value="${member.userId}" ${empty member ? "" : "readonly"}></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${member.name}"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" value="${member.password}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${member.email}"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" value="${member.address}"></td>
			</tr>
		</table>
		
		<input type="submit" value="${message eq 'insert' ? '입력' : '수정'}">
		<input type="reset" value="취소">
		
	</form>
	
</body>
</html>