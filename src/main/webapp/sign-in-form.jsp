<div class="container">
​
  <h1>Create Account: </h1>
  
  <div class="card-body">
  
  	<h3>What kind of account would you like to create: </h3>
    <input type="radio" name="login-type" value="patron" checked >
    <label for="patron">Patron</label><br>
    <input type="radio" name="login-type" value="librarian">
    <label for="librarian">Librarian</label><br><br> <br>
        
    <form action="login" method="post">
      <fieldset class="form-group">
            
        <label>Fist Name:</label>
        <input type="text" name="first_name" />
      
        <label>Last Name:</label>
        <input type="text" name="last_name"/>
        
        <label>UserName:</label>
        <input type="text" name="username"/>
        
        <label>Password:</label>
        <input type="password" name="password"/>
            
      </fieldset>
          
  ​
      <button type="submit" class="btn btn-success">Sign Up</button>
  ​
    </form>
    </div>
</div>