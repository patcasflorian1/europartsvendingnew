<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OpenProduct</title>
<!--Stylesheet-->
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("index.jsp");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
	sessionID = session.getId();
}
%>

<div class="container">
        <div class="image-container">
            <img src="data:image/jpg;base64,${model.photoProduct.base64Image1}" id="content1" class="active">
            <img src="data:image/jpg;base64,${model.photoProduct.base64Image2}" id="content2">
            <img src="data:image/jpg;base64,${model.photoProduct.base64Image3}" id="content3">
            <img src="data:image/jpg;base64,${model.photoProduct.base64Image4}" id="content4">
        </div>
        <div class="dot-container">
            <button onclick = "dot(1)"></button>
            <button onclick = "dot(2)"></button>
            <button onclick = "dot(3)"></button>
            <button onclick = "dot(4)"></button>
        </div>
        <button id="prev" onclick="prev()"> &lt; </button>
        <button id="next" onclick="next()"> &gt; </button>
    </div>
    <script src="script.js"></script>

</body>
</html>