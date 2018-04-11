package cn.gov.hrss.ln.stuenroll.classinfo;
//TODO 修改了这里
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

public class ClassinfoService implements I_ClassinfoService{
	private I_ClassinfoDao i_ClassinfoDao;
	private I_EnrollDao i_EnrollDao;
	private I_ArchiveDao i_ArchiveDao;
	private I_VisitDao i_VisitDao;
	
	public List<Record> queryListByCondition(HashMap map, long start, long length) {
		List<Record> list = i_ClassinfoDao.queryFiveColumn(map, start, length);
		List<Record> result = new ArrayList<Record>();//用来存储最终完整的记录
		System.out.println(list);
		for(Record record:list){
			Record finalRecord = new Record();//存储一条完整的记录
			//逐条从数据库中读取记录，getLong方法中的参数应该为数据库的参数
			String classinfoId = record.getStr("id");
			String classinfo_name = record.getStr("name");
			String organization_name = record.getStr("organization");
			String profession_name = record.getStr("profession");
			int year = record.getInt("year");
			String state = record.getStr("state");
			
			Long classinfo_1 = Long.valueOf(classinfoId);
			//调用enrollDao和achieveDao的queryCountAboutStudent方法中传入的map
			HashMap otherMap = new HashMap();
			otherMap.put("classinfoId", classinfo_1);
			
			
			Long organizationId = (Long) map.get("organizationId");
			
			Long stu_count;
			Long employ_count;
			
			if(state.equals("未归档") == true){
				//调用此方法获取每个班级的人数
				Record enrollRecord = i_EnrollDao.queryCountAboutStudent(otherMap);
				//取出enroll表中的班级的人数
				stu_count = enrollRecord.getLong("student_count");
				//调用此方法获取每个班级的就业人数
				Record enrollEmploy = i_EnrollDao.queryCountAboutEmployed(otherMap);
				//取出enroll表中的班级的就业人数
				employ_count = enrollEmploy.getLong("employed_count");
			}
			else{
				Record archiveRecord = i_ArchiveDao.queryCountAboutStudent(otherMap);
				stu_count = archiveRecord.getLong("student_count");
				Record archiveEmploy = i_ArchiveDao.queryCountAboutEmployedStudent(otherMap);
				employ_count = archiveEmploy.getLong("employed_count");
			}
			
			//将所有的信息添加到完整的记录中
			finalRecord.set("id", classinfoId);
			finalRecord.set("name", classinfo_name);
			finalRecord.set("organization", organization_name);
			finalRecord.set("profession", profession_name);
			finalRecord.set("year", year);
			finalRecord.set("stu_count", stu_count);
			finalRecord.set("state", state);
			finalRecord.set("employ_count", employ_count);
			//汇总所有记录
			result.add(finalRecord);
			
			
		}
		return result;
	}

	public long queryCountAboutList(HashMap map) {
		long count = i_ClassinfoDao.queryClassCount(map);
		return count;
	}


	@Override
	public List<Record> dropDownAboutClassId(HashMap map) {
		List<Record> list = i_ClassinfoDao.dropDownAboutClassId(map);
		System.out.println(list);
		return list;
	}

	@Override
	public List<Record> dropDownAboutClassYear(HashMap map) {
		List<Record> list = i_ClassinfoDao.dropDownAboutClassYear(map);
		return list;
	}

	@Override
	public List<Record> dropDownAboutOrganization(HashMap map) {
		List<Record> list = i_ClassinfoDao.dropDownAboutOraganization(map);
		System.out.println(list);
		return list;
	}

	@Override
	public List<Record> dropDownAboutProfession(HashMap map) {
		List<Record> list = i_ClassinfoDao.dropDownAboutProfession(map);
		System.out.println(list);
		return list;
	}
	
	@Before(Tx.class)
	@Override
	public int addClassinfo(HashMap map) {
		String name =(String) map.get("name");
		
		boolean exist = i_ClassinfoDao.classinfoNameIfExist(name);
		
		if(exist){
			return 0;
		}
		else{
			int record = i_ClassinfoDao.addClassinfo(map);
			return record;
		}
		
	}
	
	@Override
	public List<Record> dropDownOrganizationAboutAdd(HashMap map) {
		List<Record> list = i_ClassinfoDao.dropDownOrganizationAboutAdd(map);
		System.out.println(list);
		return list;
	}

