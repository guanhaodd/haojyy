package com.gh.app.manage.module.sys.user.service;

import com.gh.app.manage.module.sys.user.bean.TbUser;
import com.gh.app.util.dao.BaseDao;

/**
 * @author AezGenholmes
 * @Date 2018年8月1日下午2:09:39
 * @Discription  用户相关业务
 */
public interface UserService extends BaseDao<TbUser>{
	/**
	 * 根据用户名查找用户 
	 * @param userName 不可为null
	 * @return
	 */
	public TbUser findByUserName(String userName);
}
