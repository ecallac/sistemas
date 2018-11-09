package com.internal.web.beans;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
 
@DataTransferObject
public class MyBean{
 
    @RemoteProperty
    private Integer property1;
    @RemoteProperty
    private String property2;
    @RemoteProperty
    private String property3;
 
    public Integer getProperty1() {
        return property1;
    }
 
    public void setProperty1(Integer property1) {
        this.property1 = property1;
    }
 
    public String getProperty2() {
        return property2;
    }
 
    public void setProperty2(String property2) {
        this.property2 = property2;
    }
 
    public String getProperty3() {
        return property3;
    }
 
    public void setProperty3(String property3) {
        this.property3 = property3;
    }
}