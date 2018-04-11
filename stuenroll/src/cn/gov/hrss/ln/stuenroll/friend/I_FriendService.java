package cn.gov.hrss.ln.stuenroll.friend;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_FriendService {
	/**
	 * 查询好友记录
	 * 
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> searchFriend(HashMap map,long start,long length);
	/**
	 * 查詢user表中的好友
	 * 
	 * @param username
	 * @return
	 */
	public Record searchFriendFromUser(String username);
	/**
	 * 查询好友总数
	 * 
	 * @param map
	 * @return
	 */
	public long searchFriendCount(String username);
	/**
	 * 根据ID删除数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(Long id);
	  /**
	   * 查询当前机构所有年份的专业
	   * 
	   * @return
	   */
	public List<Record> searchProfessionWithOrganization(long organizationId);
		/**
		 * 查询当前机构某一专业的人数
		 * 
		 * @param professionId
		 * @param organizationId
		 * @return
		 */
	public long statisticsAtProfession(String profession,long organizationId);
	  /**
	   * 查询所有机构
	   * 
	   * @return
	   */
	  public List<Record> searchAll();
	  /**
	   * 查询所有专业
	   * @return
	   */
	  public List<Record> searchAllProfession();
		/**
		 * 查询某一机构的人数
		 * 
		 * @param professionId
		 * @return
		 */
		public long searchCountAtOrganization(String organization);
	/**
	 * 好友置顶
	 * 
	 * @param id
	 * @return
	 */
	public boolean friendTop(Long id,String username);
	/**
	 * 添加好友
	 * 
	 * @param username
	 * @param friendname
	 * @param role
	 * @param organization
	 * @return
	 */
	public boolean addFriend(String username,String friendname,String role,String organization);
	/**
	 * 判断用户是否在线
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	public boolean userOnLine(String username);
	/**
	 * 获得好友头像id
	 * 
	 * @param id
	 * @return
	 */
	public String getPhoto(String name);
}
