package cn.gov.hrss.ln.stuenroll.db;

import java.io.File;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

/**
 * AppFeedback Dao接口
 * 
 * @author cs
 *
 */
public interface I_AppFeedbackMongoDao {
	/**
	 * 保存App用户问题反馈图片
	 * 
	 * @param files
	 * @return
	 */
	public List<String> savePic(List<File> files);

	/**
	 * 保存App用户问题反馈
	 * 
	 * @param record
	 * @return
	 */
	public boolean saveRecord(Record record);

}
