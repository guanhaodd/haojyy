package com.gh.app.manage.module.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gh.app.manage.entity.AppConfig;
import com.gh.app.manage.module.product.bean.TbProductItem;
import com.gh.app.manage.module.product.bean.TbProductType;
import com.gh.app.manage.module.product.service.ProductItemService;
import com.gh.app.manage.module.product.service.ProductTypeService;
import com.gh.app.manage.module.sys.upload.bean.TbUploadFile;
import com.gh.app.manage.module.sys.upload.service.UploadService;
import com.gh.app.util.constants.Constants;
import com.gh.app.util.entities.Entities;
import com.gh.app.util.entities.JsonView;
import com.gh.app.util.service.UtilService;
import com.gh.app.util.tool.charactor.DateUtil;
import com.gh.app.util.tool.io.FileUtil;

/**
 * @author AezGenholmes
 * @Date 2018年8月1日下午11:33:43
 * @Discription  商品控制器
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private UploadService uploadService;
	
	@Resource
	private UtilService utilService;
	
	private String rootPath = AppConfig.UPLOAD_FILE_BASE_PATH;
	
	@RequestMapping("/type/grid")
	public String toProductTypeGrid(){
		return "/product/type/grid";
	}
	
	@RequestMapping("/type/list")
	@ResponseBody
	public List<TbProductType> getTypeList(TbProductType searchParams){
		List<TbProductType> result = new ArrayList<TbProductType>();
		try {
			result = productTypeService.getListByParams(searchParams);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/type/add")
	public String toProductTypeAdd(Model model,Integer parentId){
		if(parentId!=null){
			TbProductType type = productTypeService.get(parentId);
			model.addAttribute("parentType",type);
			model.addAttribute("parentName",type.getTypeName());
		}
		model.addAttribute("parentId",parentId);
		return "/product/type/add";
	}
	
	@RequestMapping("/type/edit")
	public String toProductTypeEdit(Model model,Integer typeId){
		if(typeId!=null){
			TbProductType type = productTypeService.get(typeId);
			model.addAttribute("type",type);
		}else return "error";
		return "/product/type/edit";
	}
	
	@RequestMapping("/type/saveUpdate")
	@ResponseBody
	public JsonView saveOrUpdateProductType(TbProductType type){
		JsonView jv = new JsonView();
		try {
			if(type.getId()!=null){
				productTypeService.updateType(type);
			}else{
				productTypeService.save(type);
			}
			jv.setSuccess(true);
			jv.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			jv.setSuccess(false);
			jv.setMsg("保存失败");
		} 
		jv.setObj(type);
		return jv;
	} 
	
	@RequestMapping("/type/del")
	@ResponseBody
	public JsonView delProductType(Integer typeId){
		JsonView jv = new JsonView();
		if(typeId!=null){
			try {
				productTypeService.delProductTypeById(typeId);
				jv.setSuccess(true);
				jv.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
				jv.setSuccess(false);
				jv.setMsg("操作失败");
			} 
			jv.setObj(typeId);
		}
		return jv;
	} 
	
	@RequestMapping("/type/changeShow")
	@ResponseBody
	public JsonView changeTypeShow(Integer typeId){
		JsonView jv = new JsonView();
		if(typeId!=null){
			try {
				TbProductType type = productTypeService.get(typeId);
				if(type.getIsShow()==1){
					this.productTypeService.disableProductTypeById(typeId);
				}else{
					type.setIsShow(1);
					this.productTypeService.update(type);
				}
				jv.setSuccess(true);
				jv.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
				jv.setSuccess(false);
				jv.setMsg("操作失败");
			} 
			jv.setObj(typeId);
		}
		return jv;
	} 
	
	/**************************************/
	
	@RequestMapping("/item/grid")
	public String toProductItemGrid(){
		return "/product/item/grid";
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public List<TbProductItem> getItemList(TbProductItem searchParams){
		List<TbProductItem> result = new ArrayList<TbProductItem>();
		try {
			result = productItemService.getListByParams(searchParams);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/item/add")
	public String toProductItemAdd(Model model,Integer typeId){
		if(typeId!=null){
			TbProductType type = productTypeService.get(typeId);
			model.addAttribute("type",type);
		}
		return "/product/item/add";
	}
	
	@RequestMapping("/item/edit")
	public String toProductItemEdit(Model model,Integer itemId){
		if(itemId!=null){
			TbProductItem item = productItemService.get(itemId);
			model.addAttribute("item",item);
		}else return "error";
		return "/product/item/edit";
	}
	
	@RequestMapping("/item/files")
	@ResponseBody
	public List<TbUploadFile> getItemFileList(Model model, String catalogId){
		List<TbUploadFile> fileList = this.uploadService.getFileListbyCatalogId(catalogId);
		return fileList;
	}
	
	@RequestMapping("/item/filedel")
	public void delItemfle(Model model, String fileId){
		TbUploadFile file = this.uploadService.get(fileId);
		this.deleteUploadFile(file);
	}
	
	@RequestMapping("/item/saveUpdate")
	@ResponseBody
	public JsonView saveOrUpdateProductItem(TbProductItem item){
		JsonView jv = new JsonView();
		try {
			if(item.getId()!=null){
				productItemService.update(item);
			}else{
				productItemService.save(item);
				addTypeHashShow(item);
			}
			jv.setSuccess(true);
			jv.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			jv.setSuccess(false);
			jv.setMsg("保存失败");
		} 
		jv.setObj(item);
		return jv;
	} 
	
	
	@RequestMapping("/item/changeShow")
	@ResponseBody
	public JsonView changeItemShow(Integer itemId){
		JsonView jv = new JsonView();
		if(itemId!=null){
			try {
				TbProductItem item = productItemService.get(itemId);
				if(item.getIsShow()==1){
					item.setIsShow(0);
					this.productItemService.update(item);
					removeTypeHashShow(item);
				}else{
					item.setIsShow(1);
					this.productItemService.update(item);
					addTypeHashShow(item);
				}
				jv.setSuccess(true);
				jv.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
				jv.setSuccess(false);
				jv.setMsg("操作失败");
			} 
			jv.setObj(itemId);
		}
		return jv;
	}
	
	@RequestMapping("/item/del")
	@ResponseBody
	public JsonView delProductItem(Integer itemId){
		JsonView jv = new JsonView();
		if(itemId!=null){
			try {
				productItemService.delProductItemById(itemId);
				
				removeTypeHashShow(this.productItemService.get(itemId));
				jv.setSuccess(true);
				jv.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
				jv.setSuccess(false);
				jv.setMsg("操作失败");
			} 
			jv.setObj(itemId);
		}
		return jv;
	} 
	
	
	//******************************************************
	
	@ResponseBody
	@RequestMapping("/pic/upload")
	public JsonView uploadPicFile(String moduleId, String kind, String catalogId, HttpServletRequest request,
			@RequestParam(value = "fileData") MultipartFile... fileData) {
		
		JsonView jv = new JsonView();
		jv.setSuccess(false);
		if (fileData != null && fileData.length > 0) {
			try {
				for (MultipartFile file:fileData) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("文件长度: " + file.getSize());
						LOG.debug("文件类型: " + file.getContentType());
						LOG.debug("文件名称: " + file.getName());
						LOG.debug("文件原名: " + file.getOriginalFilename());
					}
					String fileName = file.getOriginalFilename();
					String fileType = file.getContentType();
					String targetName = FileUtil.genRandomFileName(fileName);
					String destFolder = "/" + Constants.UPLOAD_FOLDER + "/" + moduleId + "/" + kind + "/" + catalogId + "/";
					FileUtil.createDirectory(rootPath + destFolder);
					File dest = new File(rootPath + destFolder + targetName);
					file.transferTo(dest);
	
					TbUploadFile ufile = new TbUploadFile();
					ufile.setName(fileName);
					ufile.setUploadFileName(fileName);
					ufile.setFileType(fileType);
					ufile.setModule(moduleId);
					ufile.setKind(kind);
					ufile.setCatalogId(catalogId);
					ufile.setTargetFileName(targetName);
					ufile.setFileSize(file.getSize());
					ufile.setPath(destFolder + targetName);
					
					List<TbUploadFile> oldfiles =  this.uploadService.getFileListbyCatalogId(ufile.getCatalogId());
					if(oldfiles!=null && oldfiles.size()>0){
						ufile.setRank(oldfiles.get(oldfiles.size()-1).getRank()+1);
					}else{
						ufile.setRank(0);
					}
					utilService.save(ufile);
					LOG.info("上传文件 : =======" + dest.getAbsolutePath());
					
				}
				jv.setSuccess(true);
				jv.setMsg("上传成功!");
			} catch (IllegalStateException e) {
				LOG.error(e.getMessage());
				jv.setMsg("上传文件失败!");
				return jv;
			} catch (IOException e) {
				LOG.error(e.getMessage());
				jv.setMsg("上传文件失败!");
				return jv;
			}
			
			
		}
		return jv;
	}
	
	/**
	 * 根据catalogId 删除所有附件
	 * 
	 * @param id
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/delete/files")
	public Boolean deleteFileByCatalogId(String catalogId){
		
		List<TbUploadFile> oldfiles= this.uploadService.getFileListbyCatalogId(catalogId);
		if(oldfiles!=null && oldfiles.size()>0){
			for(TbUploadFile file : oldfiles){
				deleteUploadFile(file);
			}
		}
		return true;
	}
	
	/**
	 * 根据ID 删除单个附件文件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/file")
	@ResponseBody
	public Boolean deleteFile(Integer id) {
		if (id != null) {
			TbUploadFile file = utilService.getObjectById(TbUploadFile.class,id);
			if (file != null) {
				deleteUploadFile(file);
			}
		}
		return true;
	}
	
	/**
	 * 删除单个文件
	 * @param file
	 */
	private void deleteUploadFile(TbUploadFile file) {
		File destFile = new File(rootPath + "/" + file.getPath());
		if (destFile.exists()) {
			destFile.delete();
			LOG.info("删除文件 :   " + destFile.getAbsolutePath());
			utilService.delete(file);
		}
	}
	
	/**
	 * 修改类型的拥有产品数 +1
	 * @param item
	 */
	private void addTypeHashShow(TbProductItem item){
		TbProductType type = this.productTypeService.get(item.getTypeId());
		type.setHasProducts(type.getHasProducts()+1);
		this.productTypeService.update(type);
	}
	
	/**
	 * 修改类型的拥有产品数 -1
	 * @param item
	 */
	private void removeTypeHashShow(TbProductItem item){
		TbProductType type = this.productTypeService.get(item.getTypeId());
		type.setHasProducts(type.getHasProducts()-1);
		this.productTypeService.update(type);
	}
	
	
}
