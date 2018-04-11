package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * 班级管理模块的相关服务接口
 * @author Administrator
 *
 */
public interface I_ClassinfoService {
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：实现分页
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：查询班级id，班级名称，培训机构，培训专业，年届，班级人数，班级状态，就业人数
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> queryListByCondition(HashMap map,long start, long length);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：机构用户查询的时候需要的条件
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：查询班级管理列表总的条目数量
	 * @param map
	 * @return
	 */
	public long queryCountAboutList(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据之前选好的条件，下拉相应的班级编号
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：下拉相应的班级编号
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutClassYear(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据之前选好的条件，下拉相应的班级编号
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：下拉相应的班级编号
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutOrganization(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据之前选好的条件，下拉相应的班级编号
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：下拉相应的
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutProfession(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据之前选好的条件，下拉相应的班级编号
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：下拉相应的班级编号
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutClassId(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：添加classinfo的属性
	 * 操作对象：就业网用户
	 * 操作内容：添加班级
	 * @param map
	 * @return
	 */
	public int addClassinfo(HashMap map);
	
	/**
	 * 班级管理中的添加中的机构的下拉
	 * 涉及到的模块：班级管理
	 * 参数作用：查找相应的机构
	 * 操作对象：就业网用户
	 * 操作内容：点击添加中的机构下拉
	 * @param map
	 * @return
	 */
	public List<Record> dropDownOrganizationAboutAdd(HashMap map);
	
	/**
	 * 班级管理中的添加中的专业的下拉
	 * 涉及到的模块：班级管理
	 * 参数作用：查找相应的专业
	 * 操作对象：就业网用户
	 * 操作内容：点击添加中的专业下拉
	 * @param map
	 * @return
	 */
	public List<Record> dropDownProfessionAboutAdd(HashMap map);
	
	/**
	 * 班级管理的删除
	 * 涉及到的模块：班级管理
	 * 参数作用：根据id删除班级
	 * TODO 参数对象
	 * 操作对象：就业网用户
	 * 操作内容：删除班级
	 * @param id
	 * @return
	 */
	public int deleteById(Long[] id);
	
	/**
	 * 班级管理的修改
	 * 涉及到的模块：班级管理
	 * 参数作用：根据班级的id查找相应信息，并修改
	 * 操作对象：就业网用户
	 * 操作内容：修改班级信息
	 * @param map
	 * @return
	 */
	public int modifyClassinfo(HashMap map);
	
	/**
	 * 班级管理中的班级归档
	 * 涉及到的模块：班级管理
	 * 参数作用：将选中的进行班级归档
	 * 操作对象：就业网用户
	 * 操作内容：班级归档
	 * @param id
	 * @return
	 */
	public int classArchive(Long[] id);
	
	/**
	 * 班级管理人数的名单
	 * 涉及到的模块：班级管理
	 * 参数作用：根据班级的id查找相应的学生名单
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：查看班级的学生名单
	 * @param map 需要判断归档或者未归档
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> searchStudentAboutClassinfo(HashMap map, long start, long length);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：实现分页
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：查询班级id，班级名称，培训机构，培训专业，年届，回访人数，回访成功人数
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> queryTelVisit(HashMap map, long start, long length);
	
	/**
	 * 涉及到的模块：班级管理
	 * 操作内容：回访成功后，根据所传入的学生id进行查找该id的学生姓名，然后告诉此学生回访成功
	 * @param stuId
	 * @return
	 */
	public Record queryStuNameById(Long stuId,Long classinfoId);
}
