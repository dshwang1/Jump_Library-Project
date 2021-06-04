<%-- <%@ include file= "header.jsp" %> --%>

<div class="container">

	<h1>Add Book: </h1>
	
	<div class="card-body">
		
		<form action="add" method="post">
		<fieldset>
			<fieldset class="form-group">
	        <label>ISBN:</label>
	        <input type="text" name="isbn"/><br>
	        
	        <label>Title:</label>
	        <input type="text" name="title"/>
	        </fieldset>
	        <br>
	        
			<fieldset class="form-group">
			<label>Description:</label>
	        <input type="text" name="description"/>
	      	</fieldset>
	      	<br>
	      	
		<button type="submit" class="btn btn-success">ADD NEW BOOK</button>
		</fieldset>
		<br>
		</form>
		
		<form action="update-form" method="post">
			<button type="submit" class="btn btn-success">UPDATE</button>
		</form>
	</div>
</div>

<%-- <%@ include file= "footer.jsp" %> --%>