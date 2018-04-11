package cn.gov.hrss.ln.stuenroll.archive;

import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;

@Spring("archiveController")
public class ArchiveController extends Controller implements I_ArchiveController {
	private I_ArchiveService i_ArchiveService;
	private int rowsInPage;
	
	@RequiresPermissions({ "4_4" })
	@Override
	public void searchArchive() {
		String name = getPara("name");
		String pid = getPara("pid");
		Integer year = getParaToInt("year");
		String sex = getPara("sex");
		String education = getPara("education");
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		Long classinfoId = getParaToLong("classinfoId");
		Long stateId = getParaToLong("stateId");

		String organization = getSessionAttr("organization"); //HttpSession中的组织名称
		// 不是就业网用户java程序会从HTTPSession中提取机构ID，强制使用这个机构ID查询数据，所以用户至看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("pid", pid);
		map.put("year", year);
		map.put("sex", sex);
		map.put("education", education);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("classinfoId", classinfoId);
		map.put("stateId", stateId);
		
		Long page = getParaToLong("page");
		if (page == null) {
			page = 1L;
		}
		long start = (page - 1) * rowsInPage;
		long length = rowsInPage;
		
		List<Record> list = i_ArchiveService.searchArchive(map, start, length);
		renderJson("result",list);
 	}
	

	@RequiresPermissions({ "4_4" })
	@Override
	public void searchArchiveCount() {
		String name = getPara("name");
		String pid = getPara("pid");
		Integer year = getParaToInt("year");
		String sex = getPara("sex");
		String education = getPara("education");
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		Long classinfoId = getParaToLong("classinfoId");
		Long stateId = getParaToLong("stateId");
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("pid", pid);
		map.put("year", year);
		map.put("sex", sex);
		map.put("education", education);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("classsinfoId", classinfoId);
		map.put("stateId", stateId);
		
		long count = i_ArchiveService.searchArchiveCount(map);
		renderJson("result",count);
	}
	
