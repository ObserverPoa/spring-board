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
				action="/Board/writeArticleMain?page=1">
				<table>
					<tbody>
						<tr>
							<th colspan="2"><c:out value="답변형 게시판 입력하기" /></th>
						</tr>
						<tr height="35">
							<td align="center" width="80"><label for="title">이름</label></td>
							<td><input type="text" id="name" name="name" maxlength="20" required /></td>
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
							<td colspan="2" align="center">
								<button type="submit">저장하기</button>
								<button type="reset">다시쓰기</button>
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