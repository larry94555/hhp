$.fn.editableTableWidget = function(options) {
	return $(this).each(function() {
		
		var buildDefaultOptions = function() {
			var opts = $.extend({}, $.fn.editableTableWidget.defaultOptions);
			opts.editor = opts.editor.clone();
			return opts;
		};
		var activeOptions = $.extend(buildDefaultOptions(), options);
		
		var active;
		
		var ARROW_LEFT = 37, 
			ARROW_UP = 38, 
			ARROW_RIGHT = 39, 
			ARROW_DOWN = 40, 
			ENTER = 13, 
			ESC = 27, 
			TAB = 9,
			BACKSPACE = 8;
		
		var element = $(this);
		
		var editor = activeOptions.editor.css('position', 'absolute').hide().appendTo(element.parent());
		
		var showEditor = function (select) {
			active = element.find('td:focus');
			if (active.length) {
				editor.val(active.text())
					.show()
					.removeClass('error')
					.offset(active.offset())
					.css(active.css(activeOptions.cloneProperties))
					.width(active.width())
					.height(active.height())
					.focus();
				if (select) {
					editor.select();
				}
			}
		};
		
		var setActiveText = function () {
			var text = editor.val();
			var evt = $.Event('change');
			var originalContent;
			
//			if (active.text() === text || editor.hasClass('error')) {
//				return true;
//			}
			originalContent = active.html();
			active.text(text).trigger(evt, text);
			if (evt.result === false) {
				active.html(originalContent);
			}
		};
		
		var movement = function (element, keycode) {
			if (keycode === ARROW_RIGHT) {
				return element.next('td');
			} else if (keycode === ARROW_LEFT) {
				return element.prev('td');
			} else if (keycode === ARROW_UP) {
				return element.parent().prev().children().eq(element.index());
			} else if (keycode === ARROW_DOWN) {
				return element.parent().next().children().eq(element.index());
			} 
			return [];
		};
		
		//editor.blur(function () {
		//	setActiveText();
		//	editor.hide();
		//})
		editor.keydown(function (e) {
			if (e.which === ENTER || e.which === TAB && e.shiftKey === false) {
				
				setActiveText();
				editor.hide();
				active.focus();
				
				e.preventDefault();
				e.stopPropagation();
				
				if (!editor.hasClass("error")) {
			
					var tabIndex = parseInt($(active).prop('tabindex'))+1;
					var next = $('[tabindex=' + tabIndex +']');
					$(next).focus();
					showEditor(true);
				}
				
				
			} else if (e.shiftKey === true && e.which=== TAB) {
				
				setActiveText();
				editor.hide();
				active.focus();
				
				e.preventDefault();
				e.stopPropagation();
				
				var tabIndex = parseInt($(active).prop('tabindex'))-1;
				if (tabIndex > -1) {
					var next = $('[tabindex=' + tabIndex +']');
					$(next).focus();
					showEditor(true);
				}
				
			} else if (e.which === ESC) {
				editor.val(active.text());
				e.preventDefault();
				e.stopPropagation();
				editor.hide();
				active.focus();

			} else if (this.selectionEnd - this.selectionStart === this.value.length) {
				var possibleMove = movement(active, e.which);
				if (possibleMove.length > 0) {
					editor.removeClass('error');
					editor.hide();
					possibleMove.focus();
					e.preventDefault();
					e.stopPropagation();
				}
			} 
		})
//		.on('input paste', function () {
//			
//		})
		.on('blur',function() {
			var evt = $.Event('validate');
			active.trigger(evt, editor.val());
			if (evt.result === false) {
				
				active = element.find('td:focus');
				active.focus();
				editor.addClass('error');
				editor.show();				
				
				return false;
			
			}
			else {
				setActiveText();
				editor.removeClass("error");
				editor.hide();
				editor.val("");
				
			}

		});
		
		element.on('click dblclick', showEditor);
		
		element.css('cursor', 'pointer');
		
		element.keydown(function (e) {
			var prevent = true,
				possibleMove = movement($(e.target), e.which);
			if (possibleMove.length > 0) {
				possibleMove.focus();
			} else if (e.which === ENTER) {
				showEditor(false);
				
			} else if (e.which === 17 || e.which === 91 || e.which === 93) {
				showEditor(true);
				prevent = false;
			} else if (e.which === BACKSPACE) {
				prevent=true;
			} else {

				prevent = false;
				
			}
			if (prevent) {
				e.stopPropagation();
				e.preventDefault();
			}
		});
		
		element.keypress(function(e) {
			var code = e.charCode;
			
			if (e.charCode < 32 || e.charCode > 127) { return; }
			
			var char = String.fromCharCode(code);
			var td = $(e.target);
			td.focus();
			if (td.length) {
				td.text($.trim(td.text())+char);
			}
			else {
				td.text(char);
			}
			
			// this puts cursor at the end.
			td.focus();
			showEditor(true);
			editor.val('');
			editor.val(td.text());

		});
		
		var tabbables = element.find("td"); //get all tabable elements
	    for(var i=0; i<tabbables.length; i++) { //loop through each element
	        tabbables[i].tabIndex=i;
	    }
		
		
	});
};

$.fn.editableTableWidget.defaultOptions = {
cloneProperties: ['padding', 'padding-top', 'padding-bottom', 'padding-left', 'padding-right',
				  'text-align', 'font', 'font-size', 'font-family', 'font-weight',
				  'border', 'border-top', 'border-bottom', 'border-left', 'border-right'],
editor: $('<input>')
};


