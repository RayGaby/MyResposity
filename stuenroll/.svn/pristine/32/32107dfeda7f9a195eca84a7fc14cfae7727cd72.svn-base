package cn.gov.hrss.ln.stuenroll.db;

import java.io.File;

import com.jfinal.plugin.activerecord.Record;

/**
 * StudentApp_User表Dao接口
 * 
 * @author cs
 *
 */
public interface I_StudentAppUserDao {
	/**
	 * 设置个人头像
	 * 
	 * @param id
	 * @param file
	 * @return
	 */
	public boolean setIcon(long id, File file);

	/**
	 * 获取个人头像
	 * 
	 * @param id
	 * @return
	 */
	public File getIcon(long id);

	/**
	 * 获取个人信息(账号&&名字)
	 * 
	 * @param id
	 * @return
	 */
	public Record getInfo(long id);

	/**
	 * 获取个人详细信息
	 * 
	 * @param id
	 * @return
	 */
	public Record getMoreInfo(long id);

	/**
	 * 检查密码是否正确
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkPassword(long id, String username, String password);

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param newpassword
	 * @return
	 */
	public boolean setPassword(long id, String username, String password, String newpassword);

	/**
	 * 修改手机号码
	 * 
	 * @param id
	 * @param tel
	 * @return
	 */
	public boolean setTel(long id, long tel);

	/**
	 * 修改email
	 * 
	 * @param id
	 * @param email
	 * @return
	 */
	public boolean setEmail(long id, String email);

	/**
	 * 修改昵称
	 * 
	 * @param id
	 * @param nickname
	 * @return
	 */
	public boolean setNickname(long id, String nickname);

}
