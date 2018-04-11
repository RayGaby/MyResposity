package cn.gov.hrss.ln.stuenroll.profession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * profession表Dao实体类
 *
 */

public class ProfessionDao implements I_ProfessionDao {

	@Override
	public List<Record> queryProfession(HashMap map){
		ArrayList param = new ArrayList<>();
		Long start = (Long)map.get("start");
		Long length = (Long) map.get("length");
		Long organizationId = (Long) map.get("organizationId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  DISTINCT ");
		sql.append("	(p.id) AS profession_id, ");
		sql.append("    p.`name` AS profession_name ");
		sql.append("FROM ");
		sql.append("	profession p ");
		sql.append("LEFT JOIN organization_profession op ON p.id = op.profession_id ");
		sql.append("LEFT JOIN organization_join oj ON op.organization_join_id = oj.id ");
		if(organizationId != null){
			sql.append("WHERE ");
			sql.append("	oj.organization_id = ? ");
			param.add(organizationId);
		}
		
		sql.append("ORDER BY ");
		sql.append("	p.id ");
		if(start != null && length != null){
			sql.append("LIMIT ?,? ");
			param.add(start);
			param.add(length);
		}
		
		List<Record> list = Db.find(sql.toString(),param.toArray());
		return list;
	}

	@Override
	public int addProfession(String professionName) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO profession (id, `name`) ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("		NEXT VALUE FOR MYCATSEQ_GLOBAL, ");
		sql.append("			? ");
		sql.append("	) ");
		
		int count = Db.update(sql.toString(),professionName);
		return count;
	}

	@Override
	public boolean professionNameIfExist(String professionName) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	count(DISTINCT p.`name`) ");
		sql.append("FROM ");
		sql.append("	profession p ");
		sql.append("WHERE ");
		sql.append("	p.`name` = ? ");
		long count = Db.queryLong(sql.toString(),professionName);
		if(count==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public int modifyProfession(String professionName, long professionId) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE profession p  ");
		sql.append("SET p.`name` = ? ");
		sql.append("WHERE p.id = ? ");
		int count = Db.update(sql.toString(),professionName,professionId);
		return count;
	}

	@Override
	public int deleteProfession(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	profession ");
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
	public List<Record> queryProfessionMember(HashMap map) {
		ArrayList<Long> param = new ArrayList<Long>();
		Long professionId = (Long) map.get("professionId");  
		Long organizationId = (Long) map.get("organizationId"); 
		
		Long start = (Long) map.get("start"); 
		Long length = (Long) map.get("length");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("((SELECT ");
		sql.append("	e.id AS id, ");
		sql.append("	e.`name` AS name, ");
		sql.append("	e.sex AS sex,  ");
		sql.append("	AES_DECRYPT(UNHEX(e.tel), 'HelloHrss') AS tel, ");
		sql.append("	e.pid AS pid, ");
		sql.append("	ss.`name` AS state ");
		sql.append("FROM enroll e,student_state ss ");
		sql.append("WHERE e.state_id = ss.id ");
		if(organizationId!=null){
			sql.append("AND e.organization_id = ? ");
			param.add(organizationId);  //添加第一个参数
		}
		sql.append("AND e.profession_id = ? ) ");
		param.add(professionId);  //添加第二个参数
		
		sql.append("UNION ALL ");
		
		sql.append("(SELECT ");
		sql.append("	e.id AS id, ");
		sql.append("	e.`name` AS name, ");
		sql.append("	e.sex AS sex, ");
		sql.append("	AES_DECRYPT(UNHEX(e.tel), 'HelloHrss') AS tel, ");
		sql.append("	e.pid AS pid, ");
		sql.append("	ss.`name` AS state ");
		sql.append("FROM archive e,student_state ss ");
		sql.append("WHERE e.state_id = ss.id ");
		if(organizationId!=null){
			sql.append("AND e.organization_id = ? ");
			param.add(organizationId);  //添加第三个参数
		}
		sql.append("AND e.profession_id = ? )) AS result ");
		param.add(professionId);  //添加第四个参数
		
		sql.append("ORDER BY result.id ");
		sql.append("LIMIT ?,? ");
		param.add(start);
		param.add(length);
		List<Record> list = Db.find(sql.toString(),param.toArray());
		for(Record record: list){
			byte[] tel = record.getBytes("tel");
			record.set("tel", new String(tel));
		}
		return list;
	}

	@Override
	public Record queryProfessionCount() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) AS count ");
		sql.append("FROM profession ");
		
		Record record = Db.findFirst(sql.toString());
		return record;
	}

}
