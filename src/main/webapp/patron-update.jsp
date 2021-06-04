<div class="container">
	
	
	<form action="patron-update" method="post">
	<p>What would you like to update: </p>
	<input type="radio" name="update-type" value="name" checked >
    <label for="name">Name </label>
    <input type="radio" name="update-type" value="username">
    <label for="username">Username</label>
    <input type="radio" name="update-type" value="password">
    <label for="password">Password</label><br><br>
    
    <fieldset>
		<label>Old Value: </label>
		<input type="text" name="old-value"><br>
		
		<label>New Value: </label>
		<input type="text" name="new-value">
	</fieldset>
	<br>
	<br>
	
	<button type="submit" class="btn btn-success">Update</button>

	</form>

</div>