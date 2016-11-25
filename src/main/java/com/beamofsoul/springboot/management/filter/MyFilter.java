package com.beamofsoul.springboot.management.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
* 使用注解标注过滤器
* @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器,
* 该注解会被@ServletComponentScan识别
* 属性filterName声明过滤器的名称,可选
* 属性urlPatterns指定要过滤的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
* @author MingshuJian
*/
//@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("#### MyFilter.init() - 过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("#### MyFilter.doFilter() - 执行过滤操作");
        chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		System.out.println("#### MyFilter.destroy() - 过滤器销毁");
	}
}
