package com.beamofsoul.springboot.management.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 重写WebMvcConfigurerAdapter特定方法
 * 用于为WebMvc手动注册拦截器、解析器(视图解析器)...
 * PS: 只有通过DispatcherServlet的请求,才会走拦截器链,所以自定义的Servlet请求不会被拦截
 * @author MingshuJian
 */
//@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 多个拦截器组成一个拦截器链(InterceptorChain)
		 * addPathPatterns 用于增加拦截规则
		 * excludePathPatterns 用于排除拦截
		 */
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//		registry.addInterceptor(new SomeInterceptor...()).addPathPatterns("/**");
        super.addInterceptors(registry);
	}
}
