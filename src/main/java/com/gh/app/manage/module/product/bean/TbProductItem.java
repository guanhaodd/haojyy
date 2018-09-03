package com.gh.app.manage.module.product.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.gh.app.manage.entity.BaseEntity;
import com.gh.app.manage.module.sys.upload.bean.TbUploadFile;

@Entity
@Table(name = "tb_product_item")
@DynamicInsert(true)
@DynamicUpdate(true)
@GenericGenerator( name="product_item_gen", strategy="enhanced-table", 
	   parameters = {
	       @Parameter( name = "table_name", value = "tb_pri_key_product"),   
	       @Parameter( name = "value_column_name", value = "next_val"), 
	       @Parameter( name = "segment_column_name",value = "generator_name"), 
	       @Parameter( name = "segment_value", value = "seq_tb_product_item"),
	       @Parameter( name = "increment_size", value = "10"), 
	       @Parameter( name = "optimizer",value = "pooled-lo") 
	   }) 
public class TbProductItem extends BaseEntity{

	private static final long serialVersionUID = 3102126542953879161L;
	private Integer id;
	private String itemName;
	private Integer typeId;
	private String typeName;
	private Integer rank;
	private String discribe;
	private String summary;
	private String price;
	private Integer onSale;
	private Integer isNew;
	private Integer isHot;
	private Integer isShow;
	private String catalogId;
	
	//临时字段
	private List<TbUploadFile> files;
	private TbUploadFile mPic;
	
	@Id
	@Column(name = "id", precision = 11, scale = 0)
	@GeneratedValue(generator = "product_item_gen")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "item_name", length = 100)
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name = "type_id", precision = 11, scale = 0)
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Column(name = "type_name", length = 100)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name = "rank", precision = 5, scale = 0)
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	@Column(name = "discribe", length = 50000)
	public String getDiscribe() {
		return discribe;
	}
	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}
	@Column(name = "summary", length = 5000)
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name = "price", length = 255)
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name = "on_sale", precision = 2, scale = 0)
	public Integer getOnSale() {
		return onSale;
	}
	public void setOnSale(Integer onSale) {
		this.onSale = onSale;
	}
	@Column(name = "is_new", precision = 2, scale = 0)
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	@Column(name = "is_hot", precision = 2, scale = 0)
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	@Column(name = "is_show", precision = 2, scale = 0)
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	@Column(name = "catalogid", length = 255)
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	@Transient
	public List<TbUploadFile> getFiles() {
		return files;
	}
	public void setFiles(List<TbUploadFile> files) {
		this.files = files;
	}
	@Transient
	public TbUploadFile getmPic() {
		return mPic;
	}
	public void setmPic(TbUploadFile mPic) {
		this.mPic = mPic;
	}

}
