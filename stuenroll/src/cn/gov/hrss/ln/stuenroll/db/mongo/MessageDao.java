package cn.gov.hrss.ln.stuenroll.db.mongo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_MessageDao;
import cn.gov.hrss.ln.stuenroll.plugin.MongoKit;

public class MessageDao implements I_MessageDao{

	@Override
	public Page getNotice() {
		Map map = new HashMap<>();
		map.put("time", null);
		Page page =  MongoKit.paginate("messageList", 1, 1, null, null, map);
		return page;
	}

	@Override
	public File getImageFile(String imageID) {
		
		File file = MongoKit.getFile("messsageImage",imageID );
		
		return file;
	}
	
	@Override
	public Page getMessage(String messageID) {
		Map map = new HashMap<>();
		map.put("messageID",messageID);		
		Page messageRecord = MongoKit.paginate("message",1,1,map);
		return messageRecord;
	}

}
