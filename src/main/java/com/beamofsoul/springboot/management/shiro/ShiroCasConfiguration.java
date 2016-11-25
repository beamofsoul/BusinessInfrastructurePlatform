//package com.beamofsoul.springboot.management.shiro;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.servlet.Filter;
//
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import com.beamofsoul.springboot.management.cache.GlobalSharedEhCacheConfiguration;
//import com.beamofsoul.springboot.management.filter.ForceLogoutFilter;
//
///**
// * Shiro配置
// * Shiro的核心是通过 Filter来实现权限控制
// * 具体来说是通过URL规则来进行过滤和权限校验
// * 就好像SpringMvc通过DispachServlet来主控制一样
// * 所以需要根据业务定义一系列关于URL的规则和访问权限并存放于FilterChain中使其按序过滤
// * @author MingshuJian
// */
//@Configuration
//public class ShiroCasConfiguration extends GlobalSharedEhCacheConfiguration {
//	
//	/**
//	 * Bean post processor for Spring that automatically calls the init() 
//	 * and/or destroy() methods on Shiro objects that 
//	 * implement the org.apache.shiro.util.Initializable 
//	 * or org.apache.shiro.util.Destroyable interfaces, respectfully. 
//	 * 
//	 * This post processor makes it easier to configure Shiro beans in Spring, 
//	 * since the user never has to worry about 
//	 * whether or not if they have to specify init-method 
//	 * and destroy-method bean attributes. 
//	 */
//	@Bean
//	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//	    return new LifecycleBeanPostProcessor();
//	}
//
//	/**
//	 * ShiroFilterFactoryBean
//	 * PS: 初始化ShiroFilterFactoryBean时必须注入一个SecurityManager, 否则会报错
//	 * 
//	 * FilterChain定义说明 :
//	 * 		1.一个URL可以配置多个Filter, 使用逗号分隔
//	 * 		2.当设置多个过滤器时, 全部验证通过时才视为通过
//	 *		3.部分过滤器可指定参数, 如permissions，roles
//	 */
//	@Bean
//	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		
//		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
//		filters.put("forceLogout", new ForceLogoutFilter());
//		
//		// 必须设置 SecurityManager
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//		
//		
//		// 拦截器
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//		filterChainDefinitionMap.put("/logout", "logout");
//		// 配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/index", "user");
//        filterChainDefinitionMap.put("/", "user");
//        // 配置并发登录自动踢人
////        filterChainDefinitionMap.put("/**", "forceLogout");
//		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
//		// authc:所有url都必须认证通过才可以访问
//		// anon:所有url都都可以匿名访问
//		filterChainDefinitionMap.put("/**", "authc,forceLogout");
//		// 再次配置了url指向Controller中的方法
//		shiroFilterFactoryBean.setLoginUrl("/login");
//		shiroFilterFactoryBean.setSuccessUrl("/index");
//		shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
//
//		shiroFilterFactoryBean
//				.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		
//		return shiroFilterFactoryBean;
//	}
//
//	@Bean
//	public SecurityManager securityManager() {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		securityManager.setRealm(customShiroRealm());
//		securityManager.setCacheManager(ehCacheManager());
//		securityManager.setRememberMeManager(rememberMeManager());
//		//注入会话管理器 - 自定义mySessionManager - 实现一个账号同时登录踢掉先登录的用户
//		securityManager.setSessionManager(customSessionManager());
//		return securityManager;
//	}
//	
//	/**
//	 * 身份认证realm
//	 */
//	@Bean
//	public CustomShiroRealm customShiroRealm() {
//		return new CustomShiroRealm();
//	}
//	
//   /**
//    * shiro缓存管理器 
//    * 使用ehCacheManager实现
//    * 需要将其注入到securityManager中进行统一管理
//    */
//	@Override
//	@Bean(name = "ehCacheManager")  
//	@DependsOn("lifecycleBeanPostProcessor")
//	public EhCacheManager ehCacheManager(){
//	    EhCacheManager ehCacheManager = new EhCacheManager();
//	    ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());  
//	    return ehCacheManager;
//	}
//	
//	/**
//     * 开启shiro AOP注解支持.
//     * 使用代理方式,所以需要开启代码支持
//     */
//   @Bean
//   public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//      AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//      authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//      return authorizationAttributeSourceAdvisor;
//   }
//   
//   /**
//    * cookie对象
//    * @return
//    */
//   @Bean
//   public SimpleCookie rememberMeCookie(){
//      // cookie的名称，对应前端的checkbox的name = rememberMe
//      SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//      // 记住此cookie有效时间30天,单位秒: 60 * 60 * 24 * 30
//      // maxAge=-1表示浏览器关闭时失效此Cookie
//      simpleCookie.setMaxAge(259200);
//      // httpOnly=true,则客户端不会暴露给客户端脚本代码
//      // 使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击
//      simpleCookie.setHttpOnly(true);
//      return simpleCookie;
//   }
//  
//   /**
//    * cookie管理对象
//    * @return
//    */
//   @Bean
//   public CookieRememberMeManager rememberMeManager(){
//      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//      //rememberme cookie加密的密钥 建议每个项目都不一样 
//      //默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
//      //KeyGenerator keygen = KeyGenerator.getInstance("AES");
//      //SecretKey deskey = keygen.generateKey();
//      //System.out.println(Base64.encodeToString(deskey.getEncoded()));
//      byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
//      cookieRememberMeManager.setCipherKey(cipherKey);
//      cookieRememberMeManager.setCookie(rememberMeCookie());
//      return cookieRememberMeManager;
//   }
//   
//   @Bean
//   public CustomSessionManager customSessionManager() {
//	   return new CustomSessionManager(new MemorySessionDAO());
//   }
//}
