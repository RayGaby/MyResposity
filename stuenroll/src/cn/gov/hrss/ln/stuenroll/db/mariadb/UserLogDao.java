package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.Date;

import com.jfinal.plugin.activerecord.Db;

import cn.gov.hrss.ln.stuenroll.db.I_UserLogDao;

/**
 * UserLog表Dao类
 * 
 * @author YangDi
 *
 */
public class UserLogDao implements I_UserLogDao {

	@Override
	public void save(String username, String operation, String type) {
		StringBuffer sql=new StringBuffer();
		sql.append("INSERT INTO user_log ( ");
		sql.append("	id, ");
		sql.append("	user_id, ");
		sql.append("	operation, ");
		sql.append("	type, ");
		sql.append("	sharding ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("	(NEXT VALUE FOR MYCATSEQ_GLOBAL, (SELECT id FROM `user` WHERE username = ?), ?, ?, ?); ");
		
		Db.update(sql.toString(),username,operation,type,new Date().getTime());

	}

	@Override
	public Date searchCreate_time(String username, String type) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT ul.create_time ");
		sql.append("FROM user_log AS ul ");
		sql.append("JOIN `user` AS u ON u.id=ul.user_id ");
		sql.append("WHERE u.username=? ");
		sql.append("AND ul.type=? ");
		sql.append("ORDER BY ul.create_time desc ");
		sql.append("LIMIT 0,1; ");
		
		Date date=Db.queryDate(sql.toString(),username,type);
		return date;
	}

}
