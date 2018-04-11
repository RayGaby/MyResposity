package cn.gov.hrss.ln.stuenroll.student;
/**
 * 学生业务模块接口
 * @author Administrator
 *
 */
public interface I_StudentService {
	/**
	 * 学生登录接口
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password);
	/**
	 * 登录时查找身份证号码接口
	 * 
	 * @param username
	 * @return
	 */
	public String queryPIDAtLogin(Long registerId);
	/**
	 * 登录时查找注册号接口
	 * 
	 * @param username
	 * @return
	 */
	public Long queryRegisterIdAtLogin(String username);
	
}
