package com.gh.app.manage.module.sys.upload.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件上传控制器
 * @author AezGenholmes
 * @Date 2018年8月3日上午11:56:22
 * @Discription  
 */
@Controller
@RequestMapping("/upload")
public class UploadFileController {

	/**
	 * 生成随机的文件上传标识ID
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/generate/catalog")
	public String generateCatalog() {
		return UUID.randomUUID().toString();
	}
}
