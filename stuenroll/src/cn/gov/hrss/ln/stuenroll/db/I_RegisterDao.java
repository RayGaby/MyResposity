package cn.gov.hrss.ln.stuenroll.db;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * 报名Dao
 * 
 * @author Viva la Vida
 *
 */
public interface I_RegisterDao {
	/**
	 * 查询 学历 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchSelectableEducation();

	/**
	 * 查询 专业 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchSelectableMajor();

	/**
	 * 查询 健康状况 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchSelectableHealthy();

	/**
	 * 查询 政治面貌 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchSelectablePolitics();

	/**
	 * 查询 培训地点 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchSelectablePlace();

	/**
	 * 查询 当前年份 培训机构 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchOrgnizationJoinInYearAtDropDown(int year);

	/**
	 * 查询 “当前年份 当前机构 申报专业” 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchOrgnizationJoinInYearWithProfessionAtDropDown(int year, long professionId);

	/**
	 * 查询 当前年份 专业 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public List<Record> searchProfessionInYearAtDropDown(int year);

	/**
	 * 提交报名信息 废弃，改用slave代理
	 * 
	 * @param map
	 * @return
	 */
	// public boolean registerSubmit(HashMap map);

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
	 * 将报名信息写入Redis
	 * 
	 * @param map
	 * @return
	 */
	public boolean registerRedis(HashMap map);
}
