package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;

import cn.gov.hrss.ln.stuenroll.db.I_OrganizationDao;
import cn.gov.hrss.ln.stuenroll.db.I_ProfessionDao;
import cn.gov.hrss.ln.stuenroll.db.I_RegisterDao;

public class RegisterDao implements I_RegisterDao {
	private I_OrganizationDao i_OrganizationDao;
	private I_ProfessionDao i_ProfessionDao;

	@Override
	public List<Record> searchSelectableEducation() {
		StringBuffer sql = new StringBuffer();
		sql.append("SHOW COLUMNS FROM enroll LIKE 'education'; ");
		List<Record> list = Db.find(sql.toString());

		return list;
	}

	@Override
	public List<Record> searchSelectableMajor() {
		StringBuffer sql = new StringBuffer();
		sql.append("SHOW COLUMNS FROM enroll LIKE 'major'; ");
		List<Record> list = Db.find(sql.toString());

		return list;
	}

	@Override
	public List<Record> searchSelectableHealthy() {
		StringBuffer sql = new StringBuffer();
		sql.append("SHOW COLUMNS FROM enroll LIKE 'healthy'; ");
		List<Record> list = Db.find(sql.toString());

		return list;
	}

	@Override
	public List<Record> searchSelectablePolitics() {
		StringBuffer sql = new StringBuffer();
		sql.append("SHOW COLUMNS FROM enroll LIKE 'politics'; ");
		List<Record> list = Db.find(sql.toString());

		return list;
	}

	@Override
	public List<Record> searchSelectablePlace() {
		StringBuffer sql = new StringBuffer();
		sql.append("SHOW COLUMNS FROM enroll LIKE 'place'; ");
		List<Record> list = Db.find(sql.toString());

		return list;
	}

	@Override
	public List<Record> searchOrgnizationJoinInYearAtDropDown(int year) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		sql.append("	o.id, ");
		sql.append("	o.`name` ");
		sql.append("FROM ");
		sql.append("	organization_join oj ");
		sql.append("JOIN organization o ON oj.organization_id = o.id ");
		sql.append("WHERE ");
		sql.append("	oj.`year` = ? ");
		sql.append("AND oj.block = FALSE; ");

