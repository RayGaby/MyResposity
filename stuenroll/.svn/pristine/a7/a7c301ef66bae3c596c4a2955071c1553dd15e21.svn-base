package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_EnrollDao {
	/**
	 * 涉及到的模块：班级管理,专业管理
	 * 操作表：enroll
	 * 操作对象：就业网用户 	机构用户
	 * 操作内容：对于班级管理，计算enroll中的每个班级人数；对于专业管理，计算每个专业的人数
	 * 需要的参数： classinfo_id		organization_id(机构用户查的权限)		profession_id
	 * @param map
	 * @return
	 */
	public Record queryCountAboutStudent(HashMap map);
	
	/**
	 * 涉及到的模块:班级管理，专业管理
	 * 操作表：enroll
	 * 操作对象：就业网用户  	机构用户
	 * 操作内容：对于班级管理，计算enroll中的每个班级的就业人数；对于专业管理，计算每个专业的就业人数
	 * 需要的参数：classinfo_id 	state_id	organization_id		profession_id
	 * @param map
	 * @return
	 */
	public Record queryCountAboutEmployed(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 操作表：enroll
	 * 操作对象：就业网用户
	 * 操作内容：根据classinfoId查找学生的所有信息,归档
	 * 需要参数：classinfoId
	 * @param classinfoId
	 * @return
	 */
	public List<Record> searchStudentinfo(Long classinfoId);
	
	/**
	 * 根据ID删除数据
	 * @param id
	 * @return
	 */
	public int deleteById(Long[] id);
	
	/**
	 * 涉及到的模块：班级管理
	 * 操作表：enroll
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：当点击班级人数是，班级的名单出现
	 * 需要参数：classinfo_id
	 * @param map
	 * @return
	 */
	public List<Record> searchStudentAboutClassinfo(Long classinfoId,long start,long length);
	
	/**
	 * 涉及到的模块：班级管理
	 * 操作表：enroll
	 * 操作对象：就业网用户
	 * 操作内容：点击电话回访中的操作，计算该班级没有回访成功的人数
	 * @param classinfoId
	 * @return
	 */
	public Record classStuCount(Long classinfoId);
	
	/**
	 * 涉及到的模块：班级管理
	 * 操作表：enroll
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：点击电话回访中的操作，随机生成一个学生信息进行回访
	 * @param classinfoId
	 * @return
	 */
	public Record randomSearch(Long classinfoId,long start);
	
	/**
	 * 涉及到的模块：班级管理
	 * 操作表：enroll
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：点击电话回访中的详细名单中的详细时，根据id查询相关信息
	 * @param stuId
	 * @return
	 */
	public Record searchStuById(Long stuId);
	
	/**
	 * 涉及到的模块： 班级管理
	 * 参数作用：利用classinfoId查询所有回访名单
	 * 操作表：tel_visit和visit,和enroll
	 * 操作内容：当进入到回访记录名单时显示回访名单
	 * @param classinfoId
	 * @return
	 */
	public List<Record> queryVisitList(HashMap map);
	
	/**
	 * 涉及到的模块： 班级管理
	 * 参数作用：利用classinfoId查询所有回访名单的数量
	 * 操作表：tel_visit和visit,和enroll
	 * 操作内容：当进入到回访记录名单时显示回访名单的总数
	 * @param classinfoId
	 * @return
	 */
	public Record queryVisitListCount(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理中的电话回访表中的导入
	 * 操作内容：根据学生的id和班级id，查找导入的学生是否存在
	 * @param stuId
	 * @param classinfoId
	 * @return
	 */
	public Record queryCountAboutIdAndClassinfoId(Long stuId,Long classinfoId);
	
	/**
	 * 涉及到的模块：班级管理
	 * 操作内容：回访成功后，根据所传入的学生id进行查找该id的学生姓名，然后告诉此学生回访成功
	 * @param stuId
	 * @return
	 */
	public Record queryStuName(Long stuId);
	
}
