package com.internal.web.dwr.controller;
import java.util.Date;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
 
@RemoteProxy(name="ajaxController")
public class MyAjaxController {
 
    @RemoteMethod
    public String test(String s){
        return "["+s+"]-"+new Date();
    }
}