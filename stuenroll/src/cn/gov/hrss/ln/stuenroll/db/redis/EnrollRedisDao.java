package cn.gov.hrss.ln.stuenroll.db.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

import cn.gov.hrss.ln.stuenroll.db.I_EnrollRedisDao;

/**
 * redis上Enroll表Dao实现类
 * 
 * @author cs
 *
 */
public class EnrollRedisDao implements I_EnrollRedisDao {

	@Override
	public Record searchPersonalEnroll(long registerId, long pid) {

		// redis中查找个人enroll信息
		Cache cache = Redis.use("EnrollCache");
		List rsmap = new ArrayList<>();

		rsmap = null;
		Record record = new Record();
		if (registerId != 0 && pid == 0) {
			List temp = new ArrayList<>();
			Set<String> keys = cache.keys("*");
			for (String key : keys) {
				temp = cache.hmget(key, "RegisterId");
				if (temp != null && temp.get(0).equals(registerId + "")) {
					rsmap = cache.hmget(key, "name", "sex", "nation", "pid", "graduateSchool", "graduateYear", "graduateDate", "education",
							"major", "healthy", "politics", "birthday", "residentAddress", "permanentAddress", "homeAddress", "tel",
							"homeTel", "email", "place", "professionId", "organizationId", "wechat", "remark");
					break;
				}
			}
		}
		if (pid != 0 && registerId == 0) {
			if (cache.exists(pid)) {
				rsmap = cache.hmget(pid, "name", "sex", "nation", "pid", "graduateSchool", "graduateYear", "graduateDate", "education",
						"major", "healthy", "politics", "birthday", "residentAddress", "permanentAddress", "homeAddress", "tel", "homeTel",
						"email", "place", "professionId", "organizationId", "wechat", "remark");
			}

		}

		if (rsmap != null) {
			for (int i = 0; i < rsmap.size(); i++) {
				if (rsmap.get(i) == null) {
					rsmap.set(i, "");
				}
			}
			record.set("name", rsmap.get(0));
			record.set("sex", rsmap.get(1));
			record.set("nation", rsmap.get(2));
			record.set("pid", rsmap.get(3));
			record.set("graduate_school", rsmap.get(4));
			record.set("graduate_year", rsmap.get(5));
			record.set("graduate_date", rsmap.get(6));
			record.set("education", rsmap.get(7));
			record.set("major", rsmap.get(8));
			record.set("healthy", rsmap.get(9));
			record.set("politics", rsmap.get(10));
			record.set("birthday", rsmap.get(11));
			record.set("resident_address", rsmap.get(12));
			record.set("permanent_address", rsmap.get(13));
			record.set("home_address", rsmap.get(14));
			record.set("tel", rsmap.get(15));
			record.set("home_tel", rsmap.get(16));
			record.set("email", rsmap.get(17));
			record.set("place", rsmap.get(18));

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("	`name` ");
			sql.append("FROM ");
			sql.append("	profession ");
			sql.append("WHERE ");
			sql.append("	id = ? ");
			Record professionname = Db.findFirst(sql.toString(), rsmap.get(19));
			record.set("profession", professionname.get("name"));
			sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("	`name` AS organization,abbreviation,liaison,liaison_tel ");
			sql.append("FROM ");
			sql.append("	organization ");
			sql.append("WHERE ");
			sql.append("	id = ? ");
			Record organization = Db.findFirst(sql.toString(), rsmap.get(20));
			record.setColumns(organization);
			record.set("wechat", rsmap.get(21));
			record.set("remark", rsmap.get(22));
		}
		else {
			record = null;
		}

		return record;
	}

	@Override
	public boolean checkRegisterIdUnique(long registerId) {
		// redis中查找registerId是否已报名
		boolean bool = false;
		Cache cache = Redis.use("EnrollCache");
		List temp = new ArrayList<>();
		Set<String> keys = cache.keys("*");
		for (String key : keys) {
			temp = cache.hmget(key, "RegisterId");	
			if (temp != null && temp.get(0).equals(registerId + "")) {
				bool = true;
				break;
			}
		}
		// true-已报名,false-未报名
		return bool;
	}

	@Override
	public boolean checkPidUnique(String pid) {
		// redis中查找registerId是否已报名
				boolean bool = false;
				Cache cache = Redis.use("EnrollCache");
				List temp = new ArrayList<>();
				Set<String> keys = cache.keys("*");
				for (String key : keys) {
					temp = cache.hmget(key, "pid");	
					if (temp != null && temp.get(0).equals(pid + "")) {
						bool = true;
						break;
					}
				}
				// true-已报名,false-未报名
				return bool;
	}

}
