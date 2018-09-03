package com.gh.app.manage.module.template.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gh.app.manage.entity.AppConfig;
import com.gh.app.manage.module.product.bean.TbProductItem;
import com.gh.app.manage.module.product.bean.TbProductType;
import com.gh.app.manage.module.product.controller.ProductController;
import com.gh.app.manage.module.product.service.ProductItemService;
import com.gh.app.manage.module.product.service.ProductTypeService;
import com.gh.app.manage.module.sys.upload.bean.TbUploadFile;
import com.gh.app.manage.module.sys.upload.service.UploadService;
import com.gh.app.util.constants.Constants;
import com.gh.app.util.entities.JsonView;
import com.gh.app.util.service.UtilService;
import com.gh.app.util.tool.io.Freemarker;

/**
 * @author AezGenholmes
 * @Date 2018年8月4日下午3:41:38
 * @Discription  信息发布 生成静态页面控制器
 */

@Controller
@RequestMapping("/genhtml")
public class GenerateHtmlController {
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private UploadService uploadService;
	
	@Resource
	private UtilService utilService;
	
	private String rootUrl = AppConfig.APP_FILE_BASE_URL;
	@RequestMapping("/topublish")
	public String toPublish(){
		return "/publish/publish";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/gennow")
	@ResponseBody
	public JsonView genNow(){
		JsonView jv = new JsonView();
		jv.setSuccess(false);
		jv.setMsg("发布失败！找我帮忙！");
		try {
			Map<String,Object> root = new HashMap<String,Object>();		//创建数据模型
			String filePath = "html/";						//存放路径
			//生成首页
			List<TbProductType> typeList = this.productTypeService.find(" from TbProductType t where t.isDele='0' and t.isShow='1' "
					+ " and t.hasProducts>0  order by t.level,t.rank");
			List<TbProductItem> allItem = this.productItemService.find(" from TbProductItem t where t.isDele='0' and t.isShow='1' "
					+ " order by t.rank");
			List<TbProductItem> top5Item = allItem.subList(0, (allItem.size()>5 ? 4: allItem.size()));
			for(TbProductItem item : top5Item){
				List<TbUploadFile> pics = this.utilService.getHqlList(" from TbUploadFile t where t.isDele='0' and"
						+ " t.catalogId='"+item.getCatalogId()+"' order by t.rank");
				item.setmPic( (pics!=null&& pics.size()>0)?pics.get(0) : null);
			}
			root.put("typeList", typeList);
			root.put("top5", top5Item);
			root.put("rootUrl",rootUrl);
			Freemarker.printFile("index_template.ftl", root, "index.html", filePath);
			
			//生成类型页面
			for(TbProductType type: typeList){
				root.put("type", type);
				List<TbProductItem> items = this.productItemService.find(" from TbProductItem t where t.isDele='0' and t.isShow='1' "
						+ "and t.typeId = "+type.getId()+" order by t.rank");
				for(TbProductItem item:items){
					List<TbUploadFile> pics = this.utilService.getHqlList(" from TbUploadFile t where t.isDele='0' and"
							+ " t.catalogId='"+item.getCatalogId()+"' order by t.rank");
					item.setmPic( (pics!=null&& pics.size()>0)?pics.get(0) : null);
					item.setFiles(pics);
					//生成商品页面
					root.put("item", item);
					Freemarker.printFile("item_template.ftl", root, "item_"+item.getId()+".html", filePath);
				}
				root.put("items", items);
				Freemarker.printFile("type_template.ftl", root, "type_"+type.getId()+".html", filePath);
			}
			//联系我们页面
			Freemarker.printFile("contactus_template.ftl", root, "contactus.html", filePath);
			jv.setSuccess(true);
			jv.setMsg("发布成功!快去看看吧!");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		
		return jv;
	}
	
	
	@RequestMapping("/clean")
	@ResponseBody
	public JsonView cleanFiles(){
		JsonView jv = new JsonView();
		jv.setSuccess(false);
		jv.setMsg("失败！找我帮忙！");
		try {
			this.uploadService.cleanCache();
			jv.setSuccess(true);
			jv.setMsg("缓存清除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return jv;
	}
	
}
