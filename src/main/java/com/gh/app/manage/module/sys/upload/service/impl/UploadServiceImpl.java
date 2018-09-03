package com.gh.app.manage.module.sys.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gh.app.manage.entity.AppConfig;
import com.gh.app.manage.module.product.bean.TbProductItem;
import com.gh.app.manage.module.product.bean.TbProductType;
import com.gh.app.manage.module.product.service.ProductItemService;
import com.gh.app.manage.module.product.service.ProductTypeService;
import com.gh.app.manage.module.sys.upload.bean.TbUploadFile;
import com.gh.app.manage.module.sys.upload.service.UploadService;
import com.gh.app.util.constants.Constants;
import com.gh.app.util.dao.impl.BaseDaoImpl;

@Transactional
@Service
public class UploadServiceImpl extends BaseDaoImpl<TbUploadFile> implements UploadService{

	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductItemService productItemService;
	
	/**
	 * 根据catalogId 获取集合
	 * 
	 * @param catalogId
	 * @return
	 */
	@Override
	public List<TbUploadFile> getFileListbyCatalogId(String catalogId){
		return this.find("from TbUploadFile t where t.isDele='0' and t.catalogId='"+catalogId+"'");
	}
	
	
	@Override
	public void cleanCache() throws IOException{
		String rootPath = AppConfig.UPLOAD_FILE_BASE_PATH;
		File file = new File(rootPath + File.separator + Constants.UPLOAD_FOLDER);
		File[] tempList = file.listFiles();
		for(int i = 0; i < tempList.length; i++){
			if (tempList[i].isDirectory()) {
				String typeId = tempList[i].getName();
				TbProductType type = this.productTypeService.getNotDeleteTypeById(Integer.valueOf(typeId));
				//先清除已删除的类型
				if(type==null){
					FileUtils.deleteDirectory(tempList[i]);
				}else{
					File itemFile = new File(rootPath + File.separator 
									+ Constants.UPLOAD_FOLDER + File.separator + type.getId()
									+ File.separator + "images");
					File[] itemList = itemFile.listFiles();
					for(int j = 0; j < itemList.length; j++){
						if (itemList[j].isDirectory()) {
							String itemcataId = itemList[j].getName();
							TbProductItem item = this.productItemService.getNotDeleteItemByCatalogId(itemcataId);
							//再清除已删除的商品图片
							if(item==null){
								FileUtils.deleteDirectory(itemList[j]);
							}else{
								//再删除未清除掉的图片
								List<TbUploadFile> itemPics = this.getFileListbyCatalogId(item.getCatalogId());
								List<String> picNames = new ArrayList<String>();
								if(itemPics!=null){
									for(TbUploadFile pic :itemPics){
										picNames.add(pic.getTargetFileName());
									}
									File itemPath = new File(rootPath + File.separator 
											+ Constants.UPLOAD_FOLDER + File.separator + type.getId()
											+ File.separator + "images" + File.separator + item.getCatalogId());
									File[] picList = itemPath.listFiles();
									for(int k = 0; k < picList.length; k++){
										if (picList[k].isFile()) {
											if(picNames.indexOf(picList[k].getName())<0){
												picList[k].delete();
											}
										}
									}
								}else{
									FileUtils.deleteDirectory(itemList[j]);
								}
							}
						}
					}
				}
			}
		}
	}
}
