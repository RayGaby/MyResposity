package cn.gov.hrss.ln.stuenroll.db.mariadb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.alibaba.dubbo.rpc.Result;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.gov.hrss.ln.stuenroll.db.I_RolePermissionDao;

public class RolePermissionDao implements I_RolePermissionDao {

	@Override
	public List<Record> searchRolePermission(long start, long length) {
		ArrayList param = new ArrayList();

		StringBuffer namesql = new StringBuffer();
		namesql.append("SELECT ");
		namesql.append("	id, ");
		namesql.append("	`name`, ");
		namesql.append("	block ");
		namesql.append("FROM ");
		namesql.append("	role  ");
		namesql.append("ORDER BY ");
		namesql.append("	id ");
		namesql.append("LIMIT ?, ? ");
		param.add(start);
		param.add(length);
		List<Record> listname = Db.find(namesql.toString(), param.toArray());
		List<Record> list = listname;

		StringBuffer UserNum = new StringBuffer();
		UserNum.append("SELECT ");
		UserNum.append("	r.id, ");
		UserNum.append(" 	COUNT(u.role_id) AS userNum ");
		UserNum.append("FROM ");
		UserNum.append("	role r ");
		UserNum.append("LEFT JOIN `user` u ON u.role_id = r.id ");
		UserNum.append("GROUP BY ");
		UserNum.append("	r.id ");
		UserNum.append("ORDER BY ");
		UserNum.append("	r.id ");
		UserNum.append("LIMIT ?, ? ");
		List<Record> listUserNum = Db.find(UserNum.toString(), param.toArray());

		StringBuffer PermissionNum = new StringBuffer();
		PermissionNum.append("SELECT ");
		PermissionNum.append("	r.id, ");
		PermissionNum.append("	COUNT(rp.permission_id) AS permissionNum ");
		PermissionNum.append("FROM ");
		PermissionNum.append("	role r ");
		PermissionNum.append("LEFT JOIN role_permission rp ON rp.role_id = r.id ");
		PermissionNum.append("GROUP BY ");
		PermissionNum.append("	r.id ");
		PermissionNum.append("ORDER BY ");
		PermissionNum.append("	r.id ");
		PermissionNum.append("LIMIT ?, ? ");
		List<Record> listPermissionNum = Db.find(PermissionNum.toString(), param.toArray());

		StringBuffer ActiveNum = new StringBuffer();
		ActiveNum.append("SELECT ");
		ActiveNum.append("	r.id, ");
		ActiveNum.append("	COUNT(u.role_id) AS activeNum ");
		ActiveNum.append("FROM ");
		ActiveNum.append("	role r ");
		ActiveNum.append("LEFT JOIN (SELECT * FROM `user` WHERE block = '0') u ON r.id = u.role_id ");
		ActiveNum.append("GROUP BY ");
		ActiveNum.append("	r.id ");
		ActiveNum.append("ORDER BY ");
		ActiveNum.append("	r.id ");
		ActiveNum.append("LIMIT ?, ? ");
		List<Record> listActiveNum = Db.find(ActiveNum.toString(), param.toArray());

		StringBuffer UnActiveNum = new StringBuffer();
		UnActiveNum.append("SELECT ");
		UnActiveNum.append("	r.id, ");
		UnActiveNum.append("	COUNT(u.role_id) AS unActiveNum ");
		UnActiveNum.append("FROM ");
		UnActiveNum.append("	role r ");
		UnActiveNum.append("LEFT JOIN (SELECT * FROM `user` WHERE block = '1') u ON r.id = u.role_id ");
		UnActiveNum.append("GROUP BY ");
		UnActiveNum.append("	r.id ");
		UnActiveNum.append("ORDER BY ");
		UnActiveNum.append("	r.id ");
		UnActiveNum.append("LIMIT ?, ? ");
		List<Record> listUnActiveNum = Db.find(UnActiveNum.toString(), param.toArray());

		StringBuffer adminUser = new StringBuffer();
		adminUser.append("SELECT ");
		adminUser.append("	r.id, ");
		adminUser.append("	COUNT(u.role_id) AS adminuser ");
		adminUser.append("FROM ");
		adminUser.append("	role r ");
		adminUser.append("LEFT JOIN (SELECT * FROM `user` WHERE organization_id= '738620600423157760') u ON r.id = u.role_id  ");
		adminUser.append("GROUP BY ");
		adminUser.append("	r.id ");
		adminUser.append("ORDER BY ");
		adminUser.append("	r.id ");
		adminUser.append("LIMIT ?, ? ");
		List<Record> listadminUser = Db.find(adminUser.toString(), param.toArray());

		StringBuffer organizationUser = new StringBuffer();
		organizationUser.append("SELECT ");
		organizationUser.append("	r.id, ");
		organizationUser.append("	COUNT(u.role_id) AS organizationuser ");
		organizationUser.append("FROM ");
		organizationUser.append("	role r ");
		organizationUser.append("LEFT JOIN (SELECT * FROM `user` WHERE organization_id <> '738620600423157760') u ON r.id = u.role_id  ");
		organizationUser.append("GROUP BY ");
		organizationUser.append("	r.id ");
		organizationUser.append("ORDER BY ");
		organizationUser.append("	r.id ");
		organizationUser.append("LIMIT ?, ? ");
		List<Record> listOrganizationUser = Db.find(organizationUser.toString(), param.toArray());

		for (Record record : list) {

			for (Record record1 : listPermissionNum) {
				if (record.get("id").equals(record1.get("id"))) {
					record.set("permissionNum", record1.get("permissionNum"));
				}
			}
			for (Record record2 : listUserNum) {
				if (record.get("id").equals(record2.get("id"))) {
					record.set("userNum", record2.get("userNum"));
				}
			}
			for (Record record3 : listActiveNum) {
				if (record.get("id").equals(record3.get("id"))) {
					record.set("activeNum", record3.get("activeNum"));
				}
			}
			for (Record record4 : listUnActiveNum) {
				if (record.get("id").equals(record4.get("id"))) {
					record.set("unActiveNum", record4.get("unActiveNum"));
				}
			}
			for (Record record5 : listadminUser) {
				if (record.get("id").equals(record5.get("id"))) {
					record.set("adminuser", record5.get("adminuser"));
				}
			}
			for (Record record6 : listOrganizationUser) {
				if (record.get("id").equals(record6.get("id"))) {
					record.set("organizationuser", record6.get("organizationuser"));
				}
			}
			record.set("id", record.getLong("id").toString());
		}
		return list;
	}

