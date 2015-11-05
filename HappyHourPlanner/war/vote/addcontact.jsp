<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="keywords" content="opensource jquery bootstrap editable table spreadsheet" />
    <meta name="description" content="This tiny jQuery bootstrap plugin turns any table into an editable spreadsheet" />
    <link href="/css/prettify.css" rel="stylesheet">
    <link href="/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="/css/font-awesome.css" rel="stylesheet">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/prettify.js"></script>
		<link href="/css/edittableindex.css" rel="stylesheet">
    <script src="/js/mindmup-editabletable.js"></script>
  </head>
  <body>
<div class="container">

          <table id="mainTable" class="table table-striped">
            <thead><tr><th>Name</th><th>Email Address</th></tr></thead>
            <tbody>
              <tr><td>Name</td><td>Email Address</td></tr>
            </tbody>
          </table>
</div>
<script>
  $('#mainTable').editableTableWidget().find('td:first').focus();
  $('#textAreaEditor').editableTableWidget({editor: $('<textarea>')});
  window.prettyPrint && prettyPrint();
</script>
<div id="fb-root"></div>
     
  </body>
</html>
