<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>

	<input type="text" id="nickname" placeholder="닉네임" required autofocus>
	<button id="inputnickname">확인</button><br>
	방 번호 : ${room.roomId}<br>
	방 이름 : ${room.roomName}<br>
	<span id="size">방 인원 : ${size}</span><br>
	<div id="chatroom" style="width:500px; height:600px; border:1px solid; border:1px solid black"></div>
	<input type="text" id="message" style="width:200px; height:60px" placeholder="채팅을 입력하세요." autofocus>
	<button id="send">전송</button><br>
	<button id="exit">나가기</button>
	
	
	<script>
		var ws;
		var nickname;
		var roomId = ${room.roomId};
		var size = ${size};
		
		$("#inputnickname").on("click", function() {
			nickname = $("#nickname").val();
			$("#nickname").hide();
			$("#inputnickname").hide();
			
			ws = new WebSocket("ws://" + location.host + "<c:url value='/multichat/chat.do/websocket' />");
			
			ws.onopen = function() {
				ws.send(JSON.stringify({
					roomId : roomId,
					messageType : 'Enter',
					user : nickname
				}));
			}
			
			ws.onmessage = function(result) {
				msg = result.data;
				room = document.getElementById("chatroom");
				room.innerHTML = room.innerHTML + "<br>" + msg;
					
				if(msg.indexOf("퇴장") > 0 || msg.indexOf("입장") > 0) {
					var xhr = new XMLHttpRequest();
					xhr.open('get', '/myapp/multichat/roomsize?roomId=${room.roomId}');
					xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
					
					xhr.send();
					
					xhr.onreadystatechange = function() {
						if(xhr.readyState === xhr.DONE) {
							if(xhr.status === 200 || xhr.status === 201) {
								console.log(xhr.responseText);
								$("#size").text("방 인원 : " + xhr.responseText);
							}
						}
					}
				}
			}
		});
		
		
		
		$("#send").on("click", function() {
			var msg = document.getElementById("message").value;
			ws.send(JSON.stringify({
				roomId : roomId,
				messageType : 'Chat',
				user : nickname,
				message : msg
			}));
			document.getElementById("message").value = "";
		});
		
		
		
		$("#exit").on("click", function() {
			if(ws != null) {
				ws.send(JSON.stringify({
					roomId : roomId,
					messageType : 'Leave',
					user : nickname
				}));
				ws.close();
			}
			
			ws.onclose = function() {
				if(size >= 2) {
					var xhr = new XMLHttpRequest();
					xhr.open('get', '/myapp/multichat/roomsize?roomId=${room.roomId}');
					xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
					xhr.send();
					
					xhr.onreadystatechange = function() {
						if(xhr.readyState === xhr.DONE) {
							if(xhr.status === 200 || xhr.status === 201) {
								$("#size").text("방 인원 : " + xhr.responseText);
							}
						}
					}
					
				} else if(size == 1) {
					var xhr = new XMLHttpRequest();
					xhr.open('get', '/myapp/multichat/delete?roomId=${room.roomId}');
					xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
					xhr.send();
				}
				
				setTimeout(function() {
					window.location.href="<c:url value='/multichat' />";
				}, 1000);
				
			}
		});
		
		
		
	</script>
	
</body>
</html>