/**
 * 主页的菜单操作
 */
(function() {
    $(document).ready(function() {
    	var fun_array = [];
    	//后期通过ajax获取
    	fun_array = [
    		{	
				id:1,
	    		funName:'商品管理',
	    		parentId:0,
	    		parentName:'',
	    		level:0,
	    		rank:0,
	    		icon:'icon-reorder',
	    		url:'',
	    		childNode:[
	    			{
	    				id:2,
	    	    		funName:'商品类型维护',
	    	    		parentId:1,
	    	    		parentName:'商品管理',
	    	    		level:1,
	    	    		rank:0,
	    	    		icon:'',
	    	    		url:'product/type/grid',
	    	    		childNode:[]
	    			},
	    			{
	    				id:3,
	    	    		funName:'商品详情维护',
	    	    		parentId:1,
	    	    		parentName:'商品管理',
	    	    		level:1,
	    	    		rank:1,
	    	    		icon:'',
	    	    		url:'product/item/grid',
	    	    		childNode:[]
	    			}
	    		]
    		},
    		{
				id:4,
	    		funName:'信息发布',
	    		parentId:0,
	    		parentName:'',
	    		level:0,
	    		rank:1,
	    		icon:'icon-cloud-upload',
	    		url:'genhtml/topublish',
	    		childNode:[]
			}
    	];
    	
    	(function initMenu(){
    		var menuDom = $("#funMenu");
    		appendChildNode(menuDom,fun_array);
    	}).call(this);
    	
    	function appendChildNode(fatherDom,fatherArray){
    		for(var i = 0 ; i < fatherArray.length ; i++){
    			var child = fatherArray[i];
    			var child_li = $("<li></li>");
    			//添加链接
    			var fun_url =(child.url!=''?( ctx+"/"+child.url):"#");
				var child_a = new $("<a style='cursor:pointer' data-url='"+fun_url+"'></a>");
				if(fun_url && fun_url!='#'){
					child_a.click(function(event){
						var fun_src = $(event.currentTarget).data('url');
						var ifm= document.getElementById("iframepage1"); 
						if(fun_src){
							ifm.src=fun_src;
						}
					})
				}
				//添加图标和名称
				child_a.append($("<i class='"+(child.icon!=''?child.icon:'icon-caret-right')+"'></i>"));
				child_a.append($("<span>"+child.funName+"</span>"));
				child_li.append(child_a);
				if(child.childNode!=null && child.childNode.length > 0){
					child_a.addClass('dropdown-collapse');
					child_a.append($("<i class='icon-angle-down angle-down'></i>"));
					var child_ul = $("<ul class='nav nav-stacked'></ul>");
					appendChildNode(child_ul,child.childNode);
					child_li.append(child_ul);
				};
    			fatherDom.append(child_li);
    		}
    	}
    	
    	function resetIframeUrl(iframe,url){
    		
    	}
    	
    	/**
    	 * 向DOM添加子节点
    	 * */
//    	function appendChildNode(father_array,num,fatherDom){
//    		fatherDom
//    		cn = father_array[num].childNode;
//    		if(cn!=null && cn.length>0){
//    			if(num!=null && num==0){
//    				fatherDom.find('a:first').append($("<i class='icon-angle-down angle-down'></i>"));
//    			}
//    			father_li = fatherDom.find("li:first");
//    			alert(father_li);
//    			var child_ul = $("<ul></ul>");
//    			child_ul.addClass("nav nav-stacked");
//    			for(var i=0 ; i<cn.length;i++){
//    				var child_li = $("<li></li>");
//    				//添加链接
//    				var child_a = $("<a class='dropdown-collapse' href='"+cn[i].url!=''?cn[i].url:"#"+"'>");
//    				//添加图标和名称
//    				child_a.append($("<i class='"+cn[i].icon+"'></i>")).append($("<span>"+cn[i].funName+"</span>"));
//        			appendChildNode(cn,i,child_ul);
//        			child_li.append(child_a);
//        			child_ul.append(child_li);
//    			}
//    			father_li.append(child_ul);
//    		}else return;
//    	}
    })
}).call(this);

