package com.gh.app.manage.module.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/main")
public class MainController {
	
	@RequestMapping(value="/index")
	public String toIndex(){
		return "/main/index";
	}
}
