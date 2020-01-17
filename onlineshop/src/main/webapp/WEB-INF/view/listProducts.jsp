<div class="container">
	<div class="row">
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>	
		<div class="col-md-9">
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts eq true}">
						<script>
							window.categoryId = '';
						</script>
						<ol class="mt-3 breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All products</li>
						</ol>
					</c:if>
					<c:if test="${userClickCategoryProducts eq true}">
					<script>
							window.categoryId = '${category.id}';
						</script>
						<ol class="mt-3 breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">Category</li>
							<li class="breadcrumb-item active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
		
			<div class="row">
				<div class="col-12">
					<table id="productListTable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty. Available</th>
								<th></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>