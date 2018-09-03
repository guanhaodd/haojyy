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

 		<div class="page-content">
			<section id="featured" class="content-section">
                <div class="section-heading">
                    <h1>联系<br><em>我们</em></h1>
                    <p>感谢您的浏览
					<br>我们努力将产品服务做得更好
                    <br>您的点击与传播就是我们的动力！</p>
                </div>
                <div class="section-content">
                    <div class="">
                        <div class="item">
                            <div class="text-content">
                                <h4>我们的联系电话：</h4>
                                <h5>管先生：18817105399</h5>
                                <h4>我们的微信号码：</h4>
                                <h5>18817105399</h5>
                                <h4>我们的服务QQ号：</h4>
                                <h5>274153356</h5>
                                <h4>感谢您愿意花费您宝贵的时间，浏览我们的网站
                                	<br/>
                                	如果有购买意向请添加我们的微信，扫码添加：
                                </h4>
                            </div>
                            
                            <div class="image" style="width:50%;height:50%">
                                <img src="img/weixin.jpg" alt="">
                            </div>
                            
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