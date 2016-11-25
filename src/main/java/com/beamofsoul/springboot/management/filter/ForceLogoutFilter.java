//package com.beamofsoul.springboot.management.filter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.apache.shiro.session.Session;
//import org.apache.shiro.web.filter.AccessControlFilter;
//import org.apache.shiro.web.util.WebUtils;
//
//import com.beamofsoul.springboot.management.util.Constants;
//
//public class ForceLogoutFilter extends AccessControlFilter {
//	
//	@Override
//	protected boolean isAccessAllowed(ServletRequest request,
//			ServletResponse response, Object mappedValue) throws Exception {
//		Session session = getSubject(request,response).getSession(false);
//		if (session == null) {
//			return true;
//		}
//		return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) == null;
//	}
//
//	@Override
//	protected boolean onAccessDenied(ServletRequest request,
//			ServletResponse response) throws Exception {
//		getSubject(request,response).logout();
//		WebUtils.issueRedirect(request, response, getLoginUrl() + "?kickout=1");
//        return false;
//	}
//}
