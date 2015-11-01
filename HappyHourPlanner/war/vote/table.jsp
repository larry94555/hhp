<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">

	<title>Tiny editable jQuery Bootstrap spreadsheet from MindMup</title>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="keywords" content="opensource jquery bootstrap editable table spreadsheet" />
    <meta name="description" content="This tiny jQuery bootstrap plugin turns any table into an editable spreadsheet" />
    <link href="../css/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
    <script src=",,/js/prettify.js"></script>
		<link href="../css/edittableindex.css" rel="stylesheet">
    <script src="../js/mindmup-editabletable.js"></script>
    <script src="../js/numeric-input-example.js"></script>
  </head>
  <body>
<div class="container">
  <div class="hero-unit">
	<hr/>
          <table id="mainTable" class="table table-striped">
            <thead><tr><th>Name</th><th>Availability</th><th>Profit</th><th>Fun</th></tr></thead>
            <tbody>
              <tr><td>Name?</td><td>100</td><td>200</td><td>0</td></tr>
            </tbody>
			<tfoot><tr><th><strong>TOTAL</strong></th><th></th><th></th><th></th></tr></thead>
          </table>
          <h2><small>just start typing to edit, or move around with arrow keys or mouse clicks!</small></h2>
</div>
</div>
<script>
  $('#mainTable').editableTableWidget().numericInputExample().find('td:first').focus();
  $('#textAreaEditor').editableTableWidget({editor: $('<textarea>')});
  window.prettyPrint && prettyPrint();
</script>
<div id="fb-root"></div>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-37452180-6', 'github.io');
  ga('send', 'pageview');
</script>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
 </script>

<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
      
  </body>
</html>
