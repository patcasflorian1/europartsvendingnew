<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
       <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modal DeleteBanner</title>


</head>
<body>

<!-- Script for modal Delete SubCategory -->
 <script type="text/javascript">
$(document).on("click", ".open-DeleteBanner", function () {
     var mysubCategoriiId = $(this).data('id');
     $(".modal-body #id").val( mysubCategoriiId);
     var bannerName = $(this).data('id1');
     $(".modal-body #bannerName").val( bannerName);
});
</script>

  <!-- Modal Delete SubCategory -->
  <div class="modal fade" id="DeleteBanner" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">Delete Banner</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          
        </div>
        <div class="modal-body">
        <form:form action="delete-banner.htm" metod="post" commandName="deleteBanner">
        <input type="text" name="id" id="id" value=""/>
        <br>
        <f2>Banner Name</f2>
        <br>
         <input type="text"  id="bannerName" value=""/>
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