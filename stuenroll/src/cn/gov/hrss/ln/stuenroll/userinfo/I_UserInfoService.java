package cn.gov.hrss.ln.stuenroll.userinfo;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_UserInfoService {
	/**
	 * 查询用户信息记录
	 * 
	 * @param map
	 * @return
	 */
	public List<Record> searchUserInfo(HashMap map);

	/**
	 * 查询记录总数
	 * 
	 * @param map
	 * @return
	 */
	public long searchUserInfoCount(HashMap map);

	/**
	 * 删除用户信息记录
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUserInfo(Long[] id);

	/**
	 * 查询角色可选项
	 * 
	 * @return
	 */
	public List<Record> searchSelectableRole();

	/**
	 * 查询 当前年份 培训机构 可选项
	 * 
	 * @param year
	 * @return
	 */
	public List<Record> searchOrganizationJoinInYearAtDropDown(int year);

	/**
	 * 插入用户信息记录
	 * 
	 * @param map
	 * @return
	 */
	public int insertUserInfo(HashMap map);

	/**
	 * 查询机构已归档成员信息记录
	 * 
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> searchOrganizationArchiveMember(HashMap map, long start, long length);

	/**
	 * 查询机构已归档成员总数
	 * 
	 * @param map
	 * @return
	 */
	public long searchMemberCount(HashMap map);

	/**
	 * 修改用户信息记录
	 * 
	 * @param map
	 * @return
	 */
	public int updateUserInfo(HashMap map);

	/**
	 * 查询选中修改记录
	 * 
	 * @param id
	 * @return
	 */
	public List<Record> searchExsitingdata(String id);

	/**
	 * 更改用户状态
	 * 
	 * @param id
	 * @param block
	 * @return
	 */
	public int updateUserBlock(String id, int block);

	/**
	 * 查询用户头像id
	 * 
	 * @param id
	 * @return
	 */
	public String searchPhotoId(String id);
	/**
	 * 导入xml，excel，csv
	 * @param list
	 * @return
	 */
	public int importFile(List<Record> list);
}
