package cn.gov.hrss.ln.stuenroll.db;

import com.jfinal.plugin.activerecord.Record;

/**
 * redis上Enroll表Dao接口
 * 
 * @author cs
 *
 */
public interface I_EnrollRedisDao {
	/**
	 * 通过registerId查询个人Enroll信息
	 * 
	 * @param registerId
	 * @return
	 */
	public Record searchPersonalEnroll(long registerId, long pid);

	/**
	 * 确认registerId是否已经报名——redis
	 * 
	 * @param registerId
	 * @return
	 */
	public boolean checkRegisterIdUnique(long registerId);

	/**
	 * 确认pid是否已经报名——redis
	 * 
	 * @param registerId
	 * @return
	 */
	public boolean checkPidUnique(String pid);
}
