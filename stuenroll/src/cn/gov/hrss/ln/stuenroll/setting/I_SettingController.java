package cn.gov.hrss.ln.stuenroll.setting;

/**
 * 用户个人设置控制接口(mobile)
 * 
 * @author cs
 *
 */
public interface I_SettingController {
	/**
	 * 手机用户登出
	 */
	public void logout();

	/**
	 * 设置个人头像
	 */
	public void setIcon();

	/**
	 * 获取个人头像
	 */
	public void getIcon();

	/**
	 * 获取个人信息(账号&&名字)
	 */
	public void getInfo();

	/**
	 * 获取个人详细信息
	 */
	public void getMoreInfo();

	/**
	 * 修改密码
	 */
	public void setPassword();

	/**
	 * 修改电话号码
	 */
	public void setTel();

	/**
	 * 修改Email
	 */
	public void setEmail();

	/**
	 * 验证邮箱,发送邮件
	 */
	public void sendEmail();

	/**
	 * 修改昵称
	 */
	public void setNickname();

	/**
	 * 存储App反馈意见信息
	 */
	public void feedback();

	/**
	 * App检查更新
	 */
	public void update();

	/**
	 * 获取app反馈意见后台 测试:显示结果
	 */
	public void getfeedback();

	/**
	 * 获取app反馈意见后台PICTURE 测试:显示结果
	 */
	public void getfeedbackpic();

}
