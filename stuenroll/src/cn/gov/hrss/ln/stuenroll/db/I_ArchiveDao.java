package cn.gov.hrss.ln.stuenroll.db;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * Archive表Dao接口
 * 
 * @author YangDi
 *
 */
public interface I_ArchiveDao {
	/**
	 * 根据条件查询归档表相关数据总数
	 * 
	 * @param year
	 * @param month
	 * @param stateId
	 * @param organizationId
	 * @return
	 */
	public long searchCountByCondition(Integer year, Integer month, Integer stateId, Long organizationId);
	
	/**
	 * 查询归档记录
	 * 
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> searchArchive(HashMap map, long start, long length);
	
	/**
	 * 查询归档总数
	 * 
	 * @param map
	 * @return
	 */
	public long searchArchiveCount(HashMap map);
	
	/**
	 * 添加归档信息
	 * 
	 * @param map
	 * @return
	 */
	public boolean insertArchive(HashMap map);
	
	/**
	 * 修改归档信息
	 * 
	 * @param map
	 * @return
	 */
	public boolean updateArchive(HashMap map);
	
	public List<Record> searchArchiveById(Long id);
	
	/**
	 * 根据ID删除数据
	 * @param id
	 * @return
	 */
	public int deleteById(Long[] id);
	
	/**
	 * 查询中退数据
	 * 
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> searchQuit(HashMap map, long start, long length);
	
	/**
	 * 查询归档总数
	 * 
	 * @param map
	 * @return
	 */
	public long searchQuitCount(HashMap map);
	
	/**
	 * 添加中退信息
	 * 
	 * @param map
	 * @return
	 */
	public boolean insertQuit(HashMap map);
	
	public List<Record> searchQuitById(Long id);
	
	/**
	 * 修改中退信息
	 * 
	 * @param map
	 * @return
	 */
	public boolean updateQuit(HashMap map);
 
	
	/**
	 * 根据ID删除中退数据
	 * @param id
	 * @return
	 */
	public int deleteQuitById(Long[] id);
	
	/**
	 * 根据ID查找机构
	 * 
	 * @param organizationId
	 * @return
	 */
	public List<Record> searchOrganization(Long organizationId);
	
	/**
	 * 根据Id查找专业
	 * 
	 * @param organizationId
	 * @return
	 */
	public List<Record> searchProfession(Long organizationId);

	/**
	 * 根据register_id查询个报名信息(for mobile) 2016年07月10日 15:11:36 caishuai
	 * 
	 * @param id
	 * @return
	 */
	public Record searchPersonalEnroll(Long registerId, Long pid);

	/**
	 * 根据register_id查询个人进度(for mobile) 2016年07月10日 15:40:32 caishuai
	 * 
	 * @param id
	 * @return
	 */
	public String searchPersonalState(Long id);

	/**
	 * 是否存在某条记录
	 * 
	 * @param pid
	 * @return
	 */
	public boolean hasRecord(String pid);

	/**
	 * 查询报名记录
	 * 
	 * @param pid
	 * @return
	 */
	public Record searchRegisterRecord(String pid);

	/**
	 * 查询注册用户是否在enroll表中有注册信息
	 * 
	 * @param registerId
	 * @return
	 */
	public boolean checkRegisterIdUnique(Long registerId);

	/**
	 * 确认pid是否已经报名——redis
	 * 
	 * @param registerId
	 * @return
	 */
	public boolean checkPidUnique(String pid);
	/**
	 * 查询当前机构某一专业的人数
	 * 
	 * @param professionId
	 * @param organizationId
	 * @return
	 */
	public long searchCountAtProfession(String profession,Long organizationId);
	
	/**
	 * 意向地点统计
	 * @param year
	 * @param place
	 * @param organizationId
	 * @return
	 */
	public long PlaceCount(Integer year ,String place , Long organizationId);
	
	/**
	 * 学员学历
	 * @param year
	 * @param edu
	 * @param organizationId
	 * @return
	 */
	public long education(Integer year ,String edu , Long organizationId);
	
	/**
	 * 本届报名最多的专业的人数统计
	 * @param year
	 * @param organizationId
	 * @return
	 */
	public HashMap professionCount(Integer year , Long organizationId);
	
	
	
	/**
	 * 本届报名最多的专业的就业率
	 * @param year
	 * @param organizationId
	 * @return
	 */
	public HashMap getJob(HashMap map1 ,Integer year, Long organizationId);
	
	/**
	 * 获得该年份和月份的报名信息
	 * @param year
	 * @param month
	 * @return
	 */
	public HashMap getApplyInfo(Integer year, Integer month, Long organizationId);
	/**
	 * 查询某一机构的人数
	 * 
	 * @param professionId
	 * @return
	 */
	public long searchCountAtOrganization(String organization);
}
