<%@ include file= "header.jsp" %>

<div class="container"> 
		<p>Books that you have checked out: </p><br>
		<br> 
		<table class="table table-striped">
		
		<thead>
			<tr>
				<th>ISBN #</th>
				<th>Book Title</th>
				<th>Description</th>
			</tr>
		</thead>
		
		<tbody>
			
			<c:forEach var="book" items="${allBooks}">
				<td>
					<c:out value="${ book.isbn }" />
				</td>
				<td>
					<c:out value="${ book.title }" />
				</td>
				<td>
					<c:out value="${ book.returnDate }" />
				</td>
			</c:forEach>
		
		</tbody>
	
	</table><br><br>
	
	<form action="list" method="post">
		<button type="submit"  class="btn btn-success">All Books</button>
	</form>
	
	<form action="checklist" method="post">
		<button type="submit" class="btn btn-success">Checked Out Books</button>
	</form>
	
	
	
</div>

<%@ include file= "footer.jsp" %>