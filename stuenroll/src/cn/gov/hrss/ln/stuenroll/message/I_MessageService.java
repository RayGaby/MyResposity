package cn.gov.hrss.ln.stuenroll.message;

import java.io.File;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public interface I_MessageService {
	public Page getNotice();
	public File getImageFile(String imageID);
	public Page getMessage(String messageId);
}
