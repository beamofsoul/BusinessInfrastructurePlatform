package com.beamofsoul.springboot.management.cache;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;

/**
 * 全局共享缓存配置类
 * <p>使用EhCache实现, 开放共享使得Spring与Apache Shiro共用一个缓存池</p>
 * @author MingshuJian
 */
public abstract class GlobalSharedEhCacheConfiguration {

	@Bean("ehcache")
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setShared(true);
		return ehCacheManagerFactoryBean;
	}
	
	/**
	 * Spring使用的Cache 
	 */
	@Bean(name = "cacheManager")  
	public EhCacheCacheManager ehCacheCacheManager(){  
	    EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();  
	    ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());  
	    return ehCacheCacheManager;  
	}
	
//	/**
//	 * Apache Shiro使用的Cache
//	 * 具体方法在ShiroCasConfiguration类中实现
//	 */
//	public abstract EhCacheManager ehCacheManager();
}
