package com.beamofsoul.springboot.management.annotation;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.DetailControl;
import com.beamofsoul.springboot.entity.Role;
import com.beamofsoul.springboot.management.query.QueryCriteria;
import com.beamofsoul.springboot.service.DetailControlService;
import com.beamofsoul.springboot.service.RoleService;

@Aspect
@Component
public class ThinAspect {

	public static final Logger LOGGER = LoggerFactory.getLogger(ThinAspect.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DetailControlService detailControlService;
	
	@Pointcut(value="@annotation(thin)")
	public void locateAnnotation(Thin thin) {}
	
	@Before("locateAnnotation(thin)")
	public void doBefore(JoinPoint joinPoint, Thin thin) {}
	
	@Around("locateAnnotation(thin)")
	public Object doAround(ProceedingJoinPoint joinPoint, Thin thin) {
		Class<?> clazz = thin.value();
		Object returnValueObject = null;
		Collection<? extends GrantedAuthority> grantedAuthorities = null;
		List<DetailControl> dcs = null;
		Set<String> column = new HashSet<String>();
		QueryCriteria qc = null;
		
		try {
			//获取当前用户权限
			grantedAuthorities = SecurityContextHolder
			.getContext().getAuthentication().getAuthorities();
			
			Object[] args = joinPoint.getArgs();
			for (Object object : args) {
				if (object instanceof QueryCriteria) {
					qc = (QueryCriteria) object;
					break;
				}
			}
			
			if (qc != null) {
				
			}
			
			
			
			
			//获取目标方法返回值
			returnValueObject = joinPoint.proceed();
			//根据不同方法返回值类型进行不同方式的过滤
			if (returnValueObject != null) {
				if (returnValueObject instanceof ModelAndView) {
					ModelAndView mav = (ModelAndView)returnValueObject;
					mav.addObject("column", "WO SHI COLUMN!");
				} else if (returnValueObject instanceof JSONObject) {
					//遍历角色,并根据角色和目标实体类获取DetailControl表中对应的限制关系
					for (GrantedAuthority grantedAuthority : grantedAuthorities) {
						//获取角色的限制记录
						//获取角色名称(去掉前置字符ROLE_),并将角色名转换成全小写
						//根据角色名获取角色对象
						dcs = getRelatedDetailControlList(
								clazz,roleService.findByName(grantedAuthority.getAuthority()
										.split("_")[1].toLowerCase()));
						//遍历获得该角色不能显示的数据列
						getHiddenColumns(column, dcs);
					}
					//将不能显示的数据列装入Json对象中以便页面使用
					JSONObject jobj = (JSONObject)returnValueObject;
					jobj.put("column", column);
				} else if (returnValueObject instanceof String) {
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
					request.setAttribute("column", "WO SHI REQUEST COLUMN!");
				} else if (returnValueObject instanceof Page<?>) {
					throw new RuntimeException("Miserly annotation donot be supported once target method returning a Page<?> object as a result...");
				} else {
					throw new RuntimeException("Miserly annotation cannnot recognize result-value-type of target method...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnValueObject;
	}

	private void getHiddenColumns(Set<String> column, List<DetailControl> dcs) {
		for (DetailControl detailControl : dcs)
			for (String uc : detailControl.getUnavailableColumns().split(","))
				column.add(uc);
	}

	private List<DetailControl> getRelatedDetailControlList(Class<?> clazz,
			Role role) {
		List<DetailControl> dcs = detailControlService.
			findByRoleIdAndEntityClass(
					role.getId(), clazz.getName(), true);
		return dcs;
	}
}
