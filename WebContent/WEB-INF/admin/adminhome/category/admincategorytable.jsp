<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Table</title>
 <!-- Font Awesome -->
  <link rel="stylesheet" href="resources2/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="resources2/dist/css/adminlte.min.css">
 <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
   <!-- Body style -->
  <link rel="stylesheet" href="resources2/Comun/body-style.css">

 
  
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


 <!-- NavBar -->
  <jsp:include page="/WEB-INF/admin/adminhome/navbar.jsp" />
  <!-- endNavbar -->

 <!-- SideBar -->
  <jsp:include page="/WEB-INF/admin/adminhome/sidebar.jsp" />
 <!-- endSideBar -->
 
 <!-- Table Category List -->
 <c:if test = "${model.listCategory!=null}"> 
 <!-- AREA CHART -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Area Chart</h3>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                </div>
              </div>
              
              </div>
              <div class="card-body">
                <div class="chart">
   <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  <!-- Content CategoryProduct -->
  <div class="container">
<div class="table-wrapper">
<div class="table-title">
<div class="row">
<div class="col-sm-8"><h2>Lista <b>Categorii</b></h2></div>

<div class="col-sm-4">
<button type="button" class="btn btn-info add-new" data-toggle="modal" data-target="#myModalCategorii" ><i class="fa fa-plus"></i>Add New</button>
<br>
<h4  style="color:red;">${model.mesaj}</h4>
</div>
</div>
</div>
<table class="table table-bordered">
<thead>
<tr>
<th>Id</th>
<th>Denumire Categorie</th>
<th>DataAdaugare</th>
<th>Statut</th>
</tr>
</thead>
<tbody>

<c:forEach var="categorii" items="${model.listCategory}">

<tr>
<td><c:out value="${categorii.id}"></c:out></td>
<td><c:out value="${categorii.nameCategory}"></c:out></td>
<td><c:out value="${categorii.dateOfPublish}"></c:out></td>
<td><c:out value="${categorii.statut}"></c:out></td>
<td>

<a href="<c:url value='editcategory.htm?idCategorie=${categorii.id}'/>"  class="open-AddBookDialog btn btn-primary" title="Edit"  ><i class="material-icons">&#xE254;</i></a>
<a data-toggle="modal" data-id="${categorii.id}"data-id1= "${categorii.nameCategory}" title="Delete" class="open-AddBookDialog btn btn-primary" href="#addBookDialog"><i class="material-icons">&#xE872;</i></a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
  </div>
  </div>
  </div>
  </c:if>
  <!-- End Table CategoryList -->
 
 
 
  <!-- Edit Category -->
  <c:if test = "${model.newCategory!=null}"> 
 <!-- AREA CHART -->
            
              <div class="card-body">
                <div class="chart">
   <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  <!-- Content CategoryProduct -->
  <div class="container">
<div class="table-wrapper">
<div class="table-title">
<div class="row">
<div class="col-sm-8"><h2>Edit <b>Category</b></h2></div>
<div class="col-sm-4">

 <div class="card-tools">
</div>
</div>
</div>
<table class="table table-bordered">
<thead>
<tr>
<th>Id</th>
<th>Denumire Categorie</th>
<th>DataAdaugare</th>
<th>Statut</th>
<th><a href="<c:url value='admin.htm'/>"  class="btn btn-info add-new" title="Close" ><i class="fas fa-times"></i>Close</a></th>
</tr>
</thead>
<tbody>
<!-- End Category list -->

<!-- UpDate Category -->
<tr>
<form:form action="update-category.htm?id=${newCategory.id}" metod="post" commandName="idcategory">
<td><c:out value="${newCategory.id}"></c:out></td>
<td><c:out value="${newCategory.nameCategory}"></c:out>
<input type="text" class="form-control" id="nameCategory" name="nameCategory" size="50">
</td>
<td><c:out value="${newCategory.dateOfPublish}"></c:out></td>
<td><c:out value="${newCategory.statut}"></c:out>
<input list="browsers" name="statut" id="browser" size="30" >
  <datalist id="browsers" >
    <option value="PUBLICAT">
    <option value="FORBIDDEN">
  </datalist>
</td>
<td>
<input type="submit" class="btn btn-info add-new" value="Salveaza"/>
</td>
</form:form>
</tr>
</tbody>
</table>
</div>
</div>
  </div>
  </div>
  </div>
  </c:if>
<!-- end EditCategory -->



<jsp:include page="/WEB-INF/admin/adminhome/category/modaladmin.jsp" />



</body>
</html>