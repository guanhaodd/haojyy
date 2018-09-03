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
							<div class='title'>商品详情维护</div>
							<form class="form-inline">
								<div class="form-group">
									<input type="text" id="itemName" placeholder="商品名称" class="form-control"/>
									<span onclick="search()" class="input-group-addon" style="cursor: pointer;"><i class="icon-search"></i></span>
									<div class='actions'>
										<a href="#" class="btn box-collapse btn-mini btn-link" ><i></i>
										</a>
									</div>
								</div>
							</form>
							
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
	                url:ctx+'/product/item/list',
	                striped:true,
	                sidePagenation:'server',
	                idField:'id',
	                pagination: true, // 在表格底部显示分页组件，默认false
				    pageList: [10, 20], // 设置页面可以显示的数据条数
				    pageSize: 10, // 页面数据条数
				    pageNumber: 1, // 首页页码
				    queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			            return {
			                pageSize: params.limit, // 每页要显示的数据条数
			                startRow: params.offset, // 每页显示数据的开始行号
			                sort: params.sort, // 要排序的字段
			                direction: params.order, // 排序规则
			                itemName: $("#itemName").val() // 额外添加的参数
			            } 
			        },
	                columns:[
	                    {
	                        field: 'itemName',
                          	title:'商品名称'
	                    },{
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
	                    		result ="";
	                    		result += "<a onclick='toEdit("+row.id+")' class='span2 btn btn-info btn-mini' title='编辑商品'><i class='icon-info'></i></a>";
	                    		result += "<a onclick='toDele("+row.id+")' class='span2 btn btn-danger btn-mini' title='删除商品'><i class='icon-remove'></i></a>";
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
	        
	        function toAdd(parentId){
	        	window.location.href = "${ctx}/product/item/add?parentId="+parentId;
	        }
	        function toEdit(itemId){
	        	window.location.href = "${ctx}/product/item/edit?itemId="+itemId;
	        }
	        function toDele(itemId){
	        	if(confirm("确定吗")){
	        		$.ajax({
	        			url:"${ctx}/product/item/del?itemId="+itemId,
	        			async:true,
	        			success:function(response){
	        				console.log(response);
	        				window.location.reload();
	        			}
	        		})
	        	};
	        }
	        
	        function changeShow(itemId,isShow){
	        	if(confirm("确定"+(isShow==1?'禁用':'显示')+"吗")){
	        		$.ajax({
	        			url:"${ctx}/product/item/changeShow?itemId="+itemId,
	        			async:true,
	        			success:function(response){
	        				console.log(response);
	        				window.location.reload();
	        			}
	        		})
	        	};
	        }
	        
	        function search(){
	        	var $table = $("#grid-table");
	            $table.bootstrapTable("refresh",{query:{itemName:$("#itemName").val()}});
	        }
		</script>
	</body>
</html>
