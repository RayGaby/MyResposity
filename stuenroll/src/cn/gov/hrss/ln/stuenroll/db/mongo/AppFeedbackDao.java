package cn.gov.hrss.ln.stuenroll.db.mongo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_AppFeedbackMongoDao;
import cn.gov.hrss.ln.stuenroll.plugin.MongoKit;

/**
 * AppFeedback Dao类
 * 
 * @author cs
 *
 */
public class AppFeedbackDao implements I_AppFeedbackMongoDao {

	@Override
	public List<String> savePic(List<File> files) {
		List<String> id = new ArrayList<>();
		for (File file : files) {
			id.add(MongoKit.save("AppFeedbackPic", file));
		}
		return id;
	}

	@Override
	public boolean saveRecord(Record record) {
		System.out.println("aaaa");
		int count = MongoKit.save("AppFeedback", record);
		System.out.println(count);
		return true;
	}

}
