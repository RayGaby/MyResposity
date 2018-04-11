package cn.gov.hrss.ln.stuenroll.rolepermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;
import com.sun.xml.internal.bind.v2.model.core.ID;

@Spring("rolePermissionController")
public class RolePermissionController extends Controller implements I_RolePermissionController {
	private I_RolePermissionService i_RolePermissionService;
	private int rowsInPage;

	@RequiresPermissions({ "2_4" })
	@Override
	public void searchRolePermission() {
		Long page = getParaToLong("page");
		if (page == null) {
			page = 1L;
		}
		long start = (page - 1) * rowsInPage;
		long length = rowsInPage;

		List<Record> list = i_RolePermissionService.searchRolePermission(start, length);
		renderJson("result", list);
	}

	@RequiresPermissions({ "2_1" })
	@Override
	public void insertRolePermission() {
		String[] id = getParaValues("id");
		String name = getPara("name");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < id.length; i++) {
			list.add(id[i]);
		}
		HashMap map = new HashMap();
		map.put("name", name);
		int result = i_RolePermissionService.insertRolePermission(map, list);
		renderJson("insert", result);

	}

	@RequiresPermissions({ "2_2" })
	@Override
	public void deleteRoleById() {
		Long[] id = getParaValuesToLong("id");
		int i = i_RolePermissionService.deleteRoleById(id);
		renderJson("deleteRows", i);

	}

	@RequiresPermissions({ "2_4" })
	@Override
	public void searchRolePermissionCount() {
		String name = getPara("name");
		HashMap map = new HashMap();
		map.put("name", name);
		Long count = i_RolePermissionService.searchRolePermissionCount(map);
		renderJson("result", count);

	}

	@RequiresPermissions({ "2_1" })
	@Override
	public void searchSelectPermission() {
		List<Record> allPermission = i_RolePermissionService.searchSelectPermission();
		renderJson("allPermission", allPermission);

	}

	@RequiresPermissions({ "2_3" })
	@Override
	public void searchRoleExistingPermission() {
		Long[] id = getParaValuesToLong("id");
		List<Record> existingPermission = i_RolePermissionService.searchRoleExistingPermission(id);
		renderJson("existingPermission", existingPermission);
	}

	@RequiresPermissions({ "2_3" })
	@Override
	public void updateRolePermissionName() {
		String id = getPara("id");
		String name = getPara("name");
		int result = i_RolePermissionService.updateRolePermissionName(id, name);
		renderJson("result", result);

	}

	@RequiresPermissions({ "2_3" })
	@Override
	public void updateRolePermissionMore() {
		String id = getPara("id");
		String[] permission_id = getParaValues("permission_id");
		ArrayList<String> list = new ArrayList<String>();
		try {
			for (int i = 0; i < permission_id.length; i++) {
				list.add(permission_id[i]);
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}

		int result = i_RolePermissionService.updateRolePermissionMore(id, list);
		renderJson("result", result);

	}

	@RequiresPermissions({ "2_3" })
	@Override
	public void updateRolePermissionLess() {
		String id = getPara("id");
		String[] permission_id = getParaValues("permission_id");
		ArrayList<String> list = new ArrayList<String>();
		try {
			for (int i = 0; i < permission_id.length; i++) {
				list.add(permission_id[i]);
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}

		int result = i_RolePermissionService.updateRolePermissionLess(id, list);
		renderJson("result", result);

	}

	@RequiresPermissions({ "2_3" })
	@Override
	public void updateBlock() {
		String id = getPara("id");
		int block = getParaToInt("block");
		int result = i_RolePermissionService.updateBlock(id, block);
		renderJson("result", result);

	}

	public I_RolePermissionService getI_RolePermissionService() {
		return i_RolePermissionService;
	}

	public void setI_RolePermissionService(I_RolePermissionService i_RolePermissionService) {
		this.i_RolePermissionService = i_RolePermissionService;
	}

	public int getRowsInPage() {
		return rowsInPage;
	}

	public void setRowsInPage(int rowsInPage) {
		this.rowsInPage = rowsInPage;
	}

}
