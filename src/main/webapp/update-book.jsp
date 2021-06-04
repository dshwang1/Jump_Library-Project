<%-- <%@ include file= "header.jsp" %> --%>

<div class="container">

	<div class="card-body">
		<%-- <c:if test="${ isbn != null }">
			<form action="update" method="post">
		</c:if>

		<c:if test="${ isbn == null }">
			<form action="insert" method="post">
		</c:if> --%>
		
		<form action="update" method="post">
			<h3>Update book information: </h3>
			<fieldset class="form-group">
	        
	        <label>ISBN #:</label>
	        <input type="text" name="isbn"/><br>
	        
	        <label>New Title:</label>
	        <input type="text" name="title"/><br>
	
			<label>New Description:</label>
	        <input type="text" name="description"/><br>
	            
	      	</fieldset>
		<button type="submit" class="btn btn-success">Update</button>
		</form>
		</div>
</div>

<%-- <%@ include file= "footer.jsp" %> --%>