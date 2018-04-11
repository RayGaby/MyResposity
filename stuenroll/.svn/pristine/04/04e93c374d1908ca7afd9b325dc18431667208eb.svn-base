package cn.gov.hrss.ln.stuenroll.report;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_ArchiveDao;
import cn.gov.hrss.ln.stuenroll.db.I_EnrollDao;
import cn.gov.hrss.ln.stuenroll.db.I_EnrollRedisDao;
import cn.gov.hrss.ln.stuenroll.db.I_NewsfeedMongoDao;

/**
 * report界面服务类
 * 
 * @author cs
 *
 */
public class ReportService implements I_ReportService {

	private I_EnrollDao i_EnrollDao;
	private I_NewsfeedMongoDao i_NewsfeedDao;
	private I_EnrollRedisDao i_EnrollRedisDao;
	private I_ArchiveDao i_ArchiveDao;

	@Override
	public Record searchPersonalEnroll(long registerId, long pid) {
		Record record = new Record();
		record = i_EnrollRedisDao.searchPersonalEnroll(registerId, pid);
		if (record == null) {
			record = i_EnrollDao.searchPersonalEnroll(registerId, pid);
		}
		if (record == null) {
			record = i_ArchiveDao.searchPersonalEnroll(registerId, pid);
		}

		return record;
	}

	@Override
	public String searchPersonalState(long id) {
		String state = new String();
		Record record = i_EnrollRedisDao.searchPersonalEnroll(id, 0);
		if (record != null) {
			state = "开始报名";
		}
		else {
			state = i_EnrollDao.searchPersonalState(id);
			if (state == null) {
				state = i_ArchiveDao.searchPersonalState(id);
			}
		}

		return state;
	}

	@Override
	public Page<Record> getNewsfeed(long id, int currentPage) {
		Page<Record> page = i_NewsfeedDao.getNewsfeed(id, currentPage);
		return page;
	}

	public I_EnrollDao getI_EnrollDao() {
		return i_EnrollDao;
	}

	public void setI_EnrollDao(I_EnrollDao i_EnrollDao) {
		this.i_EnrollDao = i_EnrollDao;
	}

	public I_NewsfeedMongoDao getI_NewsfeedDao() {
		return i_NewsfeedDao;
	}

	public void setI_NewsfeedDao(I_NewsfeedMongoDao i_NewsfeedDao) {
		this.i_NewsfeedDao = i_NewsfeedDao;
	}

	public I_EnrollRedisDao getI_EnrollRedisDao() {
		return i_EnrollRedisDao;
	}

	public void setI_EnrollRedisDao(I_EnrollRedisDao i_EnrollRedisDao) {
		this.i_EnrollRedisDao = i_EnrollRedisDao;
	}

	public I_ArchiveDao getI_ArchiveDao() {
		return i_ArchiveDao;
	}

	public void setI_ArchiveDao(I_ArchiveDao i_ArchiveDao) {
		this.i_ArchiveDao = i_ArchiveDao;
	}

}
