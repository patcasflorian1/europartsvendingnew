<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
       <%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="resources2/Comun/lista-style.css">
<title>Add  Product</title>
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


<h3 id="myHeader" > <a style="color:red" href="productlist.htm" ><==Back</a> 
<caption style=" background: #245f91;color:#eee;text-align: center;padding: 10px;border-radius: 55px;font-weight: bold;" > Organizator : Eurovending SRL Oradea Str Aleea Rogerius Nr.6,Bihor</caption> 
<a style="color:red" href="LoginOut.htm">. LogOut</a></h3>
<!-- Set Category -->
<div class="dropdown">
   <button onclick="myFunction()" class="dropbtn">SetCategory</button>
  <div id="myDropdown" class="dropdown-content">
   <c:forEach var="categorie" items="${model.listCategory}">
    <a href ="selectSubCategory.htm?nameCategory=${categorie.nameCategory}"><c:out value="${categorie.nameCategory}"></c:out></a>
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
<!-- EndSet Category -->

<!-- Set SubCategory -->
<c:if test = "${model.nameCategory != null}">
<div class="container">
<p style = "color:green">Categorie : <f2 style="color: Tomato"><c:out value="${category.nameCategory}"></c:out></f2></p>
<f2 style="color: Tomato"> <c:out value="${model.subCatMesage}"></c:out></f2>
<br>
<div class="dropdown">
   <button onclick="myFunction1()" class="dropbtn">SetSubCategory</button>
  <div id="myDropdown1" class="dropdown-content">
   <c:forEach var="subcategorie" items="${model.listSubCat}">
    <a href ="addproduct.htm?subCategory=${subcategorie.nameSubCategory}">
    <c:out value="${subcategorie.nameSubCategory}"></c:out></a>
    </c:forEach>
  </div>
</div>

<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction1() {
  document.getElementById("myDropdown1").classList.toggle("show");
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
</div>
</c:if>
<!-- EndSet SubCategory -->

<!-- Set Product -->
<c:if test = "${model.subCategory != null}">
<div class="container">
  <form action="addproduct.htm" metod="post" commandName="productName">
    <label for="category" style = "color:green">Categorie : <f2 style="color: Tomato"><c:out value="${productName.category}"></c:out></f2></label>
    <input type="hidden" id="category" name="category" value="${productName.category}" placeholder="${productName.category}">
    <br>
    <label for="subCategory">SubCategorie :<f2 style="color: Tomato"><c:out value="${productName.subCategory}"></c:out></f2> </label>
   <input type="hidden" id="subCategory" name="subCategory" value="${productName.subCategory}" placeholder="${productNamesubCategory}">
    <br>
    <label for="codProdus">Cod Produs<f2 style="color: Tomato"> <c:out value="${model.codMesage}"></c:out></f2></label>
    <input type="text" id="codProdus" name="codProdus" placeholder="Anterior code Product.. <c:out value="${model.codInitialProd}"></c:out>">
    
    <label for="numeProdus">Nume Produs<f2 style="color: Tomato"> <c:out value="${model.numeMesage}"></c:out></f2></label>
    <input type="text" id="numeProdus" name="numeProdus" placeholder="Your name Poduct..">
    
    <label for="descriereProdus">Descriere Produs</label>
    <textarea id="descriereProdus" name="descriereProdus" placeholder="Write something.." style="height:200px"></textarea>
    <label for="pretProdus">Pret Produs</label>
    <input type="text" id="pretProdus" name="pretProdus" value="${productName.pretProdus}" placeholder="Your price Product..">
     <label for="pretProdusPromotional">Pret Produs Promotional</label>
    <input type="text" id="pretProdusPromotional" name="pretProdusPromotional" value="${productName.pretProdusPromotional}" placeholder="Your Promo Price Product..">
     <label for="cantitateProdus">Cantitate Produs</label>
    <input type="text" id="cantitateProdus" name="cantitateProdus" value="${productName.cantitateProdus}" placeholder="Your Quantity Product..">
    <label style = "color:green" for="cantitateProdus"><f3 style="font-family:verdana">Alegeti Unitate De Masura </f3></label>
  <br>
  <input list="browsers" name="unitateMasura"  id="browser" size="30" >
  <datalist id="browsers" >
    <option value="BUC">
    <option value="Metru">
    <option value="Kg">
    <option value="Litru">
    <option value="Cutie">
    <option value="Bax">
  </datalist>
  <br>
      <label style = "color:green" for="statute"><f3 style="font-family:verdana">Statut Actual: </f3><f2 style="color: Tomato">FORBIDDEN</f2></label>
  <br>
  <input list="browsers1" name="statutProdus" id="browser1" size="30" >
  <datalist id="browsers1" >
    <option value="PUBLICAT">
    <option value="FORBIDDEN">
  </datalist>
  <br>
    <label for="notaProdus">Nota Produs</label>
    <input type="text" id="notaProdus" name="notaProdus"  placeholder="Your rating for the Product.." value="${productName.notaProdus}">
    <br>
     <c:if test = "${model.nameCategory != null}">
    <input type="submit" value="Submit">
    </c:if>
  </form>
</div>

</c:if>

</body>
</html>
