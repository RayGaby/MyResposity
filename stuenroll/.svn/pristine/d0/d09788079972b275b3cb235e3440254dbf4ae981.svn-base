package cn.gov.hrss.ln.stuenroll.archive;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_ArchiveDao;

public class ArchiveService implements I_ArchiveService{
	private I_ArchiveDao i_ArchiveDao;
	
	@Override
	public List<Record> searchArchive(HashMap map, long start, long length) {
		List<Record> list = i_ArchiveDao.searchArchive(map, start, length);
		return list;
	}
	
	@Override
	public long searchArchiveCount(HashMap map) {
		long count = i_ArchiveDao.searchArchiveCount(map);
		return count;
	}
	
	@Override
	public boolean insertArchive(HashMap map) {
		boolean bool = i_ArchiveDao.insertArchive(map);
		return bool;
	}
	
	@Override
	public boolean updateArchive(HashMap map) {
		boolean bool = i_ArchiveDao.updateArchive(map);
		return bool;
	}
	
	@Override
	public List<Record> searchArchiveById(Long id) {
		List<Record> list = i_ArchiveDao.searchArchiveById(id);
		return list;
	}
	
	@Override
	public int deleteById(Long[] id) {
		int i = i_ArchiveDao.deleteById(id);
		return i;
	}

	@Override
	public List<Record> searchQuit(HashMap map, long start, long length) {
		List<Record> list = i_ArchiveDao.searchQuit(map,start,length);
		return list;
	}
	
	@Override
	public long searchQuitCount(HashMap map) {
		long count = i_ArchiveDao.searchQuitCount(map);
		return count;
	}
	
	@Override
	public boolean insertQuit(HashMap map) {
		boolean bool = i_ArchiveDao.insertQuit(map);
		return bool;
	}
	
	@Override
	public List<Record> searchQuitById(Long id) {
		List<Record> list = i_ArchiveDao.searchQuitById(id);
		return list;
	}
	
	@Override
	public boolean updateQuit(HashMap map) {
		boolean bool = i_ArchiveDao.updateQuit(map);
		return bool;
	}
	
	@Override
	public int deleteQuitById(Long[] id) {
		int i = i_ArchiveDao.deleteQuitById(id);
		return i;
	}
	
	@Override
	public List<Record> searchOrganization(Long organizationId) {
		List<Record> list= i_ArchiveDao.searchOrganization(organizationId);
		return list;
	}

	@Override
	public List<Record> searchProfession(Long organizationId) {
		List<Record> list= i_ArchiveDao.searchProfession(organizationId);
		return list;
	}
	
	public I_ArchiveDao getI_ArchiveDao() {
		return i_ArchiveDao;
	}

	public void setI_ArchiveDao(I_ArchiveDao i_ArchiveDao) {
		this.i_ArchiveDao = i_ArchiveDao;
	}

}
