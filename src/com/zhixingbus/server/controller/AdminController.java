package com.zhixingbus.server.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.bean.DwzResult;
import com.zhixingbus.server.bean.special.EchartData;
import com.zhixingbus.server.config.Constans;
import com.zhixingbus.server.interceptor.AdminInterceptor;
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
import com.zhixingbus.server.utils.CookieUtils;
import com.zhixingbus.server.utils.DateUtils;
import com.zhixingbus.server.utils.MD5Util;
import com.zhixingbus.server.utils.StringUtil;
import com.zhixingbus.server.utils.Utils;

@Before(AdminInterceptor.class)
public class AdminController extends BaseController {
	/**
	 * 用户登录
	 */
	@ClearInterceptor
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		if (StringUtil.isBlankOrEmpty(username)
				&& StringUtil.isBlankOrEmpty(password)) {
			setAttr("error", "请输入用户名和密码！");
			render("inside/login.html");
			return;
		} else if (StringUtil.isBlankOrEmpty(username)) {
			setAttr("error", "错误！用户名不能为空！");
			render("inside/login.html");
			return;
		} else if (StringUtil.isBlankOrEmpty(password)) {
			setAttr("error", "错误！密码错误！");
			render("inside/login.html");
			return;
		}
		AdminModel admin = AdminModel.findByName(username);
		if (admin == null) {
			setAttr("error", "错误！没有该用户名！");
			render("inside/login.html");
			return;
		}

		String md5Password = MD5Util.string2MD5(password);

