package com.gh.app.manage.module.sys.user.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gh.app.manage.entity.BaseEntity;
import com.gh.app.util.tool.converter.CustomDateSerializer;


/**
 * @author AezGenholmes
 * @Date 2018年8月1日下午1:54:53
 * @Discription  系统用户
 */
@Entity
@Table(name = "tb_user")
@DynamicInsert(true)
@DynamicUpdate(true)
@GenericGenerator( name="user_gen", strategy="enhanced-table", 
	   parameters = {
	       @Parameter( name = "table_name", value = "tb_pri_key_system"),   
	       @Parameter( name = "value_column_name", value = "next_val"), 
	       @Parameter( name = "segment_column_name",value = "generator_name"), 
	       @Parameter( name = "segment_value", value = "seq_tb_user"),
	       @Parameter( name = "increment_size", value = "10"), 
	       @Parameter( name = "optimizer",value = "pooled-lo") 
	   }) 
public class TbUser extends BaseEntity{

	private static final long serialVersionUID = -3487478685561995867L;
	
	private Integer id;
	private String userName;
	private String realName;
	private String password;
	private Date lastLoginTime;
	private String lastLoginIp;
	private String roleids;
	
	
	@Id
	@Column(name = "id", precision = 11, scale = 0)
	@GeneratedValue(generator = "user_gen")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "user_name", length = 100)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "real_name", length = 100)
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Column(name = "password", length = 255)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login_time")
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Column(name="last_login_ip")
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Column(name = "roleids", length = 255)
	public String getRoleids() {
		return roleids;
	}
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
