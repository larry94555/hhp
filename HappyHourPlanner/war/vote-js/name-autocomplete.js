$(document).ready(function() {
    $("#vote-container div a").click(function() {
        $(this).parent().animate({
           width: '+=50px'
        }, 500);

        $(this).prev().html(parseInt($(this).prev().html()) + 1);
        return false;
    });
    
    var availableTags = [
      	"Alex",
      	"Corrina",
      	"Dennis",
      	"Doug",
      	"Larry",
      	"Nicole",
      	"Pavel",
      	"Ryan",
      	"Tom"
    ];
    $( "#names" ).autocomplete({
      	source: availableTags
    });
    
});