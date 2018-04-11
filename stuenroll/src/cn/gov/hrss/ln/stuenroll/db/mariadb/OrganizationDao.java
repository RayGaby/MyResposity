package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_OrganizationDao;

public class OrganizationDao implements I_OrganizationDao {

	@Override
	public List<Record> showOrganization() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	id, ");
		sql.append("	`name`, ");
		sql.append("	abbreviation, ");
		sql.append("	address, ");
		sql.append("	tel, ");
		sql.append("	liaison, ");
		sql.append("	( ");
		sql.append("		SELECT ");
		sql.append("			COUNT(id) ");
		sql.append("		FROM ");
		sql.append("			organization_profession op ");
		sql.append("		WHERE ");
		sql.append("			op.organization_join_id = o.id ");
		sql.append("	) AS pcount, ");
		sql.append("	( ");
		sql.append("		SELECT ");
		sql.append("			COUNT(id) ");
		sql.append("		FROM ");
		sql.append("			classinfo c ");
		sql.append("		WHERE ");
		sql.append("			c.organization_id = o.id ");
		sql.append("	) AS ccount ");
		sql.append("FROM ");
		sql.append("	organization o ");
		sql.append("GROUP BY ");
		sql.append("	o.id ");
		List<Record> list = Db.find(sql.toString());
		
