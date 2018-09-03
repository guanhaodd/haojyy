package com.gh.app.util.tool.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.gh.app.manage.entity.BaseEntity;
import com.gh.app.util.entities.Entities;
import com.gh.app.util.tool.context.ContextUtil;
import com.gh.app.util.tool.http.HttpRequestUtil;

public class BaseEntityListener {

	/**
	 * 在插入数据前更新实体
	 * @param entity
	 */
	@PrePersist
	public void prePersist(BaseEntity entity) {
		entity.setAddTime(new Date());
		entity.setIsDele(Entities.ENTITY_NORMAL);
		entity.setUpdateTime(new Date());
		HttpServletRequest request = ContextUtil.getRequest();
		String ipAddr =HttpRequestUtil.getIpAddr(request);
		if(request!=null && !StringUtils.isEmpty(ipAddr)){
			entity.setAddIp(ipAddr);
		}
		
	}

	/**
	 * 在更新数据前更新实体
	 * @param entity
	 */
	@PreUpdate
	public void preUpdate(BaseEntity entity) {
		entity.setUpdateTime(new Date());
		HttpServletRequest request = ContextUtil.getRequest();
		String ipAddr =HttpRequestUtil.getIpAddr(request);
		if(request!=null && !StringUtils.isEmpty(ipAddr)){
			entity.setUpdateIp(ipAddr);
		}
	}
}
