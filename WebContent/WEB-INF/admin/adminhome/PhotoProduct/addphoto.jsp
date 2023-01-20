<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="resources/Comun/lista-style.css">
<title>Add Photo Product</title>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Toast Notification</title>
    <!--Google Font-->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <!--Font Awesome-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!--Stylesheet-->
    <link rel="stylesheet" href="style.css">


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

</head>
<body>
<h3 id="myHeader" > <a style="color:red" href="#" ><==Back</a> 
<caption style=" background: #245f91;color:#eee;text-align: center;padding: 10px;border-radius: 55px;font-weight: bold;" > Organizator : Eurovending SRL Oradea Str Aleea Rogerius Nr.6,Bihor</caption> 
<a style="color:red" href="#">. LogOut</a></h3>

        
      <img src="data:image/jpg;base64,${photoString.base64Image1}" width="250" height="350" />
       
 
 <form action="addphoto.htm" method="post" commandName="id" enctype="multipart/form-data" >
            <label for="category" style = "color:green">CodProdus : <f2 style="color: Tomato"><c:out value="${model.codprodus}"></c:out></f2></label>
    <input type="hidden" id="codProdus" name="codProdus" value="${model.codprodus }" placeholder="${model.codprodus }">
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
            <input type="submit" />
        </form>
 
  
</body>
</html>