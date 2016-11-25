package com.beamofsoul.springboot.management.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务
 * @author MingshuJian
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {

//	//每5秒执行一次
//	@Scheduled(cron="0/5 * * * * ?")
//	public void scheduler() {
//		System.out.println("#### 自动任务 - ScheduleConfig.scheduler(): " + new Date());
//	}
//	
//	//每2秒执行一次
//	@Scheduled(cron="0/2 * * * * ?")
//	public void scheduler2() {
//		System.out.println("#### 自动任务 - ScheduleConfig.scheduler2(): " + new Date());
//	}
}