		if (admin.getPassword().equals(md5Password)) { // 密码正确
			String cookieString = CookieUtils.createUserLoginCookieString(
					username, md5Password);
			setCookie(Constans.cookieName, cookieString,
					Constans.cookie_active_time);
			redirect("/admin/main");
		} else {
			// 密码错误
			setAttr("error", "错误！密码错误！");
			render("inside/login.html");
			return;
		}
	}

	/**
	 * 用户退出登录
	 */
	public void loginout() {
		removeCookie(Constans.cookieName);
		render("inside/login.html");
	}

	/**
	 * 登录成功的首页
	 */
	public void main() {
		String userName = CookieUtils
				.getNameFromCookie(getCookie(Constans.cookieName));

		String time = DateUtils.getCurrentTime();

		setAttr("userName", userName);
		setAttr("time", time);
		render("inside/index.html");
	}

	/**
	 * 统计在校学生人数
	 */
	public void getStudentsNumber() {

		List<VstudentsNumberModel> list = VstudentsNumberModel.getList();
		String[] categories = new String[list.size()];
		Long[] values = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			categories[i] = list.get(i).gety() + "-" + list.get(i).getm();
			values[i] = list.get(i).getval();
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("categories", categories);
		json.put("values", values);
		renderJson(json);
	}

	/**
	 * 统计在校学生学习科目人数
	 */
	public void getSubjectNum() {

		List<ClassInfoModel> list = ClassInfoModel.getList();
		List<EchartData> dateList = new ArrayList<EchartData>();
		for (int i = 0; i < list.size(); i++) {
			EchartData data = new EchartData();
			data.setName(list.get(i).getsubjectName());
			data.setValue(list.get(i).getstudentNum());
			dateList.add(data);
		}
		renderJson(dateList);
	}

	/**
	 * 统计在校学生学习年级人数
	 */
	public void getGradeNum() {
		List<ClassInfoModel> list = ClassInfoModel.getGradeList();
		List<EchartData> dateList = new ArrayList<EchartData>();
		for (int i = 0; i < list.size(); i++) {
			EchartData data = new EchartData();
			data.setName(list.get(i).getgradeName());
			data.setValue(list.get(i).getstudentNum());
			dateList.add(data);
		}
		renderJson(dateList);
	}

	/**
	 * 只是用来显示后台的左边iframe的页面
	 */
	public void left() {
		String name = CookieUtils.getNameFromCookie(getCookie("admin"));
		setAttr("name", name);
		render("left.html");
	}

	/**
	 * 主页的报表
	 */
	public void report() {
		String time = DateUtils.getCurrentTime();

		setAttr("time", time);
		render("inside/www/report.html");
	}

	/**
	 * 测试
	 */
	public void test() {
		render("inside/www/db_widget.html");
	}

	/**
	 * 测试
	 */
	public void dwzOrgLookup() {
		render("inside/www/database/dwzOrgLookup.html");
	}

	/**
	 * 测试
	 */
	public void test1() {
		ArrayList<DwzResult> list = new ArrayList<DwzResult>();
		for (int i = 0; i < 6; i++) {
			DwzResult result = new DwzResult();
			result.setStatusCode("" + i);
			result.setMessage("好的");
			list.add(result);
		}
		renderJson(list);
	}

	/**
	 * 测试
	 */
	public void testpost() {
		String navTabId = getPara("navTabId");
		String name = getPara("searchKey", "");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("学生姓名不能为空！");
			renderJson(result);
			return;
		}
		boolean op = true;
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/*
	 * 查询学生信息
	 */
	public void students() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		int sex = getParaToInt("sex", 0);
		String searchKey = getPara("searchKey", "");

		Page<StudentModel> page = StudentModel.getList(pageNumber, pageSize,
				searchKey, sex);

		setAttr("page", page);
		setAttr("navTabId", "students");
		render("inside/www/student/students.html");

	}

	/**
	 * 添加学生信息页面
	 */
	public void student_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/student/student_add.html");
	}

	/**
	 * 保存学生信息
	 */
	public void studentSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		Date borthday = getParaToDate("borthday");
		int sex = getParaToInt("sex", 0);

		String addr = getPara("addr");
		int school = getParaToInt("school.id");
		int grade = getParaToInt("grade.id");
		String classes = getPara("classes");
		String tel = getPara("tel");
		String parentTel = getPara("parentTel");
		String qq = getPara("qq");
		String remark = getPara("remark");
		String weixin = getPara("weixin");
		int state = getParaToInt("state", 1);// 默认在校

		DwzResult result = new DwzResult();
		if (StudentModel.exitSameName(name, sex)) {
			result.setStatusCode("1");
			result.setMessage("学生姓名已经存在！");
			renderJson(result);
			return;
		}

		StudentModel m = new StudentModel();
		int id = StudentModel.getMaxId();
		m.setId(id);
		m.setName(name);
		m.setSex(sex);
		m.setCreatTime(new Date());
		m.setBorthday(borthday);

		StudentInfoModel info = new StudentInfoModel();
		info.setId(id);
		info.setaddr(addr);
		info.setgrade(grade);
		info.settel(tel);
		info.setParentTel(parentTel);
		info.setqq(qq);
		info.setschool(school);
		info.setclass(classes);
		info.setstate(state);
		info.setweixin(weixin);
		info.setremark(remark);
		info.setupdateTime(new Date());

		boolean op = StudentModel.save(m, info);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开学生信息更新页面
	 */
	public void student_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		StudentInfoModel model = StudentInfoModel.getModelById(id);
		StringBuffer selectSex = new StringBuffer("<select name='sex'>");
		StringBuffer selectstateStr = new StringBuffer("<select name='state'>");
		if (model != null) {
			int sex = model.getSex();
			switch (sex) {
			case 1:
				selectSex
						.append("<option value='1' selected='selected' >男</option>");
				selectSex.append("<option value='2' >女</option>");
				selectSex.append("<option value='0' >未知</option>");
				break;
			case 2:
				selectSex.append("<option value='1' >男</option>");
				selectSex
						.append("<option value='2' selected='selected'>女</option>");
				selectSex.append("<option value='0' >未知</option>");
				break;

			default:
				selectSex.append("<option value='1'  >男</option>");
				selectSex.append("<option value='2' >女</option>");
				selectSex
						.append("<option value='0' selected='selected' >未知</option>");
				break;
			}
			int state = model.getstate();
			switch (state) {
			case 1:
				selectstateStr
						.append("<option value='1' selected='selected' >在校</option>");
				selectstateStr.append("<option value='0' >离校</option>");
				break;
			default:
				selectstateStr.append("<option value='1'  >在校</option>");
				selectstateStr
						.append("<option value='0' selected='selected' >离校</option>");
				break;
			}
		}
		selectSex.append("</select>");
		selectstateStr.append("</select>");
		setAttr("selectstateStr", selectstateStr.toString());
		setAttr("selectSex", selectSex.toString());
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/student/student_update.html");
	}

	/**
	 * 更新学生信息
	 */
	public void studentUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");
		Date borthday = getParaToDate("borthday");
		int sex = getParaToInt("sex", 0);

		String addr = getPara("addr");
		int school = getParaToInt("school.id");
		int grade = getParaToInt("grade.id");
		String classes = getPara("classes");
		String tel = getPara("tel");
		String parentTel = getPara("parentTel");
		String qq = getPara("qq");
		String remark = getPara("remark");
		String weixin = getPara("weixin");
		int state = getParaToInt("state", 1);// 默认在校

		DwzResult result = new DwzResult();
		boolean op = StudentModel.update(id, name, sex, borthday, addr, school,
				grade, classes, tel, parentTel, qq, weixin, remark, state);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.职位信息tb_position
	 */
	public void positions() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<PositionModel> page = PositionModel.getList(pageNumber, pageSize,
				searchKey);

		setAttr("page", page);
		setAttr("navTabId", "positions");
		render("inside/www/basedata/positions.html");

	}

	/**
	 * 添加职位信息页面
	 */
	public void position_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/position_add.html");
	}

	/**
	 * 保存职位信息
	 */
	public void positionSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = PositionModel.save(name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开职位信息更新页面
	 */
	public void position_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		PositionModel model = PositionModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/position_update.html");
	}

	/**
	 * 更新职位信息
	 */
	public void positionUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = PositionModel.update(id, name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.报读班级类型信息tb_position
	 */
	public void classTypes() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<ClassTypeModel> page = ClassTypeModel.getList(pageNumber,
				pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "classTypes");
		render("inside/www/basedata/classTypes.html");

	}

	/**
	 * 添加报读班级类型信息页面
	 */
	public void classType_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/classType_add.html");
	}

	/**
	 * 保存报读班级类型信息
	 */
	public void classTypeSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = ClassTypeModel.save(name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开报读班级类型信息更新页面
	 */
	public void classType_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		ClassTypeModel model = ClassTypeModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/classType_update.html");
	}

	/**
	 * 更新报读班级类型信息
	 */
	public void classTypeUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = ClassTypeModel.update(id, name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.报读年级信息
	 */
	public void grades() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<GradeModel> page = GradeModel.getList(pageNumber, pageSize,
				searchKey);

		setAttr("page", page);
		setAttr("navTabId", "grades");
		render("inside/www/basedata/grades.html");

	}

	/**
	 * 添加报读年级信息页面
	 */
	public void grade_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/grade_add.html");
	}

	/**
	 * 保存报读年级信息
	 */
	public void gradeSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = GradeModel.save(name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开报读年级信息更新页面
	 */
	public void grade_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		GradeModel model = GradeModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/grade_update.html");
	}

	/**
	 * 更新报读年级信息
	 */
	public void gradeUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = GradeModel.update(id, name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.报科目信息
	 */
	public void subjects() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<SubjectModel> page = SubjectModel.getList(pageNumber, pageSize,
				searchKey);

		setAttr("page", page);
		setAttr("navTabId", "subjects");
		render("inside/www/basedata/subjects.html");

	}

	/**
	 * 添加报读科目信息页面
	 */
	public void subject_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/subject_add.html");
	}

	/**
	 * 保存报读科目信息
	 */
	public void subjectSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = SubjectModel.save(name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开报读科目信息更新页面
	 */
	public void subject_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		SubjectModel model = SubjectModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/subject_update.html");
	}

	/**
	 * 更新报读科目信息
	 */
	public void subjectUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = SubjectModel.update(id, name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.上课时间段信息
	 */
	public void timeslots() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<TimeslotModel> page = TimeslotModel.getList(pageNumber, pageSize,
				searchKey);

		setAttr("page", page);
		setAttr("navTabId", "timeslots");
		render("inside/www/basedata/timeslots.html");

	}

	/**
	 * 添加报读上课时间段信息页面
	 */
	public void timeslot_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/timeslot_add.html");
	}

	/**
	 * 保存报读上课时间段信息
	 */
	public void timeslotSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = TimeslotModel.save(name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开报读上课时间段信息更新页面
	 */
	public void timeslot_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		TimeslotModel model = TimeslotModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/timeslot_update.html");
	}

	/**
	 * 更新报读上课时间段信息
	 */
	public void timeslotUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = TimeslotModel.update(id, name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询学校信息
	 */
	public void schools() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<SchoolModel> page = SchoolModel.getList(pageNumber, pageSize,
				searchKey);

		setAttr("page", page);
		setAttr("navTabId", "schools");
		render("inside/www/basedata/schools.html");

	}

	/**
	 * 添加学校信息页面
	 */
	public void school_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/school_add.html");
	}

	/**
	 * 保存学校信息
	 */
	public void schoolSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = SchoolModel.save(name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开学校信息更新页面
	 */
	public void school_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		SchoolModel model = SchoolModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/school_update.html");
	}

	/**
	 * 更新学校信息
	 */
	public void schoolUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = SchoolModel.update(id, name);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.校区信息
	 */
	public void campuss() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<CampusModel> page = CampusModel.getList(pageNumber, pageSize,
				searchKey);

		setAttr("page", page);
		setAttr("navTabId", "campuss");
		render("inside/www/basedata/campuss.html");

	}

	/**
	 * 添加校区信息页面
	 */
	public void campus_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/campus_add.html");
	}

	/**
	 * 保存校区信息
	 */
	public void campusSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		String addr = getPara("addr");
		int seats = getParaToInt("seats");
		int employeeId = getParaToInt("employeeId");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = CampusModel.save(name, seats, employeeId, addr);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开校区信息更新页面
	 */
	public void campus_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		CampusModel model = CampusModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/campus_update.html");
	}

	/**
	 * 更新报校区目信息
	 */
	public void campusUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");
		String addr = getPara("addr");
		int seats = getParaToInt("seats");
		int state = getParaToInt("state");
		int employeeId = getParaToInt("employeeId");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = CampusModel.update(id, name, seats, employeeId, addr,
				state);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.职工信息
	 */
	public void employees() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		int sex = getParaToInt("sex", 0);

		Page<EmployeeInfoModel> page = EmployeeInfoModel.getList(pageNumber,
				pageSize, searchKey, sex);

		setAttr("page", page);
		setAttr("navTabId", "employees");
		render("inside/www/employee/employees.html");

	}

	/**
	 * 添加职工信息页面
	 */
	public void employee_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/employee/employee_add.html");
	}

	/**
	 * 保存职工信息
	 */
	public void employeeSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		Date borthday = getParaToDate("borthday");
		int sex = getParaToInt("sex", 0);
		String identification = getPara("identification");
		String nativePlace = getPara("nativePlace");
		int type = getParaToInt("type");
		int Positionid = getParaToInt("Position.id");
		String tel = getPara("tel");
		String qq = getPara("qq");
		String weixin = getPara("weixin");
		String addr = getPara("addr");
		String homeAddr = getPara("homeAddr");
		String skill = getPara("skill");
		String major = getPara("major");
		//		
		EmployeeModel e = new EmployeeModel();
		int id = EmployeeModel.getMaxId();
		e.setId(id);
		e.setBorthday(borthday);
		e.setName(name);
		e.setNativePlace(nativePlace);
		e.setSex(sex);
		e.setCreatTime(new Date());
		e.setIdentification(identification);

		EmployeeInfoModel i = new EmployeeInfoModel();
		i.setId(id);
		i.setaddr(addr);
		i.sethomeAddr(homeAddr);
		i.setinTime(new Date());
		i.setmajor(major);
		i.setpositionId(Positionid);
		i.setskill(skill);
		i.setweixin(weixin);
		i.setqq(qq);
		i.setstate(0);// 0在职，1离职
		i.settype(type);
		i.settel(tel);
		i.setupdateTime(new Date());

		DwzResult result = new DwzResult();

		boolean op = EmployeeModel.save(e, i);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开职工信息更新页面
	 */
	public void employee_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		EmployeeModel model = EmployeeModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/employee/employee_update.html");
	}

	/**
	 * 更新职工信息
	 */
	public void employeeUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");
		Date borthday = getParaToDate("borthday");
		int sex = getParaToInt("sex", 0);
		String identification = getPara("identification");
		String nativePlace = getPara("nativePlace");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = EmployeeModel.update(id, name, sex, borthday,
				identification, nativePlace);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.教室信息
	 */
	public void classrooms() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<ClassroomModel> page = ClassroomModel.getList(pageNumber,
				pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "classrooms");
		render("inside/www/basedata/classrooms.html");

	}

	/**
	 * 添加教室信息页面
	 */
	public void classroom_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/classroom_add.html");
	}

	/**
	 * 保存教室信息
	 */
	public void classroomSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		int seats = getParaToInt("seats");
		int employeeId = getParaToInt("employeeId");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = ClassroomModel.save(name, seats, employeeId);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开教室信息更新页面
	 */
	public void classroom_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		ClassroomModel model = ClassroomModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/classroom_update.html");
	}

	/**
	 * 更新教室目信息
	 */
	public void classroomUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String name = getPara("name");
		int seats = getParaToInt("seats");
		int state = getParaToInt("state");
		int employeeId = getParaToInt("employeeId");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("名称不能为空！");
			renderJson(result);
			return;
		}
		boolean op = ClassroomModel.update(id, name, seats, employeeId, state);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 查询4.报名费用信息
	 */
	public void registrationFees() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<RegistrationFeeModel> page = RegistrationFeeModel.getList(
				pageNumber, pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "registrationFees");
		render("inside/www/basedata/registrationFees.html");

	}

	/**
	 * 添加报名费用信息页面
	 */
	public void registrationFee_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/registrationFee_add.html");
	}

	/**
	 * 保存报名费用信息
	 */
	public void registrationFeeSave() {
		String navTabId = getPara("navTabId");
		String price = getPara("price");
		String remark = getPara("remark");
		int gradeId = getParaToInt("Grade.id");
		int classTypeId = getParaToInt("ClassType.id");
		DwzResult result = new DwzResult();
		BigDecimal pri = new BigDecimal(price);
		boolean op = RegistrationFeeModel.save(pri, classTypeId, gradeId,
				remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开报名费用信息更新页面
	 */
	public void registrationFee_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		RegistrationFeeModel model = RegistrationFeeModel.getModelById(id);
		setAttr("m", model);
		setAttr("navTabId", navTabId);
		render("inside/www/basedata/registrationFee_update.html");
	}

	/**
	 * 更新报名费用信息
	 */
	public void registrationFeeUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		String price = getPara("price");
		String remark = getPara("remark");
		DwzResult result = new DwzResult();
		BigDecimal pri = new BigDecimal(price);
		boolean op = RegistrationFeeModel.update(id, pri, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 班级信息
	 */
	public void classInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String teacher = getPara("teacher", "");

		Page<ClassInfoModel> page = ClassInfoModel.getList(pageNumber,
				pageSize, searchKey, teacher);

		setAttr("page", page);
		setAttr("navTabId", "classInfos");
		render("inside/www/school/classInfos.html");

	}

	/**
	 * 结束班级课程
	 */
	public void endClass() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		DwzResult result = new DwzResult();
		boolean op = ClassInfoModel.endClass(id);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/*
	 * 班级信息
	 */
	public void classAllInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String teacher = getPara("teacher", "");

		Page<ClassInfoModel> page = ClassInfoModel.getList_two(pageNumber,
				pageSize, searchKey, teacher);

		setAttr("page", page);
		setAttr("searchKey", searchKey);
		setAttr("teacher", teacher);
		setAttr("navTabId", "classAllInfos");
		render("inside/www/school/class_all_info.html");

	}

	/**
	 * 添加班级信息页面
	 */
	public void classInfo_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/school/classInfo_add.html");
	}

	/**
	 * 保存班级信息
	 */
	public void classInfoSave() {
		String navTabId = getPara("navTabId");
		String name = getPara("name");
		int classTypeId = getParaToInt("c.id");
		int gradeId = getParaToInt("g.id");
		int employeeId = getParaToInt("e.id");
		int subjectId = getParaToInt("s.id");
		int timeSlot = getParaToInt("t.id");
		DwzResult result = new DwzResult();

		if (ClassInfoModel.exitSameName(name)) {
			result.setStatusCode("1");
			result.setMessage("班级名称已经存在！");
			renderJson(result);
			return;
		}
		boolean op = ClassInfoModel.save(name, classTypeId, gradeId,
				employeeId, subjectId, timeSlot);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开班级信息更新页面
	 */
	public void classInfo_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		StudentClassModel model = StudentClassModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/school/classInfo_update.html");
	}

	/**
	 * 更新班级信息
	 */
	public void classInfoUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		int classTypeId = getParaToInt("classTypeId");
		int gradeId = getParaToInt("gradeId");
		int employeeId = getParaToInt("employeeId");
		int subjectId = getParaToInt("subjectId");
		int timeSlot = getParaToInt("timeSlot");
		Date endTime = DateUtils.string2Date(getPara("endTime"));
		Date startTime = DateUtils.string2Date(getPara("startTime"));

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(navTabId)) {
			result.setStatusCode("1");
			result.setMessage("price不能为空！");
			renderJson(result);
			return;
		}
		boolean op = ClassInfoModel.update(id, classTypeId, gradeId,
				employeeId, subjectId, timeSlot, endTime, startTime);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 学生班级信息
	 */
	public void studentClasss() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<StudentClassModel> page = StudentClassModel.getList(pageNumber,
				pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "studentClasss");
		render("inside/www/student/studentClasss.html");

	}

	/**
	 * 学生离开班级
	 */
	public void outClass() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		DwzResult result = new DwzResult();
		boolean op = StudentClassModel.outClass(id);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/*
	 * 学生班级信息
	 */
	public void studentClassAll() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<StudentClassModel> page = StudentClassModel.getList_two(
				pageNumber, pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "studentClassAll");
		render("inside/www/school/student_class_all.html");

	}

	/*
	 * 查看班级里人学生
	 */
	public void viweClassStudent() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String classId = getPara("classId");

		Page<StudentClassModel> page = StudentClassModel.getList_three(
				pageNumber, pageSize, classId);

		setAttr("page", page);
		setAttr("navTabId", "viweClassStudent");
		render("inside/www/school/viwe_class_student.html");

	}

	/**
	 * 添加学生班级信息页面
	 */
	public void studentClass_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/student/studentClass_add.html");
	}

	/**
	 * 保存学生班级信息
	 */
	public void studentClassSave() {
		String navTabId = getPara("navTabId");
		String studentRegId = getPara("studentRegId");// 学生报名id
		int studentId = getParaToInt("studentId");
		int classinfoId = getParaToInt("classinfoId");
		DwzResult result = new DwzResult();

		StudentClassModel m = StudentClassModel.getModel(studentId,
				classinfoId, 0);// 查找是否已经有在读的班级

		if (m != null) {
			result.setStatusCode("1");
			result.setMessage("操作失败！该学生已经在此班级，不需要重复加入");
			renderJson(result);
			return;
		}

		boolean op = StudentClassModel.save(studentId, classinfoId,
				studentRegId);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开学生班级信息更新页面
	 */
	public void studentClass_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		StudentClassModel model = StudentClassModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/student/studentClass_update.html");
	}

	/**
	 * 更新学生班级信息
	 */
	public void studentClassUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		int studentId = getParaToInt("studentId");
		int classinfoId = getParaToInt("classinfoId");
		Date intTime = DateUtils.string2Date(getPara("intTime"));
		Date outTime = DateUtils.string2Date(getPara("outTime"));
		String remark = getPara("remark");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(navTabId)) {
			result.setStatusCode("1");
			result.setMessage("price不能为空！");
			renderJson(result);
			return;
		}
		boolean op = StudentClassModel.update(id, studentId, classinfoId,
				intTime, outTime, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 学生上课信息
	 */
	public void studentGoClasss() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<StudentGoClassModel> page = StudentGoClassModel.getList(
				pageNumber, pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "studentGoClasss");
		render("inside/www/student/studentGoClasss.html");

	}

	/*
	 * 学生上课信息
	 */
	public void studentGoClassRcord() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String searchKey2 = getPara("searchKey2", "");

		Page<StudentGoClassModel> page = StudentGoClassModel.getList_two(
				pageNumber, pageSize, searchKey, searchKey2);

		setAttr("page", page);
		setAttr("navTabId", "studentGoClassRcord");
		render("inside/www/school/student_go_class_rcord.html");

	}

	/*
	 * 学生上课信息
	 */
	public void studentGoClassRcordByClassAndNo() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String classId = getPara("id");
		int no = getParaToInt("no");
		String searchKey = getPara("searchKey", "");

		Page<StudentGoClassModel> page = StudentGoClassModel.getList_three(
				pageNumber, pageSize, classId, no, searchKey);

		setAttr("classId", classId);
		setAttr("no", no);
		setAttr("page", page);
		setAttr("navTabId", "studentGoClassRcordByClassAndNo");
		render("inside/www/school/student_go_class_rcord_by_classid_no.html");

	}

	/*
	 * 班级上课信息
	 */
	public void classRcords() {
		String navTabId = getPara("navTabId");
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String classInfoId = getPara("searchKey");

		Page<ClassRcordModel> page = ClassRcordModel.getList(pageNumber,
				pageSize, classInfoId);

		setAttr("page", page);
		setAttr("navTabId", navTabId);
		render("inside/www/school/class_rcord.html");

	}

	/**
	 * 添加学生上课信息页面
	 */
	public void studentGoClass_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/student/studentGoClass_add.html");
	}

	/**
	 * 保存学生上课信息
	 */
	public void studentGoClassSave() {
		String navTabId = getPara("navTabId");
		int classInfoId = getParaToInt("c.id");
		int classroomId = getParaToInt("cm.id");
		int studentId = getParaToInt("s.id");
		int employeeId = getParaToInt("e.id");
		Date gotime = DateUtils.string2Date(getPara("gotime"));
		String remark = getPara("remark");
		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(navTabId)) {
			result.setStatusCode("1");
			result.setMessage("price不能为空！");
			renderJson(result);
			return;
		}
		boolean op = StudentGoClassModel.save(classInfoId, classroomId,
				studentId, employeeId, gotime, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开学生上课信息更新页面
	 */
	public void studentGoClass_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		StudentGoClassModel model = StudentGoClassModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/student/studentGoClass_update.html");
	}

	/**
	 * 更新学生上课信息
	 */
	public void studentGoClassUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		int classInfoId = getParaToInt("classInfoId");
		int classroomId = getParaToInt("classroomId");
		int studentId = getParaToInt("studentId");
		int employeeId = getParaToInt("employeeId");
		Date gotime = DateUtils.string2Date(getPara("gotime"));
		String remark = getPara("remark");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(navTabId)) {
			result.setStatusCode("1");
			result.setMessage("price不能为空！");
			renderJson(result);
			return;
		}
		boolean op = StudentGoClassModel.update(id, classInfoId, classroomId,
				studentId, employeeId, gotime, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/*
	 * 学生缴费信息
	 */
	public void studentPays() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<StudentPayModel> page = StudentPayModel.getList(pageNumber,
				pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "studentPays");
		render("inside/www/student/studentPays.html");

	}

	/**
	 * 添加学生缴费信息页面
	 */
	public void studentPay_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/student/studentPay_add.html");
	}

	public void studentPay_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		StudentPayModel model = StudentPayModel.getById(id);
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/student/studentPay_update.html");
	}

	/*
	 * 学生报名信息
	 */
	public void studentRegistrations() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<StudentRegistrationModel> page = StudentRegistrationModel.getList(
				pageNumber, pageSize, searchKey, 33);

		setAttr("page", page);
		setAttr("navTabId", "studentRegistrations");
		render("inside/www/school/studentRegistrations.html");

	}

	/**
	 * 添加学生报名信息页面
	 */
	public void studentRegistration_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/school/studentRegistration_add.html");
	}

	/**
	 * 保存学生报名信息
	 */
	public void studentRegistrationSave() {
		String navTabId = getPara("navTabId");

		Integer[] a = getParaValuesToInt("rows");
		DwzResult result = new DwzResult();
		if (getParaToInt("student.id") == null) {
			result.setStatusCode("1");
			result.setMessage("操作失败！该学生不存在！请在学生基本信息中录入，再操作");
			renderJson(result);
			return;
		}
		int studentId = getParaToInt("student.id");
		List<StudentRegistrationModel> list = new ArrayList<StudentRegistrationModel>();
		for (int i = 0; i < a.length; i++) {
			int classTypeId = getParaToInt("items[" + i + "].classType.id");
			int gradeId = getParaToInt("items[" + i + "].grade.id");
			int subjectId = getParaToInt("items[" + i + "].subject.id");
			int regNumber = getParaToInt("items[" + i + "].regNumber");
			String remark = getPara("items[" + i + "].remark");

			StudentRegistrationModel studentReg = StudentRegistrationModel
					.getModel(studentId, subjectId, classTypeId, gradeId);

			if (studentReg != null) {// 说明已经报名了，再看一下是否已经上课完成，如果上课结束，那么就可以再报名了（有可能还没有安排班，如果还没有安排）
				// 找是否已经排班
				StudentClassModel stuCla2 = StudentClassModel
						.getModelByRegId(studentReg.getId());
				if (stuCla2 != null) {// 已经排班
					// 找在读的信息
					StudentClassModel stuCla = StudentClassModel.getModel(
							studentId, subjectId, gradeId, classTypeId);
					// null说明已经找不到在读的信息，可以报名
					if (stuCla != null) {// 说明还有上课未结束的报名，就不能报名
						result.setStatusCode("1");
						result.setMessage("操作失败！该学生还在报读，同年级，同类型的科目！");
						renderJson(result);
						return;

					}
				} else {
					result.setStatusCode("1");
					result.setMessage("操作失败！该学生已经报读，同年级，同类型的科目！");
					renderJson(result);
					return;

				}

			}

			StudentRegistrationModel model = new StudentRegistrationModel();
			model.setId(Utils.createNumUUID());
			model.setclassTypeId(classTypeId);
			model.setgradeId(gradeId);
			model.setstudentId(studentId);
			model.setsubjectId(subjectId);
			model.setregNumber(regNumber);
			model.setregTime(new Date());
			model.setremark(remark);
			list.add(model);
		}

		boolean op = StudentRegistrationModel.save(list);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开学生报名页面
	 */
	public void studentRegistration_update() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		StudentRegistrationModel model = StudentRegistrationModel.getById(id);

		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/school/studentRegistration_update.html");
	}

	/**
	 * 更新学生报名信息
	 */
	public void studentRegistrationUpdate() {

		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		int classTypeId = getParaToInt("classTypeId");
		int gradeId = getParaToInt("gradeId");
		int studentPayId = getParaToInt("studentPayId");
		int studentId = getParaToInt("studentId");
		int subjectId = getParaToInt("subjectId");

		int regNumber = getParaToInt("regNumber");
		String tot = getPara("total");
		String actual = getPara("actualCost");
		String must = getPara("mustCost");
		String uncol = getPara("uncollected");
		String discount = getPara("discountCost");
		Date regTime = DateUtils.string2Date(getPara("regTime"));
		String remark = getPara("remark");

		DwzResult result = new DwzResult();
		if (StringUtil.isBlankOrEmpty(navTabId)) {
			result.setStatusCode("1");
			result.setMessage("price不能为空！");
			renderJson(result);
			return;
		}
		BigDecimal total = new BigDecimal(tot);
		BigDecimal actualCost = new BigDecimal(actual);
		BigDecimal mustCost = new BigDecimal(must);
		BigDecimal uncollected = new BigDecimal(uncol);
		BigDecimal discountCost = new BigDecimal(discount);
		boolean op = StudentRegistrationModel
				.update(id, classTypeId, gradeId, studentPayId, studentId,
						subjectId, total, actualCost, mustCost, uncollected,
						discountCost, regNumber, regTime, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/**
	 * 学生续报页面
	 */
	public void studentContinueRegist() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");
		StudentRegistrationModel m = StudentRegistrationModel.getModel(id);
		setAttr("m", m);
		setAttr("navTabId", navTabId);
		render("inside/www/school/student_continue_regist.html");
	}

	/**
	 * 保存学生续报信息
	 */
	public void studentContinueRegistSave() {

		String navTabId = getPara("navTabId");
		int classTypeId = getParaToInt("classTypeId");
		int gradeId = getParaToInt("gradeId");

		int regNumber = getParaToInt("regNumber");
		String studentRegId = getPara("studentRegId");
		String remark = getPara("remark");

		DwzResult result = new DwzResult();
		boolean op = RegorderModel.save(classTypeId, gradeId, regNumber,
				studentRegId, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/**
	 * 打开学生报名交费订单页面
	 */
	public void opStudentpayOrder() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String studentRegId = getPara("studentRegId");// 学生报名id
		String studentId = getPara("studentId");// 学生id
		Page<RegorderModel> page = RegorderModel.getList(pageNumber, pageSize,
				studentRegId, studentId, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "opStudentpayOrder");
		render("inside/www/school/regorders.html");
	}

	/**
	 * 打开学生报名交费订单页面
	 */
	public void openRgorderDiscount() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");// 学生id
		RegorderModel m = RegorderModel.getById(id);
		setAttr("m", m);
		setAttr("navTabId", navTabId);
		render("inside/www/school/regorder_discount.html");
	}

	/**
	 * 更新学生报名缴费订单信息
	 */
	public void saveRgorderDiscount() {

		String navTabId = getPara("navTabId");
		String id = getPara("id");
		int regNumber = getParaToInt("regNumber");
		String discountCost = getPara("discountCost");
		String remark = getPara("remark");

		DwzResult result = new DwzResult();
		BigDecimal dc = new BigDecimal(discountCost);
		boolean op = RegorderModel.rgorderDiscount(id, dc, regNumber, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/**
	 * 打开学生退费页面
	 */
	public void openExitCost() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");// 学生id
		RegorderModel m = RegorderModel.getById(id);

		double final_vaild_cost = 0;

		if (m != null) {
			double vaild_cost = m.getactualCost().doubleValue()
					- m.getmustCost().doubleValue();
			if (vaild_cost > 0) {
				final_vaild_cost = vaild_cost;
			}
		}
		setAttr("m", m);
		setAttr("final_vaild_cost", final_vaild_cost);
		setAttr("navTabId", navTabId);
		render("inside/www/financial/exit_cost.html");
	}

	/**
	 * 执行退费
	 */
	public void exitCost() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");
		String exitCost = getPara("exitCost");
		String remark = getPara("remark");
		DwzResult result = new DwzResult();
		BigDecimal ec = new BigDecimal(exitCost);

		boolean op = RegorderModel.exitCost(id, ec, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！数据异常(超过退费金额或者其他)");
			renderJson(result);
		}
	}

	/**
	 * 打开学生报名交费订单页面
	 */
	public void open_regorder_update() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");// 学生id
		RegorderModel m = RegorderModel.getById(id);
		setAttr("m", m);
		setAttr("navTabId", navTabId);
		render("inside/www/school/regorder_update.html");
	}

	/**
	 * 更新学生报名缴费订单信息
	 */
	public void regorderUpdate() {

		String navTabId = getPara("navTabId");
		String id = getPara("id");
		int regNumber = getParaToInt("regNumber");
		String discountCost = getPara("discountCost");
		String remark = getPara("remark");

		DwzResult result = new DwzResult();
		BigDecimal dc = new BigDecimal(discountCost);
		boolean op = RegorderModel.update(id, dc, regNumber, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	/**
	 * 打开学生交费页面
	 */
	public void open_studentpay() {
		String navTabId = getPara("navTabId");
		String orderIds[] = getParaValues("orderIDs");

		List<RegorderModel> list = RegorderModel.getList(orderIds);

		setAttr("list", list);
		setAttr("navTabId", navTabId);
		render("inside/www/school/open_oreder_pay.html");
	}

	/**
	 * 学生交费
	 */
	public void studentPay() {

		String navTabId = getPara("navTabId");

		// Integer []a=getParaValuesToInt("rows");

		String tot = getPara("total");
		String remark = getPara("remark");
		int operator = getParaToInt("emp.id");
		int paymodel = getParaToInt("paymodel");

		String payer = getPara("payer");
		String paytime = getPara("paytime");
		String payerTel = getPara("payerTel");

		BigDecimal total = new BigDecimal(tot);
		Date now = DateUtils.string2Date(paytime);
		String payId = Utils.createNumUUID();

		StudentPayModel stupay = new StudentPayModel();
		stupay.setId(payId);
		stupay.settotal(total);
		stupay.setoperator(operator);
		stupay.setmodel(paymodel);
		stupay.setpayTime(now);
		stupay.setremark(remark);
		stupay.setpayer(payer);
		stupay.setpayerTel(payerTel);

		List<RegorderdetailModel> list = new ArrayList<RegorderdetailModel>();
		for (int i = 0; i < 1; i++) {
			String detailtol = getPara("items[].detailtol");
			String regorderId = getPara("items[].regorderId");
			BigDecimal detailtotal = new BigDecimal(detailtol);
			;
			RegorderdetailModel model = new RegorderdetailModel();
			model.setId(Utils.createNumUUID());
			model.setpayId(payId);
			model.setregorderId(regorderId);
			model.settotal(detailtotal);
			model.setcreateTime(now);
			model.setremark(remark);

			list.add(model);
		}
		// for(int i=0;i<a.length;i++){
		// String detailtol=getPara("items["+i+"].detailtol");
		// BigDecimal detailtotal=new BigDecimal(detailtol);;
		// RegorderdetailModel model=new RegorderdetailModel();
		// model.setId(Utils.createNumUUID());
		// model.setpayId(payId);
		// model.setregorderId(regorderId);
		// model.settotal(detailtotal);
		// model.setcreateTime(now);
		// model.setremark(remark);
		//			
		// list.add(model);
		// }

		DwzResult result = new DwzResult();
		boolean op = RegorderdetailModel.save(list, stupay);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	// ///////////////////////////////
	/**
	 * 点击查找科目
	 */
	public void lookSubjec() {
		String name = getPara("name");
		List<SubjectModel> list = SubjectModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找年级
	 */
	public void lookGrade() {
		String name = getPara("name");
		List<GradeModel> list = GradeModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找班级类型
	 */
	public void lookClassType() {
		String name = getPara("name");
		List<ClassTypeModel> list = ClassTypeModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击学生
	 */
	public void lookStudent() {
		String name = getPara("name");
		List<StudentModel> list = StudentModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找公司在职员工
	 */
	public void lookEmployee() {
		String name = getPara("name");
		List<EmployeeInfoModel> list = EmployeeInfoModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找上课时间段
	 */
	public void lookTimeslot() {
		String name = getPara("name");
		List<TimeslotModel> list = TimeslotModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找上课时间段
	 */
	public void lookSchool() {
		String name = getPara("name");
		List<SchoolModel> list = SchoolModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找教室
	 */
	public void lookClassroom() {
		String name = getPara("name");
		List<ClassroomModel> list = ClassroomModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找校区
	 */
	public void lookCampus() {
		String name = getPara("name");
		List<CampusModel> list = CampusModel.getList(name);
		renderJson(list);
	}

	/**
	 * 点击查找职位
	 */
	public void lookPosition() {
		String name = getPara("name");
		List<PositionModel> list = PositionModel.getList(name);
		renderJson(list);
	}

	// ////////////////////////////
	/**
	 * 查看学生报名上课信息
	 */
	public void studentAllInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		int type = getParaToInt("type", 20);
		String searchKey = getPara("searchKey", "");
		Page<StudentRegistrationModel> page = StudentRegistrationModel.getList(
				pageNumber, pageSize, searchKey, type);
		StudentRegistrationModel m = StudentRegistrationModel.getTotalModel(
				searchKey, type);
		StringBuffer sel = new StringBuffer();
		sel.append("<select name='type'>");
		switch (type) {
		case 0:
			sel.append("<option value='0' selected ='selected'>全部</option>");
			sel.append("<option value='1'>未交费</option>");
			sel.append("<option value='2'>提醒交费</option>");
			sel.append("<option value='3'>已欠费</option>");
			break;
		case 1:
			sel.append("<option value='0' >全部</option>");
			sel.append("<option value='1' selected ='selected'>未交费</option>");
			sel.append("<option value='2'>提醒交费</option>");
			sel.append("<option value='3'>已欠费</option>");
			break;
		case 2:
			sel.append("<option value='0' >全部</option>");
			sel.append("<option value='1' >未交费</option>");
			sel.append("<option value='2' selected ='selected'>提醒交费</option>");
			sel.append("<option value='3'>已欠费</option>");
			break;
		case 3:
			sel.append("<option value='0' >全部</option>");
			sel.append("<option value='1'>未交费</option>");
			sel.append("<option value='2'>提醒交费</option>");
			sel.append("<option value='3' selected ='selected'>已欠费</option>");
			break;
		default:
			sel.append("<option value='0' selected ='selected'>全部</option>");
			sel.append("<option value='1'>未交费</option>");
			sel.append("<option value='2'>提醒交费</option>");
			sel.append("<option value='3'>已欠费</option>");
			break;
		}
		sel.append("</select>");

		setAttr("page", page);
		setAttr("m", m);
		setAttr("sel", sel.toString());
		setAttr("navTabId", "studentAllInfos");
		render("inside/www/school/student_all_info.html");
	}

	public void delStuReg() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");
		DwzResult result = new DwzResult();

		if (StringUtil.isBlankOrEmpty(id)) {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
			return;
		}
		boolean op = StudentRegistrationModel.delModel(id);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 给报名的学生安排上课班级
	 */
	public void studentToClass() {
		String navTabId = getPara("navTabId");
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String studentRegId = getPara("id");// 学生报名idos

		StudentRegistrationModel model = StudentRegistrationModel
				.getModel(studentRegId);
		int subjectId = model.getsubjectId();// 科目id
		int gradeId = model.getgradeId();// 年级id
		int classTypeId = model.getclassTypeId();// 班级类型id
		Page<ClassInfoModel> page = ClassInfoModel.getList(pageNumber,
				pageSize, searchKey, gradeId, subjectId, classTypeId);

		setAttr("page", page);
		setAttr("m", model);
		setAttr("navTabId", navTabId);
		render("inside/www/school/student_to_class.html");
	}

	/*
	 * 登记学生签到页面1
	 */
	public void studentSigin_classs() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		Page<ClassInfoModel> page = ClassInfoModel.getList(pageNumber,
				pageSize, searchKey);

		setAttr("page", page);
		setAttr("navTabId", "studentGoClasss");
		render("inside/www/school/studentSigned_class.html");

	}

	/*
	 * 登记学生签到页面2
	 */
	public void studentSigin_student() {
		int classinfoId = getParaToInt("classinfoId");
		String className = getPara("className");
		List<StudentClassModel> list = StudentClassModel.getList(classinfoId);
		int rows = 0;
		if (list != null) {
			rows = list.size();
		}
		setAttr("list", list);
		setAttr("rows", rows);
		setAttr("classinfoId", classinfoId);
		setAttr("className", className);
		setAttr("navTabId", "studentSigin_student");
		render("inside/www/school/studentSiged_student.html");

	}

	/*
	 * 登记学生签到页面2
	 */
	public void studentSign() {
		String navTabId = getPara("navTabId");
		int classinfoId = getParaToInt("classinfoId");
		ClassInfoModel m = ClassInfoModel.getModel(classinfoId);
		List<StudentClassModel> list = StudentClassModel.getList(classinfoId);
		int no = ClassRcordModel.getMaxNo(classinfoId);
		int rows = 0;
		if (list != null) {
			rows = list.size();
		}
		setAttr("list", list);
		setAttr("rows", rows);
		setAttr("m", m);
		setAttr("no", no);
		setAttr("navTabId", navTabId);
		render("inside/www/school/student_sign.html");

	}

	/**
	 * 保存学生签到信息
	 */
	public void studentSigin_student_save() {
		String navTabId = getPara("navTabId");

		int classInfoId = getParaToInt("classinfoId");
		int classroomId = getParaToInt("c.id");
		int type = getParaToInt("type");
		int no = getParaToInt("no", 1);
		Date gotime = DateUtils.string2Date(getPara("gotime"));
		Date overTime = DateUtils.string2DateAdd(getPara("gotime"));
		int employeeId = getParaToInt("e.id");
		String cr_remark = getPara("remark");

		int rows = getParaToInt("rows");
		DwzResult result = new DwzResult();

		// 先检查一下班级是否已经结束课程
		ClassInfoModel classinfo = ClassInfoModel.getById(classInfoId);
		if (classinfo == null) {
			result.setStatusCode("1");
			result.setMessage("操作失败！不存在该班级！");
			renderJson(result);
			return;
		}

		if (classinfo.getstate() != 0) {
			result.setStatusCode("1");
			result.setMessage("操作失败！该班级已经结束课程，不能再签到，请核查后再操作！");
			renderJson(result);
			return;
		}

		int noc = ClassRcordModel.getMaxNo(classInfoId);
		if (noc - 1 == no) {
			result.setStatusCode("1");
			result.setMessage("操作失败！本次课（第" + no + "次课）已经签到！");
			renderJson(result);
			return;
		}
		ClassRcordModel cr = new ClassRcordModel();
		String id = Utils.createNumUUID();
		cr.setId(id);
		cr.setclassInfoId(classInfoId);
		cr.setclassroomId(classroomId);
		cr.setremark(cr_remark);
		cr.setgotime(gotime);
		cr.setemployeeId(employeeId);
		cr.setoverTime(overTime);
		cr.setNo(no);
		cr.settype(type);// 0：补课1：正常上课

		List<StudentGoClassModel> list = new ArrayList<StudentGoClassModel>();
		for (int i = 0; i < rows; i++) {
			int studentId = getParaToInt("ob[" + i + "].studentId");
			int login = getParaToInt("ob[" + i + "].login");
			int availd = getParaToInt("ob[" + i + "].availd");
			String remark = getPara("ob[" + i + "].remark");

			StudentGoClassModel model = new StudentGoClassModel();

			model.setstudentId(studentId);
			model.setremark(remark);
			model.setcreateTime(new Date());
			model.setlogin(login);
			model.setavaild(availd);
			model.setClassRcordId(id);
			list.add(model);
		}

		boolean op = StudentGoClassModel.save(list, cr);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			// result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开修改学生签到记录页面
	 */
	public void openStudentSignUpdate() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		StudentGoClassModel m = StudentGoClassModel.getModel(id);

		StringBuffer loginSelect = new StringBuffer(
				"<select class='combox' name='login'>");
		StringBuffer availdSelect = new StringBuffer(
				"<select class='combox' name='availd'>");

		if (m.getlogin() == 1) {
			loginSelect
					.append("<option value='1' selected='selected'>是</option><option value='0'>否</option>");
		} else {
			loginSelect
					.append("<option value='0' selected='selected'>否</option><option value='1'>是</option>");
		}
		if (m.getavaild() == 1) {
			availdSelect
					.append("<option value='1' selected='selected' >有效</option><option value='0'>否</option>");
		} else {
			availdSelect
					.append("<option value='0' selected='selected' >否</option><option value='1'>有效</option>");
		}
		setAttr("m", m);
		setAttr("availdSelect", availdSelect);
		setAttr("loginSelect", loginSelect);
		setAttr("navTabId", navTabId);
		render("inside/www/school/student_sign_update.html");
	}

	/**
	 * 修改学生签到记录
	 */
	public void studentSignUpdate() {
		String navTabId = getPara("navTabId");
		int id = getParaToInt("id");
		int login = getParaToInt("login");
		int availd = getParaToInt("availd");
		DwzResult result = new DwzResult();

		boolean op = StudentGoClassModel.studentSignUpdate(id, login, availd);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	// ////////////////////////////
	/**
	 * 查看收费情况
	 */
	public void payDetail() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String startTime = getPara("startTime", "");
		String endTime = getPara("endTime", DateUtils.date2String(new Date()));
		Page<VPayDetailModel> page = VPayDetailModel.getList(pageNumber,
				pageSize, searchKey, startTime, endTime);

		double tatol = VPayDetailModel.getTatol(searchKey, startTime, endTime);
		setAttr("page", page);
		setAttr("startTime", startTime);
		setAttr("endTime", endTime);
		setAttr("tatol", tatol);
		setAttr("navTabId", "payDetail");
		render("inside/www/financial/pay_detail.html");
	}

	/**
	 * 查看订单交费详情
	 */
	public void regorderDetail() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String regorderId = getPara("id");

		RegorderModel reModel = RegorderModel.getModelById(regorderId);

		Page<RegorderdetailModel> page = RegorderdetailModel.getList(
				pageNumber, pageSize, regorderId, searchKey);

		setAttr("page", page);
		setAttr("m", reModel);
		setAttr("navTabId", "regorderDetail");
		render("inside/www/financial/regorderdetail.html");
	}

	public void openRegorderDetailUpdate() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");
		RegorderdetailModel model = RegorderdetailModel.getModelById(id);
		setAttr("d", model);
		setAttr("navTabId", navTabId);

		render("inside/www/financial/regorderdetail_update.html");
	}

	/**
	 * 修改交费详情
	 */
	public void regorderDetailUpdate() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");
		String actualCost = getPara("actualCost");
		String remark = getPara("remark");

		BigDecimal ac = new BigDecimal(actualCost);
		DwzResult result = new DwzResult();
		boolean op = RegorderdetailModel.regorderDetailUpdate(id, ac, remark);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	// ////////////////////////////////////////////
	/*
	 * 学生咨询详细信息
	 */
	public void consultDetails() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		int state = getParaToInt("state", 22);
		String searchKey = getPara("searchKey", "");

		Page<ConsultDetailModel> page = ConsultDetailModel.getList(pageNumber,
				pageSize, searchKey, state);

		setAttr("page", page);
		setAttr("navTabId", "consultDetails");
		render("inside/www/school/consult_details.html");

	}

	/**
	 * 学生咨询详细信息页面
	 */
	public void consult_add() {
		String navTabId = getPara("navTabId");
		setAttr("navTabId", navTabId);
		render("inside/www/school/consult_add.html");
	}

	/**
	 * 保存学生咨询详细信息
	 */
	public void consultDetailSave() {
		String navTabId = getPara("navTabId");
		String advocate = getPara("advocate");
		int channel = getParaToInt("channel");
		int receiver = getParaToInt("receiver.id");
		int campus = getParaToInt("campus.id");
		int receiverType = getParaToInt("receiverType");
		String tel = getPara("tel");
		Date time = DateUtils.string2Date(getPara("time"));
		int responsible = getParaToInt("responsible.id");
		String remark = getPara("remark");

		Integer[] a = getParaValuesToInt("rows");

		ConsultModel c = new ConsultModel();
		String id = Utils.createNumUUID();
		c.setId(id);
		c.setadvocate(advocate);
		c.setcampus(campus);
		c.setchannel(channel);
		c.setreceiver(receiver);
		c.setreceiverType(receiverType);
		c.settel(tel);
		c.settime(time);
		c.setresponsible(responsible);
		c.setremark(remark);

		List<ConsultDetailModel> list = new ArrayList<ConsultDetailModel>();
		for (int i = 0; i < a.length; i++) {
			ConsultDetailModel d = new ConsultDetailModel();

			int classType = getParaToInt("d[" + i + "].classType.id");
			int grade = getParaToInt("d[" + i + "].grade.id");
			int school = getParaToInt("d[" + i + "].school.id");
			String score = getPara("d[" + i + "].score");
			int subject = getParaToInt("d[" + i + "].subject.id");
			String detailremark = getPara("d[" + i + "].detailremark");

			d.setclassType(classType);
			d.setconsultId(id);
			d.setgrade(grade);
			d.setId(Utils.createNumUUID());
			d.setremark(detailremark);
			d.setschool(school);
			d.setscore(score);
			d.setsubject(subject);
			d.setstate(0);// 0：未处理1已处理
			list.add(d);
		}
		DwzResult result = new DwzResult();
		boolean op = ConsultModel.save(c, list);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开学生咨询详细信息更新页面
	 */
	public void consultDetail_update() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");
		setAttr("navTabId", navTabId);
		render("inside/www/school/consult_detail_update.html");
	}

	/**
	 * 更新学生咨询详细处理状态信息
	 */
	public void consultDetailUpdate() {

		String navTabId = getPara("navTabId");
		String id = getPara("id");
		int state = getParaToInt("state");
		String dulMan = getPara("receiver.name");
		String remark = getPara("remark");
		String r = remark + "(" + dulMan + ")";
		DwzResult result = new DwzResult();
		boolean op = ConsultDetailModel.update(id, state, r);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	// ////////////////////////////////////////////

	/*
	 * 学生试听记录信息
	 */
	public void auditions() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		int result = getParaToInt("result", 22);
		Page<AuditionModel> page = AuditionModel.getList(pageNumber, pageSize,
				searchKey, result);

		setAttr("page", page);
		setAttr("navTabId", "auditions");
		render("inside/www/school/auditions.html");

	}

	/**
	 * 添加学生试听记录信息页面
	 */
	public void audition_add() {
		String consulDetailID = getPara("consulDetailID");
		String subjecId = getPara("subjecId");
		String gradeId = getPara("gradeId");
		String subjecName = getPara("subjecName");
		String gradeName = getPara("gradeName");
		setAttr("navTabId", "auditions");
		setAttr("gradeId", gradeId);
		setAttr("subjecId", subjecId);
		setAttr("subjecName", subjecName);
		setAttr("gradeName", gradeName);
		setAttr("consulDetailID", consulDetailID);
		render("inside/www/school/audition_add.html");
	}

	/**
	 * 保存学生试听记录信息
	 */
	public void auditionSave() {
		String navTabId = getPara("navTabId");
		String remark = getPara("remark");
		int teacher = getParaToInt("teacher.id");
		int classroom = getParaToInt("classroom.id");
		int grade = getParaToInt("grade.id");
		int subject = getParaToInt("subjec.id");
		int student = getParaToInt("student.id");
		Date time = DateUtils.string2Date(getPara("time"));
		String consulDetailID = getPara("consulDetailID");

		AuditionModel a = new AuditionModel();

		a.setclassroom(classroom);
		a.setremark(remark);
		a.setgrade(grade);
		a.setstudent(student);
		a.setsubject(subject);
		a.setteacher(teacher);
		a.settime(time);
		a.setId(Utils.createNumUUID());
		a.setresult(1);// 0：成功，1：待定，2：失败,3取消

		if (!StringUtil.isBlankOrEmpty(consulDetailID))
			a.setconsultDetailId(consulDetailID);

		DwzResult result = new DwzResult();

		boolean op = AuditionModel.save(a);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 打开学生试听记录信息更新页面
	 */
	public void audition_update() {
		String navTabId = getPara("navTabId");
		String id = getPara("id");
		AuditionModel model = AuditionModel.getById(id);

		StringBuffer selectStr = new StringBuffer("<select name='result'>");
		if (model != null) {
			int sex = model.getresult();
			switch (sex) {
			case 0:
				selectStr
						.append("<option value='0' selected='selected'>成功</option>");
				selectStr.append("<option value='1'>待定</option>");
				selectStr.append("<option value='2'>失败</option>");
				selectStr.append("<option value='3'>取消</option>");
				break;
			case 1:
				selectStr.append("<option value='0' >成功</option>");
				selectStr
						.append("<option value='1' selected='selected'>待定</option>");
				selectStr.append("<option value='2'>失败</option>");
				selectStr.append("<option value='3'>取消</option>");
				break;
			case 2:
				selectStr.append("<option value='0' >成功</option>");
				selectStr.append("<option value='1'>待定</option>");
				selectStr
						.append("<option value='2' selected='selected'>失败</option>");
				selectStr.append("<option value='3'>取消</option>");
				break;
			case 3:
				selectStr.append("<option value='0'>成功</option>");
				selectStr.append("<option value='1'>待定</option>");
				selectStr.append("<option value='2'>失败</option>");
				selectStr
						.append("<option value='3' selected='selected'>取消</option>");
				break;

			default:
				selectStr.append("<option value='0'>成功</option>");
				selectStr
						.append("<option value='1' selected='selected'>待定</option>");
				selectStr.append("<option value='2'>失败</option>");
				selectStr.append("<option value='3'>取消</option>");
				break;
			}
		}
		selectStr.append("</select>");

		setAttr("model", model);
		setAttr("selectStr", selectStr);
		setAttr("navTabId", navTabId);
		render("inside/www/school/audition_update.html");
	}

	/**
	 * 更新学生试听记录信息
	 */
	public void auditionUpdate() {

		String navTabId = getPara("navTabId");
		String id = getPara("id");
		String remark = getPara("remark");
		int rut = getParaToInt("result");

		DwzResult result = new DwzResult();
		boolean op = AuditionModel.update(id, remark, rut);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}

	}

	// /////////////////////////
	/**
	 * 管理员信息列表 xiao 2015年12月24日 10:29:40
	 */
	public void admins() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");

		StringBuffer sb = new StringBuffer();
		sb.append("<td>选择查询条件：</td>");
		sb.append("<td>管理员名称</td><td>");
		sb.append("<input type='text' name='searchKey' value='" + searchKey
				+ "'/>");
		sb.append("</td>");

		Page<AdminModel> page = AdminModel.getList(pageNumber, pageSize,
				searchKey);

		setAttr("page", page);
		setAttr("navTabId", "admins");
		setAttr("searchContent", sb.toString());
		render("inside/www/system/admins.html");
	}

	/**
	 * 添加管理员页面
	 */
	public void adminAdd() {
		String navTabId = getPara("navTabId", "");
		StringBuffer sb = new StringBuffer();
		sb.append("<select>");
		sb.append("<option value='1'>超级管理员</option>");
		sb.append("<option value='0'>普通管理员</option>");
		sb.append("</select>");
		setAttr("type", sb.toString());
		setAttr("navTabId", navTabId);
		render("inside/www/system/admin_add.html");
	}

	/**
	 * 修改管理员页面
	 */
	public void adminEditor() {
		int id = getParaToInt("id", 0);
		String navTabId = getPara("navTabId", "");
		AdminModel model = AdminModel.getById(id);

		StringBuffer sb = new StringBuffer();
		sb.append("<select>");
		if (model.getType() == 1) {
			sb.append("<option value='1'>超级管理员</option>");
			sb.append("<option value='0'>普通管理员</option>");
		} else {
			sb.append("<option value='0'>普通管理员</option>");
			sb.append("<option value='1'>超级管理员</option>");
		}
		sb.append("</select>");
		setAttr("type", sb.toString());
		setAttr("model", model);
		setAttr("navTabId", navTabId);
		render("inside/www/system/admin_editor.html");
	}

	/**
	 * 保存管理员
	 */
	public void adminSave() {
		String name = getPara("name");
		String password = getPara("password");
		String password1 = getPara("password1");
		int type = getParaToInt("type", 0);
		String navTabId = getPara("navTabId", "");
		DwzResult result = new DwzResult();

		if (StringUtil.isBlankOrEmpty(password)) {
			result.setStatusCode("1");
			result.setMessage("密码不能为空！");
			renderJson(result);
			return;
		} else if (StringUtil.isBlankOrEmpty(name)) {
			result.setStatusCode("1");
			result.setMessage("登录名不能为空！");
			renderJson(result);
			return;
		}
		if (!password.equals(password1)) {
			result.setStatusCode("1");
			result.setMessage("两次输入的密码不一致！");
			renderJson(result);
			return;
		}
		if (AdminModel.findByName(name) != null) {
			result.setStatusCode("1");
			result.setMessage("管理员" + name + "已经存在！");
			renderJson(result);
			return;
		}
		String md5Password = StringUtil.isBlankOrEmpty(password) ? null
				: MD5Util.string2MD5(password);
		boolean op = AdminModel.saveModel(name, md5Password, type);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 管理员更新
	 */
	public void adminUpdate() {
		String password = getPara("password");
		String password1 = getPara("password1");
		String oldpassword = getPara("oldpassword");
		int type = getParaToInt("type", 0);
		int id = getParaToInt("id");
		String navTabId = getPara("navTabId", "");
		DwzResult result = new DwzResult();

		if (StringUtil.isBlankOrEmpty(password)) {
			result.setStatusCode("1");
			result.setMessage("密码不能为空！");
			renderJson(result);
			return;
		}
		if (!password.equals(password1)) {
			result.setStatusCode("1");
			result.setMessage("两次输入的密码不一致！");
			renderJson(result);
			return;
		}
		// 密码旧验证
		String md5oldpassword = StringUtil.isBlankOrEmpty(oldpassword) ? null
				: MD5Util.string2MD5(oldpassword);
		AdminModel m = AdminModel.getById(id);
		if (!m.getPassword().equals(md5oldpassword)) {
			result.setStatusCode("1");
			result.setMessage("原密码验证错误！");
			renderJson(result);
			return;
		}

		String md5Password = StringUtil.isBlankOrEmpty(password) ? null
				: MD5Util.string2MD5(password);

		boolean op = AdminModel.updateModel(id, md5Password, type);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	/**
	 * 删除系统管理员
	 */
	public void adminDel() {
		int id = getParaToInt("id");
		String navTabId = getPara("navTabId", "");
		DwzResult result = new DwzResult();
		boolean op = AdminModel.delModel(id);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}

	// /////////////////
	/*
	 * 查询学生综合信息
	 */
	public void studentComprehensiveInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String tel = getPara("tel", "");

		Page<StudentModel> page = StudentModel.studentComprehensiveInfos(
				pageNumber, pageSize, searchKey, tel);

		setAttr("page", page);
		setAttr("searchKey", searchKey);
		setAttr("tel", tel);
		setAttr("navTabId", "studentComprehensiveInfos");
		render("inside/www/report/student_comprehensive_infos.html");

	}

	/*
	 * 查询学生基本信息
	 */
	public void studentBaseInfos() {
		int id = getParaToInt("id");
		StudentModel m = StudentModel.getModelById(id);
		setAttr("m", m);
		setAttr("navTabId", "studentComprehensiveInfos");
		render("inside/www/report/student_base_info.html");

	}

	/*
	 * 班级信息
	 */
	public void studentClassInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		int studentId = getParaToInt("id");
		String searchKey = getPara("searchKey", "");
		String teacher = getPara("teacher", "");

		Page<StudentClassModel> page = StudentClassModel.studentClassInfos(
				pageNumber, pageSize, studentId, searchKey, teacher);

		StudentModel s = StudentModel.getById(studentId);
		setAttr("page", page);
		setAttr("stu", s);
		setAttr("navTabId", "studentClassInfos");
		render("inside/www/report/student_class_info.html");

	}

	/*
	 * 学生上课信息
	 */
	public void studentGoClassInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String classInfoId = getPara("classInfoId");
		String studentId = getPara("studentId");
		String searchKey = getPara("searchKey", "");

		Page<StudentGoClassModel> page = StudentGoClassModel
				.studentGoClassInfos(pageNumber, pageSize, classInfoId,
						studentId, searchKey);
		StudentClassModel m = StudentClassModel.getModelId(classInfoId);

		setAttr("page", page);
		setAttr("m", m);
		setAttr("navTabId", "studentGoClassInfos");
		render("inside/www/report/student_go_class_infos.html");

	}

	/*
	 * 查看班级里学生名单
	 */
	public void classStudentInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String classId = getPara("classId");

		Page<StudentClassModel> page = StudentClassModel.classStudentInfos(
				pageNumber, pageSize, classId);
		ClassInfoModel m = ClassInfoModel.getById(classId);
		setAttr("page", page);
		setAttr("m", m);
		setAttr("navTabId", "classStudentInfos");
		render("inside/www/report/class_student_infos.html");

	}

	/*
	 * 查看学生试听信息
	 */
	public void studentAuditionInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String key = getPara("searchKey");
		int studentId = getParaToInt("studentId");

		Page<AuditionModel> page = AuditionModel.studentAuditionInfos(
				pageNumber, pageSize, studentId, key);
		StudentModel m = StudentModel.getById(studentId);
		setAttr("page", page);
		setAttr("m", m);
		setAttr("navTabId", "studentAuditionInfos");
		render("inside/www/report/student_audition_infos.html");

	}

	/*
	 * 查看学生报名信息
	 */
	public void studentRegInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		int studentId = getParaToInt("studentId");// 学生id
		Page<StudentRegistrationModel> page = StudentRegistrationModel
				.studentRegInfos(pageNumber, pageSize, studentId, searchKey);
		StudentModel m = StudentModel.getById(studentId);
		setAttr("page", page);
		setAttr("m", m);
		setAttr("navTabId", "studentRegInfos");
		render("inside/www/report/student_reg_infos.html");
	}

	/*
	 * 查看学生报名交费信息
	 */
	public void studentPayOrderInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		int studentId = getParaToInt("studentId");// 学生id
		Page<RegorderdetailModel> page = RegorderdetailModel
				.studentPayOrderInfos(pageNumber, pageSize, studentId,
						searchKey);
		StudentModel m = StudentModel.getById(studentId);
		setAttr("page", page);
		setAttr("m", m);
		setAttr("navTabId", "studentPayOrderInfos");
		render("inside/www/report/student_pay_order_infos.html");
	}

	/**
	 * 班级综合信息
	 */
	public void classComprehensiveInfos() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String teacher = getPara("teacher", "");
		Page<ClassInfoModel> page = ClassInfoModel.classComprehensiveInfos(
				pageNumber, pageSize, searchKey, teacher);
		setAttr("page", page);
		setAttr("navTabId", "classComprehensiveInfos");
		render("inside/www/report/class_comprehensive_infos.html");
	}

	/*
	 * 查看班级里人学生
	 */
	public void classStudents() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		int classId = getParaToInt("classId");

		Page<StudentClassModel> page = StudentClassModel.classStudents(
				pageNumber, pageSize, classId);
		ClassInfoModel m = ClassInfoModel.getModel(classId);
		setAttr("page", page);
		setAttr("m", m);
		setAttr("navTabId", "classStudents");
		render("inside/www/report/class_students.html");

	}
	/**
	 * 学生更换班级
	 * 1.退出原来的班级
	 * 2.加入新的班级
	 */
	public void changeStudentClassSave() {
		String navTabId = getPara("navTabId");
		int studentId = getParaToInt("studentId");
		int old_classinfoId = getParaToInt("old_classinfoId");
		int new_classinfoId = getParaToInt("new_classinfoId");
		DwzResult result = new DwzResult();

		StudentClassModel m = StudentClassModel.getModel(studentId,
				new_classinfoId, 0);// 查找是否已经有在读的班级

		if (m != null) {
			result.setStatusCode("1");
			result.setMessage("操作失败！该学生已经在此班级，不需要重复加入");
			renderJson(result);
			return;
		}
		//查看老班级是否对的
		//0：在读，1离开
		StudentClassModel old_class = StudentClassModel.getModel(studentId,
				old_classinfoId, 0);
		
		if (old_class == null) {
			result.setStatusCode("1");
			result.setMessage("操作失败！信息有误！请联系管理员");
			renderJson(result);
			return;
		}
		
		
		boolean op = StudentClassModel.changeClass(studentId, old_class,
				new_classinfoId);
		if (op) {
			result.setStatusCode("0");
			result.setMessage("操作成功!");
			result.setCallbackType("closeCurrent");
			result.setNavTabId(navTabId);
			renderJson(result);
		} else {
			result.setStatusCode("1");
			result.setMessage("操作失败！");
			renderJson(result);
		}
	}
	/**
	 * 更改学生上课班级页面
	 */
	public void changeStudentToClass() {
		int pageNumber = getParaToInt("pageNumber", 1);
		int pageSize = getParaToInt("pageSize", 20);
		String searchKey = getPara("searchKey", "");
		String class_id = getPara("class_id");// 学生班级id
		String student_id = getPara("student_id");// 学生id
		ClassInfoModel model = ClassInfoModel
				.getById(class_id);
		int subjectId = model.getsubjectId();// 科目id
		int gradeId = model.getgradeId();// 年级id
		int classTypeId = model.getclassTypeId();// 班级类型id
		Page<ClassInfoModel> page = ClassInfoModel.getList(pageNumber,
				pageSize, searchKey, gradeId, subjectId, classTypeId);

		setAttr("page", page);
		setAttr("m", model);
		setAttr("student_id", student_id);
		setAttr("navTabId", "changeStudentToClass");
		render("inside/www/report/change_student_to_class.html");
	}
}
