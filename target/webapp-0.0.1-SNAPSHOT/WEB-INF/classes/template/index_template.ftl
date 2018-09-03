<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>昊济药业</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/fontAwesome.css">
        <link rel="stylesheet" href="css/light-box.css">
        <link rel="stylesheet" href="css/owl-carousel.css">
        <link rel="stylesheet" href="css/templatemo-style.css">


        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        
        <style type="text/css">
			<#list top5 as item>
				.Modern-Slider .item-${item.id} .image {
				  background-image: url(${rootUrl}${item.mPic.path});
				}
			</#list>
        </style>
        
    </head>

<body>
 

        <header class="nav-down responsive-nav hidden-lg hidden-md">
            <button type="button" id="nav-toggle" class="navbar-toggle" data-toggle="collapse" data-target="#main-nav">
                <span class="sr-only">目录</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--/.navbar-header-->
            <div id="main-nav" class="collapse navbar-collapse">
                <nav>
                    <ul class="nav navbar-nav">
                        <li><a data-typeid="0" onclick="toType(this)">主页</a></li>
                        <#list typeList as mtype>
						<li><a data-typeid="${mtype.id}" onclick="toType(this)">${mtype.typeName}</a></li>
						</#list>
                        <li><a data-typeid="-1" onclick="toType(this)">联系我们</a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <div class="sidebar-navigation hidde-sm hidden-xs">
            <div class="logo">
                <a data-typeid="0" onclick="toType(this)">昊&nbsp;济&nbsp;<em>药&nbsp;业</em></a>
            </div>
            <nav>
                <ul>
                    <li>
                        <a data-typeid="0" onclick="toType(this)" >
                            <span class="rect"></span>
                            <span class="circle"></span>
                           	 主页
                        </a>
                    </li>
                    <#list typeList as mtype>
                    <li>
                        <a data-typeid="${mtype.id}" onclick="toType(this)">
                            <span class="rect"></span>
                            <span class="circle"></span>
                      		${mtype.typeName}
                        </a>
                    </li>
                    </#list>
                     <li>
                        <a data-typeid="-1" onclick="toType(this)" >
                            <span class="rect"></span>
                            <span class="circle"></span>
                           	 联系我们
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
		<div class="page-content">
			<div class="slider">
	            <div class="Modern-Slider content-section" id="top">
	                <!-- Item -->
	                <#list top5 as item>
					<div class="item item-${item.id}">
	                    <div class="img-fill">
	                    <div class="image"></div>
	                    <div class="info">
	                        <div>
	                          <h1>${item.itemName}</h1>
	                          <p>${item.summary}</p>
	                          <div class="white-button button">
	                              <a onclick="toItem(this)" data-itemid='${item.id}'>阅读详情</a>
	                          </div>
	                        </div>
	                        </div>
	                    </div>
	                </div>
					</#list>
	            </div>
	        </div>
		</div>
 		

    <script src="js/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

    <script src="js/vendor/bootstrap.min.js"></script>
    
    <script src="js/plugins.js"></script>
    <script src="js/main.js"></script>

    <script>
       
    </script>

    

</body>
</html>