		List<Record> list = Db.find(sql.toString(), year);
		return list;
	}

	@Override
	public List<Record> searchOrgnizationJoinInYearWithProfessionAtDropDown(int year, long professionId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	o.id, ");
		sql.append("	o.`name` ");
		sql.append("FROM ");
		sql.append("	organization o ");
		sql.append("JOIN organization_join oj ON o.id = oj.organization_id ");
		sql.append("JOIN organization_profession op ON oj.id = op.organization_join_id ");
		sql.append("JOIN profession p ON op.profession_id = p.id ");
		sql.append("WHERE ");
		sql.append("	oj.`year` = ? ");
		sql.append("AND oj.block = FALSE ");
		sql.append("AND p.id = ?; ");

		List<Record> list = Db.find(sql.toString(), year, professionId);
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		return list;
	}

	@Override
	public List<Record> searchProfessionInYearAtDropDown(int year) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ");
		sql.append("	t.id, ");
		sql.append("	t.`name` ");
		sql.append("FROM ");
		sql.append("	( ");
		sql.append("		SELECT ");
		sql.append("			p.`name`, ");
		sql.append("			CAST(p.id AS CHAR) AS id ");
		sql.append("		FROM ");
		sql.append("			organization o ");
		sql.append("		JOIN organization_join oj ON o.id = oj.organization_id ");
		sql.append("		JOIN organization_profession op ON op.organization_join_id = oj.id ");
		sql.append("		JOIN profession p ON op.profession_id = p.id ");
		sql.append("		WHERE ");
		sql.append("			oj.`year` = ? ");
		sql.append("		AND oj.block = FALSE ");
		sql.append("		ORDER BY ");
		sql.append("			p.id ");
		sql.append("	) t; ");

		List<Record> list = Db.find(sql.toString(), year);
		return list;
	}
	// 废弃，改用slave代理
	// @Override
	// public boolean registerSubmit(HashMap map) {
	// ArrayList param = new ArrayList();
	// // 1.个人信息
	// String name = (String) map.get("name");
	// String sex = (String) map.get("sex");
	// String birthday = (String) map.get("birthday");
	// String pid = (String) map.get("pid");
	//
	// String nation = (String) map.get("nation");
	// String healthy = (String) map.get("healthy");
	// String politics = (String) map.get("politics");
	// // 2.教育信息
	// String graduate_school = (String) map.get("graduate_school");
	// String graduate_year = (String) map.get("graduate_year");
	// String graduate_date = (String) map.get("graduate_date");
	// String major = (String) map.get("major");
	// String education = (String) map.get("education");
	// // 3.联系方式
	// String resident_address = (String) map.get("resident_address");
	// String permanent_address = (String) map.get("permanent_address");
	// String home_address = (String) map.get("home_address");
	// String tel = (String) map.get("tel");
	// String email = (String) map.get("email");
	// String home_tel = (String) map.get("home_tel");
	// // 4.申报信息
	// String profession_id = (String) map.get("profession_id");
	// // 右侧orgnization是前台的历史遗留问题，忽略
	// String organization_id = (String) map.get("organization_id");
	// String place = (String) map.get("place");
	//
	// StringBuffer sql = new StringBuffer();
	// sql.append("INSERT INTO enroll ( ");
	// sql.append(" `id`, ");
	// sql.append(" `name`, ");
	// sql.append(" `sex`, ");
	// sql.append(" `nation`, ");
	// sql.append(" `pid`, ");
	// sql.append(" `graduate_school`, ");
	// sql.append(" `graduate_year`, ");
	// sql.append(" `graduate_date`, ");
	// sql.append(" `education`, ");
	// sql.append(" `major`, ");
	// sql.append(" `healthy`, ");
	// sql.append(" `politics`, ");
	// sql.append(" `birthday`, ");
	// sql.append(" `resident_address`, ");
	// sql.append(" `permanent_address`, ");
	// sql.append(" `home_address`, ");
	// sql.append(" `tel`, ");
	// sql.append(" `home_tel`, ");
	// sql.append(" `email`, ");
	// sql.append(" `wechat`, ");
	// sql.append(" `profession_id`, ");
	// sql.append(" `classinfo_id`, ");
	// sql.append(" `state_id`, ");
	// sql.append(" `organization_id`, ");
	// sql.append(" `place`, ");
	// sql.append(" `remark`, ");
	// sql.append(" `year`, ");
	// sql.append(" `create_time`, ");
	// sql.append(" `sharding` ");
	// sql.append(") ");
	// sql.append("VALUES ");
	// sql.append(" ( ");
	// sql.append(" NEXT VALUE FOR MYCATSEQ_GLOBAL, ");
	// sql.append("?, ");
	// param.add(name);
	// sql.append("?, ");
	// param.add(sex);
	// sql.append("?, ");
	// param.add(nation);
	// sql.append("?, ");
	// param.add(pid);
	// sql.append("?, ");
	// param.add(graduate_school);
	// sql.append("?, ");
	// param.add(graduate_year);
	// sql.append("?, ");
	// param.add(graduate_date);
	// sql.append("?, ");
	// param.add(education);
	// sql.append("?, ");
	// param.add(major);
	// sql.append("?, ");
	// param.add(healthy);
	// sql.append("?, ");
	// param.add(politics);
	// sql.append("?, ");
	// param.add(birthday);
	//
	// sql.append("HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
	// param.add(resident_address);
	// sql.append("HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
	// param.add(permanent_address);
	// sql.append("HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
	// param.add(home_address);
	// sql.append("HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
	// param.add(tel);
	// sql.append("HEX(AES_ENCRYPT(?, 'HelloHrss')), ");
	// param.add(home_tel);
	//
	// sql.append("?, ");
	// param.add(email);
	// sql.append("NULL, ");
	// sql.append("?, ");
	// param.add(profession_id);
	// sql.append("NULL, ");
	// sql.append("'1', ");
	// sql.append("?, ");
	// param.add(organization_id);
	// sql.append("?, ");
	// param.add(place);
	// sql.append("NULL, ");
	//
	// sql.append("?, ");
	// param.add(new Date().getYear() + 1900);
	// sql.append("DEFAULT, ");
	//
	// sql.append("? ");
	// param.add(new Date().getTime());
	// sql.append(" ); ");
	//
	// int signal = Db.update(sql.toString(), param.toArray());
	//
	// boolean bool = (signal == 1);
	// return bool;
	// }

	@Override
	public boolean hasRecord(String pid) {
		Cache cache = Redis.use("EnrollCache");
		// Cache cache = Redis.use("报名缓存");
		boolean bool = cache.exists(pid);
		return bool;
	}

	@Override
	public Record searchRegisterRecord(String pid) {
		Cache cache = Redis.use("EnrollCache");
		// Cache cache = Redis.use("报名缓存");
		boolean _hasRecord = this.hasRecord(pid);
		boolean _not = (!_hasRecord);
		if (_not) {
			return null;
		}
		List list = cache.hmget(pid, "name", "sex", "nation", "pid", "graduateSchool", "graduateYear", "graduateDate", "education", "major",
				"healthy", "politics", "birthday", "residentAddress", "permanentAddress", "homeAddress", "tel", "homeTel", "email",
				"wechat", "remark", "organizationId", "professionId");
		Object _get = list.get(20);
		long organizationId = Long.parseLong(((String) _get));
		Record record = this.i_OrganizationDao.search(organizationId);
		String organization = record.getStr("name");
		String abbreviation = record.getStr("abbreviation");
		String liaison = record.getStr("liaison");
		String liaison_tel = record.getStr("liaison_tel");
		Object _get_1 = list.get(21);
		long professionId = Long.parseLong(((String) _get_1));
		String profession = this.i_ProfessionDao.searchName(professionId);
		Record _record = new Record();
		record = _record;
		Object _get_2 = list.get(0);
		record.set("name", _get_2);
		Object _get_3 = list.get(1);
		record.set("sex", _get_3);
		Object _get_4 = list.get(2);
		record.set("nation", _get_4);
		Object _get_5 = list.get(3);
		record.set("pid", _get_5);
		Object _get_6 = list.get(4);
		record.set("graduate_school", _get_6);
		
//		Object _get_7 = list.get(5);
//		int _parseInt = Integer.parseInt(((String) _get_7));
//		record.set("graduate_year", Integer.valueOf(_parseInt));
		Object _get_7 = list.get(5);
		record.set("graduate_year", _get_7);
		
		Object _get_8 = list.get(6);
		record.set("graduate_date", _get_8);
		Object _get_9 = list.get(7);
		record.set("education", _get_9);
		Object _get_10 = list.get(8);
		record.set("major", _get_10);
		Object _get_11 = list.get(9);
		record.set("healthy", _get_11);
		Object _get_12 = list.get(10);
		record.set("politics", _get_12);
		Object _get_13 = list.get(11);
		record.set("birthday", _get_13);
		Object _get_14 = list.get(12);
		record.set("resident_address", _get_14);
		Object _get_15 = list.get(13);
		record.set("permanent_address", _get_15);
		Object _get_16 = list.get(14);
		record.set("home_address", _get_16);
		Object _get_17 = list.get(15);
		record.set("tel", _get_17);
		Object _get_18 = list.get(16);
		record.set("home_tel", _get_18);
		Object _get_19 = list.get(17);
		record.set("email", _get_19);
		Object _get_20 = list.get(18);
		record.set("wechat", _get_20);
		Object _elvis = null;
		Object _get_21 = list.get(19);
		if (_get_21 != null) {
			_elvis = _get_21;
		}
		else {
			_elvis = "";
		}
		record.set("remark", _elvis);
		record.set("organization", organization);
		record.set("abbreviation", abbreviation);
		record.set("liaison_tel", liaison_tel);
		record.set("organization", organization);
		record.set("profession", profession);
		return record;
	}
	@Override
	public boolean registerRedis(HashMap map) {
		
		String name = (String) map.get("name");
		String sex = (String) map.get("sex");
		String nation = (String) map.get("nation");
		String pid = (String) map.get("pid");
		String graduateSchool = (String) map.get("graduateSchool");
		String graduateYear = (String) map.get("graduateYear");
		String graduateDate = (String) map.get("graduateDate");
		String education = (String) map.get("education");
		String major = (String) map.get("major");
		String healthy = (String) map.get("healthy");
		String politics = (String) map.get("politics");
		String birthday = (String) map.get("birthday");
		String residentAddress = (String) map.get("residentAddress");
		String permanentAddress = (String) map.get("permanentAddress");
		String homeAddress = (String) map.get("homeAddress");
		String tel = (String) map.get("tel");
		String homeTel = (String) map.get("homeTel");
		String email = (String) map.get("email");
		String wechat = (String) map.get("wechat");
		
		String profession = (String) map.get("profession");
		String professionId=i_ProfessionDao.searchId(profession);

		String organization = (String) map.get("organization");
		String organizationId=i_OrganizationDao.searchId(organization);

		String place = (String) map.get("place");
		String remark = (String) map.get("remark");
		
		//RedisPlugin redisPlugin = new RedisPlugin("EnrollCache", "localhost", 6379, 20000, "abc123456", 1);
	    //redisPlugin.start();
	    Cache cache = Redis.use("EnrollCache");
	    Pair<String, String> _mappedTo = Pair.<String, String>of("name", name);
	    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("sex", sex);
	    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("nation", nation);
	    Pair<String, String> _mappedTo_3 = Pair.<String, String>of("pid", pid);
	    Pair<String, String> _mappedTo_4 = Pair.<String, String>of("graduateSchool", graduateSchool);
	    Pair<String, String> _mappedTo_5 = Pair.<String, String>of("graduateYear", graduateYear);
	    Pair<String, String> _mappedTo_6 = Pair.<String, String>of("graduateDate", graduateDate);
	    Pair<String, String> _mappedTo_7 = Pair.<String, String>of("education", education);
	    Pair<String, String> _mappedTo_8 = Pair.<String, String>of("major", major);
	    Pair<String, String> _mappedTo_9 = Pair.<String, String>of("healthy", healthy);
	    Pair<String, String> _mappedTo_10 = Pair.<String, String>of("politics", politics);
	    Pair<String, String> _mappedTo_11 = Pair.<String, String>of("birthday", birthday);
	    Pair<String, String> _mappedTo_12 = Pair.<String, String>of("residentAddress", residentAddress);
	    Pair<String, String> _mappedTo_13 = Pair.<String, String>of("permanentAddress", permanentAddress);
	    Pair<String, String> _mappedTo_14 = Pair.<String, String>of("homeAddress", homeAddress);
	    Pair<String, String> _mappedTo_15 = Pair.<String, String>of("tel", tel);
	    Pair<String, String> _mappedTo_16 = Pair.<String, String>of("homeTel", homeTel);
	    Pair<String, String> _mappedTo_17 = Pair.<String, String>of("email", email);
	    Pair<String, String> _mappedTo_18 = Pair.<String, String>of("wechat", wechat);
	    Pair<String, String> _mappedTo_19 = Pair.<String, String>of("professionId", professionId);
	    Pair<String, String> _mappedTo_20 = Pair.<String, String>of("classinfoId", null);
	    Pair<String, String> _mappedTo_21 = Pair.<String, String>of("stateId", "1");
	    Pair<String, String> _mappedTo_22 = Pair.<String, String>of("organizationId", organizationId);
	    Pair<String, String> _mappedTo_23 = Pair.<String, String>of("place", place);
	    Pair<String, String> _mappedTo_24 = Pair.<String, String>of("remark", remark);
	    
//	    Pair<String, String> _mappedTo = Pair.<String, String>of("name", "李强");
//	    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("sex", "男");
//	    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("nation", "汉族");
//	    Pair<String, String> _mappedTo_3 = Pair.<String, String>of("pid", "622426199602241519");
//	    Pair<String, String> _mappedTo_4 = Pair.<String, String>of("graduateSchool", "东北大学");
//	    Pair<String, String> _mappedTo_5 = Pair.<String, String>of("graduateYear", "2016");
//	    Pair<String, String> _mappedTo_6 = Pair.<String, String>of("graduateDate", "2016-1-1");
//	    Pair<String, String> _mappedTo_7 = Pair.<String, String>of("education", "本科");
//	    Pair<String, String> _mappedTo_8 = Pair.<String, String>of("major", "理学");
//	    Pair<String, String> _mappedTo_9 = Pair.<String, String>of("healthy", "健康");
//	    Pair<String, String> _mappedTo_10 = Pair.<String, String>of("politics", "群众");
//	    Pair<String, String> _mappedTo_11 = Pair.<String, String>of("birthday", "1982-1-1");
//	    Pair<String, String> _mappedTo_12 = Pair.<String, String>of("residentAddress", "");
//	    Pair<String, String> _mappedTo_13 = Pair.<String, String>of("permanentAddress", "");
//	    Pair<String, String> _mappedTo_14 = Pair.<String, String>of("homeAddress", "");
//	    Pair<String, String> _mappedTo_15 = Pair.<String, String>of("tel", "");
//	    Pair<String, String> _mappedTo_16 = Pair.<String, String>of("homeTel", "");
//	    Pair<String, String> _mappedTo_17 = Pair.<String, String>of("email", "");
//	    Pair<String, String> _mappedTo_18 = Pair.<String, String>of("wechat", "12345678");
//	    Pair<String, String> _mappedTo_19 = Pair.<String, String>of("professionId", "748874212743188480");
//	    Pair<String, String> _mappedTo_20 = Pair.<String, String>of("classinfoId", null);
//	    Pair<String, String> _mappedTo_21 = Pair.<String, String>of("stateId", "1");
//	    Pair<String, String> _mappedTo_22 = Pair.<String, String>of("organizationId", "738620746913419264");
//	    Pair<String, String> _mappedTo_23 = Pair.<String, String>of("place", "沈阳");
//	    Pair<String, String> _mappedTo_24 = Pair.<String, String>of("remark", null);
	    Map<Object, Object> map2 = Collections.<Object, Object>unmodifiableMap(CollectionLiterals.<Object, Object>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5, _mappedTo_6, _mappedTo_7, _mappedTo_8, _mappedTo_9, _mappedTo_10, _mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14, _mappedTo_15, _mappedTo_16, _mappedTo_17, _mappedTo_18, _mappedTo_19, _mappedTo_20, _mappedTo_21, _mappedTo_22, _mappedTo_23, _mappedTo_24));
	    cache.hmset(pid, map2);
		
		boolean bool = cache.exists(pid);
		return bool;

	}

	public I_OrganizationDao getI_OrganizationDao() {
		return i_OrganizationDao;
	}

	public void setI_OrganizationDao(I_OrganizationDao i_OrganizationDao) {
		this.i_OrganizationDao = i_OrganizationDao;
	}

	public I_ProfessionDao getI_ProfessionDao() {
		return i_ProfessionDao;
	}

	public void setI_ProfessionDao(I_ProfessionDao i_ProfessionDao) {
		this.i_ProfessionDao = i_ProfessionDao;
	}


}
