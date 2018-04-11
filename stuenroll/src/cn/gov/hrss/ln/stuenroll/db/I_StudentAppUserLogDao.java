package cn.gov.hrss.ln.stuenroll.db;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * StudentAppUserLog表Dao接口
 * 
 * @author cs
 *
 */
public interface I_StudentAppUserLogDao {
	/**
	 * AppUser账号安全操作日志存储
	 * 
	 * @param register_id
	 * @param operation
	 * @param new_value
	 * @param status
	 * @param time
	 * @return
	 */
	public boolean save(long register_id, String operation, String new_value, String status, long time);

	/**
	 * AppUser账号安全操作日志修改日志操作状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean update(long id, String status);

	/**
	 * AppUser账号安全操作日志 获取特定账户下特定操作的状为某状态的记录
	 *
	 * @param register_id
	 * @param operation
	 * @param new_value
	 * @param status
	 * @param time
	 * @return
	 */
	public List<Record> checkStatus(Long register_id, String operation, String new_value, String status, Long time);

}
