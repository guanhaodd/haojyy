package com.gh.app.util.tool.converter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author AezGenholmes
 * @Date 2018年8月1日下午2:10:19
 * @Discription  自定义返回JSON 数据格中日期格式化处理
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		Date date = null;  
        try {  
            date = dateFormat.parse(arg0.getText());  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return date;
	}

}
