package com.gh.app.util.tool.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.gh.app.util.tool.log.Logs;


/**
 * @author AezGenholmes
 * @Date 2018年7月27日上午11:02:41
 * @Discription  spring-mvc.xml中定义conversionService，将映射视图参数类型转换 String2Date
 */
public class StringToDateConverter implements Converter<String, Date> {
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATE_M = "yyyy-MM-dd HH:mm";
	
	@Override
	public Date convert(String source) {
		if(source != null && StringUtils.isNotBlank(source)) {
			SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_STANDARD);
			Date date = null;
			try {
				date = sdf.parse(source);
			} catch (ParseException e) {
				sdf = new SimpleDateFormat(PATTERN_DATE_M);
				try {
					date = sdf.parse(source);
					return date;
				} catch (ParseException e1) {
					sdf = new SimpleDateFormat(PATTERN_DATE);
					try {
						date = sdf.parse(source);
						return date;
					} catch (ParseException e2) {
						Logs.error(e2.getMessage()); 
					}
					Logs.error(e1.getMessage()); 
				}
				Logs.error(e.getMessage()); 
			}
//			//System.out.println(source);
//			//System.out.println(date);
			return date;
		}
		return null;
	}

}

