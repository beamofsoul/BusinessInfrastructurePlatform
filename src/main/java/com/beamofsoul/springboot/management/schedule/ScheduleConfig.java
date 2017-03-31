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

// * 第一位，表示秒，取值0-59
// * 第二位，表示分，取值0-59
// * 第三位，表示小时，取值0-23
// * 第四位，日期天/日，取值1-31
// * 第五位，日期月份，取值1-12
// * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
//           另外：1表示星期天，2表示星期一。
// * 第7为，年份，可以留空，取值1970-2099
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
