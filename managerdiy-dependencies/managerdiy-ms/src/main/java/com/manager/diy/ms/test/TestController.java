package com.manager.diy.ms.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testController")
public class TestController {
	
	@RequestMapping(value="/testFun")
	public String testFun(int m){
		System.out.println("***********成功*****************");
//		System.out.println(a);
		return "aaaa";
	}

}
