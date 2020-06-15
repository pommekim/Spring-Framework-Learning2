<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Menu</title>
</head>
<body>

	<a href="count">1. 총 사원 인원수</a> <br>
	
	<form action="count?${deptId}">
	2. 부서별 인원수 <br>
	부서 번호 : <input type="text" name="deptId"><input type="submit" value="검색"> <br>
	</form>
	
	<a href="list">3. 전체 사원 목록</a> <br>
	<a href="insert">4. 신규 사원 정보 입력</a> <br>
	<a href="list">5. 부서별 최고 급여자 조회</a> <br>
	
</body>
</html>