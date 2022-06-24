<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>답변형 게시판 입력하기</title>
	</head>
	<body>
		<div id="root">
			<header>
				<h1>답변형 게시판 입력하기</h1>
			</header>
			<hr />
			
			<section id="container">
				<form role="form" method="post" action="/board/writeArticle">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="title">이름</label><input type="text" id="name" name="name" />
								</td>
							</tr>	
							<tr>
								<td>
									<label for="writer">제목</label><input type="text" id="title" name="title" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="content">내용</label><textarea id="content" name="content" ></textarea>
								</td>
							<tr>
								<td>						
									<button type="submit">저장하기</button>
								</td>
								<td>						
									<button type="reset">다시쓰기</button>
								</td>
								<td>						
									<button type="button">돌아가기</button>
								</td>
							</tr>			
						</tbody>			
					</table>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>