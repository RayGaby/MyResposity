package cn.gov.hrss.ln.stuenroll.db;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * mongdb 动态消息Dao接口
 * 
 * @author cs
 *
 */
public interface I_NewsfeedMongoDao {
	/**
	 * 获取当前registerId下的动态消息
	 * 
	 * @param id
	 * @return
	 */
	public Page<Record> getNewsfeed(long registerId, int currentPage);
}