	@Override
	public List<Record> dropDownProfessionAboutAdd(HashMap map) {
		List<Record> list = i_ClassinfoDao.dropDownProfessionAboutAdd(map);
		System.out.println(list);
		return list;
	}
	
	@Before(Tx.class)
	public int deleteById(Long[] id){
		int i = i_ClassinfoDao.deleteById(id);
		return i;
	}
	
	

	@Before(Tx.class)
	@Override
	public int modifyClassinfo(HashMap map) {
		String name =(String) map.get("name");
		boolean ifEqual = (boolean) map.get("ifEqual");
		
		if(!ifEqual){
			boolean exist = i_ClassinfoDao.classinfoNameIfExist(name);
		
			if(exist){
				return 0;
			}
		}
		
		int record = i_ClassinfoDao.modifyClassinfo(map);
		return record;
		
	}

	@Before(Tx.class)
	@Override
	public int classArchive(Long[] id) {
		int i = 0;
		for(i = 0; i < id.length; i++){
			//一个一个获取id
			Long classinfoId = id[i];
			//根据classinfoId查找所有的学生名单的所有信息
			List<Record> list = i_EnrollDao.searchStudentinfo(classinfoId);
			
			for(Record record:list){
				
				//逐条从数据库中读取记录，getLong方法中的参数应该为数据库的参数
				String name = record.getStr("name");
				String sex = record.getStr("sex");
				String nation = record.getStr("nation");
				String pid = record.getStr("pid");
				String graduate_school = record.getStr("graduate_school");
				Long graduate_year = record.getLong("graduate_year");
				Date graduate_date = (Date) record.getDate("graduate_date");
				String education = record.getStr("education");
				String major = record.getStr("major");
				String healthy = record.getStr("healthy");
				String politics = record.getStr("politics");
				
				//TODO 导入java。sql.date
				Date birthday = (Date) record.getDate("birthday");
				String resident_address = record.getStr("resident_address");
				String permanent_address = record.getStr("permanent_address");
				String home_address = record.getStr("home_address");
				String tel = record.getStr("tel");
				String home_tel = record.getStr("home_tel");
				String email = record.getStr("email");
				String wechat = record.getStr("wechat");
				Long professionId = record.getLong("profession_id");
				Long classinfo = record.getLong("classinfo_id");
				Long stateId = record.getLong("state_id");
				Long organizationId = record.getLong("organization_id");
				String place = record.getStr("place");
				String remark = record.getStr("remark");
				int year = record.getInt("year");
				
				HashMap map = new HashMap();
				map.put("name",name);
				map.put("sex",sex);
				map.put("nation",nation);
				map.put("pid",pid);
				map.put("graduateSchool",graduate_school);
				map.put("graduateYear",graduate_year);
				map.put("graduateDate",graduate_date);
				map.put("education",education);
				map.put("major",major);
				map.put("healthy",healthy);
				map.put("politics",politics);
				map.put("birthday",birthday);
				map.put("resident_address",resident_address);
				map.put("permanent_address",permanent_address);
				map.put("home_address",home_address);
				map.put("tel",tel);
				map.put("home_tel",home_tel);
				map.put("email",email);
				map.put("wechat",wechat);
				map.put("professionId",professionId);
				map.put("classinfoId",classinfo);
				map.put("stateId",stateId);
				map.put("organizationId",organizationId);
				map.put("place",place);
				map.put("remark",remark);
				map.put("year",year);
				
				int result = i_ArchiveDao.addStudentinfo(map);
 			}
		
			Long stateId =  (long)1;
			
			HashMap map = new HashMap();
			map.put("id",classinfoId);
			map.put("stateId",stateId);
			//修改班级的状态
			int num = i_ClassinfoDao.modifyClassinfo(map);
		}
		
		int delete = i_EnrollDao.deleteById(id);
		return i;
	}

	@Override
	public List<Record> searchStudentAboutClassinfo(HashMap map, long start, long length) {
		Record record = i_ClassinfoDao.searchClassinfoName(map);
		
		Long count = record.getLong("count");
		
		if(count == 0){
			return null;
		}
		else{
			String state = (String) map.get("state");
			Long classinfoId = (Long) map.get("classinfoId");
			
			if(state.equals("未归档")){
				List<Record> list = i_EnrollDao.searchStudentAboutClassinfo(classinfoId, start, length);
				System.out.println(list);
				return list;
				
				
			}
			else{
				List<Record> list = i_ArchiveDao.searchStudentAboutClassinfo(classinfoId, start, length);
				return list;
			}
		}
		
	}

