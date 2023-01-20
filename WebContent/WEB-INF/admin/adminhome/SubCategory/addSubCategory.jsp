<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
       <%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="resources2/Comun/lista-style.css">
<title>Add  SubCategory</title>
<style>
.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}
</style>


<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>


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
<%
int idCategory []=null;
%>

<h3 id="myHeader" > <a style="color:red" href="list-subCategory.htm" ><==Back</a> 
<caption style=" background: #245f91;color:#eee;text-align: center;padding: 10px;border-radius: 55px;font-weight: bold;" > Organizator : Eurovending SRL Oradea Str Aleea Rogerius Nr.6,Bihor</caption> 
<a style="color:red" href="login-portal.htm">. LogOut</a></h3>

<div class="dropdown">
   <button onclick="myFunction()" class="dropbtn">SetCategory</button>
  <div id="myDropdown" class="dropdown-content">
   <c:forEach var="categorie" items="${model.listCategory}">
    <a href ="addsubcategory.htm?nameCategory=${categorie.nameCategory}"><c:out value="${categorie.nameCategory}"></c:out></a>
    </c:forEach>
  </div>
</div>

<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>

<div class="container">
  <form:form action="insertsubcategory.htm" metod="post" commandName="subCategory" enctype="multipart/form-data">
    
    <label for="nameCategory" style = "color:green">Categorie Setata : <f2 style="color: Tomato"><c:out value="${model.nameCategory}"></c:out></f2></label>
    <input type="text" id="nameCategory" name="nameCategory" value="${model.nameCategory}" placeholder="${model.newSubCategory.nameCategory}">
    <br>    
    <label for="nameSubCategory">Nume SubCategorie<f2 style="color: Tomato"> <c:out value="${model.numeMesage}"></c:out></f2></label>
    <input type="text" id="nameSubCategory" name="nameSubCategory" placeholder="Your name SubCategory.. <c:out value="${model.mesaj}"></c:out>">
      <label style = "color:green" for="statute"><f3 style="font-family:verdana">Statut Actual: </f3><f2 style="color: Tomato">FORBIDDEN</f2></label>
  <br>
  <input list="browsers1" name="statut" id="browser1" size="30" >
  <datalist id="browsers1" >
    <option value="PUBLICAT">
    <option value="FORBIDDEN">
  </datalist>
  <br>
   <f2>AlegeImaginea  </f2>
       <br>
<input type="file" name="photoSubCategory" id="file1" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file1"></label>
       <br>
       <c:if test = "${model.nameCategory != null}">
    <input type="submit" value="Submit">
    </c:if>
  </form:form>
</div>

</body>
</html>
