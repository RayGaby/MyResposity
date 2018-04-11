package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.HashMap;

import com.jfinal.plugin.activerecord.Record;

public interface I_VisitDao {
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据传过来的参数查找该班级回访的人数
	 * 操作表：visit
	 * 操作内容：计算每个班级中的回访人数
	 * 需要的参数： classinfo_id		organization_id(机构用户查的权限)	
	 * @param map
	 * @return
	 */
	public Record queryVisitCount(Long classinfoId);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据传过来的参数查找该班级回访成功的人数
	 * 操作表：visit
	 * 操作内容：计算每个班级中的回访成功的人数
	 * @param map
	 * @return
	 */
	public Record queryVisitSuccess(Long classinfoId);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据用户名查找用户id
	 * 操作表：user
	 * 操作内容：查找用户id
	 * @param username
	 * @return
	 */
	public Record queryUserId(String username);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：添加visit
	 * 操作表：visit
	 * 操作内容：当点击电话回访中的电话回访，则添加一条记录
	 * @param map
	 * @return
	 */
	public int addVisit(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：添加visit
	 * 操作表：visit
	 * 操作内容：当点击电话回访登记表中的保存时，则修改记录
	 * @param map
	 * @return
	 */
	public int modifyVisit(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：在visit查找是否存在此stuId
	 * 操作表：visit
	 * 操作内容：当点击电话回访中的操作时，判断visit查找是否存在此stuId
	 * @param stuId
	 * @return
	 */
	public Record searchStuId(Long stuId);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：在tel_visit查找是否存在此id
	 * 操作表：visit
	 * 操作内容：当导入的操作时，判断tel_visit查找是否存在此id，如果存在则跳出此记录，如果不存在，则继续
	 * @param stuId
	 * @return
	 */
	public Record searchStuIdAboutTel(Long stuId);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：在visit查找stuId,并修改访问人为当前用户
	 * 操作表：visit
	 * 操作内容：当点击电话回访中的操作时，visit查找此stuId，修改访问人
	 * @param stuId
	 * @return
	 */
	public int modifyStuId(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：在tel_visit表中添加记录
	 * 操作表：tel_visit
	 * 操作内容：当点击电话回访登记表中的保存时，添加一条记录
	 * @param map
	 * @return
	 */
	public int addTelVisit(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：在visit中删除记录
	 * 操作表：visit
	 * 操作内容：在visit中删除记录
	 * @param id
	 * @return
	 */
	public int deleteVistById(Long[] id);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：在tel_visit中删除记录
	 * 操作表：tel_visit
	 * 操作内容：在tel_visit表删除记录
	 * @param id
	 * @return
	 */
	public int deleteTelVistById(Long[] id);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：查找tel_visit表中关于stuId的记录
	 * 操作表：tel_visit
	 * @param stuId
	 * @return
	 */
	public Record searchVisitByStuId(Long stuId);
	
	
}
