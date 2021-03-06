package cn.gov.hrss.ln.stuenroll.classinfo;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * tel_visit和visit表service接口
 * @author Administrator
 *
 */
public interface I_VisitService {
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据state判断从哪个表中查找信息，classinfoId查找学生信息，根据用户姓名查找用户id
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：随机查询enroll表或archive表中的学生信息，并添加到visit表中
	 * @param map
	 * @return
	 */
	public Record randomQuery(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据state判断从哪个表中查找信息，classinfoId查找学生信息
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：查询选中学生的详细回访记录
	 * @param map
	 * @return
	 */
	public Record searchStuVisit(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：将map中的数据添加到tel_visit表中
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：点击保存的时候，将数据存到tel_visit表中，并修改visit表中的状态
	 * @param map
	 * @return
	 */
	public int save(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据classinfoId查询班级名称和机构名称
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：查找班级名称和机构名称
	 * @param classinfoId
	 * @return
	 */
	public Record searchOraganizationAndClass(HashMap map );
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据classinfoId查找回访名单，并进行分页
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：查找回访名单
	 * @param classinfoId
	 * @param start
	 * @param length
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
	 * 涉及到的模块：班级管理
	 * 参数作用：利用classinfoId,查询班级人数
	 * 操作表：classinfo,enroll,archive
	 * 操作内容：查询班级人数，用来在点击详细和操作时判断班级人数是否为0
	 * @param classinfoId
	 * @return
	 */
	public Record queryClassinfoCount(HashMap map);
	
	/**
	 * 班级管理的删除
	 * 涉及到的模块：班级管理
	 * 参数作用：根据id删除回访记录
	 * TODO 参数对象
	 * 操作对象：就业网用户
	 * 操作内容：删除回访记录
	 * @param id
	 * @return
	 */
	public int deleteVisitById(Long[] id);
	
	/**
	 * 回访表的导入
	 * 涉及到的模块：班级管理
	 * 参数作用：添加到数据库
	 * 操作对象：就业网用户
	 * 操作内容：导入
	 * @param list
	 * @return
	 */
	public int importFile(List<Record> list, HashMap searchMap) throws ParseException;
	
}