	@Override
	public int insertRolePermission(HashMap map, ArrayList<String> list) {
		String name = (String) map.get("name");
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO role(id,`name`) ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("		NEXT ");
		sql.append("		VALUE ");
		sql.append("			FOR MYCATSEQ_GLOBAL, ");
		sql.append("			? ");
		sql.append("	) ");
		int result = Db.update(sql.toString(), name);
		for (String permission_id : list) {
			if (permission_id.equals("-1")) {
				break;
			}
			StringBuffer sql1 = new StringBuffer();
			sql1.append("INSERT INTO role_permission ");
			sql1.append("VALUES ");
			sql1.append("	( ");
			sql1.append("		NEXT ");
			sql1.append("		VALUE ");
			sql1.append("			FOR MYCATSEQ_GLOBAL, ");
			sql1.append("			(SELECT id from role WHERE `name`=?), ");
			sql1.append("			? ");
			sql1.append("	); ");
			result += Db.update(sql1.toString(), name, permission_id);
		}
		return result;
	}

	@Override
	public long searchRolePermissionCount(HashMap map) {
		ArrayList param = new ArrayList();
		String name = (String) map.get("name");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM role r  ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if (name != null && name.length() > 0) {
			sql.append(" AND e.name = ? ");
			param.add(name);
		}
		long count = Db.queryLong(sql.toString(), param.toArray());
		return count;
	}

	@Override
	public int deleteRoleById(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	role ");
		sql.append("WHERE");
		sql.append("	id IN ( ");
		for (int i = 0; i < id.length; i++) {
			sql.append("?");
			if (i != id.length - 1) {
				sql.append(",");
			}
		}
		sql.append(") ");
		int i = Db.update(sql.toString(), id);
		StringBuffer sql1 = new StringBuffer();
		sql1.append("DELETE FROM role_permission WHERE role_id=?; ");
		Db.update(sql1.toString(), id);
		return i;
	}

	@Override
	public List<Record> searchSelectPermission() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,`name` FROM permission ; ");
		List<Record> allPermission = Db.find(sql.toString());
		return allPermission;
	}

	@Override
	public List<Record> searchRoleExistingPermission(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT permission_id AS id FROM role_permission WHERE role_id=?; ");
		List<Record> existingPermission = Db.find(sql.toString(), id);
		return existingPermission;
	}

	@Override
	public int updateRolePermissionName(String id, String name) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE role SET `name`=?  WHERE id=?; ");
		int result = Db.update(sql.toString(), name, id);
		return result;
	}

	@Override
	public int updateRolePermissionMore(String id, ArrayList<String> list) {
		int result = 0;
		for (String permission_id : list) {
			System.out.println(permission_id);
			if (permission_id.equals("-1")) {
				continue;
			}
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO role_permission VALUES(NEXT	VALUE	FOR MYCATSEQ_GLOBAL,?,?); ");
				result += Db.update(sql.toString(), id, permission_id);
			}
			catch (Exception e) {
				e.getStackTrace();
			}

		}
		return result;
	}

	@Override
	public int updateRolePermissionLess(String id, ArrayList<String> list) {
		int result = 0;
		for (String permission_id : list) {
			System.out.println(permission_id);
			if (permission_id.equals("-1")) {
				continue;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM role_permission WHERE role_id=? AND permission_id=?; ");
			result += Db.update(sql.toString(), id, permission_id);
		}
		return result;
	}

	@Override
	public int updateBlock(String id, int block) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE role SET block=? WHERE id=?; ");
		int result = Db.update(sql.toString(), block, id);
		return result;
	}

}