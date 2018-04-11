package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.spring.Spring;
/**
 * 班级管理的控制类
 * @author Administrator
 *
 */
@Spring("classinfoController")
public class ClassinfoController extends Controller implements I_ClassinfoController{
	private I_ClassinfoService i_ClassinfoService;
	private int rowsInPage;
	
	@RequiresPermissions({ "8_4" })
	@Override
	public void queryCountByCondition() {
		Long classinfoId = getParaToLong("classinfo");
		Long organizationId = getParaToLong("organization");
		Long professionId = getParaToLong("profession");
		Integer year = getParaToInt("year");
		String state = getPara("state");

		/*
		//判断机构，专业的名字
		if(organization == null||organization.equals("-选择-") ){
			organization = null;
		}
		if(classinfo == null||classinfo.equals("-选择-") ){
			classinfo = null;
		}
		Integer year;
		if(year_1==null||year_1.equals("-选择-")||year_1==""){
			year = null;
		}else{
			year = Integer.valueOf(year_1);
		}*/
		String organization = getSessionAttr("organization");
		
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
			
		}
		
		HashMap map = new HashMap();
		map.put("classinfoId", classinfoId);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("year", year);
		map.put("state", state);
		
		//TODO 在写前台注意page的获取
		Long page = getParaToLong("page");
		if(page == null){
			page = 1L;
		}
		long start = (page - 1) * rowsInPage;
		long length = rowsInPage;
		
		List<Record> list = i_ClassinfoService.queryListByCondition(map, start, length);
		
