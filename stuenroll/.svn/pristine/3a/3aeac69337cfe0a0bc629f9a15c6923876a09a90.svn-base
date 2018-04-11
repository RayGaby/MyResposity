package cn.gov.hrss.ln.stuenroll.student;

import cn.gov.hrss.ln.stuenroll.db.I_StudentDao;

/**
 * 学生业务模块具体实现类
 * 
 * @author Administrator
 *
 */
public class StudentService implements I_StudentService {
	private I_StudentDao i_StudentDao;

	/**
	 * 学生登录实现方法
	 * 
	 */
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		boolean bool = i_StudentDao.login(username, password);
		return bool;
	}

	@Override
	public Long queryRegisterIdAtLogin(String username) {
		// TODO Auto-generated method stub
		Long registerId = i_StudentDao.queryRegisterIdAtLogin(username);
		return registerId;
	}

	@Override
	public String queryPIDAtLogin(Long registerId) {
		// TODO Auto-generated method stub
		String pid = i_StudentDao.queryPIDAtLogin(registerId);
		return pid;
	}

	public I_StudentDao getI_StudentDao() {
		return i_StudentDao;
	}

	public void setI_StudentDao(I_StudentDao i_StudentDao) {
		this.i_StudentDao = i_StudentDao;
	}

}
