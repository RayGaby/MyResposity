package cn.gov.hrss.ln.stuenroll.report;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Report业务接口
 * 
 * @author cs
 *
 */
public interface I_ReportService {

	/**
	 * 根据register_id查询个报名信息(for mobile) 2016年07月01日 15:29:53
	 * 
	 * @param id
	 * @return
	 */
	public Record searchPersonalEnroll(long registerId, long pid);

	/**
	 * 根据register_id查询个人进度(for mobile) 2016年07月04日 15:40:32
	 * 
	 * @param id
	 * @return
	 */
	public String searchPersonalState(long id);

	/**
	 * 获取当前registerId下的动态消息
	 * 
	 * @param id
	 * @return
	 */
	public Page<Record> getNewsfeed(long id, int currentPage);

}
