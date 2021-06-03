<div class="container">
​
  <h1>Login As a: </h1>
  
  <div class="card-body">
  
    <input type="radio" name="login-type" value="patron" checked >
    <label for="patron">Patron</label><br>
    <input type="radio" name="login-type" value="librarian">
    <label for="librarian">Librarian</label><br>
        
    <form action="login" method="post">
      <fieldset class="form-group">
            
        <label>Username:</label>
        <input type="text" value="username" name ="username"/>
      </fieldset>
          
      <fieldset class="form-group">
            
        <label>Password:</label>
        <input type="password" value="password" name="password"/>
            
      </fieldset>
          
  ​
      <button type="submit" class="btn btn-success">Login</button>
      
  ​
    </form>
    </div>
</div>