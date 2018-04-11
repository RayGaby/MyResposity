package cn.gov.hrss.ln.stuenroll.db.mariadb;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_CheckDao;

public class CheckDao implements I_CheckDao {

	@Override
	public Long searchCheck(String pid, String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("checkin ");
		sql.append("WHERE ");
		sql.append("pid =? ");
		sql.append("AND time BETWEEN ? ");
		sql.append("AND ? ");
		endDate=endDate+" 23:59:59";
		Long count=Db.queryLong(sql.toString(),pid,startDate,endDate);
		
		return count;
	}

	@Override
	public Long searchLate(String pid, String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("checkin ");
		sql.append("WHERE ");
		sql.append("pid =? ");
		sql.append("AND HOUR (time) > 7 ");
		sql.append("AND time BETWEEN ? ");
		sql.append("AND ? ");
		endDate=endDate+" 23:59:59";
		Long count=Db.queryLong(sql.toString(),pid,startDate,endDate);
		
		return count;
	}
	
	
	
}
