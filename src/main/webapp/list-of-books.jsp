<%@ include file= "header.jsp" %>

<div class="container">

	<h1>Library Book List</h1>
	<br>
	<br>
	
	<fieldset>
		<table class="table table-striped">
			
			<thead>
				<tr>
					<th>ISBN #</th>
					<th>Book Title </th>
					<th>Checkout Status</th>
				</tr>
			</thead>
			
			<tbody>
				
				<c:forEach var="book" items="${bookList}">
				
					<td>
						<c:out value="${ book.isbn }" />
					</td>
					<td>
						<c:out value="${ book.title }" />
					</td>
					<td>
						<fieldset><c:out value="${ book.rented }" /></fieldset>
					</td>
				
				</c:forEach>
			
			</tbody>
		
		</table>
	</fieldset>

</div>

<%@ include file= "footer.jsp" %>