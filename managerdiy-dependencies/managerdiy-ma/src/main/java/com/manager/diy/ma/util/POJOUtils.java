package com.manager.diy.ma.util;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.beanutils.PropertyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: POJOUtils
 * @Description: POJO转换类
 */
public class POJOUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转map
     * 
     * @param bean
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> BeantoMap(Object bean) {
        if (bean == null) return null;
        Map<String, Object> map = null;
        try {
            // BeanUtil方法会出现错误，他会将所有的对象转成String输出Map，得到的value不是对应的类型对象
            map = PropertyUtils.describe(bean);
            map.remove("class");
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * listBean对象转Map
     * 
     * @param beanList
     * @return
     */
    public static List<Map<String, Object>> listBeantoMap(List<?> beanList) {
        if (beanList == null) return null;
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        for (Object bean : beanList) {
            maplist.add(BeantoMap(bean));
        }
        return maplist;
    }

    /**
     * map转对象BeanEx
     * 
     * @param map
     * @param c
     * @return
     */
    public static <T> T maptoBeanEx(Map<?, ?> map, Class<T> cla) {
        if (map == null || map.isEmpty()) return null;
        if (cla == null)
			try {
				throw new Exception("转换类型为空");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        String str = JSON.toJSONString(map);
        return JSON.parseObject(str, cla);
        // try {
        // T bean = cla.newInstance();
        // ApacheBeanUtilsEx.populate(bean, map);
        // return bean;
        // }
        // catch (InstantiationException | IllegalAccessException e) {
        // throw new QnxgdsTransformBeanException(e);
        // }
    }

    /**
     * listMap转BeanEx
     * 
     * @param mapList
     * @param c
     * @return
     */
    public static <T> List<T> listMaptoBeanEx(List<?> mapList, Class<T> cla) {
        if (mapList == null) return null;
        List<T> beanList = new ArrayList<T>();
        if (mapList.isEmpty()) return beanList;
        // if (cla == null) throw new QnxgdsTransformBeanException("转换类型为空");
        return JSONArray.parseArray(JSON.toJSONString(mapList), cla);
        // for (Map map : mapList) {
        // T bean = maptoBeanEx(map, cla);
        // beanList.add(bean);
        // }
        // return beanList;
    }

    /**
     * 固定泛型转对象
     * 
     * @param mapList
     * @param c
     * @return
     */
    public static <T> List<T> listMapGenericstoBeanEx(List<Map<String, Object>> mapList, Class<T> c) {
        if (mapList == null) return null;
        List<T> beanList = new ArrayList<T>();
        if (mapList.isEmpty()) return beanList;
        if (c == null)
			try {
				throw new Exception("转换类型为空");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        for (Map<String, Object> map : mapList) {
            T bean = maptoBeanEx(map, c);
            beanList.add(bean);
        }
        return beanList;
    }

    /**
     * removeNullValueS
     * 
     * @param map
     * @return
     */
    public static Map<String, String> removeNullValueS(Map<String, String> map) {
        if (map != null) {
            Map<String, String> map2 = new HashMap<String, String>();
            for (Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    map2.put(entry.getKey(), entry.getValue());
                }
            }
            return map2;
        }
        return null;
    }

    /**
     * removeNullValueO
     * 
     * @param map
     * @return
     */
    public static Map<String, Object> removeNullValueO(Map<String, Object> map) {
        if (map == null || map.isEmpty()) { return new HashMap<String, Object>(); }
        Map<String, Object> map2 = new HashMap<String, Object>();
        for (Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        return map2;
    }

    /**
     * 参数复制 单个对象
     * 
     * @param obj
     * @param cla
     * @return
     */
    public static <T> T copyProperties(Object obj, Class<T> cla) {
        if (obj == null) return null;
        if (cla == null){
			try {
				throw new Exception("转换类型为空");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        T dest = null;
		try {
			dest = cla.newInstance();
            // SpringBeanUtils.copyProperties(obj, dest);
            net.sf.cglib.beans.BeanCopier copier = net.sf.cglib.beans.BeanCopier.create(obj.getClass(), cla, false);
            copier.copy(obj, dest, null);
        }
        catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
		return dest;
    }

    /**
     * 参数复制 List对象
     * 
     * @param objList
     * @param cla
     * @return
     */
    public static <T> List<T> listCopyProperties(List<?> objList, Class<T> cla) {
        if (objList == null) return null;
        List<T> destList = new ArrayList<T>();
        if (objList.isEmpty()) return destList;
        // if (cla == null) throw new QnxgdsTransformBeanException("转换类型为空");

        for (Object obj : objList) {
            destList.add(copyProperties(obj, cla));
        }
        return destList;
    }


    /**
     * 对象转json
     * 
     * @param obj
     * @return
     */
    public static String objectToJson(Object obj) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
        	e.printStackTrace();
        }
        return json;
    }

    /**
     * xml转换成JavaBean
     * 
     * @param xml
     * @param cla
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> cla) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(cla);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T)unmarshaller.unmarshal(new StringReader(xml));
        }
        catch (JAXBException e) {
        	e.printStackTrace();
        }

        return t;
    }
}
