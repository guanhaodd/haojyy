package com.gh.app.util.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.gh.app.util.entities.JsonView;
import com.gh.app.util.entities.QueryResult;
import com.gh.app.util.entities.QuiPager;



/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午9:34:00
 * @Discription  控制器基类, 封装一些通用的的方法和属性 
 */
public abstract class BaseController {
	//@Autowired
	//protected ResourceBundleMessageSource _res;
	public static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		// 防范XSS攻击 
		//binder.registerCustomEditor(String.class, new StringEscapeEditor(false, true, true));
	}
	
	/**
	 * 其于JSR 303 – Bean Validation 数据验证的规范对实体数据进行校验
	 * @param jv
	 * @param result
	 * @return 包括不符合要求的数据提示信息的JsonView
	 */
	@SuppressWarnings("unchecked")
	protected JsonView  getFieldError(JsonView jv, BindingResult result) {
		StringBuilder errorMsg = new StringBuilder();
		List<FieldError> ferrors = result.getFieldErrors();
		for(FieldError  fe : ferrors) {
			LOG.debug("Field ---> " + fe.getField());
			LOG.debug("Message ---> " + fe.getDefaultMessage());
			errorMsg.append(fe.getDefaultMessage()).append(";  ");
		}
		jv.setSuccess(false);
		jv.setMsg(errorMsg.toString());
		return jv;
	}
	
	/**
	 *  将HQL查询结果转换为符合为前台要求的数据格式  
	 * @param qr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String,Object> queryResultToMap(QueryResult qr, QuiPager q) {
		Map<String,Object> results = new Hashtable<String, Object>();
		if(qr != null) {
			if(qr.getRows() != null && qr.getRows().size() > 0 && qr.getPager() != null) {
				results.put("pager.pageNo", qr.getPager().getPageNo());
				results.put("pager.totalRows", qr.getTotal());
				results.put("rows", qr.getRows());
			} else {
				results.put("pager.pageNo", 1);
				results.put("pager.totalRows", 0);
				if(qr.getRows() != null) {
					results.put("rows", qr.getRows());
				}
			}
			if(q != null && qr.getSort()!= null && qr.getDirection() != null) {
				results.put("sort", q.getSort());
				results.put("direction", qr.getDirection());
			}
		}
		
		return results;
	}
	
	/**
	 * 列表数据显示时  不需要分页的情况 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String,Object> listToMap(List list) {
		Map<String,Object> results = new Hashtable<String, Object>();
		results.put("rows", list);
		return results;
	}
	
	@SuppressWarnings("rawtypes")
	protected Map<String,Object> listToMap(String key, List list) {
		Map<String,Object> results = new Hashtable<String, Object>();
		results.put(key, list);
		return results;
	}
}