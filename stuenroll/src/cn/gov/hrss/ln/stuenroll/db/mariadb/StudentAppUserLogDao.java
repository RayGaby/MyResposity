package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_StudentAppUserLogDao;

/**
 * StudentAppUserLog表Dao实现类
 * 
 * @author cs
 *
 */
public class StudentAppUserLogDao implements I_StudentAppUserLogDao {

	@Override
	public boolean save(long register_id, String operation, String new_value, String status, long time) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO studentapp_user_log ( ");
		sql.append("	`id`, ");
		sql.append("	`register_id`, ");
		sql.append("	`operation`, ");
		sql.append("	`new_value`, ");
		sql.append("	`status`, ");
		sql.append("	`create_time`, ");
		sql.append("	`sharding` ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("		NEXT VALUE FOR MYCATSEQ_GLOBAL, ");
		sql.append("		?,?,HEX(AES_ENCRYPT(?, 'HelloHrss')),?,NULL,? ");
		sql.append("	); ");
		int count = Db.update(sql.toString(), register_id, operation, new_value, status, time);
		return (count == 1);
	}

	@Override
	public boolean update(long id, String status) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE studentapp_user_log ");
		sql.append("SET `status` = ? ");
		sql.append("WHERE ");
		sql.append("	id = ?; ");
		int count = Db.update(sql.toString(), status, id);
		return (count == 1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Record> checkStatus(Long register_id, String operation, String new_value, String status, Long time) {
		ArrayList param = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	id ");
		sql.append("FROM ");
		sql.append("	studentapp_user_log ");
		sql.append("WHERE 1=1 ");
		if (register_id != null) {
			sql.append("AND register_id = ? ");
			param.add(register_id);
		}
		if (operation != null) {
			sql.append("AND operation = ? ");
			param.add(operation);
		}
		if (new_value != null) {
			sql.append("AND new_value = HEX(AES_ENCRYPT(?, 'HelloHrss')) ");
			param.add(new_value);
		}
		if (status != null) {
			sql.append("AND `status` = ? ");
			param.add(status);
		}
		if (time != null) {
			sql.append("AND sharding = ?; ");
			param.add(time);
		}

		List<Record> list = Db.find(sql.toString(), param.toArray());
		return list;
	}

}
