<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/head_common.jsp" %>
	   	<%@ include file="/WEB-INF/pages/common/flatty_head.jsp" %>
	   	<!-- 框架必须引入end -->
	</head>
	<body class="contrast-blue sign-in contrast-background">
		<div id="wrapper">
		    <div class="application">
		        <div class="application-content">
		            <a href="${ctx}/login/to_login"><div class="icon-user-md"></div>
		                <span>医药销售平台后台管理入口</span>
		            </a>
		        </div>
		    </div>
		    <div class="controls">
		        <div class="caret"></div>
		        <div class="form-wrapper">
		            <h1 class="text-center">登录</h1>
		            <form id="login_form" accept-charset="UTF-8" >
		            	<div style="margin:0;padding:0;display:inline">
		            		<input name="utf8" type="hidden" value="&#x2713;" />
		            	</div>
		                <div class="row-fluid">
		                    <div class="span12 icon-over-input">
		                        <input class="span12" id="userName" name="userName" placeholder="用户名" type="text" value="" />
		                        <i class="icon-user muted"></i>
		                    </div>
		                </div>
		                <div class="row-fluid">
		                    <div class="span12 icon-over-input">
		                        <input class="span12" id="password" name="password" placeholder="密码" type="password" value="" />
		                        <i class="icon-lock muted"></i>
		                    </div>
		                </div>
		                <div class="row-fluid">
		                    <div class="span3 icon-over-input">
		                        <label class="checkbox" for="remember_me"><input id="remember_me" name="remember_me" type="checkbox" value="1" />
				                    	记住我
				                </label>
		                    </div>
		                    <div class="span9">
		                        <label class="text-red">${tip}</label>
		                    </div>
		                </div>
		                
		                <button class="btn btn-block" name="button" type="submit">登录</button>
		            </form>
		            <div class="text-center">
		                <hr class="hr-normal" />
		                <a href="#">忘记了您的密码?</a>
		            </div>
		        </div>
		    </div>
		    <div class="login-action text-center">
		        <a href="#"><i class="icon-user"></i>
		            新用户?
		            <strong>注册</strong>
		        </a>
		    </div>
		</div>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/flatty_foot.jsp" %>
		<!-- 框架必须引入end -->
		<!-- myJS -->
		<script type="text/javascript" src="${ctx }/resource/js/Base64.js"></script>
		<script>
			(function(){
				$("#login_form").submit(function(e){
					e.preventDefault();
					window.location.href = "${ctx}/login/logon?userName="+Base64.encode($("#userName").val())+"&password="+Base64.encode($("#password").val());
				})
			}).call(this)
		</script>
	</body>
</html>