		for (Record record : list) {
			String sqlOfScount = "SELECT COUNT(*) FROM enroll WHERE organization_id= ";
			sqlOfScount+=record.getLong("id").toString()+" ";
			Long scount = Db.queryLong(sqlOfScount);
			record.set("scount", scount);	//得到机构的报名人数
			record.set("id", record.getLong("id").toString());
		}
		return list;
	}

	@Override
	public long showOrganizationCount() {
		String sql = "SELECT COUNT(*) FROM organization";
		long count = Db.queryLong(sql);
		return count;
	}

	@Override
	public int deleteById(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	organization ");
		sql.append("WHERE ");
		sql.append("	id IN ( ");
		for (int i = 0; i < id.length; i++) {
			sql.append("?");
			if (i != id.length - 1) {
				sql.append(",");
			}
		}
		sql.append(") ");
		int i = Db.update(sql.toString(), id);
		return i;
	}
	
	@Override
	public long searchInOJById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	organization_join  ");
		sql.append("WHERE ");
		sql.append("	organization_id = ? ");
		long count = Db.queryLong(sql.toString(),id);
		return count;
	}

	@Override
	public boolean addOrganization(HashMap map) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		String abbreviation = (String) map.get("abbreviation");
		String address = (String) map.get("address");
		String liaison = (String) map.get("liaison");
		String tel = (String) map.get("tel");
		param.add(name);
		param.add(abbreviation);
		param.add(address);
		param.add(liaison);
		param.add(tel);
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO organization ( ");
		sql.append("	id,name,abbreviation,address,liaison,tel ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("		NEXT VALUE FOR MYCATSEQ_GLOBAL, ");
		sql.append("		?,?,?,?,? ");
		sql.append("	) ");
		
		int count = Db.update(sql.toString(),param.toArray());
		return count==1;
	}

	@Override
	public boolean updateOrganizationById(HashMap map) {
		String name = (String) map.get("name");
		String abbreviation = (String) map.get("abbreviation");
		String address = (String) map.get("address");
		String liaison = (String) map.get("liaison");
		String tel = (String) map.get("tel");
		Long id = (Long) map.get("id");
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE organization ");
		sql.append("SET `name` = ?, ");
		sql.append(" abbreviation = ?, ");
		sql.append(" address = ?, ");
		sql.append(" liaison = ?, ");
		sql.append(" tel = ? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		int count= Db.update(sql.toString(),name,abbreviation,address,liaison,tel,id);
		return count==1;
	}

	@Override
	public int addJoin(Long[] id, Integer year, Integer block) {
		String selectsql1 = ("SELECT COUNT(*) FROM organization_join WHERE organization_id=? AND year=? ");
		String selectsql2 = ("SELECT COUNT(*) FROM organization_join WHERE organization_id=? AND year=? AND block=? ");
		StringBuffer insertsql = new StringBuffer();
		StringBuffer updatesql = new StringBuffer();
		
		insertsql.append("INSERT INTO organization_join ( ");
		insertsql.append("	id, ");
		insertsql.append("	organization_id, ");
		insertsql.append("	`year`, ");
		insertsql.append("	block ");
		insertsql.append(") ");
		insertsql.append("VALUES ");
		insertsql.append("	( ");
		insertsql.append("		NEXT VALUE FOR MYCATSEQ_GLOBAL ,?,?,? ");
		insertsql.append("	) ");
		
		updatesql.append("UPDATE organization_join ");
		updatesql.append("SET block=? ");
		updatesql.append("WHERE organization_id=? ");
		
		int count = 0;
		Long check1;
		Long check2;
		for(int i=0;i<id.length;i++){
			check1 = Db.queryLong(selectsql1.toString(),id[i],year);
			if(check1 == 0){
				count +=Db.update(insertsql.toString(),id[i],year,block);
			}
			else{
				check2 = Db.queryLong(selectsql2.toString(),id[i],year,block);
				if(check2 == 0){
					count +=Db.update(updatesql.toString(),block,id[i]);
				}
			}
		}
		return count;
	}

	@Override
	public List<Record> searchJoin(HashMap map, long start, long length) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		String abbreviation = (String) map.get("abbreviation");
		Integer year = (Integer) map.get("year");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	oj.id, ");
		sql.append("	oj.organization_id, ");
		sql.append("	o.`name` AS `name`, ");
		sql.append("	o.abbreviation AS abbreviation, ");
		sql.append("	oj.`year`, ");
		sql.append("	( ");
		sql.append("		SELECT ");
		sql.append("			COUNT(id) ");
		sql.append("		FROM ");
		sql.append("			organization_profession op ");
		sql.append("		WHERE ");
		sql.append("			op.organization_join_id = oj.organization_id ");
		sql.append("	) AS pcount, ");
		sql.append("	( ");
		sql.append("		SELECT ");
		sql.append("			COUNT(id) ");
		sql.append("		FROM ");
		sql.append("			classinfo c ");
		sql.append("		WHERE ");
		sql.append("			c.organization_id = oj.organization_id ");
		sql.append("	) AS ccount, ");
		sql.append("	oj.block ");
		sql.append("FROM ");
		sql.append("	organization_join oj ");
		sql.append("LEFT JOIN organization o ON o.id = oj.organization_id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if (name != null && name.length() > 0) {
			sql.append(" AND o.name = ? ");
			param.add(name);
		}
		if (abbreviation != null && abbreviation.length() > 0) {
			sql.append(" AND o.abbreviation = ? ");
			param.add(abbreviation);
		}
		if (year != null) {
			sql.append(" AND `year` = ? ");
			param.add(year);
		}
		sql.append("GROUP BY ");
		sql.append("	oj.id ");
		sql.append("LIMIT ?, ? ");
		param.add(start);
		param.add(length);
		
		List<Record> list = Db.find(sql.toString(), param.toArray());
		for (Record record : list) {
			String tempid = record.getLong("organization_id").toString();
			String tempyear = record.getInt("year").toString();
			String sqlOfScount = "SELECT COUNT(*) FROM enroll WHERE organization_id=? AND year=? ";
			Long scount = Db.queryLong(sqlOfScount.toString(),tempid,tempyear);
			record.set("scount", scount);	//得到机构的报名人数
			String sqlOfQcount = "SELECT COUNT(*) FROM enroll WHERE organization_id=? AND year=? AND state_id=4 ";
			Long qcount = Db.queryLong(sqlOfQcount.toString(),tempid,tempyear);
			record.set("qcount", qcount);	//得到机构的中退人数
			String sqlOfWcount = "SELECT COUNT(*) FROM enroll WHERE organization_id=? AND year=? AND state_id=5 ";
			Long wcount = Db.queryLong(sqlOfWcount.toString(),tempid,tempyear);
			record.set("wcount", wcount);	//得到机构的就业人数
			record.set("id", record.getLong("id").toString());
			record.set("organization_id", record.getLong("organization_id").toString());	//转换数据类型
		}
		return list; 
	}

	@Override
	public long searchJoinCount(HashMap map) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		String abbreviation = (String) map.get("abbreviation");
		Integer year = (Integer) map.get("year");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	organization_join oj ");
		sql.append("LEFT JOIN organization o ON o.id = oj.organization_id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if (name != null && name.length() > 0) {
			sql.append(" AND o.name = ? ");
			param.add(name);
		}
		if (abbreviation != null && abbreviation.length() > 0) {
			sql.append(" AND o.abbreviation = ? ");
			param.add(abbreviation);
		}
		if (year != null) {
			sql.append(" AND `year` = ? ");
			param.add(year);
		}
		
		long count = Db.queryLong(sql.toString(), param.toArray());
		return count;
	}

	@Override
	public boolean updateJoinById(Long id,Integer year) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE organization_join ");
		sql.append("SET `year` = ? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		long count = Db.update(sql.toString(),year,id);
		return count == 1;
	}

	@Override
	public int deleteJoinById(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	organization_join ");
		sql.append("WHERE ");
		sql.append("	id IN ( ");
		for (int i = 0; i < id.length; i++) {
			sql.append("?");
			if (i != id.length - 1) {
				sql.append(",");
			}
		}
		sql.append(") ");
		int i = Db.update(sql.toString(), id);
		return i;
	}

	@Override
	public boolean openJoinById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE organization_join ");
		sql.append("SET block = 0 ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		long count = Db.update(sql.toString(),id);
		return count==1;
	}
	
	public boolean closeJoinById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE organization_join ");
		sql.append("SET block = 1 ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		long count = Db.update(sql.toString(),id);
		return count==1;
	}
	
	@Override
	public Record search(long id) {
		StringBuffer _builder = new StringBuffer();
		_builder.append("	SELECT ");
		_builder.append("	`name`, ");
		_builder.append("	abbreviation, ");
		_builder.append("	address, ");
		_builder.append("	tel, ");
		_builder.append("	liaison, ");
		_builder.append("	liaison_tel ");
		_builder.append("	FROM ");
		_builder.append("	organization ");
		_builder.append("	WHERE ");
		_builder.append("	id = ?; ");

		Record record = Db.findFirst(_builder.toString(), id);
		return record;
	}
	@Override
	public String searchId(String name) {
		StringBuffer _builder = new StringBuffer();
		_builder.append("	SELECT ");
		_builder.append("	id ");
		_builder.append("	FROM ");
		_builder.append("	organization ");
		_builder.append("	WHERE ");
		_builder.append("	`name` = ? ");

		String id = Db.queryLong(_builder.toString(), name).toString();
		return id;
	}
	@Override
	public List<Record> searchAll() {
		StringBuffer _builder = new StringBuffer();
//		_builder.append("SELECT DISTINCT ");
//		_builder.append("	o.abbreviation AS organization_name, ");
//		_builder.append("	o.id  ");
//		_builder.append("FROM ");
//		_builder.append("	enroll AS e ");
//		_builder.append("JOIN organization AS o ON o.id = e.organization_id ");
		
		_builder.append("SELECT ");
		_builder.append("	abbreviation AS organization_name, ");
		_builder.append("	id ");
		_builder.append("FROM ");
		_builder.append("	organization ");

		List<Record> list = Db.find(_builder.toString());
		return list;
	}

}
