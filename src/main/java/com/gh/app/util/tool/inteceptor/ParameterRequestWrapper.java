package com.gh.app.util.tool.inteceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AezGenholmes
 * @Date 2018年7月26日下午3:52:41
 * @Discription  用于对Request 对象进行包装, 取得request中的参数进行处理 .
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    
    private Map<String , String[]> params = new HashMap<String, String[]>();
    //不需要进行处理的表单字段名称 
    public static String notProcessingFields= "content";
    @SuppressWarnings("unchecked")
    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
    }
    //重载一个构造方法
    public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
        this(request);
        addAllParameters(extendParams);//这里将扩展参数写入参数表
    }
    
    @Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }
    
    public String[] getParameterValues(String name) {//同上
         return params.get(name);
    }

   public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数
        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }


    public void addParameter(String name , Object value) {//增加参数
        if(value != null && notProcessingFields.indexOf(name) == -1) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }
}