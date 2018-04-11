package cn.gov.hrss.ln.stuenroll.register;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * 报名Controller
 * 
 * @author Viva la Vida
 *
 */
public interface I_RegisterController {
	/**
	 * 查询 学历 可选项(for mobile) 2016年7月3日16:45:39
	 */
	public void searchSelectableEducation();

	/**
	 * 查询 专业 可选项(for mobile) 2016年7月3日16:45:39
	 */
	public void searchSelectableMajor();

	/**
	 * 查询 健康状况 可选项(for mobile) 2016年7月3日16:45:39
	 */
	public void searchSelectableHealthy();

	/**
	 * 查询 政治面貌 可选项(for mobile) 2016年7月3日16:45:39
	 */
	public void searchSelectablePolitics();

	/**
	 * 查询 培训地点 可选项(for mobile) 2016年7月3日16:45:39
	 */
	public void searchSelectablePlace();

	/**
	 * 查询 当前年份 培训机构 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public void searchOrgnizationJoinInYearAtDropDown();

	/**
	 * 查询 “当前年份 当前机构 申报专业” 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public void searchOrgnizationJoinInYearWithProfessionAtDropDown();

	/**
	 * 查询 当前年份 专业 可选项(for mobile) 2016年7月3日16:45:39
	 * 
	 * @return
	 */
	public void searchProfessionInYearAtDropDown();

	/**
	 * 提交报名信息
	 */
	public void registerSubmit();
	/**
	 * 根据身份证号码查找我的报名表
	 * 
	 */
	public void hasRecord();
	/**
	 * 查询报名记录
	 */
	public void downloadRegisterReport();

	/**
	 * 将报名信息写入Redis
	 * 
	 */
	public void registerRedis();
	/**
	 * 确认registerId是否已经报名——redis
	 * 
	 * @param registerId
	 * @return
	 */
	public void checkRegisterIdUnique();
	/**
	 * 确认pid是否已经报名——redis
	 * 
	 * @param registerId
	 * @return
	 */
	public void checkPidUnique();
}
