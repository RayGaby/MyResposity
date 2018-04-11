package cn.gov.hrss.ln.stuenroll.db;

import com.jfinal.plugin.activerecord.Record;

/**
 * 考勤模块Dao层接口
 * @author mky
 *
 */
public interface I_CheckDao {
/**
 * 根据pid、开始/结束日期查询签到次数
 */
public Long searchCheck(String pid,String startDate,String endDate);


public Long searchLate(String pid,String startDate,String endDate);
}


