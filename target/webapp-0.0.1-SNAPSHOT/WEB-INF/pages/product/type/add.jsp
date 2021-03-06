<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/head_common.jsp" %>
	   	<%@ include file="/WEB-INF/pages/common/flatty_head.jsp" %>
	   	<!-- 框架必须引入end -->
	   	<link href="${ctx }/resourece/css/iframe_page.css" rel="stylesheet" type="text/css"/>
	   	
	</head>
	<body class="contrast-blue" >
		<div id='wrapper'>
			<!-- 表格区域 -->
			<div class='row-fluid'>
				<div class='span12 box bordered-box orange-border' style='margin-bottom:0;'>
					<div class='box-header blue-background'>
						<div class='title'>商品类型维护</div>
						<div class='actions'>
							<a href="#" class="btn box-collapse btn-mini btn-link" ><i></i>
							</a>
						</div>
					</div>
					<div class='box-content box-double-padding'>
						<form class="form form-horizontal" id = "form"  action="${ctx}/product/type/saveUpdate" onsubmit="return false;">
							<fieldset>
								<div class="span8 offset1">
									<input type="hidden" name="parentId" id="parentId" value="${(parentId!=null&&parentId!='')?parentId:0}"/>
									<input type="hidden" name="isShow" id="isShow" value="1"/>
									<input type="hidden" name="level" id="level" value="${(parentType!=null&&parentType.level!=null) ? (parentType.level+1) : 0 }"/>
									<input type="hidden" name="hasProducts" id="hasProducts" value="0"/>
									<div class="control-group">
									  <label class="control-label">父类型名称：</label>
									  <input type="text" readonly = "readonly" name="parentName" value="${(parentName!=null&&parentName!='')? parentName :'无' }"/>
									</div>
									<div class="control-group">
									  <label class="control-label" for="typeName">类型名称：</label>
									  <input type="text" class="form-control" id="typeName" placeholder="类型名称" name="typeName">
									</div>
									<div class="control-group">
									  <label class="control-label" for="rank">排序：</label>
									  <input type="number" id="rank" name="rank" value="0">
									</div>
									<div class="center" style="margin-bottom: 0;">
										 <button id="submit-btn" class="btn btn-primary btn-large" onclick="submitForm()">
										 	<i class="icon-save"></i>提交
										 </button>
										 <button class="btn btn-info btn-large" onclick='window.location.href="${ctx}/product/type/grid";'>
										 	<i class=" icon-circle-arrow-left "></i>返回
										 </button>
									</div>
								</div>
							</fieldset>
							
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/flatty_foot.jsp" %>
		<!-- 框架必须引入end -->
		
		<script src="${ctx}/resource/js/form.js" type="text/javascript"></script>
		<script>
			$("#form").on('submit',function(event){
				event.preventDefault();
			})
			function submitForm(){
				if(confirm("确定提交吗？")){
					$("#form").ajaxSubmit({
				        //表单提交成功后的回调
			        	success: function(responseText, statusText, xhr, $form){
				        	var result = $.parseJSON(responseText);
				            alert(result.msg);
			            	if(result.success) {
			            		window.location.href="${ctx}/product/type/grid";
				            } 
			       		 }
			    	}); 
			    }
			}
			 window.onresize=window.parent.resetIframePage1();
		</script>
	</body>
</html>
