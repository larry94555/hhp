$('body').on('click','button#add-contact-button',function() { 
	// read contacts and forward
	var rows = $("#mainTable tr:gt(0)");
	
	var list = new Array();
	
	rows.each(function(index) {
		if ($.trim($(this).text()).length > 0) {
			//alert($(this).text());
			var name = $.trim($(this).children(":first").text());
			var email = $.trim($(this).children(":nth-child(2)").text());
			
			if (name.length > 0 && email.length > 0) {
				//alert("child1: " + child1);
				list.push({'name': name, 'email': email});
			}	

		}
	});
	
	//alert('list.size = ' + list.length);
	if (list.length > 0) {
		// add to contact list and clear out (only add once)
		
		//alert("add");
		
		//var json=[1,2,3,4];
	
		var addToContacts = $.ajax({
			url: "/contacts",
			type: "POST",
			data: {list:JSON.stringify(list)},
			dataType: "json"
		});
		addToContacts.done(function(data) {
			// add entries to contact list
			//document.reload();
			//return;
			//alert("data.html = " + data.html);
			rows.each(function(index) {
				if ($.trim($(this).text()).length > 0) {
					//alert($(this).text());
					var td_name = $(this).children(":first");
					var td_email = $(this).children(":nth-child(2)");
					var name = $.trim(td_name.text());
					var email = $.trim(td_email.text());
					if (name.length > 0 && email.length > 0) {
						
//						var activeTd = $('#mainTable').find('td:focus');
//						
//						alert("activeTd: text = " + $(activeTd).text());
//						
//						if (activeTd == td_name || activeTd == td_email) {
//							var editor = $('#mainTable').parent().children('input');
//							editor.val('');
//							editor.blur();
//						}
												
						//alert("child1: " + child1);
						$(this).children(":first").html("&nbsp;");
						$(this).children(":nth-child(2)").html("&nbsp;");
					}	

				}
			});
			
			
			
			
			$('#slider-contact-list').html(data.html);
			
			$('#contact-continue').css('visibility','visible');
			
			
		});
		
		addToContacts.fail(function(jqXHR, textStatus) {
			// not clear how to handle failures.
		});
	}
});

$('body').on('click','span.remove-indicator',function(e) {
	// remove entry
	//alert("email: " + $(this).parent().parent().children('.contact-list-email').text());
	var removeContact = $.ajax({
		url: "/remove-contact",
		type: "POST",
		data: {email:$(this).parent().parent().children('.contact-list-email').text()},
		dataType: "json"
	});
	
	removeContact.done(function(data) {
		// add entries to contact list
		//document.reload();
		//return;
		//alert("data.html = " + data.html);
		
		$('#slider-contact-list').html(data.html);
	});
	
	removeContact.fail(function(jqXHR, textStatus) {
		// not clear how to handle failures.
	});
	
	e.preventDefault();
	return false;
});

$('body').on('click','a.contact-list-entry',function(e) {
	
	//$(this).parent().children(".contact-list-email").css("display","block");
	
	e.preventDefault();
	return false;
})

