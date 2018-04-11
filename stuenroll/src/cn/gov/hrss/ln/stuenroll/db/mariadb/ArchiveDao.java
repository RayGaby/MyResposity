package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_ArchiveDao;

/**
 * Archive表Dao类
 * 
 * @author YangDi
 *
 */
public class ArchiveDao implements I_ArchiveDao {

	@Override
	public long searchCountByCondition(Integer year, Integer month, Integer stateId, Long organizationId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		sql.append("	YEAR (create_time) = ? ");
		sql.append("AND MONTH (create_time) = ? ");
		sql.append("AND state_id = ? ");
		if (organizationId != -1) {
			sql.append("AND organization_id = ? ");
			long count = Db.queryLong(sql.toString(), year, month, stateId, organizationId);
			return count;
		}
		else {
			long count = Db.queryLong(sql.toString(), year, month, stateId);
			return count;
		}

	}
	
	@Override
	public List<Record> searchArchive(HashMap map, long start, long length) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		String pid = (String) map.get("pid");
		Integer year = (Integer) map.get("year");
		String sex = (String) map.get("sex");
		String education = (String) map.get("education");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		Long classinfoId = (Long) map.get("classinfoId");
		Long stateId = (Long) map.get("stateId");

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.id, ");
		sql.append("	a.`name`, ");
		sql.append("	a.pid, ");
		sql.append("	AES_DECRYPT(UNHEX(a.tel), 'HelloHrss') AS tel, ");
		sql.append("	IFNULL(o.`name`,'') AS organization, ");
		sql.append("	IFNULL(p.`name`,'') AS profession, ");
		sql.append("	IFNULL(c.`name`,'') AS classinfo, ");
		sql.append("	a.`year`, ");
		sql.append("	ss.`name` AS state ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("LEFT JOIN organization o ON a.organization_id = o.id ");
		sql.append("LEFT JOIN profession p ON a.profession_id = p.id ");
		sql.append("LEFT JOIN classinfo c ON a.classinfo_id = c.id ");
		sql.append("LEFT JOIN student_state ss ON a.state_id = ss.id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if (name != null && name.length() > 0) {
			sql.append(" AND a.name = ? ");
			param.add(name);
		}
		if (pid != null && pid.length() > 0) {
			sql.append(" AND a.pid = ? ");
			param.add(pid);
		}
		if (year != null) {
			sql.append(" AND a.year = ? ");
			param.add(year);
		}
		if (sex != null && sex.length() > 0) {
			sql.append(" AND a.sex = ? ");
			param.add(sex);
		}
		if (education != null && education.length() > 0) {
			sql.append(" AND a.education = ? ");
			param.add(education);
		}
		if (organizationId != null) {
			sql.append(" AND a.organization_id = ? ");
			param.add(organizationId);
		}
		if (professionId != null) {
			sql.append(" AND a.profession_id = ? ");
			param.add(professionId);
		}
		if (classinfoId != null) {
			sql.append(" AND a.classinfo_id = ? ");
			param.add(classinfoId);
		}
		if (stateId == null) {
			sql.append(" AND (a.state_id = 4 or a.state_id = 5 or a.state_id=3) ");
		}
		else if(stateId == 3 || stateId == 4 || stateId == 5){
			sql.append(" AND a.state_Id = ? ");
			param.add(stateId);
		}
		sql.append("ORDER BY a.id ");
		sql.append("LIMIT ?, ? ");
		param.add(start);
		param.add(length);

		List<Record> list = Db.find(sql.toString(), param.toArray());
		for (Record record : list) {
			byte[] tel = record.getBytes("tel");
			record.set("tel", new String(tel));
			record.set("id", record.getLong("id").toString());
		}
		return list;

	}

	@Override
	public long searchArchiveCount(HashMap map) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		String pid = (String) map.get("pid");
		Integer year = (Integer) map.get("year");
		String sex = (String) map.get("sex");
		String education = (String) map.get("education");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		Long classinfoId = (Long) map.get("classinfoId");
		Long stateId = (Long) map.get("stateId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("LEFT JOIN organization o ON a.organization_id = o.id ");
		sql.append("LEFT JOIN profession p ON a.profession_id = p.id ");
		sql.append("LEFT JOIN classinfo c ON a.classinfo_id = c.id ");
		sql.append("LEFT JOIN student_state ss ON a.state_id = ss.id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if (name != null && name.length()> 0) {
			sql.append(" AND a.name = ? ");
			param.add(name);
		}
		if (pid != null && pid.length()> 0) {
			sql.append(" AND a.pid = ? ");
			param.add(pid);
		}
		if (year != null) {
			sql.append(" AND a.year = ? ");
			param.add(year);
		}
		if (sex != null && sex.length()> 0) {
			sql.append(" AND a.sex = ? ");
			param.add(sex);
		}
		if (education != null && education.length()> 0) {
			sql.append(" AND a.education = ? ");
			param.add(education);
		}
		if (organizationId != null ) {
			sql.append(" AND a.organization_Id = ? ");
			param.add(organizationId);
		}
		if (professionId != null ) {
			sql.append(" AND a.profession_Id = ? ");
			param.add(professionId);
		}
		if (classinfoId != null ) {
			sql.append(" AND a.classinfo_Id = ? ");
			param.add(classinfoId);
		}
		if (stateId == null) {
			sql.append(" AND (a.state_id = 4 or a.state_id = 5 or a.state_id=3) ");
		}
		else if(stateId == 3 || stateId == 4 || stateId == 5){
			sql.append(" AND a.state_Id = ? ");
			param.add(stateId);
		}

		long count = Db.queryLong(sql.toString(), param.toArray());
		return count;
	}
	
