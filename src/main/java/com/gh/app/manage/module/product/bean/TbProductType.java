package com.gh.app.manage.module.product.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.gh.app.manage.entity.BaseEntity;


@Entity
@Table(name = "tb_product_type")
@DynamicInsert(true)
@DynamicUpdate(true)
@GenericGenerator( name="product_type_gen", strategy="enhanced-table", 
	   parameters = {
	       @Parameter( name = "table_name", value = "tb_pri_key_product"),   
	       @Parameter( name = "value_column_name", value = "next_val"), 
	       @Parameter( name = "segment_column_name",value = "generator_name"), 
	       @Parameter( name = "segment_value", value = "seq_tb_product_type"),
	       @Parameter( name = "increment_size", value = "10"), 
	       @Parameter( name = "optimizer",value = "pooled-lo") 
	   }) 
public class TbProductType extends BaseEntity{

	private static final long serialVersionUID = -2941318010097298604L;

	private Integer id;
	private String typeName;
	private Integer parentId;
	private String parentName;
	private Integer hasProducts;
	private Integer level;
	private Integer rank;
	private Integer isShow;
	
	@Id
	@Column(name = "id", precision = 11, scale = 0)
	@GeneratedValue(generator = "product_type_gen")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "type_name", length = 100)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name = "parent_id", precision = 11, scale = 0)
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Column(name = "parent_name", length = 100 )
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@Column(name = "has_products", precision = 11, scale = 0)
	public Integer getHasProducts() {
		return hasProducts;
	}
	public void setHasProducts(Integer hasProducts) {
		this.hasProducts = hasProducts;
	}
	@Column(name = "level", precision = 2, scale = 0)
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Column(name = "rank", precision = 5, scale = 0)
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	@Column(name = "is_show", precision = 2, scale = 0)
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

}
