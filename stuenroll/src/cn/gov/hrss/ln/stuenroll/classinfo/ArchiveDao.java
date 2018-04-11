package cn.gov.hrss.ln.stuenroll.classinfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * archive表Dao类
 * @author Administrator
 *
 */

public class ArchiveDao implements I_ArchiveDao{

	@Override
	public Record queryCountAboutStudent(HashMap map) {
		
		ArrayList<Long> param = new ArrayList<Long>();
		
		Long classinfoId = (Long) map.get("classinfoId");
		Long professionId = (Long) map.get("professionId");
		Long organizationId = (Long) map.get("organizationId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(DISTINCT pid) AS student_count ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		
		if(classinfoId != null){
			sql.append("AND classinfo_id = ? ");
			param.add(classinfoId);
		}
		if(professionId != null){
			sql.append("AND profession_id = ? ");
			param.add(professionId);
		}
		if(organizationId != null){
			sql.append("AND organization_id = ? ");
			param.add(organizationId);
		}
		
		Record result = Db.findFirst(sql.toString(), param.toArray());
		
		return result;
	}

	@Override
	public Record queryCountAboutEmployedStudent(HashMap map) {
		ArrayList<Long> param = new ArrayList<Long>();
		//从调用方法的类中获取下列参数
		Long classinfoId = (Long) map.get("classinfoId");
		Long professionId = (Long) map.get("professionId");
		Long organizationId = (Long) map.get("organizationId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(DISTINCT pid) AS employed_count ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		sql.append("	state_id = 5 ");
		
		if(classinfoId != null){
			sql.append("AND classinfo_id = ? ");
			param.add(classinfoId);
		}
		if(professionId != null){
			sql.append("AND profession_id = ? ");
			param.add(professionId);
		}
		if(organizationId != null){
			sql.append("AND organization_id = ? ");
			param.add(organizationId);
		}
		
		Record result = Db.findFirst(sql.toString(), param.toArray());
		return result;
	}

	@Override
	public int addStudentinfo(HashMap map) {
		// TODO 添加所有信息，并加密
		ArrayList param = new ArrayList();
		String name = (String)map.get("name");
		String sex = (String) map.get("sex");
		String nation = (String) map.get("nation");
		String pid = (String) map.get("pid");
		String graduate_school = (String) map.get("graduateSchool");
		Long graduate_year = (Long) map.get("graduateYear");
		Date graduate_date = (Date) map.get("graduateDate");
		String education = (String) map.get("education");
		String major = (String) map.get("major");
		String healthy = (String) map.get("healthy");
		String politics = (String) map.get("politics");
		Date birthday = (Date) map.get("birthday");
		String resident_address = (String) map.get("resident_address");
		String permanent_address = (String) map.get("permanent_address");
		String home_address = (String) map.get("home_address");
		String tel = (String) map.get("tel");
		String home_tel = (String) map.get("home_tel");
		String wechat = (String) map.get("wechat");
		String email = (String) map.get("email");
		Long professionId = (Long) map.get("professionId");
		Long classinfoId = (Long) map.get("classinfoId");
		Long stateId = (Long) map.get("stateId");
		Long organizationId = (Long) map.get("organizationId");
		String place = (String) map.get("place");
		String remark = (String) map.get("remark");
		Long sharding = new Date().getTime();
		Integer year = (Integer) map.get("year");
		
		//获取当前时间的毫秒数
		//Long sharding = new Date().getTime();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO archive ( ");
		sql.append("	id, ");
		sql.append("	`name`, ");
		sql.append("	sex, ");
		sql.append("	nation, ");
		sql.append("	pid, ");
		sql.append("	graduate_school, ");
		sql.append("	graduate_year, ");
		sql.append("	graduate_date, ");
		sql.append("	education, ");
		sql.append("	major, ");
		sql.append("	healthy, ");
		sql.append("	politics, ");
		sql.append("	birthday, ");
		sql.append("	resident_address, ");
		sql.append("	permanent_address, ");
		sql.append("	home_address, ");
		sql.append("	tel, ");
		sql.append("	home_tel, ");
		sql.append("	email, ");
		sql.append("	wechat, ");
		sql.append("	profession_id, ");
		sql.append("	classinfo_id, ");
		sql.append("	state_id, ");
		sql.append("	organization_id, ");
		sql.append("	place, ");
		sql.append("	`year`, ");
		sql.append("	remark, ");
		sql.append("	sharding ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("		NEXT VALUE FOR MYCATSEQ_GLOBAL, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("			AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			? ");
		sql.append("	) ");
		
		param.add(name);
		param.add(sex);
		param.add(nation);
		param.add(pid);
		param.add(graduate_school);
		param.add(graduate_year);
		param.add(graduate_date);
		param.add(education);
		param.add(major);
		param.add(healthy);
		param.add(politics);
		param.add(birthday);
		param.add(resident_address);
		param.add(permanent_address);
		param.add(home_address);
		param.add(tel);
		param.add(home_tel);
		param.add(email);
		param.add(wechat);
		param.add(professionId);
		param.add(classinfoId);
		param.add(stateId);
		param.add(organizationId);
		param.add(place);
		param.add(year);
		param.add(remark);
		param.add(sharding);

		int record = Db.update(sql.toString(), param.toArray());
		return record;
	}
	
	public List<Record> searchStudentAboutClassinfo(Long classinfoId, long start, long length) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	e.id, ");
		sql.append("	e.`name`, ");
		sql.append("	e.sex, ");
		sql.append("	CAST( ");
		sql.append("		AES_DECRYPT(UNHEX(e.tel), 'HelloHrss') AS CHAR ");
		sql.append("	) AS tel, ");
		sql.append("	e.pid, ");
		sql.append("	ss.`name` AS state ");
		sql.append("FROM ");
		sql.append("	archive e ");
		sql.append("JOIN student_state ss ON e.state_id = ss.id ");
		sql.append("WHERE ");
		sql.append("	e.classinfo_id = ? ");
		sql.append("ORDER BY e.id ");
		sql.append("LIMIT ?, ? ");

		List<Record> list = Db.find(sql.toString(), classinfoId, start, length);

		return list;
	}
	
	public Record classStuCount(Long classinfoId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(id) AS count ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		sql.append("	classinfo_id = ? ");
		sql.append("AND id NOT IN ( ");
		sql.append("	SELECT ");
		sql.append("		stu_id ");
		sql.append("	FROM ");
		sql.append(" 		visit ");
		sql.append("	WHERE ");
		sql.append("		classinfo_id = ? ");
		sql.append("	AND state = 1 ");
		sql.append(") ");
		
		Record record = Db.findFirst(sql.toString(), classinfoId, classinfoId);
		return record;
	}
	
	public Record randomSearch(Long classinfoId, long start) { 
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.id AS id ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("JOIN profession p ON a.profession_id = p.id ");
		
		sql.append("WHERE ");
		sql.append("	a.classinfo_id = ? ");
		sql.append("AND a.id NOT IN ( ");
		sql.append("SELECT ");
		sql.append("		stu_id ");
		sql.append("	FROM ");
		sql.append("		visit ");
		sql.append("	WHERE ");
		sql.append("		classinfo_id = ? ");
		sql.append("	AND state = 1 ");
		sql.append(") ");
		sql.append("ORDER BY ");
		sql.append("	a.id ");
		sql.append("LIMIT ?,1; ");
		
		Long id = Db.queryLong(sql.toString(), classinfoId, classinfoId, start);
		
		StringBuffer testSql= new StringBuffer();
		testSql.append("SELECT ");
		testSql.append("	CAST( ");
		testSql.append("		AES_DECRYPT( ");
		testSql.append("			UNHEX( ");
		testSql.append("				AES_DECRYPT( ");
		testSql.append("					UNHEX(resident_address), ");
		testSql.append("					'HelloHrss' ");
		testSql.append("				) ");
		testSql.append("			), ");
		testSql.append("			'HelloHrss' ");
		testSql.append("		) AS CHAR ");
		testSql.append("	) AS resident_address ");
		testSql.append("FROM ");
		testSql.append("	archive ");
		testSql.append("WHERE id=? ");
		String resident_address=Db.queryStr(testSql.toString(),id);
		
		StringBuffer querysql = new StringBuffer();
		
		querysql.append("SELECT ");
		querysql.append("	a.id AS id, ");
		querysql.append("	a.`name` AS `name`, ");
		querysql.append("	a.sex, ");
		querysql.append("	a.birthday, ");
		querysql.append("	a.pid, ");
		querysql.append("	a.email, ");
		querysql.append("	CAST( ");
		querysql.append("		AES_DECRYPT(UNHEX(a.tel), 'HelloHrss') AS CHAR ");
		querysql.append("	) AS tel, ");
		if(resident_address==null){//说明解密一次就可以了？
			querysql.append("	CAST( ");
			querysql.append("		AES_DECRYPT( ");
			querysql.append("			UNHEX(resident_address), ");
			querysql.append("			'HelloHrss' ");
			querysql.append("		) AS CHAR ");
			querysql.append("	) AS resident_address, ");
		}
		else{
			querysql.append("	CAST( ");
			querysql.append("		AES_DECRYPT( ");
			querysql.append("			UNHEX( ");
			querysql.append("				AES_DECRYPT( ");
			querysql.append("					UNHEX(resident_address), ");
			querysql.append("					'HelloHrss' ");
			querysql.append("				) ");
			querysql.append("			), ");
			querysql.append("			'HelloHrss' ");
			querysql.append("		) AS CHAR ");
			querysql.append("	) AS resident_address, ");
		}
		querysql.append("	CAST( ");
		querysql.append("	AES_DECRYPT( ");
		querysql.append("			UNHEX(a.home_address), ");
		querysql.append("			'HelloHrss' ");
		querysql.append("		) AS CHAR ");
		querysql.append("	) AS home_address, ");
		querysql.append("	CAST( ");
		querysql.append("		AES_DECRYPT( ");
		querysql.append("			UNHEX(a.permanent_address), ");
		querysql.append("			'HelloHrss' ");
		querysql.append("		) AS CHAR ");
		querysql.append("	) AS permanent_address, ");
		querysql.append("	a.graduate_school, ");
		querysql.append("	p.`name` AS profession ");
		querysql.append("FROM ");
		querysql.append("	archive a ");
		querysql.append("JOIN profession p ON a.profession_id = p.id ");
		querysql.append("WHERE ");
		querysql.append("	a.id = ? ");

		Record record = Db.findFirst(querysql.toString(),id);
		
		record.set("id", record.getLong("id").toString());
		
		
		return record;
	}

	@Override
	public Record searchStuById(Long stuId) {
		StringBuffer testSql= new StringBuffer();
		testSql.append("SELECT ");
		testSql.append("	CAST( ");
		testSql.append("		AES_DECRYPT( ");
		testSql.append("			UNHEX( ");
		testSql.append("				AES_DECRYPT( ");
		testSql.append("					UNHEX(resident_address), ");
		testSql.append("					'HelloHrss' ");
		testSql.append("				) ");
		testSql.append("			), ");
		testSql.append("			'HelloHrss' ");
		testSql.append("		) AS CHAR ");
		testSql.append("	) AS resident_address ");
		testSql.append("FROM ");
		testSql.append("	archive ");
		testSql.append("WHERE id=? ");
		String resident_address=Db.queryStr(testSql.toString(),stuId);
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("	a.id, ");
		sql.append("	a.`name` AS name, ");
		sql.append("	a.sex, ");
		sql.append("	a.birthday, ");
		sql.append("	a.pid, ");
		sql.append("	a.email, ");
		sql.append("	CAST( ");
		sql.append("		AES_DECRYPT(UNHEX(a.tel), 'HelloHrss') AS CHAR ");
		sql.append("	) AS tel, ");
		if(resident_address==null){//说明解密一次就可以了？
			sql.append("	CAST( ");
			sql.append("		AES_DECRYPT( ");
			sql.append("			UNHEX(resident_address), ");
			sql.append("			'HelloHrss' ");
			sql.append("		) AS CHAR ");
			sql.append("	) AS resident_address, ");
		}
		else{
			sql.append("	CAST( ");
			sql.append("		AES_DECRYPT( ");
			sql.append("			UNHEX( ");
			sql.append("				AES_DECRYPT( ");
			sql.append("					UNHEX(resident_address), ");
			sql.append("					'HelloHrss' ");
			sql.append("				) ");
			sql.append("			), ");
			sql.append("			'HelloHrss' ");
			sql.append("		) AS CHAR ");
			sql.append("	) AS resident_address, ");
		}
		sql.append("	CAST( ");
		sql.append("	AES_DECRYPT( ");
		sql.append("			UNHEX(a.home_address), ");
		sql.append("			'HelloHrss' ");
		sql.append("		) AS CHAR ");
		sql.append("	) AS home_address, ");
		sql.append("	CAST( ");
		sql.append("		AES_DECRYPT( ");
		sql.append("			UNHEX(a.permanent_address), ");
		sql.append("			'HelloHrss' ");
		sql.append("		) AS CHAR ");
		sql.append("	) AS permanent_address, ");
		sql.append("	a.graduate_school, ");
		sql.append("	p.`name` AS profession ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("JOIN profession p ON a.profession_id = p.id ");
		sql.append("WHERE ");
		sql.append("	a.id = ? ");
		
		Record record = Db.findFirst(sql.toString(), stuId);
		
		return record;
	}
	
	public List<Record> queryVisitList(HashMap map) {
		ArrayList param = new ArrayList();
		
		Long classinfoId = (Long) map.get("classinfoId");
		String stuName = (String) map.get("stuName");
		String userName = (String) map.get("userName");
		Date startTime = (Date) map.get("startTime");
		Date endTime = (Date) map.get("endTime");
		String satisfy = (String) map.get("satisfy");
		Integer result = (Integer) map.get("result");
		Long start  = (Long) map.get("start");
		Long length  = (Long) map.get("length");
		
		param.add(classinfoId);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("	a.id AS id, ");
		sql.append("	a.`name` AS `name`, ");
		sql.append("	IFNULL(v.datetime,'') AS datetime, ");
		sql.append("	IFNULL(tv.time,'') AS time, ");
		sql.append("	u.`name` AS `user`, ");
		sql.append("	IFNULL(tv.satisfy_degree,'') AS satisfy_degree, ");
		sql.append("	IFNULL(tv.`change`,'') AS `change` ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("JOIN visit v ON a.id = v.stu_id ");
		sql.append("LEFT JOIN tel_visit tv ON a.id = tv.id ");
		sql.append("JOIN `user` u ON u.id = v.user_id ");
		sql.append("WHERE ");
		sql.append("	a.classinfo_id = ? ");
		if(stuName != null && stuName.length() > 0){
			sql.append(" AND a.`name` = ? ");
			param.add(stuName);
		}
		if(userName != null && userName.length() > 0){
			sql.append(" AND u.`name` = ? ");
			param.add(userName);
		}
		if(startTime != null){
			sql.append("AND v.datetime >= ? ");
			param.add(startTime);
		}
		if(endTime != null){
			sql.append("AND v.datetime <= ? ");
			param.add(endTime);
		}
		if(satisfy != null && satisfy.length() > 0){
			sql.append(" AND tv.satisfy_degree = ? ");
			param.add(satisfy);
		}
		if(result != null){
			sql.append(" AND v.state = ? ");
			param.add(result);
		}
		sql.append("ORDER BY ");
		sql.append("	a.id ");
		if(start != null && length != null){
			sql.append("LIMIT ?,? ");
			param.add(start);
			param.add(length);
		}
		
		
		List<Record> list = Db.find(sql.toString(), param.toArray());
		
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		return list;
	}
	
	public Record queryVisitListCount(HashMap map) {
		ArrayList param = new ArrayList();
		
		Long classinfoId = (Long) map.get("classinfoId");
		String stuName = (String) map.get("stuName");
		String userName = (String) map.get("userName");
		Date startTime = (Date) map.get("startTime");
		Date endTime = (Date) map.get("endTime");
		String satisfy = (String) map.get("satisfy");
		Integer result = (Integer) map.get("result");
		
		param.add(classinfoId);
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("	count(*) AS count ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("JOIN visit v ON a.id = v.stu_id ");
		sql.append("LEFT JOIN tel_visit tv ON a.id = tv.id ");
		sql.append("JOIN `user` u ON u.id = v.user_id ");
		sql.append("WHERE ");
		sql.append("	a.classinfo_id = ? ");
		if(stuName != null && stuName.length() > 0){
			sql.append(" AND a.`name` = ?");
			param.add(stuName);
		}
		if(userName != null && userName.length() > 0){
			sql.append(" AND u.`name` = ?");
			param.add(userName);
		}
		if(startTime != null){
			sql.append("AND v.datetime >= ? ");
			param.add(startTime);
		}
		if(endTime != null){
			sql.append("AND v.datetime <= ? ");
			param.add(endTime);
		}
		if(satisfy != null && satisfy.length() > 0){
			sql.append(" AND tv.satisfy_degree = ?");
			param.add(satisfy);
		}
		if(result != null){
			sql.append(" AND v.state = ?");
			param.add(result);
		}
		
		Record record = Db.findFirst(sql.toString(),param.toArray());
		return record;
	}
	
	public Record queryCountAboutIdAndClassinfoId(Long stuId, Long classinfoId) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("	count(*) AS count ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		sql.append("AND classinfo_id = ? ");
		
		Record record = Db.findFirst(sql.toString(),stuId, classinfoId);
		
		return record;
	}
	
	@Override
	public Record queryStuName(Long stuId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	`name` ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		Record record = Db.findFirst(sql.toString(), stuId);
		return record;
	}

}
