<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/head_common.jsp" %>
	   	<%@ include file="/WEB-INF/pages/common/flatty_head.jsp" %>
	   	<!-- 框架必须引入end -->
	   	<link href="${ctx }/resourece/css/iframe_page.css" rel="stylesheet" type="text/css"/>
	   	
	   	<link href="${ctx}/resource/treegrid/bootstrap-theme.css" rel="stylesheet">
	    <link href="${ctx}/resource/treegrid/bootstrap-table.css" rel="stylesheet">
	    <link href="${ctx}/resource/treegrid/jquery.treegrid.min.css" rel="stylesheet">


	</head>
	<body class="contrast-blue" >
		<div id='wrapper'>
				<!-- 表格区域 -->
				<div class='row-fluid'>
					<div class='span12 box bordered-box orange-border' style='margin-bottom:0;'>
						<div class='box-header purple-background'>
							<div class='title'>信息发布</div>
								<div class='actions'>
									<a href="#" class="btn box-collapse btn-mini btn-link" ><i></i>
									</a>
								</div>
							</div>
							<div class='box-content box-no-padding'>
								<div class='responsive-table'>
									<button class="btn btn-success btn-lg" onclick="publishNow()">
										<i class="icon icon-cloud-upload"></i>
										立即发布
									</button>
									<button class="btn btn-danger btn-lg" onclick="clean()">
										<i class="icon icon-refresh"></i>
										清除缓存
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>	
		</div>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/flatty_foot.jsp" %>
		<!-- 框架必须引入end -->
	    

		<script type="text/javascript">
			function publishNow(){
				if(confirm("确定发布吗")){
	        		$.ajax({
	        			url:"${ctx}/genhtml/gennow",
	        			async:true,
	        			dataType:'json',
	        			success:function(response){
	        				alert(response.msg);
	        				window.location.reload();
	        			}
	        		})
	        	};
			}
			
			
			function clean(){
				if(confirm("确定清除吗")){
	        		$.ajax({
	        			url:"${ctx}/genhtml/clean",
	        			async:true,
	        			dataType:'json',
	        			success:function(response){
	        				alert(response.msg);
	        				window.location.reload();
	        			}
	        		})
	        	};
			}
		</script>
	</body>
</html>
