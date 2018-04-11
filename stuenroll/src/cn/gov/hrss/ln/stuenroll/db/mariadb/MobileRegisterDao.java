package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;

import cn.gov.hrss.ln.stuenroll.db.I_MobileRegisterDao;

public class MobileRegisterDao implements I_MobileRegisterDao {

	public int saveRegisteInfo(String username, String password, long tel) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO studentapp_user ( ");
		sql.append("	id, ");
		sql.append("	username, ");
		sql.append("	tel, ");
		sql.append("	password,sharding ) ");
		sql.append("VALUES(NEXT VALUE FOR MYCATSEQ_GLOBAL,?,HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append("		HEX( ");
		sql.append("			AES_ENCRYPT(?, 'HelloHrss')  ");
		
		sql.append("		),?) ");
		int count = Db.update(sql.toString(), username, tel, password,new Date().getTime());
		return count;
	}

	@Override
	public boolean checkUsername(String username) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM studentapp_user WHERE username=?; ");
		long count = Db.queryLong(sql.toString(),username);
		return (count==0);
	}
}
