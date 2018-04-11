package cn.gov.hrss.ln.stuenroll.rolepermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import cn.gov.hrss.ln.stuenroll.db.I_RolePermissionDao;

public class RolePermissionService implements I_RolePermissionService {
	private I_RolePermissionDao i_RolePermissionDao;

	@Override
	public List<Record> searchRolePermission(long start, long length) {
		List<Record> list = i_RolePermissionDao.searchRolePermission(start, length);
		return list;
	}

	@Before(Tx.class)
	@Override
	public int insertRolePermission(HashMap map, ArrayList<String> list) {
		int result = i_RolePermissionDao.insertRolePermission(map, list);
		return result;
	}

	@Override
	public long searchRolePermissionCount(HashMap map) {
		long count = i_RolePermissionDao.searchRolePermissionCount(map);
		return count;
	}

	@Before(Tx.class)
	@Override
	public int deleteRoleById(Long[] id) {
		int i = i_RolePermissionDao.deleteRoleById(id);
		return i;
	}

	@Override
	public List<Record> searchSelectPermission() {
		List<Record> allPermission = i_RolePermissionDao.searchSelectPermission();
		return allPermission;
	}

	@Override
	public List<Record> searchRoleExistingPermission(Long[] id) {
		List<Record> existingPermission = i_RolePermissionDao.searchRoleExistingPermission(id);
		return existingPermission;
	}

	@Before(Tx.class)
	@Override
	public int updateRolePermissionName(String id, String name) {
		int result = i_RolePermissionDao.updateRolePermissionName(id, name);
		return result;
	}

	@Before(Tx.class)
	@Override
	public int updateRolePermissionMore(String id, ArrayList<String> list) {
		int result = i_RolePermissionDao.updateRolePermissionMore(id, list);
		return result;
	}

	@Before(Tx.class)
	@Override
	public int updateRolePermissionLess(String id, ArrayList<String> list) {
		int result = i_RolePermissionDao.updateRolePermissionLess(id, list);
		return result;
	}

	@Before(Tx.class)
	@Override
	public int updateBlock(String id, int block) {
		int result = i_RolePermissionDao.updateBlock(id, block);
		return result;
	}

	public I_RolePermissionDao getI_RolePermissionDao() {
		return i_RolePermissionDao;
	}

	public void setI_RolePermissionDao(I_RolePermissionDao i_RolePermissionDao) {
		this.i_RolePermissionDao = i_RolePermissionDao;
	}

}
