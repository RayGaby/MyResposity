package cn.gov.hrss.ln.stuenroll.welcome;

import java.util.HashMap;

/**
 * 欢迎模块业务接口
 * 
 * @author YangDi
 *
 */
public interface I_WelcomeService {
	/**
	 * 统计某一年的报名和归档数据
	 * 
	 * @param year
	 * @param organizationId
	 * @return
	 */
	public HashMap statisticsInYear(Integer year, Long organizationId);
	public HashMap PlaceCount(Integer year , Long organizationId);
	public HashMap education(Integer year , Long organizationId);
	public HashMap professionCount(Integer year , Long organizationId);
	public HashMap professionClassCount(Integer year , Long organizationId);
	public HashMap getJob(Integer year , Long organizationId);
	public HashMap getApplyInfo(Integer year, Integer month, Long organizationId);
}
