<div class="container">

  <h3>Create Account: </h3>
  
  <div class="card-body">
  
  	<p>What kind of account would you like to create: </p>
    <input type="radio" name="login-type" value="patron" checked >
    <label for="patron">Patron</label>
    <input type="radio" name="login-type" value="librarian">
    <label for="librarian">Librarian</label><br><br> 
        
    <form action="signing" method="post">
      <fieldset class="form-group">
            
        <label>Fist Name:</label>

        <input type="text" name="first_name" /><br>
      
        <label>Last Name:</label>
        <input type="text" name="last_name"/><br>
        
        <label>UserName:</label>
        <input type="text" name="username"/><br>
        
        <label>Password:</label>
        <input type="password" name="password"/><br>

            
      </fieldset>
          
  
      <button type="submit" class="btn btn-success">Create Account</button>
  
    </form>
    </div>
</div>