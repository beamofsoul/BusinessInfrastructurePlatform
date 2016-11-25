package com.beamofsoul.springboot.management.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听HttpSession的创建和销毁
 * @WebListener 该注解会被@ServletComponentScan识别
 * @author MingshuJian
 */
//@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("#### MyHttpSessionListener.sessionCreated() - HttpSession被创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("#### MyHttpSessionListener.sessionDestroyed() - HttpSession被销毁");
	}
}
