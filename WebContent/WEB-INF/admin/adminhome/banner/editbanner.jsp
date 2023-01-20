<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
       <%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit_Banner</title>
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

<h3 id="myHeader" > <a style="color:red" href="list-subCategory.htm" ><==Back</a> 
<caption style=" background: #245f91;color:#eee;text-align: center;padding: 10px;border-radius: 55px;font-weight: bold;" > Organizator : Eurovending SRL Oradea Str Aleea Rogerius Nr.6,Bihor</caption> 
<a style="color:red" href="login-portal.htm">. LogOut</a></h3>

<div class="container">
  <form:form action="updateBanner.htm" metod="post" commandName="banner" enctype="multipart/form-data">
    <label for="id" style = "color:green">Id Banner : <f2 style="color: Tomato"><c:out value="${banner.id}"></c:out></f2></label>
   <input type="hidden" id="id" name="id" value="${banner.id}" placeholder="${banner.id}">
    <br>
    <label for="dataAdaugare" style = "color:green">Data Adaugare : <f2 style="color: Tomato"><c:out value="${banner.dataAdaugare}"></c:out></f2></label>
   
    <br>    
    <label for="bannerName" style = "color:green">Nume Banner : </label>
    <input type="text" id="bannerName" name="bannerName" value="${banner.bannerName}" placeholder="${banner.bannerName} <c:out value="${model.mesaj}"></c:out>"><br>
    <label for="link" style = "color:green">Link Spre Produs : </label>
    <input type="text" id="link" name="link" value="${banner.link}" placeholder="${banner.link} <c:out value="${model.mesaj}"></c:out>"><br>
      <label style = "color:green" for="statute"><f3 style="font-family:verdana">Statut Actual: </f3><f2 style="color: Tomato"><c:out value="${banner.statut}"></c:out></f2></label>
  <br>
  <input list="browsers1" name="statut" id="browser1" size="30" placeholder="${banner.statut}" >
  <datalist id="browsers1" >
    <option value="PUBLICAT">
    <option value="FORBIDDEN">
  </datalist>
  <br>
   <f2>AlegeImaginea  </f2>
        <br>
<input type="file" name="photo" id="file1" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file1"></label>
       <br>
    <input type="submit" value="Submit">
    
  </form:form>
</div>
<div>
<f2 style="color:tomato">
Imaginea Actuala :
</f2>
</div>
<div>
<img src="data:image/jpg;base64,${banner.photoString}" width="250px" height="250"/>
</div>
</body>
</html>