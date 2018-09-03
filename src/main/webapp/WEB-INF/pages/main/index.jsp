<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/head_common.jsp" %>
	   	<%@ include file="/WEB-INF/pages/common/flatty_head.jsp" %>
	   	<!-- 框架必须引入end -->
	</head>
	<body class="contrast-blue">
		<header >
			<div class='navbar'>
				<div class='navbar-inner'>
					<div class='container-fluid'>
						<a class='brand' href='#'> <i class='icon-user-md'></i>
							<span class='hidden-phone'><%=AppConfig.APP_TITLE %></span>
						</a> <a class='toggle-nav btn pull-left' href='#'> <i class='icon-reorder'></i>
						</a>
						<ul class='nav pull-right'>
							<li class='dropdown dark user-menu'>
								<a class='dropdown-toggle' data-toggle='dropdown' href='#'> 
									<img alt='${adminuser.realName }' height='23' src='${ctx}/resource/flatty/images/avatar.jpg' width='23' /> 
									<span class='user-name hidden-phone'>${adminuser.realName }</span> <b class='caret'></b>
								</a>
								<ul class='dropdown-menu'>
									<li><a href='user_profile.html'> <i class='icon-user'></i>
											配置文件
									</a></li>
									<li><a href='user_profile.html'> <i class='icon-cog'></i>
											用户设置
									</a></li>
									<li class='divider'></li>
									<li><a href='${ctx }/login/logout'> <i class='icon-signout'></i>
											退出登录
									</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<div id='wrapper'>
			<div id='main-nav-bg'></div>
			<nav class='' id='main-nav'>
				<div class='navigation'>
					<ul class='nav nav-stacked' id="funMenu">
						<li class='active'>
							<a href='${ctx}/main/index'> 
								<i class='icon-home'></i> <span>主页</span>
							</a>
						</li>
					</ul>
				</div>
			</nav>
			<section id='content'>
				<div class='container-fluid'>
					<div class='row-fluid' id='content-wrapper'>
						<div class='span12'>
							<div class='page-header'>
								<h1 class='pull-left'>
									<i class='icon-tags'></i> <span>欢迎来到<%=AppConfig.APP_TITLE %></span>
								</h1>
							</div>
						</div>
						<iframe src="${ctx}/product/type/grid" style="margin-left: 0.5%;" class='span12  bordered-box orange-border iframepage' id="iframepage1"  onLoad="resetIframePage1()" scrolling="no" frameborder="0"></iframe>
					</div>
					
					
				</div>
			</section>
		</div>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/flatty_foot.jsp" %>
		<!-- 框架必须引入end -->
		
		<script type="text/javascript">
			 function iFrameHeight(iframeId) {
		        var ifm= document.getElementById((iframeId?iframeId:"iframepage1"));
		        var subWeb = document.frames ? document.frames[iframeId].document : ifm.contentDocument;
		        if(ifm != null && subWeb != null) {
		            ifm.height = subWeb.body.scrollHeight;
		            ifm.width = subWeb.body.scrollWidth;
		        }
		    }
		    
		    function resetIframePage1(){
		    	iFrameHeight("iframepage1");
		    }
		    
		    function changeFrameHeight(){
			    var ifm= document.getElementById("iframepage1"); 
			    ifm.height=document.documentElement.clientHeight;
			}
			
			window.onresize=function(){  
			     changeFrameHeight();  
			
			} 
		    resetIframePage1();
		</script>
	</body>
</html>
