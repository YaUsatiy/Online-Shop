<div class="container mt-4">
	
	<c:choose>
		<c:when test="${not empty orders}">
			<ul class="list-group text-center">
				<c:forEach items="${orders}" var="order">
					<li class="list-group-item">${order.orderDate} : ${order.orderTotal} &#36; <a href="${contextRoot}/show/invoice/${order.id}" class="btn btn-success">Show invoice</a></li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<div class="jumbotron my-4">
				<h3 class="text-center">You haven't got orders yet!</h3>
				<div class="text-center mb-3">
		    		<a href="${contextRoot}/show/all/products" class="btn btn-lg btn-warning">Continue Shopping</a>
		    	</div>
			</div>
		</c:otherwise>
	</c:choose>

</div>