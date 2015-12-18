<!DOCTYPE html>

<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Happy Hour Planner</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <!--[if lte IE 8]><script src="/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="/css/standard.css" />
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/querystep/css/main.css">
        <link rel="stylesheet" href="/css/login.css" />
        <link rel="stylesheet" href="/querystep/css/jquery.steps.css">
        <link rel="stylesheet" href="/css/header.css" />
        <link rel="stylesheet" type="text/css" href="/css/emailpopup.css">
        <link rel="stylesheet" href="/css/slidernav.css" />
        <link href="/css/prettify.css" rel="stylesheet">
        <link href="/css/bootstrap.min.css" rel="stylesheet">
    	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="/css/font-awesome.css" rel="stylesheet">
		<link rel="stylesheet" href="/css/preferences.css" />
		<link rel="stylesheet" href="/css/createInvite.css" />
        <script src="/querystep/lib/modernizr-2.6.2.min.js"></script>
        <script src="/querystep/lib/jquery-1.9.1.min.js"></script>
        <script src="/querystep/lib/jquery.cookie-1.3.1.js"></script>
        <script src="/querystep/lib/jquery.steps.js"></script>
        <script src="/js/slidernav.js"></script>
        <script type="text/javascript" language="javascript" src="/js/verifyEmailAccount.js"></script>     
    	<script src="/js/bootstrap.min.js"></script>
    	<script src="/js/prettify.js"></script>
		<link href="/css/edittableindex.css" rel="stylesheet">
		<script src="/js/contact-input.js"></script>
    	<script src="/js/mindmup-rewrite-editabletable.js"></script>
    	<link href="/css/contact-list.css" rel="stylesheet">	
    	<link rel="stylesheet" href="/css/searchableOptionList.css">
 		<link rel="stylesheet" href="/css/sol.css">
    	<script type="text/javascript" src="/js/sol.js"></script>
    </head>
    <body class="landing">
        
        <jsp:include page="header.jsp" />
        
		<section id="banner">
			<h2><strong>Happy Hour Planner</strong></h2>
			<ul class="actions">
			</ul>
		</section>
		
		 <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->
	
        <div class="content">
            <h1>&nbsp;</h1>

            <div id="wizard">
                <h2>Verify Email Address</h2>
                <section>
                
				<jsp:include page="verifyEmailAccount.jsp" />
                    
                </section>

                <h2>Set Preferences</h2>
                <section>
                
                <jsp:include page="setPreferences.jsp" />

                </section>

                <h2>Create Invite</h2>
                <section>

				<jsp:include page="createInvite.jsp" />
                    
                </section>
                
                <h2>Create Contact List</h2>
                <section>
                
				<jsp:include page="createContactList.jsp" />
                    
                </section>
				
				<h2>Check Out Status</h2>
                <section>
                
                <jsp:include page="checkOutStatus.jsp" />
                
                </section>
                <h2>Save the Date</h2>
                <section>
                    
                <jsp:include page="saveTheDate.jsp" />    
                    
                </section>
            </div>
        </div>
        
        <!-- Scripts -->
		<script src="/js/skel.min.js"></script>
		<script src="/js/util.js"></script>
		<!--[if lte IE 8]><script src="/js/ie/respond.min.js"></script><![endif]-->
		<script src="/js/main.js"></script>
		<script src="/js/login.js"></script>
		<script src="/js/wizard.js"></script>
        
    </body>
</html>