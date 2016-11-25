//package com.beamofsoul.springboot.management.shiro;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.subject.support.DefaultSubjectContext;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.beamofsoul.springboot.entity.User;
//import com.beamofsoul.springboot.management.util.Constants;
//import com.beamofsoul.springboot.service.UserService;
//
///**
// * 身份验证核心类
// * @author MingshuJian
// */
//public class CustomShiroRealm extends AuthorizingRealm {
//
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private CustomSessionManager sessionManager;
//
//	/**
//	 * 身份验证
//	 * 首先根据传入的用户名获取 User信息
//	 * 然后如果 user为空,那么抛出没找到帐号异常 UnknownAccountException
//	 * 如果 user找到但锁定了抛出锁定异常 LockedAccountException
//	 * 最后生成 AuthenticationInfo信息
//	 * 交给间接父类 AuthenticatingRealm使用 CredentialsMatcher进行判断密码是否匹配
//	 * 如果不匹配将抛出密码错误异常 IncorrectCredentialsException
//	 * 另外如果密码重试此处太多将抛出超出重试次数异常 ExcessiveAttemptsException
//	 * 在组装 SimpleAuthenticationInfo信息时
//	 * 需要传入:身份信息(用户名)、凭据(密文密码)、盐(username+salt)[可选]
//	 * 如果加密CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配
//	 */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(
//			AuthenticationToken token) throws AuthenticationException {
//
//		try {
//			User user = userService.findByUsername((String) token.getPrincipal());
//			if (user == null) return null;
//			
//			/*
//			 * 同一账户并发登录控制 - 踢掉先登录系统的用户
//			 */
//			Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
//			for (Session session : sessions) {
//				if (user.getUsername().equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
//					session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
//					break;
//				}
//			}
//			
//			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo
//					(user.getUsername(), user.getPassword(), getName());
//			this.putInSession(Constants.CURRENT_USER, user);
//			return authcInfo;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 权限控制: 当访问到页面的时候,使用了相应的注解或者shiro标签才会执行此方法,否则不会执行
//	 * Authorization是授权访问控制: 用于对用户进行的操作授权,证明该用户是否允许进行当前操作,如访问某个链接或资源文件等
//	 * 此方法调用 hasRole,hasPermission的时候才会进行回调
//	 * 
//	 * 权限信息(授权): 
//	 * 	1.如果用户正常退出,缓存自动清空 
//	 * 	2.如果用户非正常退出,缓存自动清空
//	 *  3.如果我们修改了用户的权限,而用户不退出系统,修改的权限无法立即生效 (需要手动编程进行实现, 放在service进行调用)
//	 * 在权限修改后调用realm中的方法, realm已经由spring管理
//	 * 所以从spring中获取realm实例, 调用clearCached方法
//	 */
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(
//			PrincipalCollection principals) {
//		/*
//		 * 当没有使用缓存的时候,不断刷新页面的话,这个代码会不断执行,
//		 * 但其实没有必要每次都重新设置权限信息,所以我们需要放到缓存中进行管理
//		 * 当放到缓存中时,doGetAuthorizationInfo就只会执行一次了,缓存过期之后会再次执行
//		 * 如果不做缓存,Shiro自己也有时间间隔机制,2分钟内不会重复执行此方法
//		 */
//		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		/*
//		 * PrincipalCollection是一个身份集合
//		 * 因为现在就一个 Realm,所以直接调用 getPrimaryPrincipal得到之前传入的用户名即可
//		 * 然后根据用户名调用 UserService接口获取角色及权限信息
//		 */
//		String username = (String) principals.getPrimaryPrincipal();
//		List<Set<String>> rps = userService.findRolesAndPermissions(username);
//		authorizationInfo.addRoles(rps.get(0));
//		authorizationInfo.addStringPermissions(rps.get(1));
//		return authorizationInfo;
//	}
//	
//	/** 
//     * 将一些数据放到ShiroSession中,以便于其它地方使用 
//     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
//     */ 
//	private void putInSession(Object key, Object value) {
//		Subject currentUser = SecurityUtils.getSubject();
//		if (currentUser != null) {
//			Session shiroSession = currentUser.getSession();
//			if (shiroSession != null) {
//				shiroSession.setAttribute(key, value);
//			}
//		}
//	}
//}
