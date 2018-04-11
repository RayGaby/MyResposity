package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * classinfo表controller接口
 * @author Administrator
 *
 */
public interface I_ClassinfoController {
	/**
	 * 查询班级记录
	 */
	public void queryCountByCondition();
	
	/**
	 * 查询班级记录的总数
	 */
	public void queryCountAboutList();
	
	/**
	 * 下拉的班级编号
	 */
	public void dropDownAboutClassId();
	
	/**
	 * 下拉的年届
	 */
	public void dropDownAboutClassYear();
	
	/**
	 * 下拉的机构
	 */
	public void dropDownAboutOrganization();
	
	/**
	 * 下拉的专业
	 */
	public void dropDownAboutProfession();
	
	/**
	 * 添加班级
	 */
	public void addClassinfo();
	
	/**
	 * 添加班级中的机构下拉
	 */
	public void dropDownOrganizationAboutAdd();
	
	/**
	 * 添加班级中的专业下拉
	 */
	public void dropDownProfessionAboutAdd();
	
	/**
	 * 修改班级信息
	 */
	public void modifyClassinfo();
	
	/**
	 * 删除班级
	 */
	public void deleteById();
	
	/**
	 * 班级归档
	 */
	public void classArchive();
	
	/**
	 * 查看每个班级的名单
	 */
	public void searchStudentAboutClassinfo();
	
	/**
	 * 电话回访的表
	 */
	public void queryTelVisit();
	
	/**
	 * 获取organization名称
	 */
	public void organization();
	
	/**
	 * 根据id获取姓名
	 */
	public void queryStuName();
	
}
