package com.zhixingbus.server.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.Const;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.zhixingbus.server.controller.AdminController;
import com.zhixingbus.server.controller.UploadFileController;
import com.zhixingbus.server.controller.WebController;
import com.zhixingbus.server.model.AdminModel;
import com.zhixingbus.server.model.special.AuditionModel;
import com.zhixingbus.server.model.special.CampusModel;
import com.zhixingbus.server.model.special.ClassInfoModel;
import com.zhixingbus.server.model.special.ClassRcordModel;
import com.zhixingbus.server.model.special.ClassTypeModel;
import com.zhixingbus.server.model.special.ClassroomModel;
import com.zhixingbus.server.model.special.ConsultDetailModel;
import com.zhixingbus.server.model.special.ConsultModel;
import com.zhixingbus.server.model.special.EmployeeInfoModel;
import com.zhixingbus.server.model.special.EmployeeModel;
import com.zhixingbus.server.model.special.GradeModel;
import com.zhixingbus.server.model.special.PositionModel;
import com.zhixingbus.server.model.special.RegistrationFeeModel;
import com.zhixingbus.server.model.special.RegorderModel;
import com.zhixingbus.server.model.special.RegorderdetailModel;
import com.zhixingbus.server.model.special.SchoolModel;
import com.zhixingbus.server.model.special.StudentClassModel;
import com.zhixingbus.server.model.special.StudentGoClassModel;
import com.zhixingbus.server.model.special.StudentInfoModel;
import com.zhixingbus.server.model.special.StudentModel;
import com.zhixingbus.server.model.special.StudentPayModel;
import com.zhixingbus.server.model.special.StudentRegistrationModel;
import com.zhixingbus.server.model.special.SubjectModel;
import com.zhixingbus.server.model.special.TimeslotModel;
import com.zhixingbus.server.model.special.VPayDetailModel;
import com.zhixingbus.server.model.special.VstudentsNumberModel;


public class ServerConfig extends JFinalConfig {

	@Override
	/**
	 * 配置常量
	 */
	public void configConstant(Constants constants) {
		// TODO Auto-generated method stub
		loadPropertyFile("WebConfig.txt");
		boolean devMode = getPropertyToBoolean("devMode", true);
		constants.setDevMode(devMode);	
		//默认10M,此处设置为最大100M
		constants.setMaxPostSize(10*Const.DEFAULT_MAX_POST_SIZE);
		Prop prop = PropKit.use("service.properties");
		ServiceConfig.PoseidonServiceHost = prop.get("poseidonServiceHost");
		ServiceConfig.RedPackServiceHost = prop.get("RedPackServiceHost");
		ServiceConfig.AresServiceHost = prop.get("AresServiceHost");
		ServiceConfig.phones = prop.get("phones");
		ServiceConfig.apolloServiceHost = prop.get("apolloServiceHost");

	}

	@Override
	/**
	 * 配置路由
	 */
	public void configRoute(Routes routes) {
		routes.add("/", WebController.class,"/WEB-INF/html/external/www");
		routes.add("/admin", AdminController.class, "/WEB-INF/html");
		routes.add("/upload",UploadFileController.class,"/WEB-INF/html");
	}

	@Override
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins plugins) {
		// TODO Auto-generated method stub
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		{
			dp.addFilter(new StatFilter());
			WallFilter wall = new WallFilter();
			wall.setDbType("mysql");
			dp.addFilter(wall);
			dp.setTestOnBorrow(true);
			dp.setTestOnReturn(true);
			dp.setMaxWait(20000);
		}
		plugins.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		boolean showSql = getPropertyToBoolean("showSql", true);
		arp.setShowSql(showSql);
		{
			arp.addMapping("tb_admin_x", AdminModel.class);
			arp.addMapping("tb_student", StudentModel.class);
			arp.addMapping("tb_studentInfo", StudentInfoModel.class);
			arp.addMapping("tb_position", PositionModel.class);
			arp.addMapping("tb_classType", ClassTypeModel.class);
			arp.addMapping("tb_grade", GradeModel.class);
			arp.addMapping("tb_subject", SubjectModel.class);
			arp.addMapping("tb_timeslot", TimeslotModel.class);
			arp.addMapping("tb_employee", EmployeeModel.class);
			arp.addMapping("tb_employeeInfo", EmployeeInfoModel.class);
			arp.addMapping("tb_classroom", ClassroomModel.class);
			arp.addMapping("tb_campus", CampusModel.class);
			arp.addMapping("tb_registrationFee", RegistrationFeeModel.class);
			arp.addMapping("tb_classInfo", ClassInfoModel.class);
			arp.addMapping("tb_studentClass", StudentClassModel.class);
			arp.addMapping("tb_studentGoClass", StudentGoClassModel.class);
			arp.addMapping("tb_studentPay", StudentPayModel.class);
			arp.addMapping("tb_studentRegistration", StudentRegistrationModel.class);
			arp.addMapping("tb_regorder", RegorderModel.class);
			arp.addMapping("tb_regorderdetail", RegorderdetailModel.class);
			arp.addMapping("tb_classRcord", ClassRcordModel.class);
			arp.addMapping("tv_pay_detail", VPayDetailModel.class);
			arp.addMapping("tb_school", SchoolModel.class);
			arp.addMapping("tb_audition", AuditionModel.class);
			arp.addMapping("tb_consult", ConsultModel.class);
			arp.addMapping("tb_consult_detail", ConsultDetailModel.class);
			arp.addMapping("tv_students_number", VstudentsNumberModel.class);
		}
		plugins.add(arp);
	}

	@Override
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}
	

}
