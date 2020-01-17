$(function() {
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All products':
		$('#listProducts').addClass('active');
		break;
	case 'Product Management':
		$('#manageProducts').addClass('active');
		break;	
	default:
		if (menu == 'Home') break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}

	var $table = $('#productListTable');
	
	if($table.length) {
		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
		}
		$table.DataTable({
			lengthMenu : [[3, 5, 10, -1], ['3 Records', '5 Records', '10 Records', 'ALL']],
			pageLength : 5,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [

						{
							data: 'code',
							bSortable: false,
							mRender: function(data, type, row) {
								return '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="dataTableImg"/>';
							}
						},
						{
							data: 'name'
						},
						{
							data: 'brand'
						},
						{
							data: 'unitPrice',
							mRender: function(data, type, row) {
								return '&#36; ' + data
							}
						},
						{
							data: 'quantity',
							mRender: function(data, type, row) {
								if (data < 1) {
									return '<span style="color : red">Out of stock!</span>';
								}
								return data;
							}
						},
						{
							data: 'id',
							bSortable: false,
							mRender: function(data, type, row) {
								var str = '';
								str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-info"><i class="fas fa-eye"></i></a>&#160';
								if (row.quantity < 1) {
									str += '<a href="javascript:void(0)" class="btn btn-success disabled"><i class="fas fa-cart-plus"></i></a>';
								} else {
									str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><i class="fas fa-cart-plus"></i></a>';
								}
								return str;
							}
						},
					  ]
		})
	}
	
	$alert = $('.alert');
		if($alert.length) {
			setTimeout(function() {
		    	$alert.fadeOut('slow');
			   }, 3000);		
		}
	//---------------------------------
		
		// list of all products for admin
		var $productsTable = $('#adminProductsTable');
		
		
		if($productsTable.length) {
			
			var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
			console.log(jsonUrl);
			
			$productsTable.DataTable({
						lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
						pageLength : 30,
						ajax : {
							url : jsonUrl,
							dataSrc : ''
						},
						columns : [		
						           	{data: 'id'},
						           	{data: 'code',
						           	 bSortable: false,
						           		mRender: function(data,type,row) {
						           			return '<img class="adminDataTableImg" src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';					           			
						           		}
						           	},
						           	{
										data : 'name'
									},
									{
										data : 'brand'
									},
									{
										data : 'quantity',
										mRender : function(data, type, row) {

											if (data < 1) {
												return '<span style="color:red">Out of Stock!</span>';
											}

											return data;

										}
									},
									{
										data : 'unitPrice',
										mRender : function(data, type, row) {
											return '&#8377; ' + data
										}
									},
									{
										data : 'active',
										bSortable : false,
										mRender : function(data, type, row) {
											var str = '';
											if(data) {											
												str += '<label class="switch"> <input type="checkbox" value="'+row.id+'" checked="checked">  <div class="slider round"> </div></label>';
												
											}else {
												str += '<label class="switch"> <input type="checkbox" value="'+row.id+'">  <div class="slider round"> </div></label>';
											}
											
											return str;
										}
									},
									{
										data : 'id',
										bSortable : false,
										mRender : function(data, type, row) {

											var str = '';
											str += '<a href="'
													+ window.contextRoot
													+ '/manage/'
													+ data
													+ '/product" class="btn btn-success"><i class="fas fa-edit"></i></a> &#160;';

											return str;
										}
									}					           	
						],
						
						
						initComplete: function () {
							var api = this.api();
							api.$('.switch input[type="checkbox"]').on('change' , function() {							
								var dText = (this.checked)? 'You want to activate the Product?': 'You want to de-activate the Product?';
								var checked = this.checked;
								var checkbox = $(this);
								debugger;
							    bootbox.confirm({
							    	size: 'medium',
							    	title: 'Product Activation/Deactivation',
							    	message: dText,
							    	callback: function (confirmed) {
								        if (confirmed) {
								            $.ajax({							            	
								            	type: 'GET',
								            	url: window.contextRoot + '/manage/product/'+checkbox.prop('value')+'/activation',
								        		timeout : 100000,
								        		success : function(data) {
								        			bootbox.alert(data);							        										        			
								        		},
								        		error : function(e) {
								        			bootbox.alert('ERROR: '+ e);
								        		}						            	
								            });
								        }
								        else {							        	
								        	checkbox.prop('checked', !checked);
								        }
							    	}
							    });																											
							});
								
						}
					});
		}
})