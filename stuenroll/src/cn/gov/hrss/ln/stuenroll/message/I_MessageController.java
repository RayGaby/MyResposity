package cn.gov.hrss.ln.stuenroll.message;

import java.io.File;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public interface I_MessageController {
	public void getNotice();
	public void  getImageFile();
	public void getMessage();
}
