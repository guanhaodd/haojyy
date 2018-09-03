package com.gh.app.manage.module.product.service;

import java.util.List;

import com.gh.app.manage.module.product.bean.TbProductItem;
import com.gh.app.manage.module.product.bean.TbProductType;
import com.gh.app.util.dao.BaseDao;

public interface ProductItemService extends BaseDao<TbProductItem>{

	List<TbProductItem> getListByParams(TbProductItem params);

	void delProductItemById(Integer itemId);

	TbProductItem getNotDeleteItemById(Integer itemId);

	TbProductItem getNotDeleteItemByCatalogId(String catalogId);

}