	@RequiresPermissions({ "0" })
	@Override
	public void insertArchive() {
		String name = getPara("name");
		String sex = getPara("sex");
		String nation = getPara("nation");
		String pid = getPara("pid");
		String graduateSchool = getPara("graduateSchool");
		Integer graduateYear = getParaToInt("graduateYear");
		String graduateDate = getPara("graduateDate");
		String education = getPara("education");
		String major = getPara("major");
		String healthy = getPara("healthy");
		String politics = getPara("politics");
		String birthday = getPara("birthday");
		String residentAddress = getPara("residentAddress");
		String homeAddress = getPara("homeAddress");
		String permanentAddress = getPara("permanentAddress");
		String tel = getPara("tel");
		String homeTel = getPara("homeTel");
		String email = getPara("email");
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		String place = getPara("place");

		HashMap map = new HashMap();
		map.put("name", name);
		map.put("sex", sex);
		map.put("nation", nation);
		map.put("pid", pid);
		map.put("graduateSchool", graduateSchool);
		map.put("graduateYear", graduateYear);
		map.put("graduateDate", graduateDate);
		map.put("education", education);
		map.put("major", major);
		map.put("healthy", healthy);
		map.put("politics", politics);
		map.put("birthday", birthday);
		map.put("residentAddress", residentAddress);
		map.put("homeAddress", homeAddress);
		map.put("permanentAddress", permanentAddress);
		map.put("tel", tel);
		map.put("homeTel", homeTel);
		map.put("email", email);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("place", place);
		
		boolean bool = i_ArchiveService.insertArchive(map);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}
	}
	
	@RequiresPermissions({ "4_3" })
	@Override
	public void updateArchive() {
		String name = getPara("name");
		String sex = getPara("sex");
		String nation = getPara("nation");
		String pid = getPara("pid");
		String graduateSchool = getPara("graduateSchool");
		Integer graduateYear = getParaToInt("graduateYear");
		String graduateDate = getPara("graduateDate");
		String education = getPara("education");
		String major = getPara("major");
		String healthy = getPara("healthy");
		String politics = getPara("politics");
		String birthday = getPara("birthday");
		String residentAddress = getPara("residentAddress");
		String homeAddress = getPara("homeAddress");
		String permanentAddress = getPara("permanentAddress");
		String tel = getPara("tel");
		String homeTel = getPara("homeTel");
		String email = getPara("email");
		String organization = getPara("organization");
		String profession = getPara("profession");
		String place = getPara("place");
		Long id = getParaToLong("id");
		
		System.out.println(graduateYear);

		HashMap map = new HashMap();
		map.put("name", name);
		map.put("sex", sex);
		map.put("nation", nation);
		map.put("pid", pid);
		map.put("graduateSchool", graduateSchool);
		map.put("graduateYear", graduateYear);
		map.put("graduateDate", graduateDate);
		map.put("education", education);
		map.put("major", major);
		map.put("healthy", healthy);
		map.put("politics", politics);
		map.put("birthday", birthday);
		map.put("residentAddress", residentAddress);
		map.put("homeAddress", homeAddress);
		map.put("permanentAddress", permanentAddress);
		map.put("tel", tel);
		map.put("homeTel", homeTel);
		map.put("email", email);
		map.put("organization", organization);
		map.put("profession", profession);
		map.put("place", place);
		map.put("id", id);
		
		boolean bool = i_ArchiveService.updateArchive(map);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}	
	}
	
	@RequiresPermissions({ "4_3" })
	@Override
	public void searchArchiveById() {
		Long id = getParaToLong("id");
		List<Record> list = i_ArchiveService.searchArchiveById(id);
		renderJson("result",list);
	}
	
	@RequiresPermissions({"4_2"})
	@Override
	public void deleteById() {
		Long[] id = getParaValuesToLong("id");
		int i = i_ArchiveService.deleteById(id);
		renderJson("deleteRows", i);
	}

	@RequiresPermissions({ "5_4" })
	@Override
	public void searchQuit() {
		String name = getPara("name");
		String pid = getPara("pid");
		Integer year = getParaToInt("year");
		String sex = getPara("sex");
		String education = getPara("education");
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		Long classinfoId = getParaToLong("classinfoId");
		
		String organization = getSessionAttr("organization");// HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("pid", pid);
		map.put("year", year);
		map.put("sex", sex);
		map.put("education", education);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("classinfoId", classinfoId);
		
		Long page = getParaToLong("page");
		if(page == null){
			page = 1L;
		}
		long start = (page - 1)* rowsInPage;
		long length = rowsInPage;
		
		List<Record> list = i_ArchiveService.searchQuit(map,start,length);
		renderJson("result",list);
	}

	@RequiresPermissions({ "5_4" })
	@Override
	public void searchQuitCount() {
		String name = getPara("name");
		String pid = getPara("pid");
		Integer year = getParaToInt("year");
		String sex = getPara("sex");
		String education = getPara("education");
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		Long classinfoId = getParaToLong("classinfoId");
		
		String organization = getSessionAttr("organization");	//HttpSession中的从组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId");
		}
		
		HashMap map = new HashMap();
		map.put("name",name);
		map.put("pid",pid);
		map.put("year",year);
		map.put("sex",sex);
		map.put("education",education);
		map.put("organizationId",organizationId);
		map.put("professionId",professionId);
		map.put("classinfoId",classinfoId);
		
		long count = i_ArchiveService.searchQuitCount(map);
		renderJson("result",count);
	}
	
	@RequiresPermissions({ "5_1" })
	@Override
	public void insertQuit() {
		String name = getPara("name");
		String sex = getPara("sex");
		String nation = getPara("nation");
		String pid = getPara("pid");
		String graduateSchool = getPara("graduateSchool");
		Integer graduateYear = getParaToInt("graduateYear");
		String graduateDate = getPara("graduateDate");
		String education = getPara("education");
		String major = getPara("major");
		String healthy = getPara("healthy");
		String politics = getPara("politics");
		String birthday = getPara("birthday");
		String residentAddress = getPara("residentAddress");
		String homeAddress = getPara("homeAddress");
		String permanentAddress = getPara("permanentAddress");
		String tel = getPara("tel");
		String homeTel = getPara("homeTel");
		String email = getPara("email");
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		String place = getPara("place");

		HashMap map = new HashMap();
		map.put("name", name);
		map.put("sex", sex);
		map.put("nation", nation);
		map.put("pid", pid);
		map.put("graduateSchool", graduateSchool);
		map.put("graduateYear", graduateYear);
		map.put("graduateDate", graduateDate);
		map.put("education", education);
		map.put("major", major);
		map.put("healthy", healthy);
		map.put("politics", politics);
		map.put("birthday", birthday);
		map.put("residentAddress", residentAddress);
		map.put("homeAddress", homeAddress);
		map.put("permanentAddress", permanentAddress);
		map.put("tel", tel);
		map.put("homeTel", homeTel);
		map.put("email", email);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("place", place);
		
		boolean bool = i_ArchiveService.insertQuit(map);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}
		
	}
	
	@RequiresPermissions({ "5_3" })
	@Override
	public void searchQuitById() {
		Long id = getParaToLong("id");
		List<Record> list = i_ArchiveService.searchQuitById(id);
		renderJson("result",list);
	}
	
	@RequiresPermissions({ "5_3" })
	@Override
	public void updateQuit() {
		String name = getPara("name");
		String sex = getPara("sex");
		String nation = getPara("nation");
		String pid = getPara("pid");
		String graduateSchool = getPara("graduateSchool");
		Integer graduateYear = getParaToInt("graduateYear");
		String graduateDate = getPara("graduateDate");
		String education = getPara("education");
		String major = getPara("major");
		String healthy = getPara("healthy");
		String politics = getPara("politics");
		String birthday = getPara("birthday");
		String residentAddress = getPara("residentAddress");
		String homeAddress = getPara("homeAddress");
		String permanentAddress = getPara("permanentAddress");
		String tel = getPara("tel");
		String homeTel = getPara("homeTel");
		String email = getPara("email");
		String organization = getPara("organization");
		String profession = getPara("profession");
		String place = getPara("place");
		Long id = getParaToLong("id");

		HashMap map = new HashMap();
		map.put("name", name);
		map.put("sex", sex);
		map.put("nation", nation);
		map.put("pid", pid);
		map.put("graduateSchool", graduateSchool);
		map.put("graduateYear", graduateYear);
		map.put("graduateDate", graduateDate);
		map.put("education", education);
		map.put("major", major);
		map.put("healthy", healthy);
		map.put("politics", politics);
		map.put("birthday", birthday);
		map.put("residentAddress", residentAddress);
		map.put("homeAddress", homeAddress);
		map.put("permanentAddress", permanentAddress);
		map.put("tel", tel);
		map.put("homeTel", homeTel);
		map.put("email", email);
		map.put("organization", organization);
		map.put("profession", profession);
		map.put("place", place);
		map.put("id", id);
		
		boolean bool = i_ArchiveService.updateArchive(map);
		if(bool == true){
			renderJson("result", "成功");
		}
		else{
			renderJson("result", "失败");
		}	
	}

	@RequiresPermissions({ "5_2" })
	@Override
	public void deleteQuitById() {
		Long[] id = getParaValuesToLong("id");
		int i = i_ArchiveService.deleteQuitById(id);
		renderJson("deleteRows",i);
	}
	
	@Override
	public void searchOrganization() {
		Long organizationId = getSessionAttr("organizationId");
		List<Record> list = i_ArchiveService.searchOrganization(organizationId);
		renderJson("result",list);
		
	}
	
	@Override
	public void searchProfession() {
		Long organizationId = getParaToLong("organizationId");
		List<Record> list = i_ArchiveService.searchProfession(organizationId);
		renderJson("result",list);
		
	}
	
	public I_ArchiveService getI_ArchiveService() {
		return i_ArchiveService;
	}

	public void setI_ArchiveService(I_ArchiveService i_ArchiveService) {
		this.i_ArchiveService = i_ArchiveService;
	}

	public int getRowsInPage() {
		return rowsInPage;
	}

	public void setRowsInPage(int rowsInPage) {
		this.rowsInPage = rowsInPage;
	}


}
