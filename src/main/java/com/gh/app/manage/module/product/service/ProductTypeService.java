package com.gh.app.manage.module.product.service;

import java.util.List;

import com.gh.app.manage.module.product.bean.TbProductType;
import com.gh.app.util.dao.BaseDao;

public interface ProductTypeService extends BaseDao<TbProductType>{

	List<TbProductType> getListByParams(TbProductType params);

	void delProductTypeById(Integer typeId);

	void disableProductTypeById(Integer typeId);

	void updateType(TbProductType type);

	TbProductType getNotDeleteTypeById(Integer typeId);

}
