package cn.gov.hrss.ln.stuenroll.checkin;

import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_CheckDao;

public class CheckService implements I_CheckService {

	private I_CheckDao i_CheckDao;
	
	
	
	@Override
	public Long searchCheck(String pid, String startDate, String endDate) {
		Long count = i_CheckDao.searchCheck(pid,startDate,endDate);
		return count;
	}
		@Override
	public Long searchLate(String pid, String startDate, String endDate) {
			Long count = i_CheckDao.searchLate(pid,startDate,endDate);
			return count;
		
	}
	
	public I_CheckDao getI_CheckDao() {
		return i_CheckDao;
	}

	public void setI_CheckDao(I_CheckDao i_CheckDao) {
		this.i_CheckDao = i_CheckDao;
	}





	
}
