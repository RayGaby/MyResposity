package cn.gov.hrss.ln.stuenroll.friend;

import java.io.IOException;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_FriendController {
	/**
	 * 查询好友记录
	 * 
	 * @param map
	 * @param start
	 * @param length
	 * @return
	 */
	public void searchFriend();
	/**
	 * 查詢user表中的好友
	 * 
	 * @param username
	 * @return
	 */
	public void searchFriendFromUser();
	/**
	 * 查询好友总数
	 * 
	 * @param map
	 * @return
	 */
	public void searchFriendCount();
	/**
	 * 根据ID删除数据
	 * 
	 */
	public void deleteById();
	  /**
	   * 查询当前机构所有年份的专业
	   * 
	   */
	 public void searchProfessionWithOrganization();
	 /**
	   * 查询关键字
	   * 
	   */
	 public void searchKeyWords();
		/**
		 * 好友置顶
		 * 
		 */
	public void friendTop();
	/**
	 * 添加好友
	 * 
	 */
	public void addFriend();
	/**
	 * 判断用户是否在线
	 * 
	 */
	public void userOnLine();
	/**
	 * 提交保存头像
	 */
	public void save();
	/**
	 * 获得好友头像id
	 * @throws IOException 
	 * 
	 */
	public void getPhoto();
	/**
	 * 获得个人资料面板的头像
	 * @param name
	 * @return
	 */
	public void initPersonalPhoto();
}
