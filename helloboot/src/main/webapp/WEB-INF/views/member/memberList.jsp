<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>전체회원조회</h3>
	<c:if test="${not empty member }">
		<table>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>이메일</th>
				<th>취미</th>
			</tr>
			<c:forEach var="m" items="${member }">
				<tr>
					<td>${m.userId }</td>
					<td>${m.userName }</td>
					<td>${m.age }</td>
					<td>${m.gender }</td>
					<td>${m.email }</td>
					<td>${m.hobby }</td>
				</tr>	
			</c:forEach>
		</table>
	</c:if>
</body>
</html>