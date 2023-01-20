<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="resources/Comun/lista-style.css">
<title>Add Photo Product</title>

<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 40%;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.container {
  padding: 2px 16px;
}
</style>


<style type="text/css">
/* The grid: Four equal columns that floats next to each other */
.column {
  float: left;
  width: 25%;
  padding: 10px;
}

/* Style the images inside the grid */
.column img {
  opacity: 0.8;
  cursor: pointer;
}

.column img:hover {
  opacity: 1;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* The expanding image container (positioning is needed to position the close button and the text) */
.container {
  position: relative;
  display: none;
}

/* Expanding image text */
#imgtext {
  position: absolute;
  bottom: 15px;
  left: 15px;
  color: white;
  font-size: 20px;
}

/* Closable button inside the image */
.closebtn {
  position: absolute;
  top: 10px;
  right: 15px;
  color: white;
  font-size: 35px;
  cursor: pointer;
}

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
<!-- ButtonCSS -->
<style type="text/css">

.myclass {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}

.myclass + label {
  font-size: 2em;
  font-weight: 700;
  color: white;
  background-color: green;
  border-radius: 10px;
  display: inline-block;
}

.myclass:focus + label,
.myclass + label:hover {
  background-color: purple;
}

</style>

<script type="text/javascript">
function myFunction(imgs) {
	  // Get the expanded image
	  var expandImg = document.getElementById("expandedImg");
	  // Get the image text
	  var imgText = document.getElementById("imgtext");
	  // Use the same src in the expanded image as the image being clicked on from the grid
	  expandImg.src = imgs.src;
	  // Use the value of the alt attribute of the clickable image as text inside the expanded image
	  imgText.innerHTML = imgs.alt;
	  // Show the container element (hidden with CSS)
	  expandImg.parentElement.style.display = "block";
	}
</script>
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
<f2 style="color: Tomato"><c:out value="${model.idProduct}"></c:out></f2>
 <!-- Insert Photo -->
 <c:if test="${productName.numeProdus!=null }">
 <form action="addphoto.htm" method="post" commandName="idProdus" enctype="multipart/form-data" >
            <label for="category" style = "color:green">IdProdus : <f2 style="color: Tomato"><c:out value="${model.idProduct}"></c:out></f2></label>
    <input type="hidden" id="idProdus" name="idProdus" value="${model.idProduct}" placeholder="${model.idProduct}">
    <br>
    <c:if test="${photoProduct.base64Image1!=null }">
    <img  src="data:image/jpg;base64,${photoProduct.base64Image1}" width="250" height="350" />
     </c:if>
     <br/>
    <input type="file" name="photo1" id="file1" class="myclass" required="required" accept=".jpg,.jpeg,.png" />
   <label for="file1">AlegeImagineaPrincipala</label>
   
     <br/><br/>
      <c:if test="${photoProduct.base64Image2!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image2}" width="250" height="350" />
      </c:if>
       <input type="file" name="photo2" id="file2" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file2">AlegeImaginea 2</label>
       <br/><br/>
     <c:if test="${photoProduct.base64Image3!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image3}" width="250" height="350" />
      </c:if>
       <input type="file" name="photo3" id="file3" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file3">AlegeImaginea 3</label>
         <br/><br/>
     <c:if test="${photoProduct.base64Image4!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image4}" width="250" height="350" />
      </c:if>
       <input type="file" name="photo4" id="file4" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file4">AlegeImaginea 4</label>
     <br/><br/>
     <c:if test="${photoProduct.base64Image5!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image5}" width="250" height="350" />
      </c:if>
      
      <input type="file" name="photo5" id="file5" class="myclass"  accept=".jpg,.jpeg,.png" />
      <label for="file5">AlegeImaginea 5</label>
    <br/><br/>
    <label for="videoLink">Link Video<f2 style="color: Tomato"> <c:out value="${photoProduct.videoLink}"></c:out></f2></label>
    <input type="text" id="videoLink" name="videoLink" value="${photoProduct.videoLink}" placeholder="Your name Poduct..">
            <input type="submit" />
        </form>

