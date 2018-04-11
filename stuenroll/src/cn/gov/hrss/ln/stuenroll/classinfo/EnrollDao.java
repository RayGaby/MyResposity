package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class EnrollDao implements I_EnrollDao{

	@Override
	public Record queryCountAboutStudent(HashMap map) {
		//对于专业管理来说，classinfo_id为空值，对于班级管理来说，profession_id为空值
		ArrayList<Long> param = new ArrayList<Long>();
		
		Long classinfoId = (Long) map.get("classinfoId");
		Long professionId = (Long) map.get("professionId");
		Long organizationId = (Long) map.get("organizationId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(DISTINCT pid) AS student_count ");
		sql.append("FROM ");
		sql.append("	enroll ");
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
	public Record queryCountAboutEmployed(HashMap map) {
		//对于专业管理来说，classinfo_id为空值，对于班级管理来说，profession_id为空值
		
		//将需要的参数加入到数组中获取
		ArrayList<Long> param = new ArrayList<Long>();
		//从调用方法的类中获取下列参数
		Long classinfoId = (Long) map.get("classinfoId");
		Long professionId = (Long) map.get("professionId");
		Long organizationId = (Long) map.get("organizationId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(DISTINCT pid) AS employed_count ");
		sql.append("FROM ");
		sql.append("	enroll ");
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
	public List<Record> searchStudentinfo(Long classinfoId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
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
		sql.append("	CAST( ");
		sql.append("				AES_DECRYPT( ");
		sql.append("			UNHEX(resident_address), ");
		sql.append("			'HelloHrss' ");
		sql.append("		) AS CHAR ");
		sql.append("	) AS resident_address, ");
		sql.append("	CAST( ");
		sql.append("		AES_DECRYPT( ");
		sql.append("			UNHEX(permanent_address), ");
		sql.append("		'HelloHrss' ");
		sql.append("	) AS CHAR ) AS permanent_address, ");
		sql.append("CAST( ");
		sql.append("		AES_DECRYPT( ");
		sql.append("			UNHEX(home_address), ");
		sql.append("			'HelloHrss' ");
		sql.append("		) AS CHAR ");
		sql.append("	) AS home_address, ");
		sql.append("CAST( ");
		sql.append("		AES_DECRYPT( ");
		sql.append("			UNHEX(tel), ");
		sql.append("			'HelloHrss' ");
		sql.append("		) AS CHAR ");
		sql.append("	) AS tel, ");
		sql.append("CAST( ");
		sql.append("		AES_DECRYPT( ");
		sql.append("			UNHEX(home_tel), ");
		sql.append("			'HelloHrss' ");
		sql.append("		) AS CHAR ");
		sql.append("	) AS home_tel, ");
		sql.append("	email, ");
		sql.append("	wechat, ");
		sql.append("	profession_id, ");
		sql.append("	classinfo_id, ");
		sql.append("	state_id, ");
		sql.append("	organization_id, ");
		sql.append("	place, ");
		sql.append("	remark, ");
		sql.append("	`year` ");
		sql.append("FROM ");
		sql.append("	enroll ");
		sql.append("WHERE ");
		sql.append("	classinfo_id = ?; ");
				
		List<Record> list = Db.find(sql.toString(), classinfoId);
		
		return list;
	}

	@Override
	public int deleteById(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	enroll ");
		sql.append("WHERE ");
		sql.append("	classinfo_id IN ( ");
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
	public List<Record> searchStudentAboutClassinfo(Long classinfoId,long start, long length) {
		
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
		sql.append("	enroll e ");
		sql.append("JOIN student_state ss ON e.state_id = ss.id ");
		sql.append("WHERE ");
		sql.append("	e.classinfo_id = ? ");
		sql.append("ORDER BY e.id ");
		sql.append("LIMIT ?, ? ");
		
		List<Record> list = Db.find(sql.toString(), classinfoId, start, length);
		
		return list;
	}

	@Override
	public Record classStuCount(Long classinfoId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(id) AS count ");
		sql.append("FROM ");
		sql.append("	enroll ");
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
	
	
	@Override
	public Record randomSearch(Long classinfoId, long start) { 
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.id AS id ");
		sql.append("FROM ");
		sql.append("	enroll a ");
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
		
		Long id = Db.queryLong(sql.toString(),classinfoId, classinfoId, start );
		
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
		testSql.append("	enroll ");
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
		querysql.append("	enroll a ");
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
		testSql.append("	enroll ");
		testSql.append("WHERE id=? ");
		String resident_address=Db.queryStr(testSql.toString(),stuId);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("	a.id, ");
		sql.append("	a.`name` AS `name`, ");
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
		sql.append("	enroll a ");
		sql.append("JOIN profession p ON a.profession_id = p.id ");
		sql.append("WHERE ");
		sql.append("	a.id = ? ");
		
		Record record = Db.findFirst(sql.toString(), stuId);
		
		return record;
	}

	@Override
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
		sql.append(" FROM ");
		sql.append("	enroll a ");
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

	@Override
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
		sql.append("	enroll a ");
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
		
		Record record = Db.findFirst(sql.toString(), param.toArray());
		return record;
	}

	@Override
	public Record queryCountAboutIdAndClassinfoId(Long stuId, Long classinfoId) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append("	count(*) AS count ");
		sql.append("FROM ");
		sql.append("	enroll ");
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
		sql.append("	enroll ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		Record record = Db.findFirst(sql.toString(), stuId);
		return record;
	}


}
