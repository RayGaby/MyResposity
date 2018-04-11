package cn.gov.hrss.ln.stuenroll.checkin;

import com.jfinal.plugin.activerecord.Record;

/**
 * 考勤查询业务模块接口
 * @author mky
 *
 */
   public interface I_CheckService {
/**
 * 根据pid、开始/结束时间查询考勤次数
 */
	
    public Long searchCheck(String pid,String startDate,String endDate);
    /**
     * 根据pid、开始/结束时间查询迟到次数
     */
    	   
    public Long searchLate(String pid,String startDate,String endDate);
}