</c:if>


  
<!-- Edit Photo -->
<c:if test="${productName.numeProdus==null }">
<div class="card">
<!-- The expanding image container -->
<div class="container">

  <!-- Close the image -->
  <span onclick="this.parentElement.style.display='none'" class="closebtn">&times;</span>

  <!-- Expanded image -->
  <img id="expandedImg" style="width:70%">

  <!-- Image text -->
  <div id="imgtext"></div>
  
</div>
  
</div>
 <!-- The grid: four columns -->
<div class="row">
  <div class="column">
 <form action="addphotoproduct1.htm" method="post" commandName="idProduct" enctype="multipart/form-data" >
            <label for="codProdus" style = "color:green">CodProdus : <f2 style="color: Tomato"><c:out value="${model.idProduct}"></c:out></f2></label>
    <input type="hidden" id="idProdus" name="idProdus" value="${model.idProduct}" placeholder="${model.idProduct}">
    <br>
    <c:if test="${photoProduct.base64Image1!=null }">
    <img  src="data:image/jpg;base64,${photoProduct.base64Image1}" width="50" height="50" onclick="myFunction(this)"/>
     </c:if>
     <br/>
    <input type="file" name="photo1" id="file1" class="myclass" required="required" accept=".jpg,.jpeg,.png" />
   <label for="file1">SetImgPrincipala</label>
   <br/>
 <input type="submit" />
  </form>
     </div>
     
      <div class="column">
      <form action="addphotoproduct2.htm" method="post" commandName="idProduct" enctype="multipart/form-data" >
    <input type="hidden" id="idProdus" name="idProdus" value="${model.idProduct}" placeholder="${model.idProduct}">
      <c:if test="${photoProduct.base64Image2!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image2}" width="50" height="50" onclick="myFunction(this)" />
      </c:if>
    <br>
       <input type="file" name="photo2" id="file2" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file2">SetImg 2</label>
      
       <br/>
       <input type="submit" />
        </form>
       </div>
        <div class="column">
        <form action="addphotoproduct3.htm" method="post" commandName="idProduct" enctype="multipart/form-data" >
    <input type="hidden" id="idProdus" name="idProdus" value="${model.idProduct}" placeholder="${model.idProduct}">
     <c:if test="${photoProduct.base64Image3!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image3}" width="50" height="50" onclick="myFunction(this)"/>
      </c:if>
    <br>
       <input type="file" name="photo3" id="file3" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file3">SetImg 3</label>
         <br/>
          <input type="submit" />
           </form>
         </div>
         <br>
          <div class="column">
          <form action="addphotoproduct4.htm" method="post" commandName="idProduct" enctype="multipart/form-data" >
    <input type="hidden" id="idProdus" name="idProdus" value="${model.idProduct}" placeholder="${model.idProduct}">
     <c:if test="${photoProduct.base64Image4!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image4}" width="50" height="50" onclick="myFunction(this)"/>
      </c:if>
    <br>
       <input type="file" name="photo4" id="file4" class="myclass"  accept=".jpg,.jpeg,.png" />
       <label for="file4">SetImg 4</label>
     <br/>
     <input type="submit" />
      </form>
    </div>
      <div class="column">
      <form action="addphotoproduct5.htm" method="post" commandName="idProduct" enctype="multipart/form-data" >
    <input type="hidden" id="idProdus" name="idProdus" value="${model.idProduct}" placeholder="${model.idProduct}">
     <c:if test="${photoProduct.base64Image5!=null }">
     <img src="data:image/jpg;base64,${photoProduct.base64Image5}" width="50" height="50" onclick="myFunction(this)"/>
      </c:if>
    <br>
      <input type="file" name="photo5" id="file5" class="myclass"  accept=".jpg,.jpeg,.png"/>
      <label for="file5">SetImg 5</label>
    <br/>
    <br/>
            <input type="submit" />
        </form>
       </div> 
        <div class="column">
      <form action="addvideolink.htm" method="post" commandName="idProduct"  >
      <input type="hidden" id="idProdus" name="idProdus" value="${model.idProduct}" placeholder="${model.idProduct}">
        <label for="videoLink">Link Video<f2 style="color: Tomato"> <c:out value="${photoProduct.videoLink}"></c:out></f2></label>
    <input type="text" id="videoLink" name="videoLink" value="${photoProduct.videoLink}" placeholder="Your name Poduct..">
       <input type="submit" />
        </form>
       </div> 
       </div>
       
      </c:if>
 
</body>
</html>