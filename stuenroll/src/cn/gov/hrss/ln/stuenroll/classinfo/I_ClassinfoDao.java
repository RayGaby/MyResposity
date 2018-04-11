package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * classinfo表Dao接口
 * @author Administrator
 *
 */
public interface I_ClassinfoDao {
	/**
	 * 根据条件查询班级表
	 * 涉及到的模块：班级管理
	 * 参数作用：start和length实现分页，map实现查询数据
	 * 操作对象：就业网用户 	机构用户
	 * 操作内容：查询班级id，班级名称，培训机构，培训专业，年届
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> queryFiveColumn(HashMap map, long start, long length );
	
	
	/**
	 * 查询记录总数
	 * 涉及到的模块：班级管理
	 * 参数作用：机构用户查询时候的需要的条件
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：查询班级管理列表总的条目数量
	 * @param map
	 * @return
	 */
	public long queryClassCount(HashMap map);
	
	/**
	 * 班级管理中的班级编号下拉
	 * 涉及到的模块：班级管理
	 * 参数作用：由于其他下拉被选定，则出现相对应的条件
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：选择相应的班级
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutClassId(HashMap map);
	
	/**
	 * 班级管理中的班级编号下拉
	 * 涉及到的模块：班级管理
	 * 参数作用：由于其他下拉被选定，则出现相对应的条件
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：选择相应的年届
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutClassYear(HashMap map);
	
	/**
	 * 班级管理中的班级编号下拉
	 * 涉及到的模块：班级管理
	 * 参数作用：由于其他下拉被选定，则出现相对应的条件
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：选择相应的机构管理
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutOraganization(HashMap map);
	
	/**
	 * 班级管理中的班级编号下拉
	 * 涉及到的模块：班级管理
	 * 参数作用：由于其他下拉被选定，则出现相对应的条件
	 * 操作对象：就业网用户	机构用户
	 * 操作内容：选择相应的年届
	 * @param map
	 * @return
	 */
	public List<Record> dropDownAboutProfession(HashMap map);
	
	/**
	 * 班级管理中的添加
	 * 涉及到的模块：班级管理
	 * 参数作用：添加相应的数据
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
	 * 班级管理中的修改
	 * 涉及到的模块：班级管理
	 * 参数作用：修改相应的数据
	 * 操作对象：就业网用户
	 * 操作内容：修改班级信息
	 * @param map
	 * @return
	 */
	public int modifyClassinfo(HashMap map);
	
	/**
	 * 班级管理的删除
	 * 涉及到的模块：班级管理
	 * 参数作用：根据id删除班级
	 * TODO 参数对象
	 * 参数对象：
	 * 操作内容：删除班级
	 * @param id
	 * @return
	 */
	public int deleteById(Long[] id);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：根据classinfoId查找班级名称，根据班级名称查找班级状态
	 * 操作对象：就业网用户，机构用户
	 * 操作内容：1.点击电话回访的详细时，显示班级姓名；2.根据回访表中的姓名查找该班级的状态
	 * @param map
	 * @return
	 */
	public Record searchClassinfoName(HashMap map);
	
	/**
	 * 涉及到的模块：班级管理
	 * 参数作用：查找这个姓名是否存在
	 * 操作对象：就业网用户
	 * 操作内容：判断这个姓名是否存在
	 * @param name
	 * @return
	 */
	public boolean classinfoNameIfExist(String name);
	
	/**
	 * 涉及到的模块：专业管理
	 * 参数作用：根据professionId和organizationId查找相关班级的数量
	 * 操作对象：就业网用户	机构用户
	 * @param map
	 * @return
	 */
	public Record queryClassCountByProfession(HashMap map);
	
}
