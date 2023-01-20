<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!-- Font Awesome -->
  <link rel="stylesheet" href="resources2/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="resources2/dist/css/adminlte.min.css">
 <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
<!-- Body style -->
  <link rel="stylesheet" href="resources2/Comun/body-style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
 
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
 <!-- modalJSP -->
  <jsp:include page="modalSubCategory.jsp" />
  <!-- endModal -->
  <%@page import="java.io.OutputStream"%>
        
 
 
 <!-- Table SubCategory List -->
 
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
<div class="col-sm-8"><h2>List <b>SubCategory</b></h2></div>
<div class="col-sm-4">
<a href="<c:url value='addsubcategory.htm'/>"  class="btn btn-info add-new" title="Edit"  ><i class="fa fa-plus"></i>Add New</a>
</div>
</div>
</div>
<table class="table table-bordered">
<thead>
<tr>
<th>Id Sub Cat</th>
<th>Denumire Categorie</th>
<th>Denumire SubCategorie</th>
<th>Photo SubCategorie</th>
<th>DataAdaugare</th>
<th>Statut</th>
</tr>
</thead>
<tbody>

<c:forEach var="subCategorii" items="${model.listSubCategory}">

<tr>
<td><c:out value="${subCategorii.id}"></c:out></td>
<td><c:out value="${subCategorii.nameCategory}"></c:out></td>
<td><c:out value="${subCategorii.nameSubCategory}"></c:out></td>
<td style="color: Green">  <img src="data:image/jpg;base64,${subCategorii.photoStringSubCategory}" width="150" height="70" /></td>
<td><c:out value="${subCategorii.dateOfPublish}"></c:out></td>
<td><c:out value="${subCategorii.statut}"></c:out></td>
<td>

<a href="<c:url value='listCategory.htm?idsubCategorii=${subCategorii.id}'/>"  class="open-editSubCat btn btn-primary" title="Edit"  ><i class="material-icons">&#xE254;</i></a>
<a data-toggle="modal" data-id="${subCategorii.id}" data-id1= "${subCategorii.nameSubCategory}" title="Delete" class="open-DeleteSubCat btn btn-primary" href="#DeleteSubCat"><i class="material-icons">&#xE872;</i></a>
</td>
</tr>

</c:forEach>
</tbody>
</table>
</div>
</div>
<div class="col-sm-4">
<a href="<c:url value='addsubcategory.htm'/>"  class="btn btn-info add-new" title="Edit"  ><i class="fa fa-plus"></i>Add New</a>
</div>
  </div>
 
  </div>
  </div>

  <!-- End Table CategoryList -->

</body>
</html>