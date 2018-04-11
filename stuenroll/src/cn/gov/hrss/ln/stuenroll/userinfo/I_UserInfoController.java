package cn.gov.hrss.ln.stuenroll.userinfo;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_UserInfoController {
	/**
	 * 查询用户信息记录
	 */
	public void searchUserInfo();

	/**
	 * 删除用户信息记录
	 */
	public void deleteUserInfo();

	/**
	 * 查询角色可选项
	 */
	public void searchSelectableRole();

	/**
	 * 查询 当前年份 培训机构 可选项
	 */
	public void searchOrganizationJoinInYearAtDropDown();

	/**
	 * 查询总数
	 */
	public void searchUserInfoCount();

	/**
	 * 插入用户信息记录
	 */
	public void insertUserInfo();

	/**
	 * 查询机构已归档成员信息记录
	 */
	public void searchOrganizationArchiveMember();

	/**
	 * 查询机构已归档成员总数
	 */
	public void searchMemberCount();

	/**
	 * 修改用户信息记录
	 */
	public void updateUserInfo();

	/**
	 * 查询选中修改记录
	 */
	public void searchExsitingdata();

	/**
	 * 更改用户状态
	 */
	public void updateUserBlock();

	/**
	 * 保存头像
	 */
	public void save();

	/**
	 * 查询用户头像id
	 */
	public void searchPhotoId();

	/**
	 * 读取头像
	 */
	public void searchFile();
	
	/**
	 * 导出xml
	 */
	public void exportXml();
	
	/**
	 * 导出cvs
	 */
	public void exportCsv();
	
	/**
	 * 导出excel
	 */
	public void exportExcel();
	/**
	 * 导入文件
	 */
	public void importFile();
}
