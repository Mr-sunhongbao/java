package com.manager.diy.ma.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.manager.diy.ma.bo.Tmodule;

public class RemoteUtil {


	    private static RestTemplate restTemplate = new RestTemplate();

	    private static List<HttpMessageConverter<?>> coverter;
	    
	    private static StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));

	    public <T> Object callMicroService(String url, Map<String,Object> map, Class<T> clazz) throws Exception {
	        initCoverter();
	        restTemplate.setMessageConverters(coverter);
	        Object responseParam = clazz.newInstance();
	        MultiValueMap<String,Object> mvm = new LinkedMultiValueMap<String,Object>();
	        mvm.setAll(map);
	        try {
	            ResponseEntity<?> respEntity =  restTemplate.postForEntity(url, mvm, Object.class);
	            Object p = respEntity.getBody();
	            if(p != null){
	            	if(p.getClass().getName().equals("java.util.ArrayList")){
	            		responseParam = new ArrayList<T>();
	            		responseParam = POJOUtils.listMaptoBeanEx((List<LinkedHashMap>)p, clazz);
	            	}
	            	if(p.getClass().getName().equals("java.util.LinkedHashMap")){
	            		responseParam = POJOUtils.maptoBeanEx((LinkedHashMap)p, clazz);
	            	}
	            }
	        }
	        catch (ResourceAccessException e) {
	            e.printStackTrace();
	        }
	        return responseParam;
	    }


	    private static void initCoverter(){
	        if(coverter == null){
	            coverter = restTemplate.getMessageConverters();
	            coverter.remove(1);
	            coverter.add(1, m);
	        }
	    }
	    
	    public static void main(String[] args) throws Exception{
	    	RemoteUtil ru = new RemoteUtil();
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("m", 6);
	    	Tmodule a = new Tmodule();
//	    	List<Tmodule> t = new ArrayList<Tmodule>();
	    	
	    	List s =  (ArrayList) ru.callMicroService("http://localhost:8088/module/queryModule", map, Tmodule.class);
//	    	Tmodule s = (Tmodule) ru.callMicroService("http://localhost:8088/module/getModuleById", map, Tmodule.class);
	    	System.out.println(s.get(0));
	    }
}
