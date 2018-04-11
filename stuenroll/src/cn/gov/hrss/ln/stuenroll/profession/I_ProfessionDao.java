package cn.gov.hrss.ln.stuenroll.profession;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_ProfessionDao {
	/**
	 * 模块：专业管理
	 * 操作对象：机构用户  就业网用户
	 * 操作内容：查询profession表的id和name
	 * @param map
	 * @return
	 */
	public List<Record> queryProfession(HashMap map);
	 

	/**
	 * 模块：专业管理
	 * 操作对象：就业网用户
	 * 操作内容：查询profession表的id和name
	 * @param profession_name
	 * @return
	 */
	public int addProfession(String professionName);
	
	/**
	 * 专业管理执行添加操作的时候，需要先判断要添加的专业的名称在profession表是否已经存在
	 * @param profession_name
	 * @return
	 */
	public boolean professionNameIfExist(String professionName);
	
	/**
	 * 模块：专业管理
	 * 操作对象：就业网用户
	 * 操作内容：修改指定专业的专业名称
	 * @param profession_name
	 * @return
	 */
	public int modifyProfession(String professionName, long professionId);
	
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
	 * 操作内容：查询班级成员名单
	 * @param map
	 * @return
	 */
	public List<Record> queryProfessionMember(HashMap map);
	
	/**
	 * 模块：就业网用户
	 * 操作对象：就业网用户
	 * 操作内容：就业网用户查看专业的总数
	 * @return
	 */
	public Record queryProfessionCount();
	
}
