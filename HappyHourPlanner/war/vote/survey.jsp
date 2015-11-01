<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Autocomplete - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="../schedule-css/autocomplete.css" />
  <link rel="stylesheet" href="../schedule-css/vote-poll.css"/>
</head>
<body>
 
<div class="ui-widget">
  <label for="names">Name: </label>
  <input id="names" placeholder="Enter name">
</div>

<div id="vote-container">
      <span id="question">Which week is best?</span>
      <div><span>0</span><a href="">Vote</a>This week</div>
      <div><span>0</span><a href="">Vote</a>Next week</div>
      <div><span>0</span><a href="">Vote</a>Week After Next</div>
      <div><span>0</span><a href="">Vote</a>Next time</div>
</div>

<script>
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
</script>
 
 
</body>
</html>