<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제</title>
</head>
<body>

	<c:choose>
		<c:when test="${message eq 'wrong'}">
			비밀번호가 틀렸습니다. <br>
			<input type="button" value="뒤로가기" onclick="history.back(-1);">
		</c:when>
		
		<c:when test="${message eq 'right'}">
			삭제하시겠습니까? <br>
			<form action="<c:url value='/member/delete' />" method="post">
				<input type="submit" value="예">
				<input type="button" value="아니오" onclick="history.back(-1);">
			</form>
		</c:when>
	</c:choose>

</body>
</html>