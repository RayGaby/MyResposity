package cn.gov.hrss.ln.stuenroll.myClass;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_MyClassDao;

/**
 * 我的班级模块业务类
 * 
 * @author Administrator
 *
 */
public class MyClassService implements I_MyClassService{

	private I_MyClassDao i_MyClassDao;
	

	@Override
	public List<Record> searchClassInfo(String pid) {
		List<Record> list= i_MyClassDao.searchClassInfo(pid);
		return list;
	}
	
	
	@Override
	public List<Record> searchClassmate(String classid) {
		List<Record> list = i_MyClassDao.searchClassmate(classid);
		return list;
	}
	
	public I_MyClassDao getI_MyClassDao() {
		return i_MyClassDao;
	}
	public void setI_MyClassDao(I_MyClassDao i_MyClassDao) {
		this.i_MyClassDao = i_MyClassDao;
	}
	
}
