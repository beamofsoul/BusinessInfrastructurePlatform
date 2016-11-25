package com.beamofsoul.springboot.management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring提供的拦截器(Interceptor) - 在MyWebMvcConfigurerAdapter中注册
 * 功能类似于Servlet的Filter,但是可以提供更精细的控制力: 
 * 		request响应之前、request响应之后、视图渲染之前、request全部结束之后
 * PS: 1.不能通过拦截器修改request的内容,但是可以通过抛出异常或是返回false来暂停request的执行
 * 	   2.只有通过DispatcherServlet的请求,才会走拦截器链,所以自定义的Servlet请求不会被拦截
 * @author MingshuJian
 */
public class MyInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("#### MyInterceptor.afterCompletion() - 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行(主要是用于进行资源清理工作)");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("#### MyInterceptor.postHandler() - 请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)");
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("#### MyInterceptor.preHandle() - 在请求处理之前进行调用(Controller方法调用之前)");
		// 只有返回true才会继续向下执行，返回false取消当前请求
		return true;
	}

}
