<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/head_common.jsp" %>
	   	<%@ include file="/WEB-INF/pages/common/flatty_head.jsp" %>
	   	<!-- 框架必须引入end -->
	   	<link href="${ctx }/resourece/css/iframe_page.css" rel="stylesheet" type="text/css"/>
   	  	<!-- 文件上传 -->
   		<link href="${ctx }/resource/uploadfile/fileinput.min.css" rel="stylesheet" type="text/css"/>
   		<!-- 富文本编辑 -->
   		<link rel="stylesheet" type="text/css" href="${ctx }/resource/simditor/simditor.css" />
	</head>
	<body class="contrast-blue" >
		<div id='wrapper'>
			<!-- 表格区域 -->
			<div class='row-fluid'>
				<div class='span12 box bordered-box orange-border' style='margin-bottom:0;'>
					<div class='box-header blue-background'>
						<div class='title'>商品详情维护</div>
						<div class='actions'>
							<a href="#" class="btn box-collapse btn-mini btn-link" ><i></i>
							</a>
						</div>
					</div>
					<div class='box-content box-double-padding'>
						<form class="form form-horizontal" id = "form"  action="${ctx}/product/item/saveUpdate" onsubmit="return false;">
							<fieldset>
								<div class="span8 offset1">
									<input type="hidden" name="id" id="id" value="${item.id}"/>
									<input type="hidden" name="addTime" id="addTime" value="${item.addTime}"/>
									<input type="hidden" name="addIp" id="addIp" value="${item.addIp}"/>
									<input type="hidden" name="version" id="version" value="${item.version}"/>
									<input type="hidden" name="typeId" id="typeId" value="${item.typeId}"/>
									<input type="hidden" name="isShow" id="isShow" value="${item.isShow}"/>
									<input type="hidden" name="catalogId" id="catalogId" value=""/>
									<div class="control-group">
									  <label class="control-label">类型名称：</label>
									  <input type="text" readonly = "readonly" name="typeName" value="${item.typeName}"/>
									</div>
									<div class="control-group">
									  <label class="control-label" for="typeName">商品名称：</label>
									  <input type="text" class="form-control" id="itemName" placeholder="商品名称" name="itemName" value="${item.itemName}">
									</div>
									<div class="control-group">
									  <label class="control-label" for="rank">参考价格：</label>
									  <input type="text" class="form-control" id="price" name="price" value="${item.price}"/>
									</div>
									<div class="control-group">
									  <label class="control-label" for="rank">排序：</label>
									  <input type="number" id="rank" name="rank" value="0">
									</div>
									<div class="control-group">
									  <div class="span12" style="text-align: center">商品简介（用于首页、列表页面）</div>
									  <div class="span12">
									  	<textarea class="form-control" id="summary" name="summary" autofocus>${item.summary }</textarea>
									  </div>
									</div>
									<div class="control-group">
										<div class="span12" style="text-align: center">商品详细介绍（用于详情页面）</div>
										<div class="span12">
										  	<textarea class="form-control" id="discribe" name="discribe" autofocus>${item.discribe }</textarea>
										</div>
									</div>
									<div class="control-group">
										<input id="main_pic" type="file" class="file" multiple  name="fileData">
									</div>
									<div class="center" style="margin-bottom: 0;">
										 <button id="submit-btn" class="btn btn-primary btn-large" onclick="submitForm()">
										 	<i class="icon-save"></i>提交
										 </button>
										 <button class="btn btn-info btn-large" onclick='window.location.href="${ctx}/product/item/grid";'>
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
		
		<script src="${ctx}/resource/js/iframe_page.js" type="text/javascript"></script>
		<!-- 框架必须引入end -->
		
		<script src="${ctx}/resource/js/form.js" type="text/javascript"></script>
		<!-- 文件上传 -->
		<script type="text/javascript" src="${ctx }/resource/uploadfile/fileinput.min.js"></script>		
		<script type="text/javascript" src="${ctx }/resource/uploadfile/fileinput_locale_zh.js"></script>		
		
		<!-- 富文本编辑 -->
		<script type="text/javascript" src="${ctx }/resource/simditor/module.js"></script>
		<script type="text/javascript" src="${ctx }/resource/simditor/hotkeys.js"></script>
		<script type="text/javascript" src="${ctx }/resource/simditor/uploader.js"></script>
		<script type="text/javascript" src="${ctx }/resource/simditor/simditor.js"></script>
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
			            		window.location.href="${ctx}/product/item/grid";
				            } 
			       		 }
			    	}); 
			    }
			}
			 window.onresize=window.parent.resetIframePage1();
			 
			//获取catalogId
			var catalogId = '${item.catalogId}';
			$.ajax({
				url:'${ctx}/upload/generate/catalog',
				success:function(result){
					catalogId = result;
					$("#catalogId").val(catalogId);
				}
			})
			 //初始化fileinput控件（第一次初始化）
			function initFileInput(ctrlName) {    
			    var control = $('#' + ctrlName); 
			    control.fileinput({
			        language: 'zh', //设置语言
			        uploadExtraData: function(previewId, index) {   //额外参数的关键点
	                    var obj = {};
	                    obj.catalogId=catalogId;
	                    obj.kind='images';
	                    obj.moduleId=${item.typeId};
	                    return obj;
	                },
	                uploadUrl: '${ctx}/product/pic/upload', //上传的地址
			        allowedFileTypes : ['jpg', 'png','jpeg'],	//允许文件类型
			        allowedFileExtensions : ['jpg', 'png','jpeg'],//接收的文件后缀
			        showUpload: false, //是否显示上传按钮
			        showCaption: false,//是否显示标题
			        minFileCount:1,	//允许上传最小文件数量
			        maxFileCount:10,	//允许上传最大文件数量
			        elCaptionText:'请上传主图片',	//标题文字
			        browseClass: "btn btn-primary", //按钮样式   
			        previewFileIcon: '<i class="icon icon-file"></i>',
			        browseIcon: '<i class="icon icon-folder-open"></i> &nbsp;',
			        removeIcon: '<i class="icon icon-trash"></i> ',
			        cancelIcon: '<i class="icon icon-ban-circle"></i> ',
			        uploadIcon: '<i class="icon icon-upload"></i> ', 
// 			        initialPreview: initialPreview,	//初始化文件列表
// 			        initialPreviewAsData: true,
// 			        initialPreviewConfig:initialPreviewConfig,
			        fileActionSettings : {
			            removeIcon: '<i class="icon icon-trash text-danger"></i>',
			            removeClass: 'btn btn-xs btn-default',
			            removeTitle: 'Remove file',
			            uploadIcon: '<i class="icon icon-upload text-info"></i>',
			            uploadClass: 'btn btn-xs btn-default',
			            uploadTitle: 'Upload file',
			            indicatorNew: '<i class="icon icon-hand-down text-warning"></i>',
			            indicatorSuccess: '<i class="icon icon-ok-sign file-icon-large text-success"></i>',
			            indicatorError: '<i class="icon icon-exclamation-sign text-danger"></i>',
			            indicatorLoading: '<i class="icon icon-hand-up text-muted"></i>',
			            indicatorNewTitle: '未上传',
			            indicatorSuccessTitle: '已上传',
			            indicatorErrorTitle: '上传失败',
			            indicatorLoadingTitle: '正在上传 ...'
			        },
			    });
			    //选择文件后触发
			   control.on("filebatchselected", function(event, files) {
			   		//父页面刷新iframe高度
					window.parent.resetIframePage1();
			   });
			   //点击右上角清空以后
			   control.on("filecleared",function(event, data, msg){
					//父页面刷新iframe高度
					window.parent.resetIframePage1();
					$.ajax({
				    	url:('${ctx}/product/delete/files?catalogId='+catalogId)
				    })
			   });
			   
// 			   //文件列表初始化
// 			    var filelist = '';
// 		    	$.ajax({
// 			    	url:'${ctx}/product/item/files?catalogId='+catalogId,
// 			    	dataType:'json',
// 			    	success:function(result){
// 			    		filelist = result;
// 			    		initialPreview = [];
// 			    		initialPreviewConfig = [];
// 			    		for(var i = 0 ; i<filelist.length ;i++){
// 			    			var file = filelist[i];
// 			    			var itemStr = 
			    			
			    			
// 			    		}
// 			    	}
// 			    })
			  
			}
			
			//初始化fileinput控件（第一次初始化）
			initFileInput("main_pic");
			
			
			//富文本编辑
			$(function(){
				Simditor.locale = 'zh-CN';
				var editor1 = new Simditor({
					textarea: $('#summary'),
					placeholder: '这里输入文字...',
					toolbar:  ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment'], //工具条都包含哪些内容
					pasteImage: true,//允许粘贴图片
					upload : {
					    url : '${ctx}/product/pic/upload', //文件上传的接口地址
					    params: {
					    	catalodId:catalogId,
					    	kind:'images',
					    	moduleId:'${item.typeId}'
					    }, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
					    fileKey: 'fileData', //服务器端获取文件数据的参数名
					    connectionCount: 3,
					    leaveConfirm: '正在上传文件'
					}
				});
				
				var editor2 = new Simditor({
					textarea: $('#discribe'),
					placeholder: '这里输入文字...',
					toolbar:  ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent', 'alignment'], //工具条都包含哪些内容
					pasteImage: true,//允许粘贴图片
					upload : {
					    url : '${ctx}/product/pic/upload', //文件上传的接口地址
					    params: {
					    	catalodId:catalogId,
					    	kind:'images',
					    	moduleId:'${item.typeId}'
					    }, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
					    fileKey: 'fileData', //服务器端获取文件数据的参数名
					    connectionCount: 3,
					    leaveConfirm: '正在上传文件'
					}
				});
				editor1.on("valuechanged",function(){
					window.parent.resetIframePage1();
				})
				editor2.on("valuechanged",function(){
					window.parent.resetIframePage1();
				})
				editor1.on("focus",function(){
					setTimeout(function(){
						window.parent.resetIframePage1();
					},1000);
				})
				editor2.on("focus",function(){
					setTimeout(function(){
						window.parent.resetIframePage1();
					},1000);
				})
				editor1.on("blur",function(){
					setTimeout(function(){
						window.parent.resetIframePage1();
					},1000);
				})
				editor2.on("blur",function(){
					setTimeout(function(){
						window.parent.resetIframePage1();
					},1000);
				})
			})
			
		</script>
	</body>
</html>
