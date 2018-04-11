package cn.gov.hrss.ln.stuenroll.myClass;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
/**
 * 我的班级模块业务接口
 * 
 * @author Gu
 *
 */
public interface I_MyClassService {
	
	/**
	 * 根据pid查询课程信息
	 * 需要在classinfo表新建period列
	 * 
	 * @param pid
	 * @return
	 */
	public List<Record> searchClassInfo(String pid);
	
	/**
	 * 根据classinfo_id查询同学
	 * @param classinfo
	 * @return
	 */
	public List<Record> searchClassmate(String classid);
	
	
}
