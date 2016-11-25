package com.beamofsoul.springboot.management.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 使用@WebListener注解，实现ServletContextListener接口
 * @WebListener 该注解会被@ServletComponentScan识别
 * @author MingshuJian
 */
//@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 System.out.println("#### ServletContex销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("#### ServletContex初始化");
	}

}