$(function () {
	
	var firstTime=true;
	
	var reloadNeeded=false;
	
	var changeStartIndex=null;
	
	function handleChangeToPreferences() {
		
		// make an ajax call to update preferences
		var settings = getSettings();
		var placeMarkers = [];
		
		$('.pref-setting-business-id').each(function() {
			placeMarkers.push({
				id:$(this).attr("id"),
				url:$(this).attr("data-url"),
				address:$(this).attr("data-address"),
				name:$(this).text()
			});
		});
		
		var updatePreferences = $.ajax({
        	url: "/preferences",
    		type: "POST",
    		data: { 
    			settings: JSON.stringify(settings),
    			placeMarkers: JSON.stringify(placeMarkers)
			},
    		dataType: "json"
        });
		
		updatePreferences.done(function(data) {
		});
		
		updatePreferences.fail(function(qXHR,textStatus) {
			
		});		
	}
	
	$('body').on('change','input.place-search-option',handleChangeToSearchOptions);
	
	$('body').on('change','input.pref-setting',handleChangeToPreferences);	
	
	$('body').on('click','a#accept-preferences', function() {
	
		handleChangeToPreferences();
		
		if ($('#wizard').data('options').startIndex == 1) {
			// update wizard
			//alert("startIndex  = " + $("#wizard").data("options").startIndex);
			
			//$("#wizard").data("options").startIndex = 2;
			//alert("startIndex  = " + $("#wizard").data("options").startIndex);
			
			//alert("changing value!");
			$("#wizard").steps("next");
			
		}
		else {
		//alert("before click");
			$('#wizard-t-2').click();
		}
	});
	
	function getDefaultLocationBasedOnIpAddress() {
		
		var getLocation = $.ajax({
			url: "https://freegeoip.net/json/", 
			type: "GET",
			data: {},
			dataType: "json"
		});
		getLocation.done(function(data) {
			
			//var value = "ip: " + data.ip + ", country code: " + data.country_code + ", country: " + data.country_name + ", region code: " 
			//+ data.region_code + ", region: " + data.region_name + ", city: " + data.city + ", zip: " + data.zip_code + ", tz: " + data.time_zone
			//+ ", latitude: " + data.latitude + ", longitude: " + data.longitude + ", metro code: " + data.metro_code;
			//$('#def-lookup-location').html(value)
			
			
			$('#detected-location').html(data.city+','+ data.region_code);
			$('#detected-longitude').val(data.longitude);
			$('#detected-latitude').val(data.latitude);
			
			fillPlaces();
			
			//defaultLocation = value;
			
		});
		
		getLocation.fail(function(jqXHR,textStatus) {
			
			// figure out why it fails.
			//alert("jqXHR = " + jqXHR + ", textStatus = " + textStatus);
			
			
			
		});
	}
	
	$('#detected-location').html('checking...');
	
	function getSettings() {
		
		var allowSuggestions = $('#pref-allow-suggestions').is(':checked');
		var useMinRating = $('#skip-low-ratings').is(':checked');
		var minRating = $.trim($('#min-rating').val());
		var restaurantsOnly = $('#pref-restaurants-only').is(':checked');
		var fullBar = $('#pref-spirits-too').is(':checked');
		var minAvailable = $.trim($('#min-num-available').val());
		var mon = $('#pref-allow-m').is(':checked');
		var tue = $('#pref-allow-t').is(':checked');
		var wed = $('#pref-allow-w').is(':checked');
		var thur = $('#pref-allow-th').is(':checked');
		var fri = $('#pref-allow-f').is(':checked');
		var sat = $('#pref-allow-sa').is(':checked');
		var sun = $('#pref-allow-su').is(':checked');
		
		var availability = "";
		if (mon) availability += "Mo";
		if (tue) availability += "Tu";
		if (wed) availability += "We";
		if (thur) availability += "Th";
		if (fri) availability += "Fr";
		if (sat) availability += "Sa";
		if (sun) availability += "Su";
		
		
		return {
			allowSuggestions: allowSuggestions,
			useMinRating: useMinRating,
			minRating: minRating,
			restaurantsOnly: restaurantsOnly,
			fullBar: fullBar,
			availability: availability,
			minAvailable: minAvailable
		};
	}
	

	
	function handleChangeToSearchOptions() {
		if (reloadNeeded) {
			reloadNeeded=false;
			
			fillPlaces(true);
		}
	}
	
	function fillPlaces(reset) {
		
		if (reset) {
			// handle reset
			//$(".sol-container").remove();
			//$(".sol-selection").remove();
		}
		
		
		// if near is set, use that, otherwise use longitude, latitude
		
		var settings = getSettings();	
		
		var location = $.trim($("#pref-near").text());
		var longitude = "";
		var latitude = "";
		var offset = $('#place-search-offset').val();
		
		if (settings.minRating.length == 0) settings.minRating="0.0";
		var checkValue = parseFloat(settings.minRating);
		if (isNaN(checkValue) || checkValue < 1 || checkValue > 5) settings.minRating="0.0";
		if (settings.useMinRating === false) settings.minRating="0.0";
		
		if (location === "") {
			// use longitude/latitude
			longitude = $.trim($('#detected-longitude').val());
			latitude = $.trim($('#detected-latitude').val());
			location = $.trim($('#detected-location').text());	
		}
		
		// do a look up for
		getPlaceList = $.ajax({
			url: "/place-list",
			type: "POST",
			data:{
				location: location,
				longitude: longitude,
				latitude: latitude,
				category: 'happy hour',
				minRating: settings.minRating,
				minAvailable: settings.minAvailable,
				restaurantsOnly: settings.restaurantsOnly,
				fullBar: settings.fullBar,
				offset: offset
				
			},
			dataType: "json"
		});
		
		getPlaceList.done(function(data){
			
			$('#place-list-select').html(data.html);
			$('#place-list-select').searchableOptionList(
				{
					maxHeight: '250px',
					events: {
						onChange: handleChangeToPreferences
					}
				});
			
			reloadNeeded=true;
			$('#placeListLoadingIndicator').css("display","none");
    
		});		
		
		getPlaceList.fail(function(qXHR,textStatus) {
			
		});
		
	}
	
	function showPosition(position) {
		
      var getAddressFromLatitudeLongitude = $.ajax({
      	url: "https://pelias.mapzen.com/v1/reverse?api_key=search-h-wI3wM&point.lat=" + position.coords.latitude + 
      	"&point.lon=" + position.coords.longitude+"&size=1",
  		type: "GET",
  		data: {},
  		dataType: "json"
      });
      getAddressFromLatitudeLongitude.done(function(data){
      	// get the label
      	value = data.features[0].properties.label;
      	var value2 = data.features[0].properties.locality+", " + data.features[0].properties.region_a;
      	$('#detected-location').html(value2);
      	$('#detected-longitude').val(position.coords.longitude);
			$('#detected-latitude').val(position.coords.latitude);
			fillPlaces();
      	
      });
      getAddressFromLatitudeLongitude.fail(function(qXHR,textStatus) {
      	getDefaultLocationBasedOnIpAddress();
      });
      
	}
	
	function showError(error) {
		
		getDefaultLocationBasedOnIpAddress();
		
	    switch(error.code) {
	        case error.PERMISSION_DENIED:
	            // User denied the request for Geolocation.
	            break;
	        case error.POSITION_UNAVAILABLE:
	            // Location information is unavailable.
	            break;
	        case error.TIMEOUT:
	            // The request to get user location timed out
	            break;
	        case error.UNKNOWN_ERROR:
	            // An unknown error occurred.");
	            break;
	        default:
	        	// Unexpected
	            break;
	    }
	}
	
	function getDefaultLocation() {
	    if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(showPosition,showError);
	        
	    } else { 
	        getDefaultLocationBasedOnIpAddress();
	    }
	}
	
    $("#wizard").steps({
        headerTag: "h2",
        bodyTag: "section",
        enableKeyNavigation: false,
        startIndex: parseInt($('#main-current-state').val()),
        enablePagination: false,
        transitionEffect: "slideLeft",
        stepsOrientation: "vertical",
        onStepChanging: function (event, currentIndex, newIndex) {
        	
        	if (newIndex == 1 && firstTime == true && $('#main-current-state').val() >= 1) {
        		firstTime=false;
        		getDefaultLocation();
        		
        	}
        	return true;
        }
        	
    });
 
    $('#slider').sliderNav({height:'500'});
    $('#mainTable').editableTableWidget().contactInput().find('td:first').focus();
		$('#mainTable td').on('mouseover', function() {
		if ($.trim($(this).text()).length===0 && $(this).prop('tabindex')===0) $(this).focus();
	});

	window.prettyPrint && prettyPrint();
	if ($('#main-current-state').val() > 1) {
		$('#contact-continue').css('visibility','visible');
	}
	
	// handle event where preferences tab is selected for the first time
	
});



