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
		<div  class="page-content">
			<section id="blog" class="content-section">
               <div class="section-heading">
                   <h1>商品<br><em>目录</em></h1>
                   <p>${type.typeName} 
                   <br>请尽情挑选，我们将竭诚服务</p>
               </div>
               <div class="section-content">
                   <div class="tabs-content">
                       <div class="wrapper">
                           <section id="first-tab-group" class="tabgroup">
                               <div id="tab1">
                                   <ul>
                                       <li>
                                       	   <#list items as item>
                                           <div class="item">
                                               <img src="${rootUrl}${item.mPic.path}" alt="">
                                               <div class="text-content">
                                                   <h4>${item.itemName}</h4>
                                                   <span>参考价：${item.price}</span>
                                                   <p>简介：${item.summary}</p>
                                                   
                                                   <div class="accent-button button">
                                                       <a onclick="toItem(this)" data-itemid="${item.id}">阅读详情</a>
                                                   </div>
                                               </div>
                                           </div>
                                           </#list>
                                       </li>
                                   </ul>
                               </div>
                           </section> 
                       </div>
                   </div>
               </div>
           </section>
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