		renderJson("result",list);
	}

	@RequiresPermissions({ "8_4" })
	@Override
	public void queryCountAboutList() {
		Long classinfoId = getParaToLong("classinfo");
		Long organizationId = getParaToLong("organization");
		Long professionId = getParaToLong("profession");
		Integer year = getParaToInt("year");
		String state = getPara("state");
		/*//判断机构，专业的名字
		if((organization == null||organization.equals("-选择-"))== true ){
			organization = null;
		}
		if((classinfo == null||classinfo.equals("-选择-"))==true ){
			classinfo = null;
		}
		Integer year;
		if(year_1==null||year_1.equals("-选择-")||year_1==""){
			year = null;
		}else{
			year = Integer.valueOf(year_1);
		}*/
		
		String organization = getSessionAttr("organization");
		
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		HashMap map = new HashMap();
		map.put("classinfoId", classinfoId);
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("year", year);
		map.put("state", state);
		
		long count = i_ClassinfoService.queryCountAboutList(map);
		System.out.println(count);
		renderJson("result",count);
		
		
	}

	@RequiresPermissions({ "8_4" })
	@Override
	public void dropDownAboutClassId() {
		Long organizationId = getParaToLong("organization");
		Long professionId = getParaToLong("profession");
		Integer year = getParaToInt("year");
		/*//判断机构，专业的名字
		if(profession.equals("-选择-")){
			profession = null;
		}
		if(organization.equals("-选择-")){
			organization = null;
		}
		Integer year;
		if(year_1.equals("-选择-")){
			year = null;
		}else{
			year = Integer.valueOf(year_1);
		}*/
		
		String state = getPara("state");
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		
		HashMap map = new HashMap();
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("year", year);
		map.put("state", state);
		
		List<Record> list = i_ClassinfoService.dropDownAboutClassId(map);
		renderJson("result",list);
	}

	@RequiresPermissions({ "8_4" })
	@Override
	public void dropDownAboutClassYear() {
		Long organizationId = getParaToLong("organization");
		Long professionId = getParaToLong("profession");
		Long classinfoId = getParaToLong("classinfo");
		/*//判断机构，专业的名字
		if(profession.equals("-选择-")){
			profession = null;
		}
		if(organization.equals("-选择-")){
			organization = null;
		}
		if(classinfo.equals("-选择-")){
			classinfo = null;
		}*/
		String state = getPara("state");
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		
		HashMap map = new HashMap();
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("classinfoId", classinfoId);
		map.put("state", state);
		
		List<Record> list = i_ClassinfoService.dropDownAboutClassYear(map);
		renderJson("result",list);
		
	}

	@RequiresPermissions({ "8_4" })
	@Override
	public void dropDownAboutOrganization() {
		Long classinfoId = getParaToLong("classinfo");
		Long professionId = getParaToLong("profession");
		Integer year = getParaToInt("year");
		/*//判断机构，专业的名字
		if(profession.equals("-选择-")){
			profession = null;
		}
		if(classinfo.equals("-选择-")){
			classinfo = null;
		}
		Integer year;
		if(year_1.equals("-选择-")){
			year = null;
		}else{
			year = Integer.valueOf(year_1);
		}*/
		String state = getPara("state");
		
		String organization = getSessionAttr("organization");
		Long organizationId = null;
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		
		HashMap map = new HashMap();
		map.put("organizationId", organizationId);
		map.put("professionId", professionId);
		map.put("classinfoId", classinfoId);
		map.put("year", year);
		map.put("state", state);
		
		List<Record> list = i_ClassinfoService.dropDownAboutOrganization(map);
		renderJson("result",list);
		
	}

	@RequiresPermissions({ "8_4" })
	@Override
	public void dropDownAboutProfession() {
		Long classinfoId = getParaToLong("classinfo");
		Long organizationId = getParaToLong("organization");
		Integer year = getParaToInt("year");
		/*//判断机构，专业的名字
		if(organization.equals("-选择-")){
			organization = null;
		}
		if(classinfo.equals("-选择-")){
			classinfo = null;
		}
		Integer year;
		if(year_1.equals("-选择-")){
			year = null;
		}else{
			year = Integer.valueOf(year_1);
		}*/
		String state = getPara("state");
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
		}
		
		HashMap map = new HashMap();
		map.put("organizationId", organizationId);
		map.put("classinfoId", classinfoId);
		map.put("year", year);
		map.put("state", state);
		
		List<Record> list = i_ClassinfoService.dropDownAboutProfession(map);
		//System.out.println(list);
		renderJson("result",list);
		
	}
	
	@RequiresPermissions({ "8_1" })
	@Override
	public void addClassinfo() {
		int record = 0;
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			record = -1;
		}
		else{
			String name = (String)getPara("classinfo");
			Long organizationId = (Long) getParaToLong("organization");
			Long professionId = (Long) getParaToLong("profession");
			Integer year = (Integer) getParaToInt("year");
			
			HashMap map = new HashMap();
			map.put("name",name);
			map.put("organizationId",organizationId);
			map.put("professionId",professionId);
			map.put("year",year);
			
			record = i_ClassinfoService.addClassinfo(map);
		}
		
		
		renderJson("result",record);
		
	}
	
	@RequiresPermissions({ "8_2" })
	public void deleteById(){
		int i = 0;
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			i = -1;
		}
		else{
			Long[] id = getParaValuesToLong("id");
			System.out.println(id);
			i = i_ClassinfoService.deleteById(id);
		}
		renderJson("deleteRows", i);
	}

	@RequiresPermissions({ "8_1","8_3" })
	@Override
	public void dropDownOrganizationAboutAdd() {
		Integer year = (Integer) getParaToInt("year");
		Long professionId = (Long) getParaToLong("profession");
		
		HashMap map = new HashMap();
		map.put("year", year);
		map.put("professionId", professionId);
		
		List<Record> result = i_ClassinfoService.dropDownOrganizationAboutAdd(map);
		
		renderJson("result",result);
	}

	@RequiresPermissions({ "8_1","8_3" })
	@Override
	public void dropDownProfessionAboutAdd() {
		Integer year = (Integer) getParaToInt("year");
		Long organizationId = (Long) getParaToLong("organization");
		
		HashMap map = new HashMap();
		map.put("year", year);
		map.put("organizationId", organizationId);
		
		List<Record> result = i_ClassinfoService.dropDownProfessionAboutAdd(map);
		System.out.println(result);
		renderJson("result",result);
		
	}

	@RequiresPermissions({ "8_3" })
	@Override
	public void modifyClassinfo() {
		int record = 0;
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			record = -1;
		}
		else{
			
			//Long professionId = (Long) getParaToLong("profession");
			Long id = (Long) getParaToLong("id");
			Long professionId = (Long) getParaToLong("profession");
			Long organizationId = (Long) getParaToLong("organization");
			Integer year = (Integer) getParaToInt("year");
			boolean ifEqual = (boolean) getParaToBoolean("ifEqual");
			
			String name = (String)getPara("classinfo");
			
		
			HashMap map = new HashMap();
			map.put("name",name);
			map.put("organizationId",organizationId);
			map.put("professionId",professionId);
			map.put("year",year);
			map.put("id", id);
			map.put("ifEqual", ifEqual);
			
			record = i_ClassinfoService.modifyClassinfo(map);
		}
		renderJson("result",record);
		
	}
	
	@RequiresPermissions({ "8_10" })
	@Override
	public void classArchive() {
		int i = 0;
		
		String organization = getSessionAttr("organization");
		if(organization.equals("辽宁省就业网") == false){
			i = -1;
		}
		else{
			Long[] id = getParaValuesToLong("id");
			
			i = i_ClassinfoService.classArchive(id);
		}
			
		renderJson("archive", i );
		
	}
	@RequiresPermissions({ "8_4" })
	@Override
	public void searchStudentAboutClassinfo() {
		Long classinfoId = (Long) getParaToLong("classinfo");
		String state = (String) getPara("state");
		
		String organization = getSessionAttr("organization");
		Long organizationId = null;
		if(organization.equals("辽宁省就业网") == false){
			organizationId = getSessionAttr("organizationId");
			
		}
		HashMap map = new HashMap();
		map.put("state",state);
		map.put("classinfoId",classinfoId);
		map.put("organizationId",organizationId);
		
		Long page = getParaToLong("page");
		if(page == null){
			page = 1L;
		}
		long start = (page - 1) * 10;
		long length = 10;
	
		List<Record> list = i_ClassinfoService.searchStudentAboutClassinfo(map, start, length);
		
		renderJson("result",list);
		
	}
	@RequiresPermissions({ "8_4" })
	@Override
	public void queryTelVisit() {
		Long classinfoId = getParaToLong("classinfo");
		Long organizationId = getParaToLong("organization");
		Long professionId = getParaToLong("profession");
		Integer year = getParaToInt("year");

		List<Record> list = null;
		
		String organization = getSessionAttr("organization");
		
		if(organization.equals("辽宁省就业网")){
			HashMap map = new HashMap();
			map.put("classinfoId", classinfoId);
			map.put("organizationId", organizationId);
			map.put("professionId", professionId);
			map.put("year", year);
			
			//TODO 在写前台注意page的获取
			Long page = getParaToLong("page");
			if(page == null){
				page = 1L;
			}
			long start = (page - 1) * rowsInPage;
			long length = rowsInPage;
			
			list = i_ClassinfoService.queryTelVisit(map, start, length);
		}
		
		
		
		renderJson("result",list);
		
	}
	
	public void organization(){
		String organization = getSessionAttr("organization");
		
		renderJson("result",organization);
	}

	public I_ClassinfoService getI_ClassinfoService() {
		return i_ClassinfoService;
	}

	public void setI_ClassinfoService(I_ClassinfoService i_ClassinfoService) {
		this.i_ClassinfoService = i_ClassinfoService;
	}

	public int getRowsInPage() {
		return rowsInPage;
	}

	public void setRowsInPage(int rowsInPage) {
		this.rowsInPage = rowsInPage;
	}

	@Override
	public void queryStuName() {
		Long classinfoId = getParaToLong("classinfoId");
		Long stuId = getParaToLong("stuId");
		
		Record record = i_ClassinfoService.queryStuNameById(stuId, classinfoId);
		
		renderJson("result",record);
		
	}

	

	
}
