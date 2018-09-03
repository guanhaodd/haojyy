package com.gh.app.manage.module.product.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gh.app.manage.module.product.bean.TbProductType;
import com.gh.app.manage.module.product.service.ProductTypeService;
import com.gh.app.util.dao.impl.BaseDaoImpl;

@Transactional
@Service
public class ProductTypeServiceImpl extends BaseDaoImpl<TbProductType> implements ProductTypeService{

	
	@Override
	public List<TbProductType> getListByParams(TbProductType params){
		String hql = "from TbProductType t where t.isDele= '0' ";
		if(StringUtils.isNotEmpty(params.getTypeName())){
			hql +=" and t.typeName = '"+params.getTypeName()+"' ";
		}
		//排序
		hql +=" order by t.isShow,t.level,t.rank ";
		return this.find(hql);
	}
	
	@Override
	public TbProductType getNotDeleteTypeById(Integer typeId){
		String hql = "from TbProductType t where t.isDele= '0' and t.id="+typeId;
		List<TbProductType> types = this.find(hql);
		return types.size()>0 ? types.get(0) :null;
	}
	/**
	 * 删除类型
	 * @param typeId
	 */
	public void delProductTypeById(Integer typeId){
		TbProductType type = this.get(typeId);
		String childIds = "-1";
		childIds = delChildTypeByParentId(typeId,childIds);
		type.setIsDele("1");
		this.update(type);
		this.executeHql("update TbProductType t set t.isDele = '1' where t.id in ("+childIds+")");
	}
	
	/**
	 * 删除父类型下的子类型
	 * @param parentId
	 */
	public String delChildTypeByParentId(Integer parentId,String ids){
		List<TbProductType> childs = this.find("from TbProductType t where t.isDele= '0' and t.parentId = "+parentId+" ");
		if(childs!=null && childs.size()>0){
			for(int i=0 ; i<childs.size() ;i++){
				ids = delChildTypeByParentId(childs.get(i).getId(),ids);
				ids +=","+childs.get(i).getId();
			}
		}
		return ids;
	}
	
	/**
	 * 禁用类型
	 * @param typeId
	 */
	@Override
	public void disableProductTypeById(Integer typeId){
		String childIds = ""+typeId;
		childIds = delChildTypeByParentId(typeId,childIds);
		this.executeHql("update TbProductType t set t.isShow = 0 where t.id in ("+childIds+")");
	}
	
	
	
	/**
	 * 更新类型时  更新所有商品的类型名称
	 * @param type
	 */
	@Override
	public void updateType(TbProductType type){
		this.update(type);
		this.executeHql("update TbProductItem i set i.typeName = '"+type.getTypeName()+"' where i.typeId ="+type.getId());
	}
}
