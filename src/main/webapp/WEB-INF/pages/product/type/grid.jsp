<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/head_common.jsp" %>
	   	<%@ include file="/WEB-INF/pages/common/flatty_head.jsp" %>
	   	<!-- 框架必须引入end -->
	   	<link href="${ctx }/resource/css/iframe_page.css" rel="stylesheet" type="text/css"/>
	   	
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
							<div class='title'>商品类型维护</div>
								<div class='actions'>
									<a class="btn btn-mini btn-success" onclick="toAddType()"><i class="icon icon-plus"></i>
									</a>
									<a href="#" class="btn box-collapse btn-mini btn-link" ><i></i>
									</a>
								</div>
							</div>
							<div class='box-content box-no-padding'>
								<div class='responsive-table'>
									<div class='scrollable-area'>
										<table id="grid-table"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>	
		</div>
		<!-- 框架必须引入start -->
		<%@ include file="/WEB-INF/pages/common/flatty_foot.jsp" %>
		<!-- 框架必须引入end -->
			    <!-- 弹出框 -->
		<!-- 树组件 -->
	    <script type="text/javascript" src="${ctx}/resource/treegrid/bootstrap-table.js"></script>
	    <script type="text/javascript" src="${ctx}/resource/treegrid/bootstrap-table-zh-CN.js"></script>
	    <script type="text/javascript" src="${ctx}/resource/treegrid/bootstrap-table-treegrid.js"></script>
	    <script type="text/javascript" src="${ctx}/resource/treegrid/jquery.treegrid.min.js"></script>
	    

		<script type="text/javascript">
			
	        $(function () {
	            var $table = $("#grid-table");
	            $table.bootstrapTable({
	                url:ctx+'/product/type/list',
	                striped:true,
	                sidePagenation:'server',
	                idField:'id',
	                columns:[
	                    {
	                        field: 'typeName',
                          	title:'类别名称'
	                    },{
	                        field:'isShow',
	                        title:'是否展示',
	                        formatter:function(value,row,index){
                        		if(value){
                        			return "<a class='label label-success' onclick='changeShow("+row.id+","+row.isShow+")'>正常</a>";
                        		}else{
                        			return "<a class='label label-important' onclick='changeShow("+row.id+","+row.isShow+")'>禁用</a>";
	                        	}
	                      	}
	                    },{
	                    	field:'function',
	                    	title:'操作',
	                    	formatter:function(value,row,index){
	                    		result = "<a onclick='toAdd("+row.id+")' class='span2 btn btn-success btn-mini' title='添加商品'><i class='icon-plus'></i></a>";
	                    		result += "<a onclick='toEdit("+row.id+")' class='span2 btn btn-info btn-mini' title='编辑类型'><i class='icon-info'></i></a>";
	                    		result += "<a onclick='toDele("+row.id+")' class='span2 btn btn-danger btn-mini' title='删除类型'><i class='icon-remove'></i></a>";
	                    		return result;
	                    	}
	                    }
	                ],
	                treeShowField: 'typeName',
	                parentIdField: 'parentId',
	                onLoadSuccess: function(data) {
	                    $table.treegrid({
	                        initialState: 'collapsed',//收缩
	                        treeColumn: 1,//指明第几列数据改为树形
	                        expanderExpandedClass: 'icon icon-chevron-down',
	                        expanderCollapsedClass: 'icon icon-chevron-right',
	                        onChange: function() {
	                            $table.bootstrapTable('resetWidth');
	                            $table.bootstrapTable('resetView');
	                            window.parent.resetIframePage1();
	                        },
	                    });
	                     window.parent.resetIframePage1();
	                },
	                onDblClickCell: function (field, value, row, $element) {
			           $element.bind("click",function(){
			           		$($element.closest("tr")).treegrid("toggle")
			           });
			        }
	            });
	        })
	        
	         function toAddType(){
	        	window.location.href = "${ctx}/product/type/add?";
	        }
	        
	        function toAdd(typeId){
	        	window.location.href = "${ctx}/product/item/add?typeId="+typeId;
	        }
	        function toEdit(typeId){
	        	window.location.href = "${ctx}/product/type/edit?typeId="+typeId;
	        }
	        function toDele(typeId){
	        	if(confirm("确定吗")){
	        		$.ajax({
	        			url:"${ctx}/product/type/del?typeId="+typeId,
	        			async:true,
	        			success:function(response){
	        				console.log(response);
	        				window.location.reload();
	        			}
	        		})
	        	};
	        }
	        
	        function changeShow(typeId,isShow){
	        	if(confirm("确定"+(isShow==1?'禁用':'显示')+"吗")){
	        		$.ajax({
	        			url:"${ctx}/product/type/changeShow?typeId="+typeId,
	        			async:true,
	        			success:function(response){
	        				console.log(response);
	        				window.location.reload();
	        			}
	        		})
	        	};
	        }
		</script>
	</body>
</html>
