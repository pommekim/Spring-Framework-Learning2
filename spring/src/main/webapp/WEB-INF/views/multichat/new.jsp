<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>채팅방 생성</h2>
	<form action="newroom" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		채팅방 이름 : <input type="text" name="roomName"><br>
		<button type="submit">생성</button>
		<button type="button" onclick="location.href='/myapp/multichat'">뒤로</button>
	</form>

</body>
</html>