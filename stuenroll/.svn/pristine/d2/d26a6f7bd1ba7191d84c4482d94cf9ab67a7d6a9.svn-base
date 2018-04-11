package cn.gov.hrss.ln.stuenroll.db;

import java.util.Date;

/**
 * UserLog表Dao接口
 * 
 * @author YangDi
 *
 */
public interface I_UserLogDao {
	/**
	 * 记录用户行为
	 * 
	 * @param username
	 * @param operation
	 * @param type
	 */
	public void save(String username, String operation, String type);
	/**
	 * 查询用户最近登陆退出时间
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	public Date searchCreate_time(String username, String type);
}
