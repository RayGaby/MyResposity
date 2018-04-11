package cn.gov.hrss.ln.stuenroll.news;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_NewsDao;

public class NewsService implements I_NewsService {
	private I_NewsDao i_NewsDao;
	
	public I_NewsDao getI_NewsDao() {
		return i_NewsDao;
	}

	public void setI_NewsDao(I_NewsDao i_NewsDao) {
		this.i_NewsDao = i_NewsDao;
	}

	@Override
	public List<Record> searchNews(int start, int pageSize) {
		List<Record> list=i_NewsDao.searchNews(start, pageSize);
		return list;
	}

	@Override
	public Record searchNewsById(Long id) {
		Record record=i_NewsDao.searchNewsById(id);
		return record;
	}

}
