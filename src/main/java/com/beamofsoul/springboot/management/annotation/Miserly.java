package com.beamofsoul.springboot.management.annotation;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 根据角色控制数据显示细节
 * <p>1. 控制哪些数据类针对特定角色不能显示</p>
 * <p>2. 控制根据特定角色对相应的数据进行筛选过滤</p>
 * <p>PS: 该注解需要的传递唯一参数为被限制实体类的类型</p>
 * @see com.beamofsoul.springboot.management.annotation.MiserlyAspect 
 * @author MingshuJian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Miserly {

	/**
	 * 目标实体类类型 
	 */
	Class<? extends Serializable> value();
}
