package com.gh.app.util.tool.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author AezGenholmes
 * @Date 2018年8月1日下午1:44:41
 * @Discription  自定义返回JSON 数据格中日期格式化处理
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String formattedDate = dateFormat.format(value);
		jgen.writeString(formattedDate);
	}

}