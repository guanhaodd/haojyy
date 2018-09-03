package com.gh.app.manage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gh.app.util.entities.Entities;
import com.gh.app.util.tool.converter.CustomDateDeserializer;
import com.gh.app.util.tool.converter.CustomDateSerializer;
import com.gh.app.util.tool.listener.BaseEntityListener;

@EntityListeners({BaseEntityListener.class})
@MappedSuperclass
public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1540923141844069121L;
	
	
	private Integer version;
	private String isDele =Entities.ENTITY_NORMAL;
	private Date AddTime;
	private Date UpdateTime;
	private String addIp;
	private String updateIp;
	
	@Version
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name="is_dele")
	public String getIsDele() {
		return isDele;
	}
	public void setIsDele(String isDele) {
		this.isDele = isDele;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="add_time")
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getAddTime() {
		return AddTime;
	}
	@JsonDeserialize(using=CustomDateDeserializer.class)
	public void setAddTime(Date addTime) {
		AddTime = addTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}
	@Column(name="add_ip")
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	@Column(name="update_ip")
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	
	/**
	 * 更新前重新存储该属性
	 * @param baseEntity
	 */
	public void setAddProperties(BaseEntity baseEntity) {
		this.setAddTime(baseEntity.getAddTime());
		this.setAddIp(baseEntity.getAddIp());
	}
}