	@Override
	public boolean insertArchive(HashMap map) {
		String name = (String) map.get("name");
		String sex = (String) map.get("sex");
		String nation = (String) map.get("nation");
		String pid = (String) map.get("pid");
		String graduateSchool = (String) map.get("graduateSchool");
		Integer graduateYear = (Integer) map.get("graduateYear");
		String graduateDate = (String) map.get("graduateDate");
		String education = (String) map.get("education");
		String major = (String) map.get("major");
		String healthy = (String) map.get("healthy");
		String politics = (String) map.get("politics");
		String birthday = (String) map.get("birthday");
		String residentAddress = (String) map.get("residentAddress");
		String homeAddress = (String) map.get("homeAddress");
		String permanentAddress = (String) map.get("permanentAddress");
		String tel = (String) map.get("tel");
		String homeTel = (String) map.get("homeTel");
		String email = (String) map.get("email");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		String place = (String) map.get("place");
				
		Date date=new Date();
		Long sharding=date.getTime();//距离1970年1月1日0点0分0秒的毫秒数
				
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
		sql.append("	organization_id, ");
		sql.append("	profession_id, ");
		sql.append("	place, ");
		sql.append("	`year`, ");
		sql.append("	create_time, ");
		sql.append("	sharding, ");
		sql.append("	state_id ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("		NEXT ");
		sql.append("		VALUE ");
		sql.append("			FOR MYCATSEQ_GLOBLE, ");
		sql.append("			?, ");
		sql.append("			CAST(? AS CHAR), ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			DATE(?), ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			DATE(?), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
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
		sql.append("			YEAR (NOW()), ");
		sql.append("			TIMESTAMP (NOW()), ");
		sql.append("			?, ");
		sql.append("			3 ");
		sql.append("	); ");
				
		ArrayList param = new ArrayList();
		param.add(name);
		param.add(sex);
		param.add(nation);
		param.add(pid);
		param.add(graduateSchool);
		param.add(graduateYear);
		param.add(graduateDate);
		param.add(education);
		param.add(major);
		param.add(healthy);
		param.add(politics);
		param.add(birthday);
		param.add(residentAddress);
		param.add(permanentAddress);
		param.add(homeAddress);
		param.add(tel);
		param.add(homeTel);
		param.add(email);
		param.add(organizationId);
		param.add(professionId);
		param.add(place);
		param.add(sharding);
				
		int count = Db.update(sql.toString(), param.toArray());
		return count==1;
	}
	
	@Override
	public boolean updateArchive(HashMap map) {
		String name = (String) map.get("name");
		String sex = (String) map.get("sex");
		String nation = (String) map.get("nation");
		String pid = (String) map.get("pid");
		String graduateSchool = (String) map.get("graduateSchool");
		Integer graduateYear = (Integer) map.get("graduateYear");
		String graduateDate = (String) map.get("graduateDate");
		String education = (String) map.get("education");
		String major = (String) map.get("major");
		String healthy = (String) map.get("healthy");
		String politics = (String) map.get("politics");
		String birthday = (String) map.get("birthday");
		String residentAddress = (String) map.get("residentAddress");
		String homeAddress = (String) map.get("homeAddress");
		String permanentAddress = (String) map.get("permanentAddress");
		String tel = (String) map.get("tel");
		String homeTel = (String) map.get("homeTel");
		String email = (String) map.get("email");
		String organization = (String) map.get("organization");
		String profession = (String) map.get("profession");
		String place = (String) map.get("place");
		Long id = (Long) map.get("id");
		
		String sqlOfOrganization = "SELECT id FROM organization WHERE abbreviation = ?";
		Long organizationId = Db.queryLong(sqlOfOrganization,organization);
		String sqlOfProfession = "SELECT id FROM profession WHERE `name` = ?";
		Long professionId = Db.queryLong(sqlOfProfession,profession);
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE archive ");
		sql.append("SET `name` = ?, ");
		sql.append(" sex = ?, ");
		sql.append(" nation = ?, ");
		sql.append(" pid = ?, ");
		sql.append(" graduate_school = ?, ");
		sql.append(" graduate_year = ?, ");
		sql.append(" graduate_date = DATE(?), ");
		sql.append(" education = ?, ");
		sql.append(" major = ?, ");
		sql.append(" healthy = ?, ");
		sql.append(" politics = ?, ");
		sql.append(" birthday = DATE(?), ");
		sql.append(" resident_address = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" permanent_address = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" home_address = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" tel = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" home_tel = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" email = ?, ");
		sql.append(" organization_id = ?, ");
		sql.append(" profession_id = ?, ");
		sql.append(" place = ? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		ArrayList param = new ArrayList();
		param.add(name);
		param.add(sex);
		param.add(nation);
		param.add(pid);
		param.add(graduateSchool);
		param.add(graduateYear);
		param.add(graduateDate);
		param.add(education);
		param.add(major);
		param.add(healthy);
		param.add(politics);
		param.add(birthday);
		param.add(residentAddress);
		param.add(permanentAddress);
		param.add(homeAddress);
		param.add(tel);
		param.add(homeTel);
		param.add(email);
		param.add(organizationId);
		param.add(professionId);
		param.add(place);
		param.add(id);
		
		System.out.println(sql.toString());
		System.out.println(param.toString());
		int count = Db.update(sql.toString(), param.toArray());
		return count==1;
	}
	
	@Override
	public List<Record> searchArchiveById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.id, ");
		sql.append("	a.`name`, ");
		sql.append("	a.sex, ");
		sql.append("	a.nation, ");
		sql.append("	a.pid, ");
		sql.append("	a.graduate_school, ");
		sql.append("	a.graduate_year, ");
		sql.append("	a.graduate_date, ");
		sql.append("	a.major, ");
		sql.append("	a.healthy, ");
		sql.append("	a.politics, ");
		sql.append("	a.birthday, ");
		sql.append("	AES_DECRYPT(UNHEX(a.resident_address),'HelloHrss') AS resident_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.permanent_address),'HelloHrss') AS permanent_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.home_address),'HelloHrss') AS home_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.tel),'HelloHrss') AS tel, ");
		sql.append("	AES_DECRYPT(UNHEX(a.home_tel),'HelloHrss') AS home_tel, ");
		sql.append("	a.email, ");
		sql.append("	o.abbreviation AS organization, ");
		sql.append("	p.`name` AS profession, ");
		sql.append("	a.place, ");
		sql.append("	a.education ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("LEFT JOIN profession p ON a.profession_id = p.id ");
		sql.append("LEFT JOIN organization o ON a.organization_id = o.id ");
		sql.append("WHERE  ");
		sql.append("	a.id = ? ");
		List<Record> list = Db.find(sql.toString(),id);
		for (Record record : list) {
			byte[] tel = record.getBytes("tel");
			record.set("tel", new String(tel));
			record.set("id", record.getLong("id").toString());
			byte[] resident_address = record.getBytes("resident_address");
			record.set("resident_address", new String(resident_address));
			byte[] permanent_address = record.getBytes("permanent_address");
			record.set("permanent_address", new String(permanent_address));
			byte[] home_address = record.getBytes("home_address");
			record.set("home_address", new String(home_address));
			byte[] home_tel = record.getBytes("home_tel");
			record.set("home_tel", new String(home_tel));
		}
		return list;
	}


	@Override
	public int deleteById(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		sql.append("	id IN ( ");
		for (int i = 0; i < id.length; i++) {
			sql.append("?");
			if (i != id.length - 1) {
				sql.append(",");
			}
		}
		sql.append(") ");
		int i = Db.update(sql.toString(),id);
		return i;
	}

	@Override
	public List<Record> searchQuit(HashMap map, long start, long length) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		String pid = (String) map.get("pid");
		Integer year = (Integer) map.get("year");
		String sex = (String) map.get("sex");
		String education = (String) map.get("education");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		Long classinfoId = (Long) map.get("classinfoId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.id, ");
		sql.append("	a.`name`, ");
		sql.append("	a.pid, ");
		sql.append("	AES_DECRYPT(UNHEX(a.tel), 'HelloHrss') AS tel, ");
		sql.append("	IFNULL(o.`name`,'') AS organization, ");
		sql.append("	IFNULL(p.`name`,'') AS profession, ");
		sql.append("	IFNULL(c.`name`,'') AS classinfo, ");
		sql.append("	a.`year`, ");
		sql.append("	ss.`name` AS state ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("LEFT JOIN organization o ON a.organization_id = o.id ");
		sql.append("LEFT JOIN profession p ON a.profession_id = p.id ");
		sql.append("LEFT JOIN classinfo c ON a.classinfo_id = c.id ");
		sql.append("LEFT JOIN student_state ss ON a.state_id = ss.id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		sql.append(" AND a.state_id = 4 ");
		if (name != null && name.length() > 0) {
			sql.append(" AND a.name = ? ");
			param.add(name);
		}
		if (pid != null && pid.length() > 0) {
			sql.append(" AND a.pid = ? ");
			param.add(pid);
		}
		if (year != null) {
			sql.append(" AND a.year = ? ");
			param.add(year);
		}
		if (sex != null && sex.length() > 0) {
			sql.append(" AND a.sex = ? ");
			param.add(sex);
		}
		if (education != null && education.length() > 0) {
			sql.append(" AND a.education = ? ");
			param.add(education);
		}
		if (organizationId != null) {
			sql.append(" AND a.organization_id = ? ");
			param.add(organizationId);
		}
		if (professionId != null) {
			sql.append(" AND a.profession_id = ? ");
			param.add(professionId);
		}
		if (classinfoId != null) {
			sql.append(" AND a.classinfo_id = ? ");
			param.add(classinfoId);
		}
		
		sql.append("ORDER BY a.id ");
		sql.append("LIMIT ?, ? ");
		param.add(start);
		param.add(length);
		
		List<Record> list = Db.find(sql.toString(), param.toArray());
		for(Record record : list) {
			byte[] tel = record.getBytes("tel");
			record.set("tel", new String(tel));
			record.set("id", record.getLong("id").toString());
		}
		return list;
	}

	@Override
	public long searchQuitCount(HashMap map) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		String pid = (String) map.get("pid");
		Integer year = (Integer) map.get("year");
		String sex = (String) map.get("sex");
		String education = (String) map.get("education");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		Long classinfoId = (Long) map.get("classinfoId");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("LEFT JOIN organization o ON a.organization_id = o.id ");
		sql.append("LEFT JOIN profession p ON a.profession_id = p.id ");
		sql.append("LEFT JOIN classinfo c ON a.classinfo_id = c.id ");
		sql.append("LEFT JOIN student_state ss ON a.state_id = ss.id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		sql.append(" AND a.state_id = 4 ");
		if (name != null && name.length() > 0) {
			sql.append(" AND a.name = ? ");
			param.add(name);
		}
		if (pid != null && pid.length() > 0) {
			sql.append(" AND a.pid = ? ");
			param.add(pid);
		}
		if (year != null) {
			sql.append(" AND a.year = ? ");
			param.add(year);
		}
		if (sex != null && sex.length() > 0) {
			sql.append(" AND a.sex = ? ");
			param.add(sex);
		}
		if (education != null && education.length() > 0) {
			sql.append(" AND a.education = ? ");
			param.add(education);
		}
		if (organizationId != null) {
			sql.append(" AND a.organization_id = ? ");
			param.add(organizationId);
		}
		if (professionId != null) {
			sql.append(" AND a.profession_id = ? ");
			param.add(professionId);
		}
		if (classinfoId != null) {
			sql.append(" AND a.classinfo_id = ? ");
			param.add(classinfoId);
		}
		System.out.println(sql.toString());
		System.out.println(param.toString());
		long count = Db.queryLong(sql.toString(),param.toArray());
		return count;
	}
	
	@Override
	public boolean insertQuit(HashMap map) {
		String name = (String) map.get("name");
		String sex = (String) map.get("sex");
		String nation = (String) map.get("nation");
		String pid = (String) map.get("pid");
		String graduateSchool = (String) map.get("graduateSchool");
		Integer graduateYear = (Integer) map.get("graduateYear");
		String graduateDate = (String) map.get("graduateDate");
		String education = (String) map.get("education");
		String major = (String) map.get("major");
		String healthy = (String) map.get("healthy");
		String politics = (String) map.get("politics");
		String birthday = (String) map.get("birthday");
		String residentAddress = (String) map.get("residentAddress");
		String homeAddress = (String) map.get("homeAddress");
		String permanentAddress = (String) map.get("permanentAddress");
		String tel = (String) map.get("tel");
		String homeTel = (String) map.get("homeTel");
		String email = (String) map.get("email");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		String place = (String) map.get("place");
				
		Date date=new Date();
		Long sharding=date.getTime();//距离1970年1月1日0点0分0秒的毫秒数
				
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO archive ( ");
		sql.append("	state_id, ");
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
		sql.append("	organization_id, ");
		sql.append("	profession_id, ");
		sql.append("	place, ");
		sql.append("	`year`, ");
		sql.append("	create_time, ");
		sql.append("	sharding ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("	    4, ");
		sql.append("		NEXT ");
		sql.append("		VALUE ");
		sql.append("			FOR MYCATSEQ_GLOBLE, ");
		sql.append("			?, ");
		sql.append("			CAST(? AS CHAR), ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			DATE(?), ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			DATE(?), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
		sql.append("			), ");
		sql.append("			HEX( ");
		sql.append("				AES_ENCRYPT(?, 'HelloHrss') ");
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
		sql.append("			YEAR (NOW()), ");
		sql.append("			TIMESTAMP (NOW()), ");
		sql.append("			? ");
		sql.append("	); ");
				
		ArrayList param = new ArrayList();
		param.add(name);
		param.add(sex);
		param.add(nation);
		param.add(pid);
		param.add(graduateSchool);
		param.add(graduateYear);
		param.add(graduateDate);
		param.add(education);
		param.add(major);
		param.add(healthy);
		param.add(politics);
		param.add(birthday);
		param.add(residentAddress);
		param.add(permanentAddress);
		param.add(homeAddress);
		param.add(tel);
		param.add(homeTel);
		param.add(email);
		param.add(organizationId);
		param.add(professionId);
		param.add(place);
		param.add(sharding);
				
		System.out.println(sql.toString());
		System.out.println(param.toString());
		int count = Db.update(sql.toString(), param.toArray());
		return count==1;
	}
	
	@Override
	public List<Record> searchQuitById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.id, ");
		sql.append("	a.`name`, ");
		sql.append("	a.sex, ");
		sql.append("	a.nation, ");
		sql.append("	a.pid, ");
		sql.append("	a.graduate_school, ");
		sql.append("	a.graduate_year, ");
		sql.append("	a.graduate_date, ");
		sql.append("	a.major, ");
		sql.append("	a.healthy, ");
		sql.append("	a.politics, ");
		sql.append("	a.birthday, ");
		sql.append("	AES_DECRYPT(UNHEX(a.resident_address),'HelloHrss') AS resident_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.permanent_address),'HelloHrss') AS permanent_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.home_address),'HelloHrss') AS home_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.tel),'HelloHrss') AS tel, ");
		sql.append("	AES_DECRYPT(UNHEX(a.home_tel),'HelloHrss') AS home_tel, ");
		sql.append("	a.email, ");
		sql.append("	o.abbreviation AS organization, ");
		sql.append("	p.`name` AS profession, ");
		sql.append("	a.place, ");
		sql.append("	a.education ");
		sql.append("FROM ");
		sql.append("	archive a ");
		sql.append("LEFT JOIN profession p ON a.profession_id = p.id ");
		sql.append("LEFT JOIN organization o ON a.organization_id = o.id ");
		sql.append("WHERE  ");
		sql.append("	a.id = ? ");
		List<Record> list = Db.find(sql.toString(),id);
		for (Record record : list) {
			byte[] tel = record.getBytes("tel");
			record.set("tel", new String(tel));
			record.set("id", record.getLong("id").toString());
			byte[] resident_address = record.getBytes("resident_address");
			record.set("resident_address", new String(resident_address));
			byte[] permanent_address = record.getBytes("permanent_address");
			record.set("permanent_address", new String(permanent_address));
			byte[] home_address = record.getBytes("home_address");
			record.set("home_address", new String(home_address));
			byte[] home_tel = record.getBytes("home_tel");
			record.set("home_tel", new String(home_tel));
		}
		return list;
	}
	
	@Override
	public boolean updateQuit(HashMap map) {
		String name = (String) map.get("name");
		String sex = (String) map.get("sex");
		String nation = (String) map.get("nation");
		String pid = (String) map.get("pid");
		String graduateSchool = (String) map.get("graduateSchool");
		Integer graduateYear = (Integer) map.get("graduateYear");
		String graduateDate = (String) map.get("graduateDate");
		String education = (String) map.get("education");
		String major = (String) map.get("major");
		String healthy = (String) map.get("healthy");
		String politics = (String) map.get("politics");
		String birthday = (String) map.get("birthday");
		String residentAddress = (String) map.get("residentAddress");
		String homeAddress = (String) map.get("homeAddress");
		String permanentAddress = (String) map.get("permanentAddress");
		String tel = (String) map.get("tel");
		String homeTel = (String) map.get("homeTel");
		String email = (String) map.get("email");
		String organization = (String) map.get("organization");
		String profession = (String) map.get("profession");
		String place = (String) map.get("place");
		Long id = (Long) map.get("id");
		
		String sqlOfOrganization = "SELECT id FROM organization WHERE abbreviation = ?";
		Long organizationId = Db.queryLong(sqlOfOrganization,organization);
		String sqlOfProfession = "SELECT id FROM profession WHERE `name` = ?";
		Long professionId = Db.queryLong(sqlOfProfession,profession);
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE archive ");
		sql.append("SET `name` = ?, ");
		sql.append(" sex = ?, ");
		sql.append(" nation = ?, ");
		sql.append(" pid = ?, ");
		sql.append(" graduate_school = ?, ");
		sql.append(" graduate_year = ?, ");
		sql.append(" graduate_date = DATE(?), ");
		sql.append(" education = ?, ");
		sql.append(" major = ?, ");
		sql.append(" healthy = ?, ");
		sql.append(" politics = ?, ");
		sql.append(" birthday = DATE(?), ");
		sql.append(" resident_address = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" permanent_address = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" home_address = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" tel = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" home_tel = HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
		sql.append(" email = ?, ");
		sql.append(" organization_id = ?, ");
		sql.append(" profession_id = ?, ");
		sql.append(" place = ? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		ArrayList param = new ArrayList();
		param.add(name);
		param.add(sex);
		param.add(nation);
		param.add(pid);
		param.add(graduateSchool);
		param.add(graduateYear);
		param.add(graduateDate);
		param.add(education);
		param.add(major);
		param.add(healthy);
		param.add(politics);
		param.add(birthday);
		param.add(residentAddress);
		param.add(permanentAddress);
		param.add(homeAddress);
		param.add(tel);
		param.add(homeTel);
		param.add(email);
		param.add(organizationId);
		param.add(professionId);
		param.add(place);
		param.add(id);
		
		System.out.println(sql.toString());
		System.out.println(param.toString());
		int count = Db.update(sql.toString(), param.toArray());
		return count==1;
	}

	@Override
	public int deleteQuitById(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	archive ");
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
	public List<Record> searchOrganization(Long organizationId) {
		StringBuffer sql = new StringBuffer();
		List<Record> list = new ArrayList<Record>();
		sql.append("SELECT ");
		sql.append("	o.id, ");
		sql.append("	o.`abbreviation` ");
		sql.append("FROM ");
		sql.append("	organization o ");
		if(organizationId!=738620600423157760l){
			sql.append("WHERE o.id = ? ");
			list = Db.find(sql.toString(),organizationId);
		}
		else{
			sql.append("ORDER BY o.id");
			list = Db.find(sql.toString());
		}
		for(int i = 0;i<list.size();i++){
			list.get(i).set("id", list.get(i).get("id").toString());
		}
		return list;
	}

	@Override
	public List<Record> searchProfession(Long organizationId) {
		StringBuffer sql = new StringBuffer();
		List<Record> list = new ArrayList<Record>();
		sql.append("SELECT ");
		sql.append("	op.profession_id, ");
		sql.append("	p.`name` ");
		sql.append("FROM ");
		sql.append("	organization_profession op ");
		sql.append("LEFT JOIN organization_join oj ON oj.id = op.organization_join_id ");
		sql.append("LEFT JOIN profession p ON p.id = op.profession_id ");
		if(organizationId!=738620600423157760l){
			sql.append("WHERE oj.organization_id = ? ");
			sql.append("GROUP BY op.profession_id ");
			list = Db.find(sql.toString(),organizationId);
		}
		else{
			sql.append("GROUP BY op.profession_id ");
			list = Db.find(sql.toString());
		}
		for(int i = 0;i<list.size();i++){
			list.get(i).set("id", list.get(i).get("profession_id").toString());
		}
		return list;
	}

	@Override
	public Record searchPersonalEnroll(Long registerId, Long pid) {
		// 获取enroll个人详细信息(mobile)从归档表
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.id,a.`name`,a.sex,a.pid,a.birthday, ");
		sql.append("	a.nation,a.politics,a.healthy, ");
		sql.append("	a.graduate_school,a.graduate_year,a.graduate_date, ");
		sql.append("	a.major,a.education,a.wechat,a.remark, ");
		sql.append("	AES_DECRYPT(UNHEX(a.resident_address),'HelloHrss') AS resident_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.permanent_address),'HelloHrss') AS permanent_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.home_address),'HelloHrss') AS home_address, ");
		sql.append("	AES_DECRYPT(UNHEX(a.tel), 'HelloHrss') AS tel ,a.email, ");
		sql.append("	AES_DECRYPT(UNHEX(a.home_tel),'HelloHrss') AS home_tel, ");
		sql.append("	p.`name` AS profession,o.`name` AS organization,a.place, ");
		sql.append("	o.abbreviation AS abbreviation,o.liaison AS liaison, o.liaison_tel AS liaison_tel ");
		sql.append("FROM archive a ");
		sql.append("JOIN profession p ON a.profession_id = p.id ");
		sql.append("JOIN organization o ON a.organization_id = o.id ");
		Record record = null;
		if (registerId != 0 && pid == 0) {
			sql.append("WHERE a.register_id=?; ");
			record = Db.findFirst(sql.toString(), registerId);
		}
		if (pid != 0 && registerId == 0) {
			sql.append("WHERE a.pid=?; ");
			record = Db.findFirst(sql.toString(), pid);
		}

		if (record != null) {
			Map<String, Object> map = record.getColumns();
			for (String key : map.keySet()) {
				if (map.get(key) == null) {
					record.set(key, "");
				}
			}
			byte[] temp = record.getBytes("resident_address");
			record.set("resident_address", new String(temp));
			temp = record.getBytes("permanent_address");
			record.set("permanent_address", new String(temp));
			temp = record.getBytes("home_address");
			record.set("home_address", new String(temp));
			temp = record.getBytes("tel");
			record.set("tel", new String(temp));
			temp = record.getBytes("home_tel");
			record.set("home_tel", new String(temp));
			map = record.getColumns();
			for (String key : map.keySet()) {
				record.set(key, map.get(key).toString());
			}
		}

		return record;
	}

	@Override
	public String searchPersonalState(Long id) {
		// 获取个人当前状态信息(mobile)
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		sql.append("	ss.`name` ");
		sql.append("FROM student_state ss ");
		sql.append("JOIN archive a ON a.state_id=ss.id  ");
		sql.append("WHERE a.register_id=?; ");

		String state = Db.queryStr(sql.toString(), id);
		return state;
	}

	@Override
	public boolean hasRecord(String pid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ");
		sql.append("FROM archive ");
		sql.append("WHERE pid=? ");
		
		long count = Db.queryLong(sql.toString(),pid);

		boolean bool = (count == 1);
		return bool;
	}

	@Override
	public Record searchRegisterRecord(String pid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	a.`name`, ");
		sql.append("	a.sex, ");
		sql.append("	a.nation, ");
		sql.append("	a.pid, ");
		sql.append("	a.graduate_school, ");
//		sql.append("	a.graduate_year, ");
		sql.append("	CAST(a.graduate_year AS CHAR) AS graduate_year, ");
		sql.append("	CAST(DATE_FORMAT(a.graduate_date,'%Y-%m-%d') AS CHAR) AS graduate_date, ");
		sql.append("	a.education, ");
		sql.append("	a.major, ");
		sql.append("	a.healthy, ");
		sql.append("	a.politics, ");
		sql.append("	CAST(DATE_FORMAT(a.birthday,'%Y-%m-%d') AS CHAR) AS birthday, ");
		sql.append("	CAST(AES_DECRYPT(UNHEX(a.resident_address),'HelloHrss') AS CHAR) AS resident_address, ");
		sql.append("	CAST(AES_DECRYPT(UNHEX(a.permanent_address),'HelloHrss') AS CHAR) AS permanent_address, ");
		sql.append("	CAST(AES_DECRYPT(UNHEX(a.home_address),'HelloHrss') AS CHAR) AS home_address, ");
		sql.append("	CAST(AES_DECRYPT(UNHEX(a.tel),'HelloHrss') AS CHAR) AS tel, ");
		sql.append("	CAST(AES_DECRYPT(UNHEX(a.home_tel),'HelloHrss') AS CHAR) AS home_tel, ");
		sql.append("	a.email, ");
//		sql.append("	CAST(AES_DECRYPT(UNHEX(a.wechat),'HelloHrss') AS CHAR) AS wechat, ");
		sql.append("	a.wechat, ");
		sql.append("	CAST(IFNULL(a.remark,'') AS CHAR) AS remark, ");
		sql.append("	o.`name` AS organization, ");
		sql.append("	o.abbreviation, ");
		sql.append("	o.liaison, ");
		sql.append("	o.liaison_tel, ");
		sql.append("	p.`name` AS profession ");
		sql.append("FROM ");
		sql.append("	archive AS a ");
		sql.append("JOIN organization AS o ON a.organization_id = o.id ");
		sql.append("JOIN profession AS p ON a.profession_id = p.id ");
		sql.append("WHERE ");
		sql.append("	a.pid = ?; ");

		Record record = Db.findFirst(sql.toString(),pid);
		return record;
	}

	@Override
	public boolean checkRegisterIdUnique(Long registerId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM archive WHERE register_id = ?;");
		long count = Db.queryLong(sql.toString(),registerId);
		boolean bool = (count == 1);
		return bool;
	}

	@Override
	public boolean checkPidUnique(String pid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM archive WHERE pid = ?;");
		long count = Db.queryLong(sql.toString(),pid);
		boolean bool = (count == 1);
		return bool;
	}
	@Override
	public long searchCountAtProfession(String profession, Long organizationId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	count(*) ");
		sql.append("FROM ");
		sql.append("	archive AS a ");
		sql.append("JOIN profession AS p ON p.id=a.profession_id ");
		sql.append("WHERE ");
		sql.append("	p.`name`=? ");

		if (organizationId != -1) {
			sql.append("AND organization_id = ? ");
			long count = Db.queryLong(sql.toString(),profession, organizationId);
			return count;
		}
		else {
			long count = Db.queryLong(sql.toString(),profession);
			return count;
		}
	}

	@Override
	public long searchCountAtOrganization(String organization) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	count(*) ");
		sql.append("FROM ");
		sql.append("	archive AS a  ");
		sql.append("JOIN organization AS o ON o.id = a.organization_id ");
		sql.append("WHERE o.abbreviation=? ");
		
		long count = Db.queryLong(sql.toString(),organization);
		return count;
	}

	@Override
	public long education(Integer year, String edu, Long organizationId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		if(organizationId==-1){
			sql.append("	education = ? AND year =?  ");
			return Db.queryLong(sql.toString(),edu,year);
		}
		else{
			sql.append("	education = ? AND year =? AND organization_id = ? ");
			return Db.queryLong(sql.toString(),edu,year,organizationId);
		}
		
	}

	@Override
	public long PlaceCount(Integer year ,String place , Long organizationId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	archive ");
		sql.append("WHERE ");
		if(organizationId==-1){
			sql.append("	place = ? AND year =?  ");
			return Db.queryLong(sql.toString(),place,year);
		}
		else{
			sql.append("	place = ? AND year =? AND organization_id = ? ");
			return Db.queryLong(sql.toString(),place,year,organizationId);
		}
	}
	@Override
	public HashMap professionCount(Integer year, Long organizationId) {
		ArrayList<Long> list1 = new ArrayList();
		ArrayList<String> list2 = new ArrayList();
		StringBuffer sql_1 = new StringBuffer();
		sql_1.append("SELECT ");
		sql_1.append("	COUNT(*) ");
		sql_1.append("FROM ");
		sql_1.append("	archive e JOIN profession p ON e.profession_id = p.id ");
		sql_1.append("WHERE ");
		sql_1.append("	p.id = ? AND e.year = ? ");
		StringBuffer sql_2 = new StringBuffer();
		sql_2.append("SELECT ");
		sql_2.append("	p.`name` ");
		sql_2.append("FROM ");
		sql_2.append("	archive e JOIN profession p ON e.profession_id = p.id ");
		sql_2.append("WHERE ");
		sql_2.append("	p.id = ? AND e.year = ? ");
		if(organizationId!=-1){
			sql_1.append(" AND e.organization_id = ?  ");
			sql_2.append(" AND e.organization_id = ?  ");
			for(int i = 1;i<=15;i++){
				list1.add(Db.queryLong(sql_1.toString(),i,year,organizationId));
				list2.add(Db.queryStr(sql_2.toString(),i,year,organizationId));
			}
		}else{
			for(int i = 1;i<=15;i++){
				list1.add(Db.queryLong(sql_1.toString(),i,year));
				list2.add(Db.queryStr(sql_2.toString(),i,year));
			}
		}
		HashMap map = new HashMap();
		map.put("pn_1",list2.get(0));
		map.put("pn_2",list2.get(1));
		map.put("pn_3",list2.get(2));
		map.put("pn_4",list2.get(3));
		map.put("pn_5",list2.get(4));
		map.put("pn_6",list2.get(5));
		map.put("pn_7",list2.get(6));
		map.put("pn_8",list2.get(7));
		map.put("pn_9",list2.get(8));
		map.put("pn_10",list2.get(9));
		map.put("pn_11",list2.get(10));
		map.put("pn_12",list2.get(11));
		map.put("pn_13",list2.get(12));
		map.put("pn_14",list2.get(13));
		map.put("pn_15",list2.get(14));
		map.put("pc_1",list1.get(0));
		map.put("pc_2",list1.get(1));
		map.put("pc_3",list1.get(2));
		map.put("pc_4",list1.get(3));
		map.put("pc_5",list1.get(4));
		map.put("pc_6",list1.get(5));
		map.put("pc_7",list1.get(6));
		map.put("pc_8",list1.get(7));
		map.put("pc_9",list1.get(8));
		map.put("pc_10",list1.get(9));
		map.put("pc_11",list1.get(10));
		map.put("pc_12",list1.get(11));
		map.put("pc_13",list1.get(12));
		map.put("pc_14",list1.get(13));
		map.put("pc_15",list1.get(14));
		return map;
	}
	
	
	@Override
	public HashMap getJob(HashMap map1 ,Integer year, Long organizationId) {
		ArrayList<Long> list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	archive a JOIN profession p ON a.profession_id = p.id  ");
		sql.append("WHERE ");
		sql.append("	a.state_id = '5' AND p.`name` = ? AND a.`year` = ?  ");
		if(organizationId==-1){
			list.add(Db.queryLong(sql.toString(),map1.get("pn_1"),year));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_2"),year));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_3"),year));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_4"),year));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_5"),year));
		}else{
			sql.append(" AND a.organization_id = ?  ");
			list.add(Db.queryLong(sql.toString(),map1.get("pn_1"),year,organizationId));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_2"),year,organizationId));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_3"),year,organizationId));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_4"),year,organizationId));
			list.add(Db.queryLong(sql.toString(),map1.get("pn_5"),year,organizationId));
		}
		//懒得写假数据
		map1.put("pe_1","90");
		map1.put("pe_2","80");
		map1.put("pe_3","60");
		map1.put("pe_4","85");
		map1.put("pe_5","77");
//		map.put("pe_1",(long)list.get(0)/(long)map.get("pc_1")*100);
//		map.put("pe_2",(long)list.get(1)/(long)map.get("pc_2")*100);
//		map.put("pe_3",(long)list.get(2)/(long)map.get("pc_3")*100);
//		map.put("pe_4",(long)list.get(3)/(long)map.get("pc_4")*100);
//		map.put("pe_5",(long)list.get(4)/(long)map.get("pc_5")*100);
		return map1;
	}
	
