package com.zhixingbus.server.controller.maintenance;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zhixingbus.server.controller.BaseController;

public class MaintenanceController extends BaseController implements Job  {

	/**
	 * 查询一条线路有多少辆车
	 */
	public void query_road_bus() {
		MaintenanceService.queryRoadBuses(this);
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//System.out.println(new Date());
		//MaintenanceService.checkRoadGps(this);
	}
}
