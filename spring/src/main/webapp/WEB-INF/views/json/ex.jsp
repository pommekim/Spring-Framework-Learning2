<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.js"></script>
</head>
<body>

	<button id="btn1">XMLHttpRequest 전송</button>
	<button id="btn2">jQuery 전송</button>
	<div id="result"></div>

	<script>
	
	    var jsonVO = {name:"Jay", age:20};
	    
	    $("#btn1").on("click", function() {
	       var request = new XMLHttpRequest();
	       request.onreadystatechange = function() {
	          if(request.readyState === request.DONE) {
	             if(request.status === 200 || request.status === 201) {
	                document.getElementById("result").innerText = request.responseText;
	             }
	          }
	       }
	       request.open("post", "json/ex");
	       request.setRequestHeader("content-type", "application/json");
	       //객체를 문자형태로 바꿔줌!!! -> 커맨드 객체를 쓰기 위해!!!
	       request.send(JSON.stringify(jsonVO));
	    });
	    
	    
	    $("#btn2").on("click", function() {
	       $.ajax({
	          url: "json/ex",
	          type: "post",
	          dataType: "text",
	          data: JSON.stringify(jsonVO),
	          headers: {"content-type":"application/json"},
	          success: function(result) {
	             $("#result").text(result);
	          },
	          error: function(error) {
	             alert(error.statusText);
	          }
	       });
	    });
    
 	</script>
	


</body>
</html>