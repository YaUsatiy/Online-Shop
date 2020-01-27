<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">

	<c:if test="${not empty message}">	
		<div class="mt-3 row">			
			<div class="col-12">			
				<div class="alert alert-success" role="alert">${message}
					 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						  <span aria-hidden="true">&times;</span>
	 				</button>			
 				 </div>	
			</div>
		</div>
	</c:if>
	
	<div class="row">
		<div class="col-12">
			<div class="my-3 card">
				<div class="card-header">
					<h4>Write your own review</h4>
				</div>	
				<div class="card-body">
					<sf:form class="form" modelAttribute="review" action="${contextRoot}/product/${review.productId}/add/review" method="POST">
						<div class="form-row mb-3">
							<label class="col-form-label col-4">Comment</label>
							<div class="col-8">
								<sf:textarea path="comment" class="form-control"
									placeholder="Your comment..." /> 
								<sf:errors path="comment" cssClass="help-block" element="em"/>	
							</div>
						</div>
						
						<div class="form-row mb-3">
							<label class="col-form-label col-4">Rating</label>
							<div class="col-8">
								<sf:input type="number" min="1" max="5" path="rating" class="form-control"
									placeholder="Your rating..." />
								<sf:errors path="rating" cssClass="help-block" element="em"/> 
							</div>
						</div>

						<div class="form-row">
							<div class="offset-4 col-4">
								<input type="submit" name="submit" value="Save" class="btn btn-primary"/>
							</div>
						</div>						
										
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	
	<c:choose>
		<c:when test="${not empty reviews}">	
			<ul class="list-group text-center mt-4">
				<c:forEach items="${reviews}" var="review">
					<li class="list-group-item"><b>${review.rating}</b> - ${review.reviewDate} : ${review.comment}</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<div class="jumbotron my-4">
				<h3 class="text-center">There are no reviews here!</h3>
				<div class="text-center mb-3">
		    		<a href="${contextRoot}/show/all/products" class="btn btn-lg btn-warning">Continue Shopping</a>
		    	</div>
			</div>
		</c:otherwise>
	</c:choose>

</div>