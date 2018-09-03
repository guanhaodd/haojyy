package com.gh.app.manage.module.product.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gh.app.manage.module.product.bean.TbProductItem;
import com.gh.app.manage.module.product.bean.TbProductType;
import com.gh.app.manage.module.product.service.ProductItemService;
import com.gh.app.util.dao.impl.BaseDaoImpl;

@Transactional
@Service
public class ProductItemServiceImpl extends BaseDaoImpl<TbProductItem> implements ProductItemService{

	
	@Override
	public List<TbProductItem> getListByParams(TbProductItem params){
		String hql = "from TbProductItem t where t.isDele= '0' ";
		if(StringUtils.isNotEmpty(params.getItemName())){
			hql +=" and t.itemName like '%"+params.getItemName()+"%' ";
		}
		//排序
		hql +=" order by t.isHot,t.isShow,t.rank ";
		return this.find(hql);
	}
	
	/**
	 * 删除商品
	 * @param typeId
	 */
	@Override
	public void delProductItemById(Integer itemId){
		TbProductItem item = this.get(itemId);
		item.setIsDele("1");
		this.update(item);
	}
	
	@Override
	public TbProductItem getNotDeleteItemById(Integer itemId){
		String hql = "from TbProductItem t where t.isDele= '0' and t.id="+itemId;
		List<TbProductItem> items = this.find(hql);
		return items.size()>0 ? items.get(0) :null;
	}
	
	@Override
	public TbProductItem getNotDeleteItemByCatalogId(String catalogId){
		String hql = "from TbProductItem t where t.isDele= '0' and t.catalogId='"+catalogId+"'";
		List<TbProductItem> items = this.find(hql);
		return items.size()>0 ? items.get(0) :null;
	}
}
