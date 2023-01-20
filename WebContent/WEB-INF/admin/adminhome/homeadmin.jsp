<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Eurovending AdminHome</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
 
 
  
</head>
<body class="hold-transition sidebar-mini">
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



<div class="wrapper">
<!-- NavBar -->
  <jsp:include page="navbar.jsp" />
  <!-- endNavbar -->
  <!-- NavBar -->
  <jsp:include page="sidebar.jsp" />
  <!-- endNavbar -->
  <c:if test = "${model.listCategory==null}">
  <jsp:include page="/WEB-INF/admin/adminhome/category/admincharttable.jsp" />
  </c:if>
  <c:if test = "${model.listCategory !=null}">
  <jsp:include page="/WEB-INF/admin/adminhome/category/admincategorytable.jsp" />
  </c:if>
  
   </div>
              
  <!-- /.content-wrapper -->
  
  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.0.5
    </div>
    <strong>Copyright &copy; 2021-2025 <a href="http://europartsvending.ro">Eurovending</a>.</strong> All rights
    reserved.
  </footer>


</body>
</html>
