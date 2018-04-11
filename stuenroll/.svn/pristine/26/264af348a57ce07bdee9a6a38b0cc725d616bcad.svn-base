package cn.gov.hrss.ln.stuenroll.message;

import java.io.File;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_MessageDao;

public class MessageService implements I_MessageService {
	private I_MessageDao i_MessageDao;

	@Override
	public Page getNotice() {
		Page page = i_MessageDao.getNotice();
		return page;
	}
	@Override
	public File getImageFile(String imageID) {
		File file = i_MessageDao.getImageFile(imageID);
		return file;
	}
	@Override
	public Page getMessage(String messageId) {
		Page messagePage = i_MessageDao.getMessage(messageId);
		return messagePage;
	}

	public I_MessageDao getI_MessageDao() {
		return i_MessageDao;
	}

	public void setI_MessageDao(I_MessageDao i_MessageDao) {
		this.i_MessageDao = i_MessageDao;
	}

}
