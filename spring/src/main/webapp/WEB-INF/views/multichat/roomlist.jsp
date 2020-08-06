<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>방 이름</th>
					<th>입장버튼</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="room" items="${rooms}">
				<tr>
					<td>${room.roomId}</td>
					<td>${room.roomName}</td>
					<td>
						<a href="/myapp/multichat/room/${room.roomId}">
							<button id="b${room.roomId}">입장</button>
						</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<br>
		<a href="/myapp/multichat/new">
			<button id="new">채팅방 개설</button>
		</a>
	</div>

</body>
</html>