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
								str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-info"><i class="fas fa-eye"></i>View</a>&#160';
								if (row.quantity < 1) {
									str += '<a href="javascript:void(0)" class="btn btn-success disabled"><i class="fas fa-cart-plus"></i>Add</a>';
								} else {
									str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><i class="fas fa-cart-plus"></i>Add</a>';
								}
								return str;
							}
						},
					  ]
		})
	}
	
})
