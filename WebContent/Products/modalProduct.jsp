<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>
<body>




 <!-- Modal adCategory -->
  <div class="modal fade" id="myModalSubCategorii" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">AdNewCategory</h4>
        </div>
        
        <div class="dropdown">
  <button onclick="myFunction()" class="dropbtn">SelectSubCategorie</button>
  <div id="myDropdown" class="dropdown-content">
   <c:forEach var="subcategorie" items="${model.listSubCategory }">
    <a href ="list-subCategory.htm?numeCatParinte=${subcategorie.nameCategory}"><c:out value="${subcategorie.nameCategory}"></c:out></a>
    
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
        
        <div class="modal-body">
         <form:form action="add-category.htm" metod="post" commandName="newCategory">

<div class="form-group">
  <label style = "color:green"><f3 style="font-family:verdana">Category Name : </f3></label>
  <br>
  <input type="text" class="form-control" id="nameCategory" name="nameCategory" size="50">
     </div>
     <br>         
  <label style = "color:green" for="statute"><f3 style="font-family:verdana">Statut : </f3></label>
  <br>
  <input list="browsers1" name="statut" id="browser1" size="30" >
  <datalist id="browsers1" >
    <option value="PUBLICAT">
    <option value="FORBIDDEN">
  </datalist> 
         
         
        <!-- Modal footer -->
        <div class="modal-footer">
        <input type="submit" class="btn btn-info add-new"  value="Save"/>
        </div>
</form:form>
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      </div>
    </div>

  
<!-- Script for modal DeleteProduct -->
 <script type="text/javascript">
$(document).on("click", ".button", function () {
     var myProductId = $(this).data('id');
     $(".modal-body #id").val( myProductId);
     var numeProdus = $(this).data('id1');
     $(".modal-body #numeProdus").val( numeProdus);
});
</script>

  <!-- Modal Delete Product -->
  <div class="modal fade" id="DeleteProduct" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">Delete SubCategorie</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
        <form:form action="delete-product.htm" >
        <input type="hidden" name="id" id="id" value=""/>
        <h3>ProductName</h3>
        <br>
         <input type="text" name="numeProdus" id="numeProdus" value=""/>
         
    </div>
        <!-- Modal footer -->
        <div class="modal-footer">
        <input type="submit"  class="btn btn-info add-new" value="Delete"/>
         </form:form>
          <button type="button" class="btn btn-info add-new" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- End Modal&Script Delete SubCategory -->
  
 
</body>
</html>