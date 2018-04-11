package cn.gov.hrss.ln.stuenroll.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public interface I_RolePermissionDao {
	/**
	 * 查询角色权限信息
	 * 
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Record> searchRolePermission(long start, long length);

	/**
	 * 查询记录总数
	 * 
	 * @param map
	 * @return
	 */
	public long searchRolePermissionCount(HashMap map);

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 */
	public int deleteRoleById(Long[] id);

	/**
	 * 插入角色权限信息
	 * 
	 * @param map
	 * @return
	 */
	public int insertRolePermission(HashMap map, ArrayList<String> list);

	/**
	 * 查询所有权限
	 * 
	 * @return
	 */
	public List<Record> searchSelectPermission();

	/**
	 * 查询角色已有权限
	 * 
	 * @return
	 */
	public List<Record> searchRoleExistingPermission(Long[] id);

	/**
	 * 修改角色权限名称
	 * 
	 * @param name
	 * @param id
	 * @return
	 */
	public int updateRolePermissionName(String id, String name);

	/**
	 * 插入修改后原先没有的角色权限
	 * 
	 * @param id
	 * @param list
	 * @return
	 */
	public int updateRolePermissionMore(String id, ArrayList<String> list);

	/**
	 * 删除修改后原先有的角色权限
	 * 
	 * @param id
	 * @param list
	 * @return
	 */
	public int updateRolePermissionLess(String id, ArrayList<String> list);

	/**
	 * 修改角色状态
	 * 
	 * @param id
	 * @param block
	 * @return
	 */
	public int updateBlock(String id, int block);
}
