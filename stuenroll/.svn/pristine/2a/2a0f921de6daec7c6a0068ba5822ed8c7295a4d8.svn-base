package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_MyClassDao;
/**
 * 我的班级模块Dao类
 * 
 * @author Gu
 *
 */
public class MyClassDao implements I_MyClassDao{

	@Override
	public List<Record> searchClassInfo(String pid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	c.`name` AS classid, ");
		sql.append("	c.period, ");
		sql.append("	c.date, ");
		sql.append("	o.`name` AS organization, ");
		sql.append("	o.address, ");
		sql.append("	o.tel, ");
		sql.append("	o.liaison ");
		sql.append("FROM ");
		sql.append("	enroll e ");
		sql.append("JOIN classinfo c ON e.classinfo_id = c.id ");
		sql.append("JOIN organization o ON e.organization_id = o.id ");
		sql.append("WHERE  ");
		sql.append("e.pid = ?; ");
		
		List<Record>  list= Db.find(sql.toString(),pid);
		
		return list;
	}
	
	@Override
	public List<Record> searchClassmate(String classid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	e.`name`, ");
		sql.append("	e.sex, ");
		sql.append("	YEAR(e.birthday) AS birthyear, ");
		sql.append("	AES_DECRYPT(UNHEX(e.tel), 'HelloHrss') AS tel, ");
		sql.append("	e.pid, ");
		sql.append("	e.id ");
		sql.append("FROM ");
		sql.append("	enroll e ");
		sql.append("WHERE ");
		sql.append("classinfo_id = (SELECT id FROM classinfo WHERE `name` = ?) ");
		sql.append("ORDER BY e.id; ");
		
		List<Record> list=Db.find(sql.toString(),classid);
		for(Record record : list){
			byte[] tel = record.getBytes("tel");
			record.set("tel", new String(tel));
//			record.set("pid", record.getLong("pid").toString());
			//System.out.println(record.getStr("pid"));
		}
		return list;
	}

	@Override
	public int deleteCollect(String pid, String collect_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCollect(String pid, String collect_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCollect(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
