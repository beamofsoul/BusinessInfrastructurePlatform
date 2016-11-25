package com.beamofsoul.springboot.management.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.beamofsoul.springboot.management.util.RolePermissionsMapping;
import com.beamofsoul.springboot.service.RolePermissionService;

/**
 * 实现自定义CommandLineRunner
 * 自定义服务器启动时执行某些操作
 * SpringBoot在应用程序启动后,会遍历CommondLineRunner接口的实例并运行它们的run方法
 * @Order 该注解用于排序多个自定义实现的CommandLineRunner实例,其中数值越小,越先执行
 * @author MingshuJian
 */
@Component
@Order(2)
public class CustomDataFillingStartupRunner implements CommandLineRunner {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomDataFillingStartupRunner.class);
	
	@Autowired
	private RolePermissionService rolePermissionService;

	@Override
	public void run(String... args) throws Exception {
		/**
		 * 这里的args就是程序启动的时候进行设置的:
		 * 		eg. SpringApplication.run(App.class, new String[]{"hello,","world"});
		 * 
		 * eclipse中给java应用传args参数的方法如下:
		 * 		1.先写好Java代码，比如文件名为IntArrqy.java
		 * 		2.在工具栏或菜单上点run as下边有个Run Configuration
		 * 		3.在弹出窗口点选第二个标签arguments
		 * 		4.把输入的参数写在program argumenst,多个参数使用空格隔开
		 * 完成后点run即可通过运行结果看到参数使用情况了。
		 */
		LOGGER.info("MyStartupRunner.run() - 服务启动执行,执行加载数据等操作...");
		
		LOGGER.info("MyStartupRunner.run() - 开始加载用户权限映射数据...");
		RolePermissionsMapping
			.fill(rolePermissionService.findAllRolePermissionMapping());	
		LOGGER.info("MyStartupRunner.run() - 用户权限映射数据加载完毕...");
	}
}
