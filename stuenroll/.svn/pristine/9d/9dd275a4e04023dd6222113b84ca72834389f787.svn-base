package cn.gov.hrss.ln.stuenroll.classinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * Classinfo表Dao类
 * @author Administrator
 *
 */

public class ClassinfoDao implements I_ClassinfoDao{

	@Override
	public List<Record> queryFiveColumn(HashMap map, long start, long length) {
		ArrayList param = new ArrayList();
		Long classinfoId = (Long) map.get("classinfoId");
		Long organizationId = (Long) map.get("organizationId");
		Integer year = (Integer) map.get("year");
		Long professionId = (Long) map.get("professionId");
		String state = (String) map.get("state");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	c.id AS id, ");
		sql.append("	c.`name` AS `name`, ");
		sql.append("	o.`name` AS organization, ");
		sql.append("	p.`name` AS profession, ");
		sql.append("	c.`year` AS `year`, ");
		sql.append("	cs.`name` AS state ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("JOIN organization o ON c.organization_id = o.id ");
		sql.append("JOIN profession p ON c.profession_id = p.id ");
		sql.append("JOIN class_state cs ON c.state_id = cs.id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if(classinfoId !=null ){
			sql.append("AND c.id = ? ");
			param.add(classinfoId);
		}
		if(state !=null && state.length() > 0){
			sql.append("AND cs.`name` = ? ");
			param.add(state);
		}
		if(organizationId !=null){
			sql.append("AND o.id = ? ");
			param.add(organizationId);
		}
		if(year != null){
			sql.append("AND c.`year` = ? ");
			param.add(year);
		}
		if(professionId !=null){
			sql.append("AND p.id = ? ");
			param.add(professionId);
		}
		sql.append("ORDER BY c.id ");
		sql.append("LIMIT ?, ? ");
		param.add(start);
		param.add(length);
		
		List<Record> list = Db.find(sql.toString(), param.toArray());
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		
		return list;
	}

	@Override
	public long queryClassCount(HashMap map) {
		ArrayList param = new ArrayList();
		Long classinfoId = (Long) map.get("classinfoId");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		Integer year = (Integer) map.get("year");
		String state = (String) map.get("state");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(*) ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("JOIN organization o ON c.organization_id = o.id ");
		sql.append("JOIN profession p ON c.profession_id = p.id ");
		sql.append("JOIN class_state cs ON c.state_id = cs.id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if(classinfoId !=null){
			sql.append("AND c.id = ? ");
			param.add(classinfoId);
		}
		if(state !=null && state.length() > 0){
			sql.append("AND cs.`name` = ? ");
			param.add(state);
		}
		if(organizationId !=null ){
			sql.append("AND o.id = ? ");
			param.add(organizationId);
		}
		if(year !=null){
			sql.append("AND c.`year` = ? ");
			param.add(year);
		}
		if(professionId !=null){
			sql.append("AND c.profession_id = ? ");
			param.add(professionId);
		}
		
		long count = Db.queryLong(sql.toString(), param.toArray());
		return count;
	}

	
	public List<Record> dropDownAboutClassId(HashMap map) {
		//机构，年届，专业
		ArrayList param = new ArrayList();
		Long organizationId = (Long) map.get("organizationId");
		Integer year = (Integer) map.get("year");
		Long professionId = (Long) map.get("professionId");
		String state = (String) map.get("state");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	c.id, ");
		sql.append("	c.`name` ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("JOIN class_state cs ON cs.id = c.state_id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if(state !=null && state.length() > 0){
			sql.append("AND cs.`name` = ? ");
			param.add(state);
		}
		if(organizationId !=null ){
			sql.append("AND c.organization_id = ? ");
			param.add(organizationId);
		}
		if(year !=null){
			sql.append("AND c.`year` = ? ");
			param.add(year);
		}
		if(professionId !=null ){
			sql.append("AND c.profession_id = ? ");
			param.add(professionId);
		}
		sql.append("GROUP BY c.id ");
		
		List<Record> list = Db.find(sql.toString(),param.toArray());
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		
		return list;
	}
	
	public List<Record> dropDownAboutClassYear(HashMap map) {
		//机构，班级，专业
		ArrayList param = new ArrayList();
		Long organizationId = (Long) map.get("organizationId");
		Long classinfoId = (Long) map.get("classinfoId");
		Long professionId = (Long) map.get("professionId");
		String state = (String) map.get("state");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	c.id, ");
		sql.append("	c.`year` ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("JOIN class_state cs ON cs.id = c.state_id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if(state !=null && state.length() > 0){
			sql.append("AND cs.`name` = ? ");
			param.add(state);
		}
		if(organizationId !=null){
			sql.append("AND c.organization_id = ? ");
			param.add(organizationId);
		}
		if(classinfoId !=null){
			sql.append("AND c.id = ? ");
			param.add(classinfoId);
		}
		if(professionId !=null){
			sql.append("AND c.profession_id = ? ");
			param.add(professionId);
		}
		sql.append("GROUP BY c.`year` ");
		
		List<Record> list = Db.find(sql.toString(),param.toArray());
		
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		
		return list;
	}


	@Override
	public List<Record> dropDownAboutOraganization(HashMap map) {
		//班级，年届，专业
		ArrayList param = new ArrayList();
		Integer year = (Integer) map.get("year");
		Long classinfoId = (Long) map.get("classinfoId");
		Long professionId = (Long) map.get("professionId");
		Long organizationId = (Long) map.get("organizationId");
		String state = (String) map.get("state");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	o.id, ");
		sql.append("	o.abbreviation ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("JOIN class_state cs ON cs.id = c.state_id ");
		sql.append("JOIN organization o ON o.id = c.organization_id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if(state !=null && state.length() > 0){
			sql.append("AND cs.`name` = ? ");
			param.add(state);
		}
		if(year !=null ){
			sql.append("AND c.`year` = ? ");
			param.add(year);
		}
		if(classinfoId !=null){
			sql.append("AND c.id = ? ");
			param.add(classinfoId);
		}
		if(professionId !=null){
			sql.append("AND c.profession_id = ? ");
			param.add(professionId);
		}
		if(organizationId !=null){
			sql.append("AND o.id = ? ");
			param.add(organizationId);
		}
		sql.append("GROUP BY o.id ");
		
		List<Record> list = Db.find(sql.toString(),param.toArray());
		
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		
		return list;
	}

	@Override
	public List<Record> dropDownAboutProfession(HashMap map) {
		//班级，年届，机构
		ArrayList param = new ArrayList();
		Integer year = (Integer) map.get("year");
		Long classinfoId = (Long) map.get("classinfoId");
		Long organizationId = (Long) map.get("organizationId");
		String state = (String) map.get("state");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	p.id, ");
		sql.append("	p.`name` ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("JOIN class_state cs ON cs.id = c.state_id ");
		sql.append("JOIN profession p ON p.id = c.profession_id ");
		sql.append("WHERE ");
		sql.append("	1 = 1 ");
		if(state !=null && state.length() > 0){
			sql.append("AND cs.`name` = ? ");
			param.add(state);
		}
		if(year !=null ){
			sql.append("AND c.`year` = ? ");
			param.add(year);
		}
		if(classinfoId !=null){
			sql.append("AND c.id = ? ");
			param.add(classinfoId);
		}
		if(organizationId !=null ){
			sql.append("AND c.organization_id = ? ");
			param.add(organizationId);
		}
		sql.append("GROUP BY p.id ");
		
		List<Record> list = Db.find(sql.toString(),param.toArray());
		
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		
		return list;
	}

	@Override
	public int addClassinfo(HashMap map) {
		ArrayList param = new ArrayList();
		String classinfo_name = (String) map.get("name");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		Integer year = (Integer)map.get("year");
		
		//向数组添加数据
		param.add(classinfo_name);
		param.add(organizationId);
		param.add(professionId);
		param.add(year);
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO classinfo ( ");
		sql.append("	id, ");
		sql.append("	`name`,");
		sql.append("	organization_id, ");
		sql.append("	profession_id, ");
		sql.append("	`year`, ");
		sql.append("	state_id ");
		sql.append(") ");
		sql.append("VALUES ");
		sql.append("	( ");
		sql.append("		NEXT ");
		sql.append("		VALUE ");
		sql.append("		FOR MYCATSEQ_GLOBAL, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			?, ");
		sql.append("			2 ");
		sql.append("	) ");
		
		int record = Db.update(sql.toString(), param.toArray());
		return record;
	}

	@Override
	public int deleteById(Long[] id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("	classinfo ");
		sql.append("WHERE ");
		sql.append("	id IN ( ");
		for (int i = 0; i < id.length; i++) {
			sql.append("?");
			if (i != id.length - 1) {
				sql.append(",");
			}
		}
		sql.append(") ");
		int i = Db.update(sql.toString(), id);
		return i;
	}

	@Override
	public List<Record> dropDownOrganizationAboutAdd(HashMap map) {
		ArrayList param = new ArrayList();
		
		Integer year = (Integer) map.get("year");
		Long professionId = (Long) map.get("professionId");
		
		param.add(year);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	o.id, ");
		sql.append("	o.abbreviation ");
		sql.append("FROM ");
		sql.append("	organization_join oj ");
		sql.append("LEFT JOIN organization o ON oj.organization_id = o.id ");
		sql.append("LEFT JOIN organization_profession op ON oj.id = op.organization_join_id ");
		sql.append("WHERE ");
		sql.append("	oj.block = 0 AND oj.`year` = ? ");
		if(professionId !=null){
			sql.append("AND op.profession_id = ? ");
			param.add(professionId);
		}
		sql.append("GROUP BY o.id ");
		
		List<Record> list = Db.find(sql.toString(),param.toArray());
		
		for (Record record : list) {
			record.set("id", record.getLong("id").toString());
		}
		
		return list;
	}

	@Override
	public List<Record> dropDownProfessionAboutAdd(HashMap map) {
		ArrayList param = new ArrayList();
		
		Integer year = (Integer) map.get("year");
		Long organizationId = (Long) map.get("organizationId");
		
		param.add(year);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	p.id, ");
		sql.append("	p.`name` ");
		sql.append("FROM ");
		sql.append("	organization_join oj ");
		sql.append("LEFT JOIN organization_profession op ON oj.id = op.organization_join_id ");
		sql.append("LEFT JOIN profession p ON op.profession_id = p.id ");
		sql.append("WHERE ");
		sql.append("	oj.block = 0 AND oj.`year` = ? ");
		if(organizationId !=null){
			sql.append("AND oj.organization_id = ? ");
			param.add(organizationId);
		}
		sql.append("GROUP BY p.id ");
		
		List<Record> list = Db.find(sql.toString(),param.toArray());
		for (Record record : list) {
			if(record.get("id") != null && record.get("id") !=""){
				record.set("id", record.getLong("id").toString());
				
			}
			else{
				list = null;
			}
		}
		return list;
		
		
	}

	public int modifyClassinfo(HashMap map) {
		ArrayList param = new ArrayList();
		String classinfo_name = (String) map.get("name");
		Long organizationId = (Long) map.get("organizationId");
		Long professionId = (Long) map.get("professionId");
		Integer year = (Integer)map.get("year");
		Long id = (Long) map.get("id");
		Long stateId = (Long) map.get("stateId");
		//向数组添加数据
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE classinfo c ");
		sql.append("SET ");
		if(classinfo_name != null && classinfo_name.length() > 0){
			sql.append(" c.`name` = ? ");
			param.add(classinfo_name);
		}
		if(year != null){
			sql.append(" ,c.`year` = ? ");
			param.add(year);
		}
		if(stateId != null){
			sql.append(" c.state_id = ? ");
			param.add(stateId);
		}
		if(organizationId != null){
			sql.append(" ,c.organization_id = ? ");
			param.add(organizationId);
		}
		if(professionId != null){
			sql.append(" ,c.profession_id = ? ");
			param.add(professionId);
		}
		sql.append("WHERE");
		sql.append("	c.id = ?; ");
		param.add(id);
		
		int record = Db.update(sql.toString(), param.toArray());
		return record;
	}
	
	public Record searchClassinfoName(HashMap map){
		ArrayList param = new ArrayList<>();
		Long classinfoId = (Long) map.get("classinfoId");
		Long organizationId = (Long) map.get("organizationId");
		param.add(classinfoId);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) AS count, ");
		sql.append("	c.`name` AS classinfo, ");
		sql.append("	cs.`name` AS state, ");
		sql.append("	o.`name` AS organization ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("JOIN class_state cs ON c.state_id = cs.id ");
		sql.append("JOIN organization o ON c.organization_id = o.id ");
		sql.append("WHERE ");
		sql.append("	c.id = ? ");
		if(organizationId != null){
			sql.append("	AND o.id = ? ");
			param.add(organizationId);
		}
		
		Record record = Db.findFirst(sql.toString(), param.toArray());
		
		return record;
		
	}

	@Override
	public boolean classinfoNameIfExist(String name) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	count(DISTINCT c.`name`) ");
		sql.append("FROM ");
		sql.append("	classinfo c ");
		sql.append("WHERE ");
		sql.append("	c.`name` = ? ");
		long count = Db.queryLong(sql.toString(),name);
		if(count==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public Record queryClassCountByProfession(HashMap map) {
		ArrayList param = new ArrayList<>();
		Long professionId = (Long) map.get("professionId");
		Long organizationId = (Long) map.get("organizationId");
		
		param.add(professionId);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	COUNT(id) AS count ");
		sql.append("FROM ");
		sql.append("	classinfo ");
		sql.append("WHERE ");
		sql.append("	profession_id = ? ");
		if(organizationId != null){
			sql.append("AND organization_id = ? ");
			param.add(organizationId);
		}
		
		Record record = Db.findFirst(sql.toString(), param.toArray());
		
		return record;
		
		
	}

}
