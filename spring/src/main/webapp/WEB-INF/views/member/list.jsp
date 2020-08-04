<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>

	<h1>회원 리스트</h1>
	
		<form action="<c:url value='/member/view' />">
			아이디 : <input type="text" name="userId">
			<input type="submit" value="검색">
		</form>

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
				<td><a href="${mem.userId}">${mem.userId}</a></td>
				<td>${mem.name}</td>
				<td>${mem.email}</td>
				<td>${mem.address}</td>
				<c:set var="len" value="${fn:length(mem.auth)}"/>
				<td>${fn:substring(mem.auth, 5, len)}</td>
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan=5>
					<h6>
							[<a href="list?page=1">처음</a>]
						<c:if test="${pageManager.nowBlock gt 1}">
							[<a href="list?page=${pageManager.startPage-1}">이전</a>]
						</c:if>
						<c:forEach var="i" begin="${pageManager.startPage}" end="${pageManager.endPage}">
							[<a href="list?page=${i}">${i}</a>]
						</c:forEach>
						<c:if test="${pageManager.nowBlock < pageManager.totalBlock}">
							[<a href="list?page=${pageManager.endPage+1}">다음</a>]
						</c:if>
					</h6>
				</td>
			</tr>
			
		</table>
	
</body>
</html>