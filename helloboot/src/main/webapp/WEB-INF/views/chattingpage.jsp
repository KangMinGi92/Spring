<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅화면</title>
</head>
<body>
	<h2>채팅페이지</h2>
	<div>
	
	</div>
	
	<script>
		const websocket=new WebSocket("ws://localhost:9090/chatting");
		websocket.onopen=data=>{
			console.log(data);
		}
	</script>
</body>
</html>