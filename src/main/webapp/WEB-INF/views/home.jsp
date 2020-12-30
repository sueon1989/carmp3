<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<h2> <a href='/board/list?pageNum=2&amount=10'> 게시글 목록 - 20개씩 2페이지 보기 </a> </h2>
<h2> <a href='/board/register/'> 게시글 작성 </a> </h2>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
