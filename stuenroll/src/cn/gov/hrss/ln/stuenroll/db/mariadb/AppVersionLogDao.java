package cn.gov.hrss.ln.stuenroll.db.mariadb;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_AppVersionLogDao;

/**
 * App发布日志appversion_log表Dao类
 * 
 * @author cs
 *
 */
public class AppVersionLogDao implements I_AppVersionLogDao {

	@Override
	public Record checkupdate(String appid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	version, ");
		sql.append("	note, ");
		sql.append("	url ");
		sql.append("FROM ");
		sql.append("	appversion_log ");
		sql.append("WHERE ");
		sql.append("	create_time = ( ");
		sql.append("		SELECT ");
		sql.append("			MAX(create_time) ");
		sql.append("		FROM ");
		sql.append("			appversion_log ");
		sql.append("		WHERE ");
		sql.append("			appid = ? ");
		sql.append("	); ");
		Record record = Db.findFirst(sql.toString(), appid);
		return record;
	}

}
