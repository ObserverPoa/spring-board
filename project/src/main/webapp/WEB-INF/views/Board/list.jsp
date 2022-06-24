<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Project2</title>

<style>
table {
	border: 1px solid #000000;
	border-collapse: collapse;
	width: 1000px;
	padding: 5px;
	margin-left: auto;
    margin-right: auto;
}
th, td {
	border: 1px solid #000000;
	padding: 5px;
}
a{
	text-decoration: none;
	color: black;
}
a.article_lnk:hover {
	color: red;
}
a.page_btn_cur {
	display: inline-block;
	border: 2px solid red;
	width: 24px;
	height: 24px;
	padding: 1px;
}
a.page_btn {
	display: inline-block;
	width: 28px;
	height: 24px;
	padding: 1px;
}
a.page_btn:hover {
	display: inline-block;
	border: 2px solid red;
	width: 24px;
	height: 24px;
	padding: 1px;
}
</style>
</head>
<body>
	<div id="root">
		<section id="container">
			<table>
				<tr>
					<th colspan="5"><c:out value="게시판 보기"/></th>
				</tr>
				<tr>
					<td colspan="5" align="right"><c:out value="${total}(${page}/${page_max})"/></td>
				</tr>
				<tr align="center">
					<td width="80">글번호</td>
					<td width="110">이름</td>
					<td width="540">제목</td>
					<td width="120">작성일</td>
					<td>조회수</td>
				</tr>

				<c:forEach items="${list}" var="ls">
					<fmt:formatDate var="write_date" value="${ls.wrdate}" pattern="yyyy.MM.dd(E)" /> 
					<tr align="center" height="37">
						<td>${ls.anum}</td>
						<td>${ls.name}</td>
						<td align="left">
							<c:forEach begin="1" end="${ls.depth}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
							<c:if test="${ls.depth > 0}">
								<img src="/resources/images/reply.png" />
							</c:if>
							<c:if test="${write_date == today}">
								<img src="/resources/images/new1.png" />
							</c:if>
							<a class="article_lnk" href="/Board/read?page=${page}&anum=${ls.anum}">${ls.title}</a>
							<c:if test="${ls.views > 10}">
								<img src="/resources/images/hot.gif" />
							</c:if>
						</td>
						<td>
							<c:choose>
								<c:when test="${write_date == today}">
									<fmt:formatDate value="${ls.wrdate}" pattern="a h:mm" />
								</c:when>
								<c:otherwise>
									${write_date}
								</c:otherwise>
							</c:choose></td>
						<td>${ls.views}</td>
					</tr>
				</c:forEach>
				<tr align="center" height="45">
					<td colspan="5">
						<button type="button"
							onclick="location='/Board/list?page=${ctrl_btn1}'"
							<c:if test="${ctrl_btn1 < 0}"><c:out value="disabled='disabled'"/></c:if>>start page</button>
					
						<button type="button"
							onclick="location='/Board/list?page=${ctrl_btn2}'"
							<c:if test="${ctrl_btn2 < 0}"><c:out value="disabled='disabled'"/></c:if>>-10 page</button>
					
						<button type="button"
							onclick="location='/Board/list?page=${ctrl_btn3}'"
							<c:if test="${ctrl_btn3 < 0}"><c:out value="disabled='disabled'"/></c:if>>-1 page</button>
					
						<c:forEach begin="${page_leftmost}" end="${page_rightmost}" var="p">
							<c:choose>
								<c:when test="${p == page}">
									<a class="page_btn_cur" href="/Board/list?page=${p}">${p}</a>
								</c:when>
								<c:otherwise>
									<a class="page_btn" href="/Board/list?page=${p}">${p}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					
						<button type="button"
							onclick="location='/Board/list?page=${ctrl_btn4}'"
							<c:if test="${ctrl_btn4 < 0}"><c:out value="disabled='disabled'"/></c:if>>+1 page</button>
					
						<button type="button"
							onclick="location='/Board/list?page=${ctrl_btn5}'"
							<c:if test="${ctrl_btn5 < 0}"><c:out value="disabled='disabled'"/></c:if>>+10 page</button>
					
						<button type="button"
							onclick="location='/Board/list?page=${ctrl_btn6}'"
							<c:if test="${ctrl_btn6 < 0}"><c:out value="disabled='disabled'"/></c:if>>end page</button>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="right">
						<button type="button" onclick="location='/Board/writeMain?page=${page}'">글쓰기</button>
					</td>
				</tr>
			</table>
		</section>
	</div>
</body>
</html>
