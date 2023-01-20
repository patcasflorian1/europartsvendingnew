<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
  <jsp:include page="modalProduct.jsp" />
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
<div class="col-sm-8"><h2>List <b>Products</b></h2></div>
<div class="col-sm-4">
<a href="<c:url value='selectCategory.htm'/>"  class="btn btn-info add-new" title="Edit"  ><i class="fa fa-plus"></i>Add New</a>
</div>
</div>
</div>
<table class="table table-bordered">
<thead>
<tr>

<th>Denumire Categorie</th>
<th>Denumire SubCategorie</th>
<th>Cod Produs</th>
<th>Nume Produs</th>
<th>DataModificare</th>
<th>Statut</th>
<th>Nota Produs</th>
</tr>
</thead>

<tbody>
<c:forEach var="product" items="${model.productList}">

<tr>

<td><c:out value="${product.category}"></c:out></td>
<td><c:out value="${product.subCategory}"></c:out></td>
<td><c:out value="${product.codProdus}"></c:out></td>
<td><c:out value="${product.numeProdus}"></c:out></td>
<td><c:out value="${product.dataModificare}"></c:out></td>
<td><c:out value="${product.statutProdus}"></c:out></td>
<td><c:out value="${product.notaProdus}"></c:out></td>
<td class="project-actions text-right">
                          <a class="btn btn-primary btn-sm" href="<c:url value='viewProduct.htm?idProduct=${product.id}'/>">
                              <i class="fas fa-folder">
                              </i>
                              View
                          </a>
                          <a class="btn btn-info btn-sm" href="<c:url value='listProductForEdit.htm?idProduct=${product.id}'/>">
                              <i class="fas fa-pencil-alt">
                              </i>
                              Edit
                          </a>
                          
                          <a class="button btn btn-danger btn-sm" data-toggle="modal" data-id="${product.id}" data-id1= "${product.numeProdus}" title="Delete"  href="#DeleteProduct" id="view-source" title="Delete">
                              <i class="fas fa-trash">
                              </i>
                              Delete
                          </a>
                      </td>
</tr>

</c:forEach>
</tbody>
</table>
</div>
</div>
<div class="col-sm-4">
<a href="<c:url value='selectCategory.htm'/>"  class="btn btn-info add-new" title="Edit"  ><i class="fa fa-plus"></i>Add New</a>
</div>
  </div>
 
  </div>
  </div>

  <!-- End Table CategoryList -->

</body>
</html>