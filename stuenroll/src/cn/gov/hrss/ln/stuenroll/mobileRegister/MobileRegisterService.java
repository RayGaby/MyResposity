package cn.gov.hrss.ln.stuenroll.mobileRegister;

import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_MobileRegisterDao;
import jodd.util.Base64;

public class MobileRegisterService implements I_MobileRegisterService {
	private I_MobileRegisterDao i_MobileRegisterDao;

	@Override
	public int saveRegisteInfo(String username, String password, long tel) {
		boolean bool = i_MobileRegisterDao.checkUsername(username);
		int count = 0;
		if (bool) {
			count = i_MobileRegisterDao.saveRegisteInfo(username, password, tel);
		}else {
			count = 2;
		}
		return count;
	}

	public I_MobileRegisterDao getI_MobileRegisterDao() {
		return i_MobileRegisterDao;
	}

	public void setI_MobileRegisterDao(I_MobileRegisterDao i_MobileRegisterDao) {
		this.i_MobileRegisterDao = i_MobileRegisterDao;
	}
}
