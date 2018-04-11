package cn.gov.hrss.ln.stuenroll.profession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import cn.gov.hrss.ln.stuenroll.classinfo.I_ArchiveDao;
import cn.gov.hrss.ln.stuenroll.classinfo.I_ClassinfoDao;
import cn.gov.hrss.ln.stuenroll.classinfo.I_EnrollDao;

public class ProfessionService implements I_ProfessionService{
	private I_OrganizationJoinDao i_OrganizationJoinDao;  //查询
    private I_EnrollDao i_EnrollDao;   //查询
    private I_ArchiveDao i_ArchiveDao;   //查询
    private I_ProfessionDao i_ProfessionDao;
    private I_ClassinfoDao i_ClassinfoDao;
	
	public List<Record> queryProfession(HashMap map) {
		//专业管理列表  profession_id   profession_name 的list
		
		List<Record> list = i_ProfessionDao.queryProfession(map); 
		List<Record> result = new ArrayList<Record>();  //用来存储最终最终版本record的list
		
		for(Record record:list){
			Record resultRecord = new Record();  //对应专业管理列表的各个栏目
			//逐条取出list里面的结果
			Long professionId = record.getLong("profession_id");
			String professionName = record.getStr("profession_name");
	        
			//将取出来的profession_id放到map里面
			HashMap searchMap = new HashMap();
			searchMap.put("professionId", professionId);
			
			//从前台传进来的map中取得organization_id
			Long organizationId = (Long)map.get("organizationId");
			searchMap.put("organizationId", organizationId);
			
			//调用OrganizationJoinDao的queryConnectedOrganizationAndConnectedYear方法取出对应profession_id的关联机构和关联年届
			Record connectedRecord = i_OrganizationJoinDao.queryOrganizationAndYear(searchMap); 
			
			//取出关联机构和关联年届
			Long organization = connectedRecord.getLong("organization");
			Long year = connectedRecord.getLong("year");
			
			//调用EnrollDao的queryCountAboutStudent方法取出enroll表的培训人数
			Record enroll = i_EnrollDao.queryCountAboutStudent(searchMap);
			//取出enroll表培训人数
			Long studentCount_enroll = enroll.getLong("student_count");
			
			//调用ArchiveDao的queryCountAboutStudent方法取出archive表的培训人数
			Record archive = i_ArchiveDao.queryCountAboutStudent(searchMap);
			//取出enroll表培训人数
			Long studentCount_archive = archive.getLong("student_count");
			
			//调用EnrollDao的queryCountAboutEmployed方法取出enroll表的总的就业人数
			Record enrollEmployee = i_EnrollDao.queryCountAboutEmployed(searchMap);
			Long employCount_enroll = enrollEmployee.getLong("employed_count");
			
			//调用ArchiveDao的queryCountAboutEmployed方法取出archive表的总的就业人数
			Record archiveEmployee = i_ArchiveDao.queryCountAboutEmployedStudent(searchMap);
			Long employCount_archive = archiveEmployee.getLong("employed_count");
			
			Record classCount = i_ClassinfoDao.queryClassCountByProfession(searchMap);
		    Long class_count = classCount.getLong("count");  //最终的班级数量
		    
		    Long studentCount = studentCount_enroll + studentCount_archive;  //最终的培训人数
		    Long employedCount = employCount_archive + employCount_enroll; //最终的就业人数
		    
		    String profession_id = String.valueOf(professionId);
		    resultRecord.set("profession_id", profession_id);
		    resultRecord.set("professionName", professionName);
		    resultRecord.set("organization", organization);
		    resultRecord.set("year", year);
		    resultRecord.set("class_count", class_count);
		    resultRecord.set("student_count",studentCount);
		    resultRecord.set("employed_count", employedCount);
		    result.add(result.size(),resultRecord);
		   // System.out.println(resultRecord);
		}
		return result;
	}

	@Before(Tx.class)
	@Override
	public int addProfession(String professionName) {
		boolean exist =  i_ProfessionDao.professionNameIfExist(professionName);
		if(exist){
			LogKit.warn("添加专业操作失败，要添加的专业"+professionName+"在profession表中已经存在");
			return 0;
		}
		int count = i_ProfessionDao.addProfession(professionName);
		return count;
	}

	@Before(Tx.class)
	@Override
	public int modifyProfession(String professionName, Long professionId) {
		boolean exist =  i_ProfessionDao.professionNameIfExist(professionName);
		if(exist){
			LogKit.warn("添加专业操作失败，要修改的专业"+professionName+"在profession表中已经存在");
			return 0;
		}
		if((professionName!=null && professionName !="") && professionId!=null){
			int count = i_ProfessionDao.modifyProfession(professionName, professionId);
			return count;
		}
		LogKit.warn("修改专业操作失败，传进来的参数不满足执行条件");
		return 0;
	}

	@Before(Tx.class)
	@Override
	public int deleteProfession(Long[] id) {
		int count = i_ProfessionDao.deleteProfession(id);
		return count;
	}

	@Override
	public List<Record> queryProfessionMember(HashMap map) {
		List<Record> list = i_ProfessionDao.queryProfessionMember(map);
		return list;
	}

	public I_OrganizationJoinDao getI_OrganizationJoinDao() {
		return i_OrganizationJoinDao;
	}

	public void setI_OrganizationJoinDao(I_OrganizationJoinDao i_OrganizationJoinDao) {
		this.i_OrganizationJoinDao = i_OrganizationJoinDao;
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

	public I_ProfessionDao getI_ProfessionDao() {
		return i_ProfessionDao;
	}

	public void setI_ProfessionDao(I_ProfessionDao i_ProfessionDao) {
		this.i_ProfessionDao = i_ProfessionDao;
	}

	@Override
	public Record queryProfessionCount(Long organizationId) {
		Record record = null;
		if(organizationId == null){
			record = i_ProfessionDao.queryProfessionCount();
		}
		else{
			record = i_OrganizationJoinDao.queryProfessionCount(organizationId);
		}
		return record;
		
		
	}

	public I_ClassinfoDao getI_ClassinfoDao() {
		return i_ClassinfoDao;
	}

	public void setI_ClassinfoDao(I_ClassinfoDao i_ClassinfoDao) {
		this.i_ClassinfoDao = i_ClassinfoDao;
	}

	@Before(Tx.class)
	@Override
	public int importFile(List<Record> list) {
		int count = 0;
		for(Record record: list){
			String professionName = record.getStr("name");
			//如果解析出来的专业名称为空或者null的话，跳过当次循环
			if(professionName == null || professionName ==""){
				continue;
			}
			//如果添加的某些记录已经存在，跳出当次循环
			boolean exist =  i_ProfessionDao.professionNameIfExist(professionName);
			if(exist){
				LogKit.warn("当前添加的记录"+professionName+"在profession表中已经存在");
				continue;
			}else{
				i_ProfessionDao.addProfession(professionName);
				count =count+1;
			}
		}
		LogKit.info("成功添加了"+count+"条记录");
		return count;
	}

	
	

}
