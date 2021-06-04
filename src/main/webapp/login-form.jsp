<div class="container">
     
    <form action="login" method="post">
    
    	<h3>Sign In As: </h3>
    	
    	<fieldset>
		    <input type="radio" name="login-type" value="patron" checked >
		    <label for="patron">Patron</label>
		    <input type="radio" name="login-type" value="librarian">
		    <label for="librarian">Librarian</label><br>
	    </fieldset>
	    
	    <br>
	    
      	<fieldset class="form-group">
	        <label>Username:</label>
	        <input type="text"  name ="username"/><br>
	            
	        <label>Password:</label>
	        <input type="password" name="password"/>
    	</fieldset>

		
	 
 		<button type="submit" class="btn btn-success">Login</button><br>
	
	</form>
</div>