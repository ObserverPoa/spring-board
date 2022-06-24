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
			<form role="form" method="post" action="/Board/writeArticleSub?page=${page}">
				<table>
					<tbody>
						<tr>
							<th colspan="4"><c:out value="질문글 보기"/></th>
						</tr>
						<tr align="center">
							<td width="80">글번호</td>
							<td width="120">작성자</td>
							<td width="210">작성일</td>
							<td>조회수</td>
						</tr>
						<tr align="center">
							<td>${read.anum}</td>
							<td>${read.name}</td>
							<td><fmt:formatDate value="${read.wrdate}" pattern="yyyy/MM/dd(E) HH:mm" /></td>
							<td>${read.views}</td>
						</tr>
						<tr>
							<td align="center">제목</td>
							<td colspan="3">${read.title}</td>
						</tr>
						<tr>
							<td align="center">내용</td>
							<td colspan="3"><pre style="font-size: 15px">${read.content}</pre></td>
						</tr>
					</tbody>
				</table>
				
				<br />
				
				<table>
					<tbody>
						<tr>
							<th colspan="2"><c:out value="답글 쓰기"/></th>
						</tr>
						<tr height="35">
							<td align="center" width="80"><label for="title">이름</label></td>
							<td><input type="text" id="name" name="name" required /></td>
						</tr>
						<tr height="35">
							<td align="center"><label for="writer">제목</label></td>
							<td><input type="text" size="57" id="title" name="title" required /></td>
						</tr>
						<tr>
							<td align="center"><label for="content">내용</label></td>
							<td><textarea rows="10" cols="59" id="content" name="content" required></textarea></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="hidden" id="mainord" name="mainord" value="${read.mainord}" /> 
								<input type="hidden" id="subord" name="subord" value="${read.subord}" /> 
								<input type="hidden" id="depth" name="depth" value="${read.depth}" />
								<button type="submit">답글저장</button>
								<button type="reset">다시쓰기</button>
								<button type="button" onclick="location='/Board/read?page=${page}&anum=${read.anum}'">돌아가기</button>
								<button type="button" onclick="location='/Board/list?page=${page}'">목록보기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</section>
	</div>
</body>
</html>