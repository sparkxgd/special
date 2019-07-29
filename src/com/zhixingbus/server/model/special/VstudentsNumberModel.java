package com.zhixingbus.server.model.special;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhixingbus.server.utils.StringUtil;
/**
 *tv_students_number 统计在校学生
 * @author xiao
 *
 */
public class VstudentsNumberModel extends Model<VstudentsNumberModel> {
	
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tv_students_number";
	public Integer gety(){
		return get("y");
	}
	public Integer getm(){
		return get("m");
	}
	public Long getval(){
		return get("val");
	}
	public static final VstudentsNumberModel dao = new VstudentsNumberModel();
	public static List<VstudentsNumberModel> getList() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT y,m,COUNT(*) as val FROM  ").append(tableName).append(" GROUP BY y,m");
		return dao.find(sql.toString());
	}
}
