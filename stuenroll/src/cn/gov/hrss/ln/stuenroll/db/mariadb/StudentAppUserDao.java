package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.io.File;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_StudentAppUserDao;
import cn.gov.hrss.ln.stuenroll.plugin.MongoKit;

public class StudentAppUserDao implements I_StudentAppUserDao {

	@Override
	public boolean setIcon(long id, File file) {
		boolean bool = false;
		String fileId = MongoKit.save("StudentAppUserIcon", file);
		if (fileId != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("	icon ");
			sql.append("FROM ");
			sql.append("	studentapp_user ");
			sql.append("WHERE ");
			sql.append("	id = ?;  ");
			String icon = Db.queryStr(sql.toString(), id);
			MongoKit.removeFile("StudentAppUserIcon", icon);
			sql = new StringBuffer();
			sql.append("UPDATE studentapp_user ");
			sql.append("SET icon = ? ");
			sql.append("WHERE ");
			sql.append("	id = ?;  ");
			int count = Db.update(sql.toString(), fileId, id);
			bool = (count == 1);
		}
		return bool;
	}

	@Override
	public File getIcon(long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	icon ");
		sql.append("FROM ");
		sql.append("	studentapp_user ");
		sql.append("WHERE ");
		sql.append("	id = ?;  ");
		String icon = Db.queryStr(sql.toString(), id);
		File file;
		if (icon != null || icon == "") {
			file = MongoKit.getFile("StudentAppUserIcon", icon);
		}
		else {
			file = null;
		}
		return file;
	}

	@Override
	public Record getInfo(long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	username,`name` ");
		sql.append("FROM ");
		sql.append("	studentapp_user ");
		sql.append("WHERE ");
		sql.append("	id = ?;  ");
		Record record = Db.findFirst(sql.toString(), id);
		return record;
	}

	@Override
	public Record getMoreInfo(long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	AES_DECRYPT(UNHEX(tel),'HelloHrss') AS tel,email ");
		sql.append("FROM ");
		sql.append("	studentapp_user ");
		sql.append("WHERE ");
		sql.append("	id = ?;  ");
		Record record = Db.findFirst(sql.toString(), id);
		if (record != null) {
			Map<String, Object> map = record.getColumns();
			for (String key : map.keySet()) {
				if (map.get(key) == null) {
					record.set(key, "");
				}
			}
			byte[] temp = record.getBytes("tel");
			// 号码中间4位加密
			for (int i = 3; i < 7; i++) {
				temp[i] = 42;
			}
			record.set("tel", new String(temp));
			for (String key : map.keySet()) {
				record.set(key, map.get(key).toString());
			}
		}
		return record;
	}

	@Override
	public boolean checkPassword(long id, String username, String password) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	studentapp_user ");
		sql.append("WHERE ");
		sql.append("	id = ?  ");
		sql.append("AND username = ? ");
		sql.append("AND `password` = HEX( ");
		sql.append("	AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("); ");
		long count = Db.queryLong(sql.toString(), id, username, password);
		return (count == 1);
	}

	@Override
	public boolean setPassword(long id, String username, String password, String newpassword) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE studentapp_user ");
		sql.append("SET `password` = HEX(AES_ENCRYPT(?, 'HelloHrss')) ");
		sql.append("WHERE ");
		sql.append("	id = ?;  ");
		int count = Db.update(sql.toString(), newpassword, id);
		return (count == 1);
	}

	@Override
	public boolean setTel(long id, long tel) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE studentapp_user ");
		sql.append("SET tel = HEX(AES_ENCRYPT(?, 'HelloHrss')) ");
		sql.append("WHERE ");
		sql.append("	id = ?;  ");
		int count = Db.update(sql.toString(), tel, id);
		boolean bool = (count == 1);
		return bool;
	}

	@Override
	public boolean setEmail(long id, String email) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE studentapp_user ");
		sql.append("SET email = ? ");
		sql.append("WHERE ");
		sql.append("	id = ?;  ");
		int count = Db.update(sql.toString(), email, id);
		boolean bool = (count == 1);
		return bool;
	}

	@Override
	public boolean setNickname(long id, String nickname) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE studentapp_user ");
		sql.append("SET `name` = ? ");
		sql.append("WHERE ");
		sql.append("	id = ?;  ");
		int count = Db.update(sql.toString(), nickname, id);
		boolean bool = (count == 1);
		return bool;
	}

}