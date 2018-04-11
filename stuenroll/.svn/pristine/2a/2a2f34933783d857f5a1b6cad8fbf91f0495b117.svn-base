package cn.gov.hrss.ln.stuenroll.organization;

import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;

@Spring("organizationController")
public class OrganizationController extends Controller implements I_OrganizationController{
	private I_OrganizationService i_OrganizationService;
	private int rowsInPage;
	
	@RequiresPermissions({ "7_4" })
	@Override
	public void showOrganization() {
		List<Record> list = i_OrganizationService.showOrganization();
		renderJson("result",list);
	}
	
	@RequiresPermissions({ "7_4" })
	@Override
	public void showOrganizationCount() {
		long count = i_OrganizationService.showOrganizationCount();
		renderJson("result",count);
	}
	
	@RequiresPermissions({ "7_2" })
	@Override
	public void deleteById() {
		Long[] id = getParaValuesToLong("id");
		int i = i_OrganizationService.deleteById(id);
		renderJson("deleteRows", i);
	}
	
	@Override
	public void searchInOJById() {
		Long id = getParaToLong("id");
		long count = i_OrganizationService.searchInOJById(id);
		renderJson("result",count);
	}
	
	@RequiresPermissions({ "7_1" })
	@Override
	public void addOrganization() {
		String name = getPara("name");
		String abbreviation = getPara("abbreviation");
		String address = getPara("address");
		String liaison = getPara("liaison");
		String tel = getPara("tel");
		
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("abbreviation", abbreviation);
		map.put("address", address);
		map.put("liaison", liaison);
		map.put("tel", tel);
		
		boolean bool = i_OrganizationService.addOrganization(map);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}
	}
	
	@RequiresPermissions({ "7_3" })
	@Override
	public void updateOrganizationById() {
		String name = getPara("name");
		String abbreviation = getPara("abbreviation");
		String address = getPara("address");
		String liaison = getPara("liaison");
		String tel = getPara("tel");
		Long id = getParaToLong("id");
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("abbreviation", abbreviation);
		map.put("address", address);
		map.put("liaison", liaison);
		map.put("tel", tel);
		map.put("id", id);
		boolean bool = i_OrganizationService.updateOrganizationById(map);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}
	}
	
	@RequiresPermissions({ "7_23" })
	@Override
	public void addJoin() {
		Long[] id = getParaValuesToLong("id");
		System.out.println(id);
		Integer year = getParaToInt("year");
		Integer block = getParaToInt("block");
		int count = i_OrganizationService.addJoin(id, year, block);
		renderJson("addRows",count);
	}
	
	@RequiresPermissions({ "7_4" })
	@Override
	public void searchJoin() {
		String name = getPara("name");
		String abbreviation = getPara("abbreviation");
		Integer year = getParaToInt("year");
		
		String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			name = getSessionAttr("organization");
		}
		
		HashMap map = new HashMap();
		map.put("name",name);
		map.put("abbreviation",abbreviation);
		map.put("year",year);
		
		Long page = getParaToLong("page");
		if (page == null) {
			page = 1L;
		}
		long start = (page - 1) * rowsInPage;
		long length = rowsInPage;	
		
		List<Record> list = i_OrganizationService.searchJoin(map, start, length);
		renderJson("result",list);
	}
	
	@RequiresPermissions({ "7_4" })
	@Override
	public void searchJoinCount() {
		String name = getPara("name");
		String abbreviation = getPara("abbreviation");
		Integer year = getParaToInt("year");
		
		String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			name = getSessionAttr("organization");
		}
		
		HashMap map = new HashMap();
		map.put("name",name);
		map.put("abbreviation",abbreviation);
		map.put("year",year);
		
		long count = i_OrganizationService.searchJoinCount(map);
		renderJson("result",count);
		
	}
	
	@RequiresPermissions({ "7_3" })
	@Override
	public void updateJoinById() {
		Integer year = getParaToInt("year");
		Long id = getParaToLong("id");
		
		boolean bool = i_OrganizationService.updateJoinById(id, year);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}
	}
	
	@RequiresPermissions({ "7_2" })
	@Override
	public void deleteJoinById() {
		Long[] id = getParaValuesToLong("id");
		int i = i_OrganizationService.deleteJoinById(id);
		renderJson("deleteRows", i);
		
	}
	
	@RequiresPermissions({ "7_24" })
	@Override
	public void openJoinById() {
		Long id = getParaToLong("id");
		boolean bool = i_OrganizationService.openJoinById(id);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}
	}
	
	@RequiresPermissions({ "7_25" })
	@Override
	public void closeJoinById() {
		Long id = getParaToLong("id");
		boolean bool = i_OrganizationService.closeJoinById(id);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}
	}

	public I_OrganizationService getI_OrganizationService() {
		return i_OrganizationService;
	}

	public void setI_OrganizationService(I_OrganizationService i_OrganizationService) {
		this.i_OrganizationService = i_OrganizationService;
	}

	public int getRowsInPage() {
		return rowsInPage;
	}

	public void setRowsInPage(int rowsInPage) {
		this.rowsInPage = rowsInPage;
	}


}
