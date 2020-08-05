<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>

	<span id=ajaxarea>ajax 값</span>
	<br>
	<button id=ajax>ajax 실행</button>
	
	<!--
	<script>
		$("#ajax").click("on", function() {
			var xhr = new XMLHttpRequest();
			var setArea = function(word) {
				document.getElementById("ajaxarea").innerText = word;
			}
			
			<%--기본셋팅--%>
			xhr.open("get", "json/example?userId=abc1234");
			xhr.setRequestHeader("content-type", "application/json");
			<%--post때 csrf가 없으면 정보가 넘어가지 않음
				다른 서버에서는 이 토큰값을 받지 못하기 때문에 막힘--%>
			xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			<%--requestbody에 넣고 싶은 데이터를 넣어줌--%>
			xhr.send();
			<%--모든 상태를 다 찍겠다는 의미, 5가지가 있음!!!--%>
			xhr.onreadystatechange = function() {
				if(xhr.readyState === xhr.LOADING) {
					console.log("로딩중");
					setArea("로딩중");
				}
				if(xhr.readyState === xhr.DONE) {
					if(xhr.status === 200 || xhr.status === 201) {
						setTimeout(function() {
							setArea(xhr.responseText)
						}, 5000);
					}
				}
			}
		});
	</script>
	-->
		
		
		
	<script>	
		$.ajax({
			url : "json/example",
			type : "post",
			data : {userId:"abc1234", "${_csrf.parameterName}":"${_csrf.token}"},
			dataType : "text",
			success : function(result) {
				$("#ajaxarea").text(result);
			},
			error : function(e) {
				console.log(e.statusText);
			}
		});
		
		
		/* //로딩 시작과 끝에 사용할 수 있는 문법
		.ajaxStart(function() {
			
		});
		.ajaxStop(function() {
			
		}); */
	</script>

</body>
</html>