	@Override
	public HashMap getApplyInfo(Integer year, Integer month, Long organizationId) {
		HashMap map = new HashMap();
		String param,ps;
		ps = year+"%";
		if(month!=null&&month>=10){
			param = year+"-"+month+"%";
		}else{
			param = year+"-0"+month+"%";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	archive  ");
		sql.append("WHERE ");
		sql.append("	create_time LIKE ? ");
		StringBuffer sql_1 = new StringBuffer(sql);
		StringBuffer sql_4 = new StringBuffer(sql);
		Long actualNumber;
		Long quitNumber;
		sql.append(" ");
		sql_1.append("AND state_id = '1' ");
		sql_4.append("AND state_id = '4' ");
		if(organizationId==-1){
			actualNumber = Db.queryLong(sql.toString(),ps);
			quitNumber = Db.queryLong(sql_4.toString(),param);
		}else{
			sql.append("AND organization_id =? ");
			sql_4.append("AND organization_id =?  ");
			actualNumber = Db.queryLong(sql.toString(),ps,organizationId);
			quitNumber = Db.queryLong(sql_4.toString(),param,organizationId);
		}
		if(quitNumber==null)
			quitNumber = (long) 0;
		map.put("actualNumber", actualNumber);
		map.put("quitNumber", quitNumber);
		return map;
	}


}
