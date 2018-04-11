package cn.gov.hrss.ln.stuenroll.db;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
/**
 * 我的班级模块Dao层接口
 * @author Gu
 *
 */
public interface I_MyClassDao {
	
	/**
	 * 根据pid查询课程信息
	 * 需要在classinfo表新建period列
	 * 
	 * @param pid
	 * @return
	 */
	public List<Record> searchClassInfo(String pid);
	
	/**
	 * 根据classinfo_id查询同学
	 * @param classinfo
	 * @return
	 */
	public List<Record> searchClassmate(String classid);
	
	/**
	 * 删除收藏
	 * @param pid
	 * @param collect_id
	 * @return
	 */
	public int deleteCollect(String pid,String collect_id);
	
	
	/**
	 * 更新收藏
	 * @param pid
	 * @param collect_id
	 * @return
	 */
	public int updateCollect(String pid,String collect_id);
	
	/**
	 * 获取收藏的好友的pid
	 * @param pid
	 * @return
	 */
	public String getCollect(String pid);
}
