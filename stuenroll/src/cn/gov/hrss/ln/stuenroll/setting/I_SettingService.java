package cn.gov.hrss.ln.stuenroll.setting;

import java.io.File;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * app个人信息设置服务接口
 * 
 * @author cs
 *
 */
public interface I_SettingService {
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
	 * 修改密码
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param newpassword
	 * @return
	 */
	public String setPassword(long id, String username, String password, String newpassword);

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
	public boolean setEmail(long id, String email, long time);

	/**
	 * 验证邮箱,发送邮件
	 * 
	 * @param id
	 * @param email
	 * @return
	 */
	boolean sendEmail(long id, String email);

	/**
	 * 修改昵称
	 * 
	 * @param id
	 * @param nickname
	 * @return
	 */
	public boolean setNickname(long id, String nickname);

	/**
	 * 存储App反馈问题信息
	 * 
	 * @param record
	 * @param list
	 * @return
	 */
	public boolean saveFeedback(Record record, List<File> files);

	/**
	 * 检测App最新版本
	 * 
	 * @param appid
	 * @return
	 */
	public Record checkupdate(String appid);
}
