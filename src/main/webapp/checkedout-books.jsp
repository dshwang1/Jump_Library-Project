<%@ include file= "header.jsp" %>

<div class="container">

	<h1>Books That Have Been Checked Out By You: </h1>
	<br>
	<br>
	
	<table class="table table-striped">
		
		<thead>
			<tr>
				<th>ISBN #</th>
				<th>Book Title</th>
				<th>Return Status</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="book" items="${checkedoutBooks}">
			
			<tr>
				<td>
					<c:out value="${ book.isbn }" />
				</td>
				<td>
					<c:out value="${ book.title }" />
				</td> 
				<td>
					<c:out value="${ book.descr }" />
				</td>
			</tr>
				
			</c:forEach>
		</tbody>
	
	</table>

</div>

<%@ include file= "footer.jsp" %>