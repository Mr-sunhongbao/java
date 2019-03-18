package com.manager.diy.ma.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContainer {
	
	@Value("${ms_url}")
	public static String MS_URL;

}
