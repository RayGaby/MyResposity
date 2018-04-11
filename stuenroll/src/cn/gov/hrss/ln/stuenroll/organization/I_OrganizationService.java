package cn.gov.hrss.ln.stuenroll.organization;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_OrganizationService {
	/**
	 * 显示所有机构数据
	 * 
	 * @return
	 */
	public List<Record> showOrganization();
	
	/**
	 * 显示机构数量
	 * 
	 * @return
	 */
	public long showOrganizationCount();
	
	/**
	 * 根据id删除机构数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(Long[] id);
	
	/**
	 * 根据name在organization_join查找organization是否存在
	 * 
	 * @param name
	 * @return
	 */
	public long searchInOJById(Long id);
	
	public boolean addOrganization(HashMap map); 
	
	/**
	 * 根据ID找到要修改数据
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateOrganizationById(HashMap map);
	
	/**
	 * 添加参与转换机构
	 * 
	 * @param id
	 * @param year
	 * @param block
	 * @return
	 */
	public int addJoin(Long[] id,Integer year,Integer block);
	
	/**
	 * 查询参与转换机构信息
	 * 
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> searchJoin(HashMap map,long start,long length);
	
	/**
	 * 查询参与转换机构数量
	 * 
	 * @param map
	 * @return
	 */
	public long searchJoinCount(HashMap map);
	
	/**
	 * 根据id修改机构年届
	 * 
	 * @param id
	 * @param year
	 * @return
	 */
	public boolean updateJoinById(Long id,Integer year);
	
	/**
	 * 根据ID删除参与转换机构
	 * 
	 * @param id
	 * @return
	 */
	public int deleteJoinById(Long[] id);
	
	/**
	 * 改变开放报名状态
	 * 
	 * @param id
	 * @return
	 */
	public boolean openJoinById(Long id);
	
	public boolean closeJoinById(Long id);
}
