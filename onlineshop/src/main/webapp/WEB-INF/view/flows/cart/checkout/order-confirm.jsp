<%@include file="../../shared/flow-header.jsp"%>

<div class="content">
	<div class="container">
		<c:choose>
			<c:when test="${orderDetail.user.email eq actualEmail || actualRole eq 'ADMIN'}">
	
				<div class="alert alert-success mt-3" role="alert">
					<h3 class="text-center">Your Order is Confirmed!!
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							  <span aria-hidden="true">&times;</span>
		 				</button>		
		 			</h3>
				</div>		
			    <div class="row mt-2">
			        <div class="col-12">
			    		<div class="invoice-title">
			    			<h2>Invoice</h2> 
			    			<h3 class="float-right">Order # ${orderDetail.id}</h3>
			    		</div>
			    		<hr>
			    		<div class="row">
			    			<div class="col-6">
			    				<address>
			    				<strong>Billed To:</strong><br>
			    					${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
			    					First address : ${orderDetail.billing.addressLineOne}<br>
			    					Second address :${orderDetail.billing.addressLineTwo}<br>
			    					City : ${orderDetail.billing.city} - ${orderDetail.billing.postalCode}<br>
			    					State : ${orderDetail.billing.state} - ${orderDetail.billing.country}
			    				</address>
			    			</div>
			    			<div class="col-6 text-right">
			    				<address>
			        			<strong>Shipped To:</strong><br>
			    					${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
			    					First address : ${orderDetail.shipping.addressLineOne}<br>
			    					Second address : ${orderDetail.shipping.addressLineTwo}<br>
			    					City : ${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}<br>
			    					State : ${orderDetail.shipping.state} - ${orderDetail.shipping.country}
			    				</address>
			    			</div>
			    		</div>
			    		<div class="row">
			    			<div class="col-6">
			    				<address>
			    					<strong>Payment Method:</strong><br>
			    					Card Payment <br>
			    					${orderDetail.user.email}
			    				</address>
			    			</div>
			    			<div class="col-6 text-right">
			    				<address>
			    					<strong>Order Date:</strong><br>
			    					${orderDetail.orderDate}<br><br>
			    				</address>
			    			</div>
			    		</div>
			    	</div>
			    </div>
			    
			    <div class="row">
			    	<div class="col-12">
			    		<div class="card my-3">
							<div class="card-header">
								<h4>Order summary</h4>
							</div>
							<div class="card-body">		    			
			    				<div class="table-responsive-xl">
			    					<table class="table table-small">
			    						<thead>
			                                <tr>
			        							<td><strong>Item</strong></td>
			        							<td class="text-center"><strong>Price</strong></td>
			        							<td class="text-center"><strong>Quantity</strong></td>
			        							<td class="text-right"><strong>Totals</strong></td>
			                                </tr>
			    						</thead>
			    						<tbody>
			    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
			    							<c:forEach items="${orderDetail.orderItems}" var="orderItem">
				    							<tr>
				    								<td>${orderItem.product.name}</td>
				    								<td class="text-center">&#36; ${orderItem.buyingPrice}</td>
				    								<td class="text-center">${orderItem.productCount}</td>
				    								<td class="text-right">&#36; ${orderItem.total}</td>
				    							</tr>
			    							</c:forEach>
			    						</tbody>
			    					</table>
			    				</div>
			    			</div>
			    		</div>
			    	</div>
			    </div>
			    <div class="text-center mb-3">
			    	<a href="${contextRoot}/show/all/products" class="btn btn-lg btn-warning">Continue Shopping</a>
			    </div>
		    
    		</c:when>
    		<c:otherwise>
    			<h1>This information is not for you!</h1>
    		</c:otherwise>
		</c:choose>
	</div>
</div>

<%@include file="../../shared/flow-footer.jsp"%>