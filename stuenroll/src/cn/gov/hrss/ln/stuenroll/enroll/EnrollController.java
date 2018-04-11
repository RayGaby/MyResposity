package cn.gov.hrss.ln.stuenroll.enroll;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;
import com.sun.jmx.snmp.Timestamp;

@Spring("enrollController")
public class EnrollController extends Controller implements I_EnrollController {
	private I_EnrollService i_EnrollService;
	private int rowsInPage;

	@RequiresPermissions({ "3_4" })
	@Override
	public void searchEnroll() {
		String name = getPara("name");
		String pid = getPara("pid");
		Integer year = getParaToInt("year");
		String sex = getPara("sex");
		String education = getPara("education");
		String index = getPara("index");
		String organizationId = getPara("organization");
		String professionId = getPara("profession");
		String classinfoId = getPara("className");
		Long stateId = getParaToLong("stateId");
		if(index!=null&&index.equals("中退学员")){
			stateId = (long) 4;
		}
		String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId").toString();
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

		List<Record> list = i_EnrollService.searchEnroll(map, start, length,index);
		renderJson("result", list);
	}

	@RequiresPermissions({ "3_4" })
	@Override
	public void searchEnrollCount() {
		String name = getPara("name");
		String pid = getPara("pid");
		Integer year = getParaToInt("year");
		String sex = getPara("sex");
		String education = getPara("education");
		String index = getPara("index");
		String organizationId = getPara("organization");
		String professionId = getPara("profession");
		String classinfoId = getPara("className");
		Long stateId = getParaToLong("stateId");
		if(index!=null&&index.equals("中退学员")){
			stateId = (long) 4;
		}
		
		String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization!=null&&organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId").toString();
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

		long count = i_EnrollService.searchEnrollCount(map,index);
		renderJson("result", count);

	}

	@RequiresPermissions({ "3_2" })
	@Override
	public void deleteById() {
		Long[] id = getParaValuesToLong("id");
		int i = i_EnrollService.deleteById(id);
		renderJson("deleteRows", i);
	}

	public I_EnrollService getI_EnrollService() {
		return i_EnrollService;
	}

	public void setI_EnrollService(I_EnrollService i_EnrollService) {
		this.i_EnrollService = i_EnrollService;
	}

	public int getRowsInPage() {
		return rowsInPage;
	}

	public void setRowsInPage(int rowsInPage) {
		this.rowsInPage = rowsInPage;
	}

	@RequiresPermissions({ "3_4" })
	@Override
	public void getinfo() {
		// TODO Auto-generated method stub
		Long id = getParaToLong("id");
		Record record = i_EnrollService.getinfo(id);
		renderJson("result", record);
	}

	@RequiresPermissions({ "3_3" })
	@Override
	public void update() {
		String method = getPara("method");
		String name = getPara("name");
		String nation = getPara("nation");
		String graduate_year = getPara("graduate_year");
		String major = getPara("major");
		String healthy = getPara("healthy");
		String politics = getPara("politics");
		String graduate_school = getPara("graduate_school");
		String graduate_date = getPara("graduate_date");
		String birthday = getPara("birthday");
		String resident_address = getPara("resident_address");
		String permanent_address = getPara("permanent_address");
		String home_address = getPara("home_address");
		String tel = getPara("tel");
		String home_tel = getPara("home_tel");
		String email = getPara("email");
		String pid = getPara("pid");
		String id = getPara("id");
		String sex = getPara("sex");
		Date date = new Date();
		long ye = date.getYear()+1900;
		long month = date.getMonth()+1;
		String time = ye+"-"+month+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		String place = getPara("place");
		String year = getPara("year");
		String organizationId = getPara("organization_id");
		String professionId = getPara("professionId");
		String classinfoId = getPara("classinfo_id");
		String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId").toString();
		}
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("nation", nation);
		map.put("graduate_year", graduate_year);
		map.put("major", major);
		map.put("healthy", healthy);
		map.put("politics", politics);
		map.put("graduate_school", graduate_school);
		map.put("graduate_date", graduate_date);
		map.put("resident_address", resident_address);
		map.put("permanent_address", permanent_address);
		map.put("home_address", home_address);
		map.put("tel", tel);
		map.put("home_tel", home_tel);
		map.put("birthday", birthday);
		map.put("email", email);
		map.put("pid", pid);
		map.put("id", id);
		map.put("sex", sex);
		map.put("create_time", time);
		map.put("place", place);
		map.put("year", year);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("classinfoId", classinfoId);
		long res =  i_EnrollService.update(map, method);
		renderJson("updateRows", res);
	}
	@RequiresPermissions({ "3_19" })
	@Override
	public void divide() {
		// TODO Auto-generated method stub
		Long[] id = getParaValuesToLong("id");
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		String classinId = getPara("classId");
		String place = getPara("place");
		System.out.println(organizationId);
		System.out.println(professionId);
		System.out.println(classinId);
		int i = i_EnrollService.divide(id,organizationId,professionId,classinId,place);
		renderJson("divideRows", i);
	}

	@RequiresPermissions({ "3_20" })
	@Override
	public void undivided() {
		// TODO Auto-generated method stub
		Long[] id = getParaValuesToLong("id");
		int i = i_EnrollService.undivided(id);
		renderJson("undividedRows", i);
	}

	@RequiresPermissions({ "5_1" })
	@Override
	public void quit() {
		Long id = getParaToLong("id");
		String message = getPara("message");
		String date = getPara("date");
		int i = i_EnrollService.quit(id,message,date);
		renderJson("quitRows", i);
	}

	@RequiresPermissions({ "5_2" })
	@Override
	public void unquit() {
		Long[] id = getParaValuesToLong("id");
		int i = i_EnrollService.unquit(id);
		renderJson("unquitRows", i);
	}

	@RequiresPermissions({ "3_4" })
	@Override
	public void getOrganization() {
		// TODO Auto-generated method stub
		Integer year = getParaToInt("year");
		Long organizationId  = (long)-1;
		String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId");
		}
		List<Record> list = i_EnrollService.getOrganization(year,organizationId);
		renderJson("result", list);
	}

	@RequiresPermissions({ "3_4" })
	@Override
	public void getProfession() {
		// TODO Auto-generated method stub
		String organizationId = getPara("organizationId");
		String organization = getSessionAttr("organization"); // HttpSession中的组织名称
		// 如果不是就业网用户那么Java程序从HttpSession中提取机构ID，强制使用这个机构ID查询数据，所以用户只能看到自己机构的数据
		if (organization.equals("辽宁省就业网") == false) {
			organizationId = getSessionAttr("organizationId").toString();
		}
		Integer year = getParaToInt("year");
		List<Record> list = i_EnrollService.getProfession(organizationId,year);
		renderJson("result", list);
	}
	@RequiresPermissions({ "3_4" })
	@Override
	public void getPlace() {
		// TODO Auto-generated method stub
		Long organizationId = getParaToLong("organizationId");
		List<Record> list = i_EnrollService.getPlace(organizationId);
		renderJson("result", list);
	}

	@RequiresPermissions({ "3_4" })
	@Override
	public void getProfessionClass() {
		// TODO Auto-generated method stub
		Long organizationId = getParaToLong("organizationId");
		Long professionId = getParaToLong("professionId");
		Integer year = getParaToInt("year");
		List<Record> list = i_EnrollService.getProfessionClass(organizationId,professionId,year);
		renderJson("result", list);
	}


}
