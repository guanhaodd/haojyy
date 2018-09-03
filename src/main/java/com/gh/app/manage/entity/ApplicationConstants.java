package com.gh.app.manage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.gh.app.manage.module.sys.user.bean.TbUser;;

/**
 * 
 * 名称 : 
 * 描述 :  系统相关定义常量信息 
 * 创建人 :  Song Haitao
 * 创建时间: 2014-10-20 下午03:57:22
 *
 */
public class ApplicationConstants {

	public static Map<String,HttpSession> SESSION_MAP = 
        new HashMap<String,HttpSession>();//用来索引所有session
    public static List<TbUser> ONLIE_USERS = new ArrayList<TbUser>();
    
    public static int CURRENT_LOGIN_COUNT = 0;//当前登录用户总数
    public static int TOTAL_HISTORY_COUNT = 0;//历史访客总数
    public static int MAX_ONLINE_COUNT = 0;//最大在线访客数量
    public static Date START_DATE = new Date();//服务器启动时间
    public static Date MAX_ONLINE_COUNT_DATE = new Date();//达到最大访客量的日期
    public static String MAX_DATE_STR = ""; // 最大访问日期格式化字符串
}