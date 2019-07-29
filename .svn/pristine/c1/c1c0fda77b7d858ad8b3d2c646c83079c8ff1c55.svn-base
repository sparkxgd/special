package com.zhixingbus.server.controller.maintenance;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.jfinal.plugin.IPlugin;

public class QuartzPlugin implements IPlugin {
	private SchedulerFactory mSchedulerFactory;
	private Scheduler mScheduler;

	public QuartzPlugin() {
	}

	@Override
	public boolean start() {
		mSchedulerFactory = new StdSchedulerFactory();
		try {
			mScheduler = mSchedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		JobDetail jobDetail = JobBuilder.newJob(MaintenanceController.class).withIdentity("testJob_1", "group_1").build();
		Trigger trigger = null;
		trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "group_1").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5) // 时间间隔
						.repeatForever()).build();
		try {
			mScheduler.scheduleJob(jobDetail, trigger);
			mScheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean stop() {
		try {
			mScheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return true;
	}

}
