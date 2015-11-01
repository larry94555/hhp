  var grid;
  var columns = [
  	{id: "day", name: "", field: "day", width: 70, cssClass: "day-column-class column-class", formatter: formatter},
    {id: "duration", name: "Oct 26 - Oct 30", field: "duration", width: 120, cssClass: "std-column-class column-class", formatter: formatter},
    {id: "%", name: "Nov 2 - Nov 6", field: "percentComplete",width: 120, cssClass: "std-column-class column-class", formatter: formatter},
    {id: "start", name: "Nov 9 - Nov 13", field: "start", width: 120, cssClass: "std-column-class column-class", formatter: formatter},
    {id: "finish", name: "Nov 16 - Nov 20", field: "finish", width: 120, cssClass: "std-column-class column-class", formatter: formatter},
    {id: "effort-driven", name: "Nov 23 - Nov 27", field: "effortDriven", width: 120 , cssClass: "std-column-class column-class", formatter: formatter}
  ];
  var options = {
    enableCellNavigation: true,
    enableColumnReorder: false,
    rowHeight: 100,
    
  };
  
  var dayOfWeek = [
  	"<div class='day-div'>M</div>",
  	"<div class='day-div'>T</div>",
  	"<div class='day-div'>W</div>",
  	"<div class='day-div'>Th</div>",
  	"<div class='day-div'>F</div>"
  ];
  
  function genVoteDiv(id) {
  
  	return '\
  		<div class="voting_wrapper" id="' + id + '">\
            <div class="voting_btn">\
                <div class="up_button">&nbsp;</div><span class="up_votes">0</span>\
            </div>\
            <div class="voting_btn">\
                <div class="down_button">&nbsp;</div><span class="down_votes">0</span>\
            </div>\
        </div>\
  	';
  
  }
  
  var week1= [
  	"26",
  	"27",
  	"28",
  	"29",
  	"30"
  ];
  
  var week2= [
  	"2",
  	"3",
  	"4",
  	"5",
  	"6"
  ];
  
  var week3 = [
  	"9",
  	"10",
  	"11",
  	"12",
  	"13"
  ];
  
  var week4 = [
  	"16",
  	"17",
  	"18",
  	"19",
  	"20"	
  ];
  
  var week5 = [
  	"23",
  	"24",
  	"25",
  	"26",
  	"27"
  ];
  
  var sequence=1000;
  
  function formatter(row, cell, value, columnDef, dataContext) {
  		//if (columnDef.id === "day") {
  		//	return value;
  		//}
        //else {
        	sequence++;
        	return value+genVoteDiv(sequence);
        //}
  }
  
  
  $(function () {
    var data = [];
    for (var i = 0; i < 5; i++) {
      data[i] = {
      	day: dayOfWeek[i],
        duration: week1[i],
        percentComplete: week2[i],
        start: week3[i],
        finish: week4[i],
        effortDriven: week5[i]
      };
    }
    grid = new Slick.Grid("#myGrid", data, columns, options);
    
    	//####### on page load, retrive votes for each content
	$.each( $('.voting_wrapper'), function(){
		
		//retrive unique id from this voting_wrapper element
		var unique_id = $(this).attr("id");
		
	});

	
	
	//####### on button click, get user vote and send it to vote_process.php using jQuery $.post().
	$(".voting_wrapper .voting_btn").click(function (e) {
	 	
		//get class name (down_button / up_button) of clicked element
		var clicked_button = $(this).children().attr('class');
		
		//get unique ID from voted parent element
		var unique_id 	= $(this).parent().attr("id");
		var count; 
		var child_unique_id;
		
		if(clicked_button==='down_button') //user disliked the content
		{
			// if it is a day, mark all days.
			if ($('#'+unique_id).parent().is('.day-column-class')) {
				// do this one and others
				$('#'+unique_id).parent().parent().children('.std-column-class').each(function(i){
					child_unique_id = $(this).children(":first").attr("id");
					//alert($(this).children(":first").attr('class'));
					
					if ($('#'+child_unique_id+' .down_button').css('display') != "none") {
						count = 1+parseInt($('#'+child_unique_id+' .down_votes').text());
						//alert("value = " + count);
				
						$('#'+child_unique_id+' .down_votes').text(count);
					}
				
					// turn box red
					$('#'+child_unique_id).parent().css( "background", "pink" );
					$('#'+child_unique_id+' .down_button').css('display','none');
					
					if ($('#'+child_unique_id+' .up_button').css('display') == "none") {
						count = parseInt($('#'+child_unique_id+' .up_votes').text())-1;
						$('#'+child_unique_id+' .up_votes').text(count);
						$('#'+child_unique_id+' .up_button').css('display','block');
						
					}
					
				});
				
				
			}
			else if ($('#'+unique_id+' .up_button').css('display') == "none") {
				count = parseInt($('#'+unique_id+' .up_votes').text())-1;
				$('#'+unique_id+' .up_votes').text(count);
			
				// turn box red
				$('#'+unique_id).parent().css( "background", "transparent" );
				$('#'+unique_id+' .up_button').css('display','block');
				
			}
			else {
				count = 1+parseInt($('#'+unique_id+' .down_votes').text());
				//alert("value = " + count);
			
				$('#'+unique_id+' .down_votes').text(count);
			
				// turn box red
				$('#'+unique_id).parent().css( "background", "pink" );
				$('#'+unique_id+' .down_button').css('display','none');
			}
			

		}
		else if(clicked_button==='up_button') //user liked the content
		{
			// if it is a day, mark all days.
			if ($('#'+unique_id).parent().is('.day-column-class')) {
				//alert($('#'+unique_id).parent().parent().attr('class'));
				$('#'+unique_id).parent().parent().children('.std-column-class').each(function(i){
			
					child_unique_id = $(this).children(":first").attr("id");
					if ($('#'+child_unique_id+' .up_button').css('display') != "none") {
						count = 1+parseInt($('#'+child_unique_id+' .up_votes').text());
				
						//alert("value = " + count);
				
						$('#'+child_unique_id+' .up_votes').text(count);
					}
				
					// turn box red
					$('#'+child_unique_id).parent().css( "background", "aquamarine" );
					$('#'+child_unique_id+' .up_button').css('display','none');
					
					if ($('#'+child_unique_id+' .down_button').css('display') == "none") {
						count = parseInt($('#'+child_unique_id+' .down_votes').text())-1;
						$('#'+child_unique_id+' .down_votes').text(count);
						$('#'+child_unique_id+' .down_button').css('display','block');
						
					}
					
				});
					
			}
		
			else if ($('#'+unique_id+' .down_button').css('display') == "none") {
				count = parseInt($('#'+unique_id+' .down_votes').text())-1;
				$('#'+unique_id+' .down_votes').text(count);
			
				// turn box red
				$('#'+unique_id).parent().css( "background", "transparent" );
				$('#'+unique_id+' .down_button').css('display','block');
				
			}
			else {
				count = 1+parseInt($('#'+unique_id+' .up_votes').text());
				//alert("value = " + count);
				
				$('#'+unique_id+' .up_votes').text(count);
			
				// turn box green
				$('#'+unique_id).parent().css( "background", "aquamarine" );
				$('#'+unique_id+' .up_button').css('display','none');
			}

		}
		});
		
  });