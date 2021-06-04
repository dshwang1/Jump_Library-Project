<%-- <%@ include file= "header.jsp" %> --%>

<div class="container">

	<div class="card-body">
		
		<form action="librarian-updated" method="post">
			<h3>Update profile: </h3>
			<fieldset>
			    <input type="radio" name="update-type" value="username" >
			    <label for="username">Username </label>
			    <input type="radio" name="update-type" value="password">
			    <label for="password">Password</label><br>
	    	</fieldset>
	    	
	    	<br>
	    
			<fieldset class="form-group">
		        <label>Old:</label>
		        <input type="text" name="old-value"/><br>
		        
		        <label>New:</label>
		        <input type="text" name="new-value"/>
	      	</fieldset>
	      	<br>
	      	
		<button type="submit" class="btn btn-success">Update</button>
		</form>
	</div>
</div>

<%-- <%@ include file= "footer.jsp" %> --%>