package cn.gov.hrss.ln.stuenroll.organization;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_OrganizationDao;

public class OrganizationService implements I_OrganizationService{
	private I_OrganizationDao i_OrganizationDao;
	
	@Override
	public List<Record> showOrganization() {
		List<Record> list = i_OrganizationDao.showOrganization();
		return list;
	}
	
	@Override
	public long showOrganizationCount() {
		long count = i_OrganizationDao.showOrganizationCount();
		return count;
	}
	
	@Override
	public int deleteById(Long[] id) {
		int i = i_OrganizationDao.deleteById(id);
		return i;
	}
	
	@Override
	public long searchInOJById(Long id) {
		long count = i_OrganizationDao.searchInOJById(id);
		return count;
	}
	
	@Override
	public boolean addOrganization(HashMap map) {
		boolean bool = i_OrganizationDao.addOrganization(map);
		return bool;
	}

	@Override
	public boolean updateOrganizationById(HashMap map) {
		boolean bool = i_OrganizationDao.updateOrganizationById(map);
		return bool;
	}
	
	@Override
	public int addJoin(Long[] id, Integer year, Integer block) {
		int count = i_OrganizationDao.addJoin(id, year, block);
		return count;
	}
	
	@Override
	public List<Record> searchJoin(HashMap map, long start, long length) {
		List<Record> list = i_OrganizationDao.searchJoin(map,start,length);
		return list;
	}

	@Override
	public long searchJoinCount(HashMap map) {
		long count = i_OrganizationDao.searchJoinCount(map);
		return count;
	}

	@Override
	public boolean updateJoinById(Long id, Integer year) {
		boolean bool = i_OrganizationDao.updateJoinById(id,year);
		return bool;
	}
	
	@Override
	public int deleteJoinById(Long[] id) {
		int i = i_OrganizationDao.deleteJoinById(id);
		return i;
	}

	@Override
	public boolean openJoinById(Long id) {
		boolean bool = i_OrganizationDao.openJoinById(id);
		return bool;
	}
	
	public boolean closeJoinById(Long id) {
		boolean bool = i_OrganizationDao.closeJoinById(id);
		return bool;
	}
	
	public I_OrganizationDao getI_OrganizationDao() {
		return i_OrganizationDao;
	}

	public void setI_OrganizationDao(I_OrganizationDao i_OrganizationDao) {
		this.i_OrganizationDao = i_OrganizationDao;
	}
}
