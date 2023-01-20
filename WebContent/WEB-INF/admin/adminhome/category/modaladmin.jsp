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
  <div class="modal fade" id="myModalCategorii" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">AdNewCategory</h4>
        </div>
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

  
<!-- Script for modal Delete Category -->
 <script type="text/javascript">
$(document).on("click", ".open-AddBookDialog", function () {
     var myCategoryId = $(this).data('id');
     $(".modal-body #myCategoryId").val( myCategoryId);
     var myCategoryName = $(this).data('id1');
     $(".modal-body #myCategoryName").val( myCategoryName);
});
</script>

  <!-- Modal Delete Category -->
  <div class="modal fade" id="addBookDialog" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">Delete Categorie</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
        <form:form action="delete-category.htm" metod="post" commandName="deleteCategory">
        <input type="hidden" name="idCategorie" id="myCategoryId" value=""/>
        <h3>CategoryName</h3>
        <br>
         <input type="text" name="nameCategory" id="myCategoryName" value=""/>
         
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
  <!-- End Modal&Script Delete Category -->
  
 
</body>
</html>