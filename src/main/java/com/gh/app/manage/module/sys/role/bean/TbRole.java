package com.gh.app.manage.module.sys.role.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.gh.app.manage.entity.BaseEntity;


@Entity
@Table(name = "tb_role")
@GenericGenerator( name="role_gen", strategy="enhanced-table", 
	   parameters = {
	       @Parameter( name = "table_name", value = "tb_pri_key_system"), 
	       @Parameter( name = "value_column_name", value = "next_val"), 
	       @Parameter( name = "segment_column_name",value = "generator_name"), 
	       @Parameter( name = "segment_value", value = "seq_tb_role"),
	       @Parameter( name = "increment_size", value = "10"), 
	       @Parameter( name = "optimizer",value = "pooled-lo") 
	   }) 
public class TbRole extends BaseEntity{

	private static final long serialVersionUID = 1527545384197898806L;
	
	private Integer id;
	private String name;  //角色名称
	private String remark;  //备注
	
	
	@Id
	@Column(name = "id", precision = 11, scale = 0)
	@GeneratedValue(generator = "role_gen")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "remark", length = 100)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
