package com.gh.app.manage.module.sys.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gh.app.manage.module.sys.user.bean.TbUser;
import com.gh.app.manage.module.sys.user.service.UserService;
import com.gh.app.util.dao.impl.BaseDaoImpl;
import com.gh.app.util.entities.Entities;

@Transactional
@Service
public class UserServiceImpl extends BaseDaoImpl<TbUser> implements UserService{
	public TbUser findByUserName(String userName) {
		String hql = "from TbUser u where u.userName = '" + userName 
			+"' and u.isDele='"+Entities.ENTITY_NORMAL+"'";
		List<TbUser> user = super.find(hql);
		if(!user.isEmpty()) {
			return (TbUser)user.get(0);
		}
		return null;
	}
}
