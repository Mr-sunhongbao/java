package com.manager.diy.ma.bo;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Tmodule的POJO类
 *
 * @author  sunhb  [2019-03-07 16:45:48]
 * 
 */
public class Tmodule implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6718661691900347993L;

	/** 
     * 属性id
     */
    private String id;
	
    /** 
     * 属性moduleName
     */
    private String moduleName;
	
    /** 
     * 属性moduleCode
     */
    private String moduleCode;
	
    /**
     * Tmodule构造函数
     */
    public Tmodule() {
        super();
    }  
	
    /**
     * Tmodule完整的构造函数
     */  
    public Tmodule(String id){
        this.id = id;
    }
 
	
	
    /**
     * 属性 id 的get方法
     * @return String
     */
    public String getId(){
        return id;
    }
	
    /**
     * 属性 id 的set方法
     * @return
     */
    public void setId(String id){
        if(id != null && id.trim().length() == 0){
            this.id = null;
        }else{
            this.id = id;
        }
    } 
	
	
	
    /**
     * 属性 moduleName 的get方法
     * @return String
     */
    public String getModuleName(){
        return moduleName;
    }
	
    /**
     * 属性 moduleName 的set方法
     * @return
     */
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    } 
	
	
	
    /**
     * 属性 moduleCode 的get方法
     * @return String
     */
    public String getModuleCode(){
        return moduleCode;
    }
	
    /**
     * 属性 moduleCode 的set方法
     * @return
     */
    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    } 
	
    /**
     * Hibernate通过该方法判断对象是否相等
     * @return boolean
     */  
    public boolean equals(Object o) {
        if (this == o)
			return true;
		
        if (o == null || !(o instanceof Tmodule))
	        return false; 
			
        if(getId() == null) 
	        return false;

        Tmodule other = (Tmodule) o;	        
	    return new EqualsBuilder()
            .append(this.getId(), other.getId())
			.isEquals();
    } 
     
    /**
     * toString方法
     * @return String
     */
	public String toString(){

		  return new StringBuffer()
            .append("id"+":"+getId())
            .append("moduleName"+":"+getModuleName())
            .append("moduleCode"+":"+getModuleCode())
            .toString(); 
			
    } 
   

    /**
     * hashcode方法
     * @return int
     * 
     */
    @Override
    public int hashCode(){
		return super.hashCode();
	}

}