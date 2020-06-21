<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>

	<h1>회원 리스트</h1>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>주소</th>
				<th>권한</th>
			<tr>
			
			<c:forEach var="mem" items="${memList}">
			<tr>
				<td>${mem.userId}</td>
				<td>${mem.name}</td>
				<td>${mem.email}</td>
				<td>${mem.address}</td>
				<td>${mem.authorities}</td>
			</tr>
			</c:forEach>
		</table>
		
	<form action="<c:url value='/member/view' />">
		아이디 : <input type="text" name="userId">
		<input type="submit" value="검색">
	</form>
	
</body>
</html>