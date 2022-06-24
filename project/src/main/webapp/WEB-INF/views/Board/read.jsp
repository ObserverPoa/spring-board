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
	width: 550px;
	padding: 5px;
	margin-left: auto;
    margin-right: auto;
}
th, td {
	border: 1px solid #000000;
	padding: 5px;
}
</style>
</head>
<body>
	<div id="root">
		<section id="container">
			<form role="form" method="post"
				action="/Board/modifyArticle?page=${page}&anum=${read.anum}">
				<table>
					<tbody>
						<tr>
							<th colspan="4"><c:out value="게시글 보기"/></th>
						</tr>
						<tr align="center">
							<td width="80"><label for="anum">글번호</label></td>
							<td width="120"><label for="name">작성자</label></td>
							<td width="210"><label for="wrdate">작성일</label></td>
							<td><label for="views">조회수</label></td>
						</tr>
						<tr align="center">
							<td>${read.anum}</td>
							<td>${read.name}</td>
							<td><fmt:formatDate value="${read.wrdate}" pattern="yyyy/MM/dd(E) HH:mm" /></td>
							<td>${read.views}</td>
						</tr>
						<tr height="35">
							<td align="center"><label for="title">제목</label></td>
							<td colspan="3"><input type="text" size="57" id="title" name="title" value="${read.title}" required /></td>
						</tr>
						<tr>
							<td align="center"><label for="title">내용</label></td>
							<td colspan="3"><textarea rows="10" cols="59" id="content" name="content" required><c:out value="${read.content}" /></textarea></td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<button type="submit">수정하기</button>
								<button type="button" onclick="location='/Board/deleteArticle?page=${page}&anum=${read.anum}'">삭제하기</button>
								<button type="button" onclick="location='/Board/writeSub?page=${page}&anum=${read.anum}'">답변달기</button>
								<button type="button" onclick="location='/Board/list?page=${page}'">돌아가기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</section>
	</div>
</body>
</html>