<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=""/>
</jsp:include>
<section>
<section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        <button class="btn btn-outline-success" onclick="location.assign('${path}/board/boardForm.do')">글쓰기</button>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty boards}">
	            <c:forEach var="b" items="${boards}" >
		            <tr>
		            	<td><c:out value="${b.boardNo }"/></td>
		            	<td><a href="${path }/board/boardView.do?no=${b.boardNo}"><c:out value="${b.boardTitle }"/></a></td>
		            	<td><c:out value="${b.boardWriter.userId }"/></td>
		            	<td><fmt:formatDate value="${b.boardDate }" pattern="yyyy-MM-dd" /></td>
		            	<td>
		            		<c:if test="${not empty b.file }">
		            			<img src="${path }/resources/images/file.png" alt="첨부파일사진">
		            			<span>${b.file.size() }</span>
		            		</c:if>
		            	</td>
		            	<td><c:out value="${b.boardReadCount }"/></td>
		            </tr>
	            </c:forEach>
            </c:if>
        </table>
        <div id="pageBar">
        	<c:out value="${pageBar}" escapeXml="false"/>
        </div>
</section>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>