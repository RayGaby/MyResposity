package cn.gov.hrss.ln.stuenroll.archive;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_ArchiveService {
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
	 * 查询中退总数
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
	 * 修改归档信息
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
}
