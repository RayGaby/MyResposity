package cn.gov.hrss.ln.stuenroll.profession;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_ProfessionService {
	/**
	 * 涉及到的模块：专业管理
	 * 参数作用：实现分页
	 * 操作对象：就业网用户 机构用户
	 * 操作内容：查询专业id、专业名称、关联机构、关联年届、班级数量、培训人数、就业率
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> queryProfession(HashMap map); 
	
	/**
	 * 涉及到的模块：专业管理
	 * 参数作用：实现分页
	 * 操作对象：就业网用户 机构用户
	 * 操作内容：查找专业的总记录数
	 * @param map
	 * @return
	 */
	public Record queryProfessionCount(Long organizationId); 
	
	
	/**
	 * 专业管理添加操作
	 * @param professionName
	 * @return
	 */
	public int addProfession(String professionName);
	
	/**
	 * 专业管理修改操作
	 * @param professionName
	 * @param professionId
	 * @return
	 */
	public int modifyProfession(String professionName, Long professionId);
	
	/**
	 * 模块：专业管理
	 * 操作对象：就业网用户
	 * 操作内容：删除选中的专业
	 * @param id
	 * @return
	 */
	public int deleteProfession(Long[] id);
	
	
	/**
	 * 模块：专业管理
	 * 操作对象：就业网用户    机构用户
	 * 操作内容：查询enroll表的班级成员
	 * 参数：profession_id   organization_id 	  start  length
	 * @param frontMap
	 * @return
	 */
	public List<Record> queryProfessionMember(HashMap map);
	
	/**
	 * 模块：专业管理
	 * 操作对象：就业网用户 	机构用户
	 * 操作内容：导入xml，excel，csv
	 * @param list
	 * @return
	 */
	public int importFile(List<Record> list);
	
}
