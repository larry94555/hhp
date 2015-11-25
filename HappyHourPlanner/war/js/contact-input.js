/* global $ */
/* this is used for validation and change events */

$.fn.contactInput = function () {
	
	var element = $(this);
	
	element.find('td').on('change', function (evt) {
		
		var cell = $(this),
			column = cell.index(),
			total = 0;
		if (column === 0) {
			return;
		}
		element.find('tbody tr').each(function () {
			var row = $(this);
		});
		

	}).on('validate', function (evt, value) {
		var cell = $(this),
			column = cell.index();
		
		if (cell.hasClass('contact-email')) {
			
			// validate email
			return $.trim(value)==='' || validateEmail(value);
		}
		
		
	});
	return this;
	
	
};