package cn.gov.hrss.ln.stuenroll.db;

import com.jfinal.plugin.activerecord.Record;

/**
 * App发布日志appversion_log表Dao接口
 * 
 * @author cs
 *
 */
public interface I_AppVersionLogDao {
	/**
	 * 检测App最新版本
	 * 
	 * @param appid
	 * @return
	 */
	public Record checkupdate(String appid);
}
