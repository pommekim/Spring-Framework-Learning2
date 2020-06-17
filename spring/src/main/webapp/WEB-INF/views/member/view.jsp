<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<input type="submit" value="회원정보수정"> <br>
	
	<form action="" method="post">
		비밀번호 입력: <input type="password" name="pw">
		<input type="submit" value="회원탈퇴">
	</form>
	

</body>
</html>