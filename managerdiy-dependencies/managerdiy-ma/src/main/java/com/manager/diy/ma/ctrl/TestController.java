package com.manager.diy.ma.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.diy.ma.util.ApplicationContainer;

@RestController
@RequestMapping("/testController/")
public class TestController {

	@RequestMapping(value = "/test")
	public void test(){
		System.out.println(ApplicationContainer.MS_URL);
	}
}
