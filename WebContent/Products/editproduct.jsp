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
<title>Edit  Product</title>
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
<style>

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}
</style>

<title>View Source Button</title>

        <link rel="stylesheet" href="//css-tricks.com/examples/ViewSourceButton/css/style.css">
        <link rel="stylesheet" href="//css-tricks.com/examples/ViewSourceButton/css/prettify.css">
        <style>
                #source-code { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(255,255,255,0.8); }
                #source-code:target { display: block; }
                #source-code pre { padding: 20px; font: 14px/1.6 Monaco, Courier, MonoSpace; margin: 50px auto; background: rgba(0,0,0,0.8); color: white; width: 80%; height: 80%; overflow: auto; }
                #source-code pre a, #source-code pre a span { text-decoration: none; color: #00ccff !important; }
                #x { position: absolute; top: 30px; left: 10%; margin-left: -41px; }
                .button { background: #00ccff; padding: 10px 20px; color: white; text-decoration: none; -moz-border-radius: 10px; -webkit-border-radius: 10px; border-radius: 10px; }
        </style>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
        <script src="//css-tricks.com/examples/ViewSourceButton/prettify/prettify.js"></script>
       

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

<p style = "color:green">Id Produs : <f2 style="color: Tomato"><c:out value="${product.id}"></c:out></f2></p>

<p style = "color:green">Cod Produs : <f2 style="color: Tomato"><c:out value="${product.codProdus}"></c:out></f2></p>

<p style = "color:green">Nume Produs : <f2 style="color: Tomato"><c:out value="${product.numeProdus}"></c:out></f2></p>
<p style = "color:green">Categoria Produsului : <f2 style="color: Tomato"><c:out value="${product.category}"></c:out></f2></p>
<p style = "color:green">SubCategoria Produsului : <f2 style="color: Tomato"><c:out value="${product.subCategory}"></c:out></f2></p>
<div class="tab">
   <button onclick="openCity(event, '<c:out value="${product.category}"></c:out>')" class="tablinks">SetCategory</button>
    <button onclick="openCity(event, '<c:out value="${product.subCategory}"></c:out>')" class="tablinks">SetSubCategory</button>
   <a href="<c:url value='getPhoto.htm?idProduct=${product.id}'/>"><button  class="tablinks">Edit Photo</button></a>
</div>

<div id="<c:out value="${product.category}"></c:out>" class="tabcontent">
  <c:forEach var="categorie" items="${model.listCategory}">
    <a href ="setCategoryProduct.htm?nameCategory=${categorie.nameCategory}&idProduct=${product.id}"><c:out value="${categorie.nameCategory}"></c:out></a>
    <br>
    </c:forEach>
</div>

<div id="<c:out value="${product.subCategory}"></c:out>" class="tabcontent">

  <c:forEach var="subCategorie" items="${model.listSubCategory}">
    <a href ="setSubCategoryProduct.htm?nameSubCategory=${subCategorie.nameSubCategory}&idProduct=${product.id}&nameCategory=${product.category}"><c:out value="${subCategorie.nameSubCategory}"></c:out></a>
    <br>
    </c:forEach>
    <c:if test = "${model.listSubCategory == null}">
    
    <h3 style="color: Tomato"><c:out value="${model.subCatMesage}"></c:out></h3>
</c:if>
</div>

<script>
function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}
</script>
<h3 style="color: Tomato"><c:out value="${model.editSubCat}"></c:out></h3>
 
<!-- AREA CHART -->
            
              <div class="card-body">
                <div class="chart">

 
<!-- Edit Product -->
<c:if test = "${model.editSubCat != null}">
<div class="container">
  <form action="updateProduct.htm" metod="post" commandName="idProduct">
  <label for="id" style = "color:green">Id Produs : <f2 style="color: Tomato"><c:out value="${product.id}"></c:out></f2></label>
    <input type="hidden" id="id" name="id" value="${product.id}" placeholder="${product.id}">
    <br>
    <label for="category" style = "color:green">Categorie : <f2 style="color: Tomato"><c:out value="${product.category}"></c:out></f2></label>
    <input type="hidden" id="category" name="category" value="${product.category}" placeholder="${product.category}">
    <br>
    <label for="subCategory">SubCategorie :<f2 style="color: Tomato"><c:out value="${product.subCategory}"></c:out></f2> </label>
   <input type="hidden" id="subCategory" name="subCategory" value="${product.subCategory}" placeholder="${productNamesubCategory}">
    <br>
    <label for="codProdus">Cod Produs<f2 style="color: Tomato"> <c:out value="${product.codProdus}"></c:out></f2></label>
    <input type="text" id="codProdus" value="${product.codProdus}" name="codProdus">
    
    <label for="numeProdus">Nume Produs<f2 style="color: Tomato"> <c:out value="${product.numeProdus}"></c:out></f2></label>
    <input type="text" id="numeProdus" name="numeProdus" value="${product.numeProdus}" placeholder="Your name Poduct..">
    <label for="descriereProdus">Descriere Produs</label>
     <textarea id="descriereProdus" name="descriereProdus" rows="4" cols="50"><c:out value="${product.descriereProdus}"></c:out></textarea>
    <label for="pretProdus">Pret Produs</label>
    <input type="text" id="pretProdus" name="pretProdus" value="${product.pretProdus}" placeholder="Your price Product..">
     <label for="pretProdusPromotional">Pret Produs Promotional</label>
    <input type="text" id="pretProdusPromotional" name="pretProdusPromotional" value="${product.pretProdusPromotional}" placeholder="Your Promo Price Product..">
     <label for="cantitateProdus">Cantitate Produs</label>
    <input type="text" id="cantitateProdus" name="cantitateProdus" value="${product.cantitateProdus}" placeholder="Your Quantity Product..">
   
  <br>
   <label for="unitateMasura"><f3 style="font-family:verdana">Alegeti Unitate De Masura </f3></label>
  <select name="unitateMasura" id="unitateMasura">
    <option value="BUC">BUC</option>
    <option value="Metru">Metru</option>
    <option value="Kg">Kg</option>
    <option value="Litru">Litru</option>
    <option value="Cutie">Cutie</option>
    <option value="Bax">Bax</option>
  </select>
  <br><br>
      <label style = "color:green" for="statute"><f3 style="font-family:verdana">Statut Actual: </f3><f2 style="color: Tomato"><c:out value="${product.statutProdus}"></c:out></f2></label>
  <br>
 
  <select id="statute" name="statutProdus" >
    <option value="PUBLICAT">PUBLICAT</option>
    <option value="FORBIDDEN">FORBIDDEN</option>
  </select>
  <br>
    <label for="notaProdus">Nota Produs</label>
    <input type="text" id="notaProdus" name="notaProdus"  placeholder="Your rating for the Product.." value="${product.notaProdus}">
    <br>
     
    <input type="submit" value="Submit">
   
  </form>
</div>

</c:if>
 </div>
  </div>
</body>
</html>
