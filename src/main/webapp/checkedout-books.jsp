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
			<c:forEach var="book" items="${allBooks}">
				<td>
					<c:out value="${ book_checkout.isbn }" />
				<%-- </td>
				<td>
					<c:out value="${ book_checkout.book.title }" />
				</td> --%>
				<td>
					<c:out value="${ book_checkout.checkedout }" />
				</td>
			</c:forEach>
		</tbody>
	
	</table>

</div>

<%@ include file= "footer.jsp" %>