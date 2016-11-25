//package com.beamofsoul.springboot.management.exception;
//
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.web.servlet.ErrorPage;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//
///**
// * 注册自定义的异常页面
// * 主要异常处理功能在ExceptionController中实现
// * @author MingshuJian
// * @see com.beamofsoul.springboot.controller.ExceptionController
// */
//@Configuration
//public class GlobalExceptionConfiguration {
//	
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return new EmbeddedServletContainerCustomizer() {
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer container) {
////				container.addErrorPages(new ErrorPage(org.apache.shiro.authz.UnauthorizedException.class,"/error/403"));
//				container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/error/403"));
//				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
//				container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
//				container.addErrorPages(new ErrorPage(java.lang.Throwable.class, "/error/500"));
//			}
//		};
//	}
//}
