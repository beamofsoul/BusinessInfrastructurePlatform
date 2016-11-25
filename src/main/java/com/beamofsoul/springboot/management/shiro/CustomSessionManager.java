//package com.beamofsoul.springboot.management.shiro;
//
//import org.apache.shiro.session.mgt.eis.SessionDAO;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.stereotype.Component;
//
///**
// * 在身份认证时用到了并发登录控制(多人同时登录同一个账号),需要SessionDAO获取所有在线用户
// * 所以继承DefaultWebSessionManager暴露其SessionDAO
// * PS: 如不想创建该类并想实现并发登录控制,则需要额外配置spring配置文件
// * @author MingshuJian
// *
// */
//@Component
//public class CustomSessionManager extends DefaultWebSessionManager {
//
//	CustomSessionManager(SessionDAO sessionDAO) {
//		super.setSessionDAO(sessionDAO);
//	}
//	
//	@Override
//	public SessionDAO getSessionDAO() {
//		return super.getSessionDAO();
//	}
//}
