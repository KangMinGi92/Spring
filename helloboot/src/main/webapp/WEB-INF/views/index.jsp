<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath  }"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boot 메인화면</title>
</head>
<body>
	<h2>나의 첫 boot화면</h2>
	<h3><a href="${pageContext.request.contextPath }/member/memberAll">전체회원 조회</a></h3>
	<form action="${path}/fileUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="submit" value="파일저장">
	</form>
	<form action="${path }/datatest" method="post">
		<input type="text" name="data">
		<input type="submit" value="전송">
	</form>
	<!-- 아이디로 조회 -->
	<form action="${path }/memberId" method="post">
		<input type="text" name="userId">
		<input type="submit" value="아이디조회">
	</form>
		<!-- 이름으로 조회 -->
	<form action="${path }/membername" method="get">
		<input type="text" name="userName">
		<input type="submit" value="이름조회">
	</form>
	
	<button onclick="openchatting();">채팅하기</button>
	
	<script>
		function openchatting(){
			open("/chattingpage","_blank","width=400, height=500");
		}
	</script>
	<h3>ajax요청처리하기</h3>
	<button onclick="memberAll();">요청처리하기</button>
	<script>
	const memberAll=()=>{
		fetch("${pageContext.request.contextPath}/ajax/memberAll")
			.then(response=>{
					if(!response.ok) 
						throw new Error("요청에러"); 
					return response.json();
				}
			)
			.then(data=>{
				console.log(data);
			})
			.catch(error=>{
				console.log(error);
			});
		}
	</script>
</body>
</html>