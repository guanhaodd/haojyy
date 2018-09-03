package com.gh.app.manage.module.sys.upload.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.gh.app.util.entities.Entities;

@Entity
@Table(name = "tb_upload_file")
@GenericGenerator(name = "upload_file_gen", strategy = "enhanced-table", parameters = {
		@Parameter(name = "table_name", value = "tb_pri_key_system"),
		@Parameter(name = "value_column_name", value = "next_val"),
		@Parameter(name = "segment_column_name", value = "generator_name"),
		@Parameter(name = "segment_value", value = "seq_tb_upload_file"),
		@Parameter(name = "increment_size", value = "10"),
		@Parameter(name = "optimizer", value = "pooled-lo") })
public class TbUploadFile {
	
	private Integer id;
	private String name;
	private String discription;
	private String kind;
	private String module;
	private String uploadFileName;
	private String targetFileName;
	private String fileType;
	private Long fileSize;
	private String path;
	private String catalogId;
	private String isDele=Entities.ENTITY_NORMAL;
	private Integer rank;
	
	@Id
	@Column(name = "id", precision = 11, scale = 0)
	@GeneratedValue(generator = "upload_file_gen")
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
	@Column(name = "discription", length = 100)
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	@Column(name = "kind", length = 100)
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	@Column(name = "module", length = 100)
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	@Column(name = "upload_file_name", length = 100)
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	@Column(name = "target_file_name", length = 100)
	public String getTargetFileName() {
		return targetFileName;
	}
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	@Column(name = "file_type", length = 100)
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name = "file_size", precision = 11, scale = 0)
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name = "path", length =200)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name = "catalogid", length = 100)
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	@Column(name = "is_dele", length =10)
	public String getIsDele() {
		return isDele;
	}
	public void setIsDele(String isDele) {
		this.isDele = isDele;
	}
	@Column(name = "rank", precision = 11, scale = 0)
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