	@Override
	public List<Record> queryTelVisit(HashMap map, long start, long length) {
		List<Record> list = i_ClassinfoDao.queryFiveColumn(map, start, length);
		//用来存储最终返回的记录
		List<Record> result = new ArrayList<Record>();
		
		for(Record record : list){
			Record finalRecord = new Record();//存储一条完整的记录
			//逐条从数据库中读取记录，getLong方法中的参数应该为数据库的参数
			String classinfo_id = record.getStr("id");
			String classinfo_name = record.getStr("name");
			String organization_name = record.getStr("organization");
			String profession_name = record.getStr("profession");
			int year = record.getInt("year");
			String state = record.getStr("state");
			
			//将classinfoId转换为Long类型
			Long classinfoId = Long.valueOf(classinfo_id);
			
			HashMap otherMap = new HashMap();
			otherMap.put("classinfoId", classinfoId);
			
			Long organizationId = (Long) map.get("organizationId");
			otherMap.put("organizationId", organizationId);
			
			otherMap.put("state", state);
			
			Long stu_count;
			
			if(state.equals("未归档") == true){
				//调用此方法获取每个班级的人数
				Record enrollRecord = i_EnrollDao.queryCountAboutStudent(otherMap);
				//取出enroll表中的班级的人数
				stu_count = enrollRecord.getLong("student_count");
			}
			else{
				Record archiveRecord = i_ArchiveDao.queryCountAboutStudent(otherMap);
				stu_count = archiveRecord.getLong("student_count");
			}
			
			//获取每个班级中 的回访人数
			Record visitCount = i_VisitDao.queryVisitCount(classinfoId);
			Long visit_count = visitCount.getLong("visit_count");
			
			//获取每个班级中 的回访成功的人数
			Record visitSuccessCount = i_VisitDao.queryVisitSuccess(classinfoId);
			Long visit_success = visitSuccessCount.getLong("visit_success");
			
			//将所有的信息添加到完整的记录中
			finalRecord.set("id", classinfo_id);
			finalRecord.set("name", classinfo_name);
			finalRecord.set("organization", organization_name);
			finalRecord.set("profession", profession_name);
			finalRecord.set("year", year);
			finalRecord.set("stu_count", stu_count);
			finalRecord.set("visit_count", visit_count);
			finalRecord.set("visit_success", visit_success);
			//汇总所有记录
			result.add(finalRecord);
		}
		return result;
	}

	public I_ClassinfoDao getI_ClassinfoDao() {
		return i_ClassinfoDao;
	}
	
	public void setI_ClassinfoDao(I_ClassinfoDao i_ClassinfoDao) {
		this.i_ClassinfoDao = i_ClassinfoDao;
	}
	
	public I_EnrollDao getI_EnrollDao() {
		return i_EnrollDao;
	}
	
	public void setI_EnrollDao(I_EnrollDao i_EnrollDao) {
		this.i_EnrollDao = i_EnrollDao;
	}
	
	public I_ArchiveDao getI_ArchiveDao() {
		return i_ArchiveDao;
	}
	
	public void setI_ArchiveDao(I_ArchiveDao i_ArchiveDao) {
		this.i_ArchiveDao = i_ArchiveDao;
	}
	
	public I_VisitDao getI_VisitDao() {
		return i_VisitDao;
	}

	public void setI_VisitDao(I_VisitDao i_VisitDao) {
		this.i_VisitDao = i_VisitDao;
	}

	@Override
	public Record queryStuNameById(Long stuId,Long classinfoId) {
		HashMap map = new HashMap<>();
		map.put("classinfoId", classinfoId);
		Record classState = i_ClassinfoDao.searchClassinfoName(map);
		
		String state = classState.getStr("state");
		Record record ;
		if(state.equals("未归档")){
			record = i_EnrollDao.queryStuName(stuId);
		}
		else{
			record = i_ArchiveDao.queryStuName(stuId);
		}
		return record;
	}

	
}
